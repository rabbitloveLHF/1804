package com.jt.sys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class PageController {
	
	  /**访问此方法时返回starter页面*/
	  @RequestMapping("doIndexUI")
	  public String doIndexUI(){
		  return "starter";//html
	  }
	  /**访问此方法时返回分页页面*/
	  @RequestMapping("doPageUI")
	  public String doPageUI(){
		  return "common/page";
	  }
}
