package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao {
	/**
	 * 基于角色id查询角色对象的菜单信息.
	 * 一个用户对应的角色id可能有多个
	 * @param roleIds
	 * @return
	 */
	List<Integer> findMenuIdsByRoleId(
			@Param("roleIds") Integer... roleIds);
	
	/**
	 * 将角色与菜单的关系数据写入到数据库
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObject(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	
	/**
	 * 基于菜单id删除角色菜单中间表的数据
	 * @param menuId
	 * @return
	 */
	int deleteObjectsByMenuId(Integer menuId);
	/**
	 * 基于角色id删除角色菜单中间表的数据
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	
	/**
	 * 思考:如上两个delete是否可以写成如下一个方法
	 * 
	 * @param column
	 * @param id
	 * @return
	 * <delete id="deleteObjectsById">
	 *   delete from sys_role_menus
	 *   where ${column}=#{id}
	 * </delete>
	 */
	
	//int deleteObjectsById(String column,Integer id);
	
}
