package com.stroe.spring;

import org.springframework.context.ApplicationContext;
/**
 * 手动Srping管理
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月26日 上午9:55:59
 */
public class SpringBeanManger {

private static ApplicationContext context;
	

	public static void initContext(ApplicationContext ctx){
		context=ctx;
	}
	
	public static Object getBean(String name){
		return context.getBean(name);
	}

	public static ApplicationContext getContext() {
		return context;
	}
	
	public static <T> T getBean(String name, Class<T> cls) {
		return context.getBean(name, cls);
	}
}
