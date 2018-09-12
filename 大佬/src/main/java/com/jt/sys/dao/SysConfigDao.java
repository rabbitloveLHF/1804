package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysConfig;

public interface SysConfigDao {
	
	  int deleteObjects(
	   @Param("ids")Integer... ids);
	
      /**
       * 依据条件查询记录总数
       * @param name
       * @return
       */
	  public int getRowCount(
			  @Param("name")String name);
	  
	  /**
	   * 依据条件查询当前页记录
	   * @param name  参数名
	   * @param startIndex 起始位置
	   * @param pageSize  页面大小
	   * @return
	   */
	  public List<SysConfig> findPageObjects(
			  @Param("name")String name,
			  @Param("startIndex")Integer startIndex,
			  @Param("pageSize")Integer pageSize);
	  
}
