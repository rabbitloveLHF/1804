package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDao {
	
	
	
	/**
	 * 基于用户id查找角色id
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(
			Integer userId);
	
	/**
	 * 根据角色id删除关系数据
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	
	/**
	 * 根据用户id删除关系数据
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByUserId(Integer userId);
	
	/**
	 * 保存用户和角色的关系数据
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int insertObject(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[]roleIds);
   
}
