package com.jt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;
import com.jt.sys.vo.SysUserDeptResult;
@Service
public class SysUserServiceImpl	 implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		//1.验证参数有效性
		if(id==null||id<1)
		throw new IllegalArgumentException("参数值无效");
		//2.查询用户自身信息
		SysUserDeptResult sysUser=sysUserDao.findObjectById(id);
		//3.查询用户对应的角色信息
		List<Integer> roleIds=
		sysUserRoleDao.findRoleIdsByUserId(id);
		//4.封装数据
		Map<String,Object> map=
		new HashMap<String,Object>();
		map.put("user",sysUser);
		map.put("roleIds", roleIds);
		//5.返回结果
		return map;
	}
	@Transactional()//启用事务,底层基于AOP实现-->声明式修改
	@Override
	public int saveObject(SysUser entity, 
			Integer[] roleIds) {
		//1.验证参数有效性
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
		throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
		throw new IllegalArgumentException("密码不能为空");
		if(roleIds==null||roleIds.length==0)
		throw new ServiceException("必须为用户分配一个角色");
		//2.对密码进行加密
		String salt=UUID.randomUUID().toString();
		//此API由Shiro框架提供
		SimpleHash sHash=new SimpleHash(
			    "MD5",//algorithmName (不可逆加密算法)
				entity.getPassword(), //source
				salt);//salt
//		new SimpleHash(algorithmName, source, salt, 设置加密次数);
		entity.setPassword(sHash.toHex());
		entity.setSalt(salt);
		//3.保存用户自身信息
		int rows=sysUserDao.insertObject(entity);
		//4.保存用户角色关系数据
		sysUserRoleDao.insertObject(
				entity.getId(),
				roleIds);
		
		if(rows==1)throw new ServiceException();
		//5.返回结果
		return rows;
	}
	@Override
	public int updateObject(SysUser entity, 
			Integer[] roleIds) {
		//1.验证参数有效性
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(roleIds==null||roleIds.length==0)
			throw new ServiceException("必须为用户分配一个角色");
		if(!StringUtils.isEmpty(entity.getPassword())){
		//2.对密码进行加密
		String salt=UUID.randomUUID().toString();
		//此API由Shiro框架提供
		SimpleHash sHash=new SimpleHash(
				"MD5",//algorithmName (不可逆加密算法)
				entity.getPassword(), //source
				salt);//salt
		entity.setPassword(sHash.toHex());
		entity.setSalt(salt);
		}
		//3.保存用户自身信息
		int rows=sysUserDao.updateObject(entity);
		//4.删除用户和角色关系数据
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		//5.保存用户角色关系数据
		sysUserRoleDao.insertObject(
				entity.getId(),
				roleIds);
		//5.返回结果
		return rows;
	}
	/**
	 * 通过该注解
	 * 进行标识访问此方法需要什么权限
	 * 当执行此方法时,底层系统通过反射获得该方法的注解
	 * 若有注解,就获取注解中的内容
	 * 并通过subject.isPermitted("sys:user:valid")
	 * 对用户是否允许访问此方法进行权限检测
	 * @RequiresPermissions(values={"sys:user:valid"})
	 */
	@RequiresPermissions("sys:user:valid")
	@Override
	public int validById(
			Integer id,
			Integer valid, 
			String modifiedUser) {
		System.out.println("++++++++++++"+id);
		//1.参数有效性验证
		if(id==null||id<1)
		throw new IllegalArgumentException("id值无效");
		if(valid==null||(valid!=0&&valid!=1))
		throw new IllegalArgumentException("状态值无效");
		if(StringUtils.isEmpty(modifiedUser))
		throw new ServiceException("修改用户不能为空");
		//2.执行禁用启用操作
		int rows=
		sysUserDao.validById(id, valid, modifiedUser);
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		//3.验证并返回结果
		return rows;
	}
	
	@Override
	public PageObject<SysUserDeptResult> 
	   findPageObjects(String username, 
			Integer pageCurrent) {
		//1.验证参数有效性
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("参数值无效");
		//2.依据提交统计记录数,并验证结果
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
		throw new ServiceException("查询无结果");
		//3.依据条件查询当前页记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDeptResult> records=
		sysUserDao.findPageObjects(username,
				startIndex, pageSize);
		//4.封装查询结果
		PageObject<SysUserDeptResult> pageObject=
		new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRecords(records);
		//5.返回结果
		return pageObject;
	}

}
