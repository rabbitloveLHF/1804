package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
	private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    
    @Override
    public int updateObject(SysMenu entity) {
    	//1.验证对象是否为空.
    	if(entity==null)
    	throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getName().trim()))
    	throw new IllegalArgumentException("菜单名不能为空");
    	//2.将对象保存到数据库
    	int rows=sysMenuDao.updateObject(entity);
    	//3.返回结果
    	return rows;
    }
    @Override
    public int saveObject(SysMenu entity) {
    	//1.验证对象是否为空.
    	if(entity==null)
    		throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getName().trim()))
    		throw new IllegalArgumentException("菜单名不能为空");
    	//2.将对象保存到数据库
    	int rows=sysMenuDao.insertObject(entity);
    	//3.返回结果
    	return rows;
    }
    @Override
    public List<Node> findZtreeMenuNodes() {
      return sysMenuDao.findZtreeMenuNodes();
    }
    
    @Override
    public int deleteObject(Integer id) {
    	//1.id合法校验
    	if(id==null||id<1)
    	throw new IllegalArgumentException("id的值不正确");
    	//2.基于id统计子元素,并进行判定
    	int count=sysMenuDao.getChildCount(id);
    	if(count>0)
    	throw new ServiceException("请先删除子元素");
    	//3.删除当前元素
    	int rows=sysMenuDao.deleteObject(id);
    	//4.删除关系表数据
    	sysRoleMenuDao.deleteObjectsByMenuId(id);
    	//5.返回数据
    	return rows;
    }
    
    
	@Override
	public List<Map<String, Object>> 
	        findObjects() {
		return sysMenuDao.findObjects();
	}
}
