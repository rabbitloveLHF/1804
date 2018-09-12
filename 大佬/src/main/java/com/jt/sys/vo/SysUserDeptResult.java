package com.jt.sys.vo;
import java.io.Serializable;
import java.util.Date;

import com.jt.sys.entity.SysDept;
/**
 * 通过此对象封装用户以及与用户对应的部门信息
 * @author we are young
 */
public class SysUserDeptResult implements Serializable{
	private static final long serialVersionUID = 5657948690505840292L;
	/**用户id*/
	private Integer id;
	/**用户名*/
	private String username;
	/**密码(数据库存储的密码必须是加密过的)*/
	private String password;
	/**盐值(对密码进行加密时使用)*/
	private String salt;
	private String email;
	private String mobile;
	/**用户状态(启用-1和禁用-0)*/
	private Integer valid=1;
	/**部门信息*/
	private SysDept sysDept;//数据库表中没有对应字段
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}
	public SysDept getSysDept() {
		return sysDept;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
    
}
