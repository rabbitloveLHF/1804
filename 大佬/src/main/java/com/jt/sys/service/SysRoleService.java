package com.jt.sys.service;
import java.util.List;
import java.util.Map;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysRole;

public interface SysRoleService {
	
	 /**
	  * 查询角色id和名字信息
	  * @return
	  */
	 List<CheckBox> findObjects();
	 
	 
	 int updateObject(SysRole entity,Integer[] menuIds);
	
	 /**
	  * 基于角色Id查询角色以及对应的菜单信息
	  * @param id
	  * @return
	  */
	 Map<String,Object> findObjectById(Integer id);
	
	 /**
	  * 保存角色信息以及角色和菜单的关系数据
	  * @param entity
	  * @param menuIds
	  * @return
	  */
	 int saveObject(SysRole entity,
			 Integer[]menuIds);
	 /**
	  * 删除角色信息以及角色对应的关系数据
	  * @param id
	  * @return
	  */
	 int deleteObject(Integer id);
	
	 PageObject<SysRole> findPageObjects(
	 String name,Integer pageCurrent);
	 
}
