package com.jt.common.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//spring-configs.xml
@ComponentScan(value="com.lol"
,includeFilters={@Filter(type=FilterType.ANNOTATION,classes={Controller.class,ControllerAdvice.class})}
,useDefaultFilters=false)//配置包扫描
@EnableWebMvc //启动mvc默认配置
public class AppServletConfig extends 
    WebMvcConfigurerAdapter{
	/**配置视图解析器*/
	@Override
	public void configureViewResolvers(
		ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/pages/",".html");
	}
}
