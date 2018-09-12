package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;

@RequestMapping("/user/")
@Controller
public class SysUserController {
	   @Autowired
	   private SysUserService sysUserService;
	   @RequestMapping("doUserListUI")
	   public String doUserListUI(){
		   return "sys/user_list";
	   }
	   
	   @RequestMapping("doUserEditUI")
	   public String doUserEditUI(){
		   return "sys/user_edit";
	   }
	   
	   @RequestMapping("doFindObjectById")
	   @ResponseBody
	   public JsonResult doFindObjectById(
			   Integer id){
		   return new JsonResult(
		   sysUserService.findObjectById(id));
	   }
	   
	   /**
	    * 保存用户信息以及用户与角色的关系数据
	    * @param entity
	    * @param roleIds
	    * @return
	    */
	   @RequestMapping("doSaveObject")
	   @ResponseBody
	   public JsonResult doSaveObject(
			   SysUser entity,
			   Integer[] roleIds){
		       sysUserService.saveObject(entity,
				   roleIds);
		   return new JsonResult("save ok");
	   }
	   /**
	    * 更新用户自身信息以及用户与角色的关系数据
	    * @param entity
	    * @param roleIds
	    * @return
	    */
	   @RequestMapping("doUpdateObject")
	   @ResponseBody
	   public JsonResult doUpdateObject(
			   SysUser entity,
			   Integer[] roleIds){
		   sysUserService.updateObject(entity,roleIds);
		   return new JsonResult("update ok");
	   }
	   /**
	    * 禁用和启用用户对象信息
	    * @param id
	    * @param valid
	    * @return
	    */
	   
	   @RequestMapping("doValidById")
	   @ResponseBody
	   public JsonResult doValidById(
			   Integer id,
			   Integer valid){
		   
		    sysUserService.validById(id,valid, "admin");//admin暂时为假数据
		    System.out.println("+++++++++++++++++++++++++++++++++++++++++"+id);
		   return new JsonResult(valid==1?"启用OK":"禁用OK");
	   }
	   
	   @RequestMapping("doFindPageObjects")
	   @ResponseBody
	   public JsonResult doFindPageObjects(
			   String username,
			   Integer pageCurrent){
		   return new JsonResult(
			sysUserService.findPageObjects(username, pageCurrent));
	   }                                            
}


