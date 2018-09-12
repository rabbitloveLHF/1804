package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.CheckBox;
import com.jt.sys.entity.SysRole;
import com.jt.sys.entity.SysUser;

public interface SysRoleDao {
	
	
	/**
	 * 查询角色信息
	 * @return
	 */
	List<CheckBox> findObjects();
	
	
	/**
	 * 更新角色自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);
	
	/**
	 * 基于角色ID查询角色自身信息
	 * @return
	 */
	SysRole findObjectById(Integer id);
	
	/**
	 * 将角色自身信息写到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity);
	
	/**基于id删除角色自身信息*/
	int deleteObject(Integer id);
	
	 /**
     * 分页查询角色信息
     * @param startIndex 上一页的结束位置
     * @param pageSize 每页要查询的记录数
     * @return
     */
	List<SysRole> findPageObjects(
            @Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 查询记录总数
	 * @return
	 */
	int getRowCount(@Param("name")String name);

}
