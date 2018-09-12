package com.jt.common.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jt.common.vo.JsonResult;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
    public JsonResult doHandleRuntimeException(
			Exception e){
		e.printStackTrace();
		JsonResult result = new JsonResult();
		result.setData(0);
		if(e instanceof IncorrectCredentialsException){
			result.setMessage("密码不正确");
			return result;
		}else if(e instanceof AuthorizationException){
			result.setMessage("没有权限");
			return result;
		}
    	return result;
	}
}
