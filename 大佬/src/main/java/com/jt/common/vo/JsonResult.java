package com.jt.common.vo;
import java.io.Serializable;
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 583312263212167841L;
	private int state=1;//SUCCESS
	private String message="ok";
	private Object data;
	public JsonResult() {
	}
	public JsonResult(String message) {//delete ok,insert ok,delete ok
		this.message=message;
	}
	public JsonResult(Object data) {
		this.data=data;
	}
	public JsonResult(Throwable e) {
		this.state=0;//ERROR
		this.message=e.getMessage();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
