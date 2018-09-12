package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuDao {
	/**
	 * 基于菜单id找到对应的权限标识
	 * 例如sys:user:update
	 * @param menuIds
	 * @return
	 */
	List<String> findPermissions(
			@Param("menuIds") Integer... menuIds);

	
	int updateObject(SysMenu entity);
	/**
	 * 插入菜单信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysMenu entity);
	
	/**
	 * 获取所有菜单的节点信息
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	/**
	 * 基于菜单id统计子元素个数
	 * @param id
	 * @return
	 */
	int getChildCount(Integer id);
	
	/**
	 * 基于菜单id执行菜单对象的删除操作
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	/***
	 * 查询所有菜单以及它的上一级菜单信息.
	 * @return
	 */
	List<Map<String,Object>> findObjects();
}
