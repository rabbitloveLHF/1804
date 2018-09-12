package com.jt.common.config;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
@PropertySource("classpath:configs.properties")//Spring整合的资源底层shiro也有properties文件,为了让系统支持多个properties需要配置
@ComponentScan(value="com.jt",
excludeFilters={@Filter(type=FilterType.ANNOTATION,classes={Controller.class,ControllerAdvice.class})})
@MapperScan(basePackages="com.jt.**.dao")
@EnableAspectJAutoProxy//启用AOP
@EnableTransactionManagement//启用事务管理[Spring Boot是用全注解]
public class AppRootConfig {
	
	 /** 让系统支持多个properties文件应用 @return*/
	 @Bean
	 public PropertySourcesPlaceholderConfigurer newPropertyPlaceholderConfigurer(){
		 return new PropertySourcesPlaceholderConfigurer();
	 }
	
	@Bean(value="dataSource",
		  initMethod="init",
		  destroyMethod="close")  //==<bean id="" ...>
	public DruidDataSource newDruidDataSource(
		 @Value("${jdbcDriver}")String driver,
		 @Value("${jdbcUrl}")String url,
		 @Value("${jdbcUser}")String user,
		 @Value("${jdbcPassword}")String password){
		DruidDataSource ds=
		new DruidDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		return ds;
	}
	//WWWW+H
	@Bean("sqlSessionFactory")
	public SqlSessionFactoryBean 
	       newSqlSessionFactoryBean(
	       @Autowired DataSource dataSource) throws IOException{
		SqlSessionFactoryBean fBean=
		new SqlSessionFactoryBean();
		//关联数据源对象
		fBean.setDataSource(dataSource);
		//关联mapper映射文件
		Resource[] mapperLocations=
		new PathMatchingResourcePatternResolver()
		.getResources("classpath*:mapper/sys/*Mapper.xml");
		fBean.setMapperLocations(mapperLocations);
		
		return fBean;
	}
/*	@Bean //默认id为方法名
	public MapperScannerConfigurer newMapperScannerConfigurer(){
		MapperScannerConfigurer msCfg=
		new MapperScannerConfigurer();
		msCfg.setBasePackage("com.jt.**.dao");
		msCfg.setSqlSessionFactoryBeanName(
				"sqlSessionFactory");
		return msCfg;
	}*/
	@Bean("cacheManager")//默认是方法名
	public EhCacheManager newEhCacheManager(){
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return cacheManager;
	}
	
	/**
	 * SecurityManager:shiro核心
	 * @param userRealm
	 * @return
	 */
	@Bean("securityManager")
	public DefaultWebSecurityManager neweDefaultWebSecurityManager(
			AuthorizingRealm userRealm,CacheManager cacheManager){//默认会按类型注入
		//构建securityManager对象
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		//设置关联的realm对象(暂时可以将其理解为service对象)
		sManager.setRealm(userRealm);//realm对象
		//设置缓存管理器(扩展点)
		sManager.setCacheManager(cacheManager);
		return sManager;
	}
	
	@Bean("shiroFilterFactoryBean")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(
			SecurityManager securityManager){//shiro 包
			ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
			bean.setSecurityManager(securityManager);
		    //当此用户是一个非认证用户,需要先登陆进行认证
			bean.setLoginUrl("/doLoginUI.do");//设置登录页面
			LinkedHashMap<String,String> fcMap=
					new LinkedHashMap<>();
			fcMap.put("/bower_components/**","anon");//anon表示允许匿名访问
			fcMap.put("/build/**", "anon");
			fcMap.put("/dist/**","anon");
			fcMap.put("/plugins/**","anon");
			fcMap.put("/doLogin.do","anon");
			fcMap.put("/doLogout.do ","logout");//退出操作会自动把用户信息清除
			fcMap.put("/**", "authc");//除以上的资源必须授权才能访问
			bean.setFilterChainDefinitionMap(fcMap);//配置过滤链
			return bean;
	}
	/**配置shiro组件对象的生命周期管理*/
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor(){
			return new LifecycleBeanPostProcessor();
	}
	
	/**当执行授权操作时需要如下两个配置*/
	@DependsOn(value="lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator(){
			return new DefaultAdvisorAutoProxyCreator();
	}
	@Bean
	public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor(
				SecurityManager securityManager){
			    AuthorizationAttributeSourceAdvisor bean=
				new AuthorizationAttributeSourceAdvisor();
			    bean.setSecurityManager(securityManager);
			return bean;
	}
	//====================================================================
	/**定义事务管理Bean对象*/
	@Bean("txManaer")
	public DataSourceTransactionManager newDataSourceTransactionManager(DataSource dataSource){
		DataSourceTransactionManager tManager = new DataSourceTransactionManager();
		tManager.setDataSource(dataSource);
		return tManager;
	}
	
}
