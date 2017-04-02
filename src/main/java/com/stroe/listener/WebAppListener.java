package com.stroe.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jfinal.log.Logger;
import com.stroe.spring.SpringBeanManger;


public class WebAppListener implements ServletContextListener{

	private Logger log=Logger.getLogger(getClass());
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext app=new  ClassPathXmlApplicationContext("spring-context.xml");
		SpringBeanManger.initSpring(app);
		log.info("spring-context.xml加载完成.......");
	}

}
