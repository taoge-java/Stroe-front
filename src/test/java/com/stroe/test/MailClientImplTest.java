package com.stroe.test;


import org.junit.Test;

import com.stroe.dto.Mail;
import com.stroe.mail.MailClient;
import com.stroe.mail.MailClientImpl;

public class MailClientImplTest {

	@SuppressWarnings("unused")
	@Test
	public void testSendMsg() {
		Mail mail=new Mail("18296640717@163.com","CHINA5232786", "18296640717@163.com", "smtp.163.com", 25, true);
		MailClient mailclent= new MailClientImpl(mail);
		boolean result=mailclent.senMsg("天下淘网络商城密码找回邮件", "<p><h3>请点击下面链接进行重设登录密码</h3></p><a href='https://www.baidu.com'>请点击这里</a>", "1913188966@qq.com");
		System.out.println("发送成功");
	}

}
