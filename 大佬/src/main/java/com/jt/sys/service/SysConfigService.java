package com.jt.sys.service;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysConfig;

public interface SysConfigService {
    
	 int deleteObjects(Integer... ids);
	
	 PageObject<SysConfig> findPageObjects(
			 String name,
			 Integer pageCurrent);
}
