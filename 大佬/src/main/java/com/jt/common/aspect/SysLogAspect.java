package com.jt.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import com.jt.common.anno.RequestLog;
import com.jt.common.util.IPUtils;
import com.jt.sys.entity.SysLog;
import com.jt.sys.entity.SysUser;
/**
 * 定义一个日志切面(横切面)
 * 使用切面还要启用AOP
 * @Aspect 修饰的类为一个切面对象
 * @author Administrator
 */
@Aspect
@Service//扩展业务
//@Component也可以
public class SysLogAspect {
	@Pointcut("bean(sysConfigServiceImpl)")//若用@#annotion注解指定有注解的方法才生产日志,就不会出现注解获取值为空的现象
	public void logPointCut(){}
	/***
	 * bean表达式在对象上添加一个扩展功能:叫通知
	 * 环绕通知:方法执行前后都执行的通知
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
//	@Around("bean(sysCongfigServiceImpl)")//该类下的方法组成连接点
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point )throws Throwable{//连接点对象
		//1.目标方法开始执行时间
		long startTime = System.nanoTime();
		//2.执行目标方法
		Object result = point.proceed();//底层是通过代理对象调用目标对象的方法
		/**
		 * try-catch把这个东西包起来,在catch中捕捉异常信息,封装给SysLog
		 */
		//3.目标方法结束执行的时间
		long endTime = System.nanoTime();
		long totleTime = endTime-startTime;
		saveSysLog(point, totleTime);
		return result;
	}
	
	private void saveSysLog(ProceedingJoinPoint point, long totleTime) throws NoSuchMethodException {
		//获取目标对象
		Class<? extends Object> targetClass = point.getTarget().getClass();
		System.out.println(targetClass);
		
		//获取目标方法对象
		MethodSignature ms = (MethodSignature)point.getSignature();
		Method targetMethod = targetClass.getDeclaredMethod(
				ms.getName(), 
				ms.getParameterTypes());
		System.out.println("方法名:"+ms.getName());
		System.out.println(targetMethod);
		//获取目标方法上的注解
		RequestLog anno = targetMethod.getDeclaredAnnotation(RequestLog.class);
		String str = anno.value();
		System.out.println("注解内容:"+str);
		//获取请求参数
		Object[] args = point.getArgs();
		System.out.println("请求参数:"+Arrays.toString(args));
		
		//获取ip地址
		String ip = IPUtils.getIpAddr();
		System.out.println("ip="+ip);
		System.out.println();
		System.out.println("总时长:"+totleTime);
		//构建日志对象
		SysLog sysLog = new SysLog();
		SysUser user =(SysUser) SecurityUtils.getSubject().getPrincipal();
		sysLog.setUsername(user.getUsername());
		//此处日志这是只保存了操作成功的信息,没有异常日志
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
