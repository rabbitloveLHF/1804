package com.jt.common.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**思考:
1)此类何时被加载? tomcat 启动时
2)加载的流程是怎样的?
2.1) tomcat 启动时会读取类路径下的如下文件
META-INF/services/javax.servlet.ServletContainerInitializer
2.2) 获取文件中定义的具体类,并且加载此类
2.3) 然后加载修饰此类的@HandlesTypes注解中定义的类型的子类类型.
*/
//web.xml
public class AppWebInitializer extends 
AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("onStartup");
		// super.onStartup(servletContext);
		registerContextLoaderListener(servletContext);
		registerShiroFilter(servletContext);
		registerDispatcherServlet(servletContext);
	}

	private void registerShiroFilter(ServletContext servletContext) {
		// 注册Filter对象
		// 什么时候需要采用此方式进行注册?
		// 项目没有web.xml并且此filter不是自己写的
		FilterRegistration.Dynamic dy = servletContext.addFilter(
				"filterProxy", 
				DelegatingFilterProxy.class);//过滤器名字随意写
		dy.setInitParameter("targetBeanName",
				"shiroFilterFactoryBean");// 这个名字和之前的shiro工厂Bean别名一致
		// 定义过滤路径url
		dy.addMappingForUrlPatterns(null, // EnumSet<DispatcherType> 由底层自己去适配
				false, "/*");// url-pattern	/*代表所有路径,所以false代表不用匹配
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("getRootConfigClasses()");
		return new Class[]{AppRootConfig.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("getServletConfigClasses()");
		return new Class[]{AppServletConfig.class};
	}
	@Override
	protected String[] getServletMappings() {
		System.out.println("getServletMappings()");
		return new String[]{"*.do"};
	}
}


