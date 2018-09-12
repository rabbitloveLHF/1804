package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysUser;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserDao {
	/**
	 * 基于用户名查找用户信息
	 * @param username
	 * @return
	 */
	SysUser findUserByUserName(String username);

	int updateObject(SysUser entity);

	/**
	 * 基于id查找用户自身信息
	 * 
	 * @param id
	 * @return
	 */
	SysUserDeptResult findObjectById(Integer id);

	/**
	 * 保存用户自身信息
	 * 
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);

	/**
	 * 禁用启用用户信息
	 * 
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	int validById(
			@Param("id") Integer id, 
			@Param("valid") Integer valid, 
			@Param("modifiedUser") String modifiedUser);

	/**
	 * 查询当前页要显示的数据
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptResult> findPageObjects(@Param("username") String username, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	/**
	 * 依据条件统计总记录数
	 * 
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username") String username);

}
