package com.jt.common.vo;
import java.io.Serializable;
/**
 * VO:节点对象(通常用于封装树结构中的节点信息)
 * @author we are young
 */
public class Node implements Serializable{
	private static final long serialVersionUID = 4351174414771192644L;
	private Integer id;
	private String name;
	private Integer parentId;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
