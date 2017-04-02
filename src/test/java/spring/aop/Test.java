package spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("spring-context.xml");
		UserServices user=(UserServices) app.getBean("userServices");
		user.add();
	}

}
