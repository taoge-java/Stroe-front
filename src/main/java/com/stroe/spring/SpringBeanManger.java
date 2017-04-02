package com.stroe.spring;

import org.springframework.context.ApplicationContext;
/**
 * 手动管理spring
 * @author ADMIN
 *
 */
public class SpringBeanManger {

	private static ApplicationContext context;
	
	public static void initSpring(ApplicationContext ctx){
		context=ctx;
	}
	
	public Object getBean(String name){
		return context.getBean(name);
	}
	
	public static <T> T getBean(String  name,Class<T> cls){
		return context.getBean(name, cls);
	}
}
