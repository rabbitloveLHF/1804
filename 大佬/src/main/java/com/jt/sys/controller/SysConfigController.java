package com.jt.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysConfig;
import com.jt.sys.service.SysConfigService;


@Controller
@RequestMapping("/config/")
public class SysConfigController {
	  @Autowired
	  private SysConfigService sysConfigService;
	  
	  @RequestMapping("doConfigListUI")
	  public String doConfigListUI(){
		  return "sys/config_list";
	  }
	  @RequestMapping("doDeleteObjects")
	  @ResponseBody
	  public JsonResult doDeleteObjects(
			  Integer... ids){
		  int rows=sysConfigService.deleteObjects(ids);
		  return new JsonResult("删除OK,共计"+rows+"行");
	  }
	  @RequestMapping("doFindPageObjects")
	  @ResponseBody
	  public JsonResult doFindPageObjects(
			  String name,Integer pageCurrent){
		  System.out.println("name="+name);
		  PageObject<SysConfig> data=
		  sysConfigService.findPageObjects(name,
				  pageCurrent);
		  return new JsonResult(data);
	  }
}








