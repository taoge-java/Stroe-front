package com.aop.annation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Log {

	@Before("execution(* com.aop.annation.*.*(..))")
	public void before(){
		System.out.println("方法执行前");
	}
	
	@After("execution(* com.aop.annation.*.*(..))")
	public void after(){
		System.out.println("方法执行后");
	}
}
