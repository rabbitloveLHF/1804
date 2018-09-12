package com.jt.sys.entity;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
/**
 * 1.何为对象序列化?
 * 2.何为对象反序列化?
 * 3.对象序列化的应用场景?
 * 1)RPC(跨进程调用)
 * 2)缓存对象(例如mybatis二级缓存)
 * 3)....
 * 4.如何实现对象的序列化?
 * 1)将类直接或间接的实现Serializable接口并添加序列化id。
 * 2)通过流对象ObjectOutputStream将对象序列化。
 * 
 * 5.对象序列化时类中定义的序列化的ID有何作用？
 * 更好的应用于对象的反序列化(当一个已序列化的
 * 对象状态发生变化，仍旧可以反序列化)。
 * 
 * 6.对象序列化时如何对内容进行加密？
 * 
 * 在对象对应的类中添加如下方法，在方法内部进行加密
 * private void writeObject(
	    ObjectOutputStream out) 
	throws IOException{}
 * 
 * 7.对象反序列化时如何对内容进行解密？
 * 在对象对应的类中添加如下方法，在方法内部进行解密
   private void readObject(
			ObjectInputStream in) 
   throws ClassNotFoundException, IOException{
   }
  
 * 8.对象序列化的粒度如何控制？
 * 方案：使用transient关键字对属性进行修饰
 * 或让对象实现Externalizable接口
 * 1)一个对象100个属性，只有一个属性不序列化该如何做？
 *   让这个属性使用transient关键字进行修饰
 * 2)一个对象100个属性，只有一个属性要序列化该如何做？
         让对象实现Externalizable接口
 */
public class SysRole implements Serializable{
	private static final long serialVersionUID = -2113802202295967078L;
	private Integer id;
    private String name;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
