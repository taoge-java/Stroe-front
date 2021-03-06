package com.stroe.interceptor;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
/**
 * spring插件拦截器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月19日下午1:12:26
 */
public class IocInterceptor implements Interceptor{

   public static ApplicationContext ctx;

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Field[] fields = controller.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(Inject.BY_NAME.class))
				bean = ctx.getBean(field.getName());
			else if (field.isAnnotationPresent(Inject.BY_TYPE.class))
				bean = ctx.getBean(field.getType());
			else if(field.isAnnotationPresent(Autowired.class))
				bean = ctx.getBean(field.getName());
			else
				continue ;
			
			try {
				if (bean != null) {
					field.setAccessible(true);
					field.set(controller, bean);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		inv.invoke();
	}
}
