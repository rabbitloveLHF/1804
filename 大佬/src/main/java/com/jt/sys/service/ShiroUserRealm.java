package com.jt.sys.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysMenuDao sysMenuDao;

	/** 认证:完成用户认证信息的获取及封装 */
	/** 我们不比对密码,由认证器比对 */
	/**
	 * @param token
	 *            封装了待认证用户的身份及密码信息
	 */
	/**设置加密算法(默认也是MD5,好像可以不写-->自己试验)*/
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		cMatcher.setHashAlgorithmName("MD5");
//		cMatcher.setHashIterations(hashIterations);设置加密次数
		super.setCredentialsMatcher(cMatcher);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.获取用户身份信息(用户名)
		String username = ((UsernamePasswordToken) token).getUsername();

		// Object username = token.getPrincipal();
		// Object password = token.getCredentials();
		// 2.基于用户名查找数据库,获取用户信息
		SysUser user = sysUserDao.findUserByUserName(username);
		// 3.判定此用户是否存在
		if (user == null)
			throw new AuthenticationException("用户不存在");
		// 4.判定是否已经被禁用
		System.out.println(user.getValid());
		System.out.println(user.getId());
		if (user.getValid() == 0)
			throw new AuthenticationException("用户被禁用");
		// 5.封装用户信息并返回
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user, // principal,user/username都可以Object接收,用户名或用户对向象都可以-->主身份决定授权时取出的身份类型
				user.getPassword(), // hashedCredentials:数据库密码
				credentialsSalt, 
				getName());// realmName

		return info;// 返回值应传递给认证管理器
	}

	/** 授权:完成用户权限信息的获取及封装
	 * 1)查询权限信息方案1	(多表关联查询)
	 * select permission
	 * from sys_users u join sys_user_roles ur
	 * on u.id=ur.user_id join sys_role_menus	rm
	 * on ur.role_id=rm.role_id join sys_menus m
	 * rm.menu_id=m.id
	 * where u.id=#{id}
	 * 2)查询权限信息方案2 (多次单表查询)
	 * 2.1)基于用户id查找角色id
	 * 2.2)基于角色id查找菜单id
	 * 2.3)基于菜单id查找权限标识permission
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1.获取用户id
		SysUser user = (SysUser)principals.getPrimaryPrincipal();//获取主身份
		int userId = user.getId();
		
		//2.基于用户id查找角色id
		List<Integer> roleIdsList = sysUserRoleDao.findRoleIdsByUserId(userId);
		Integer[] roleIds = roleIdsList.toArray(new Integer[]{});
		if(roleIds==null||roleIds.length==0)
			throw new AuthorizationException();
		//3.基于角色id查找菜单id
		List<Integer> menuIdList = sysRoleMenuDao.findMenuIdsByRoleId(roleIds);
		if(menuIdList==null|| menuIdList.size()==0)
			throw new AuthorizationException();
		Integer[] menuIds={};
		menuIdList.toArray(menuIds);
		//4.基于菜单id查找权限标识
		List<String> permissionList = sysMenuDao.findPermissions(menuIds);
		//5.对数据封装并返回
		Set<String> permissionSet = new HashSet<>();
		for(String permission :permissionList){
			if(!StringUtils.isEmpty(permission)){
				permissionSet.add(permission);
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permissionSet);
		return info;//将权限信息返回给授权管理器
	}
	

}
