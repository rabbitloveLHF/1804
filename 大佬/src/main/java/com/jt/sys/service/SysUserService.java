package com.jt.sys.service;

import java.util.Map;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysUser;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserService {
	 /**
	  * 修改用户自身信息以及用户和角色
	  * 的关系数据
	  * @param entity
	  * @param roleIds
	  * @return
	  */
	 int updateObject(SysUser entity,
			 Integer[] roleIds);
	
	 /**
	  * 基于用户id查找用户自身信息以及
	  * 相关联的角色ID信息
	  * @param id 用户id
	  * @return
	  */
	 Map<String,Object> findObjectById(
			 Integer id);
	
	 /**
	  * 保存用户自身信息以及与角色的关系数据
	  * @param entity
	  * @param roleIds
	  * @return
	  */
	 int saveObject(SysUser entity,
			 Integer[] roleIds);
	 /**
	  * 基于用户id,禁用启用实现
	  * @param id
	  * @param valid
	  * @param modifiedUser
	  * @return
	  */
	 
	 int validById(
			 Integer id,
			 Integer valid,
			 String modifiedUser);
	
	 PageObject<SysUserDeptResult> findPageObjects(
			 String username,
			 Integer pageCurrent);
}
