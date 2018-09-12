package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.anno.RequestLog;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysConfigDao;
import com.jt.sys.entity.SysConfig;
import com.jt.sys.service.SysConfigService;

@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
	private SysConfigDao sysConfigDao;
    
    @Override
    public int deleteObjects(Integer... ids) {
    	//1.参数有效性验证
    	if(ids==null||ids.length==0)
        throw new IllegalArgumentException("参数值为空");
    	//2.执行删除操作
    	int rows=0;
    	try{
    	rows=sysConfigDao.deleteObjects(ids);
    	}catch(Throwable e){
    	e.printStackTrace();
    	//给运维人员发短息,报警
        throw new ServiceException("系统故障,正在维护中");
    	}
    	//3.验证并返回结果
    	if(rows==0)
    	throw new ServiceException("此记录可能已经不存在");
    	return rows;
    }
    @RequestLog("配置管理分页查询")
	@Override
	public PageObject<SysConfig> findPageObjects(String name,
	        		  Integer pageCurrent) {
	    //1.参数有效性验证
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值无效");
		//2.基于条件查询总记录数
		int rowCount=sysConfigDao.getRowCount(name);
		//3.对总记录数进行校验.
		if(rowCount==0)
		throw new ServiceException("没有查到对应记录");
		//4.查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysConfig> list=
		sysConfigDao.findPageObjects(name,startIndex, pageSize);
		//5.对查询结果进行封装并返回.
		PageObject<SysConfig> po=new PageObject<>();
		po.setPageCurrent(pageCurrent);
		po.setPageSize(pageSize);
		po.setRowCount(rowCount);
		po.setRecords(list);
		return po;
	}
}
