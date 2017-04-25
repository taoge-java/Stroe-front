package com.stroe.dto;

import java.util.Date;

/**
 * 手机验证码
 * @author zengjintao
 * 2017年3月6日 21:29
 */
public class MobileCode {

	private Date dateTime;
	
	public MobileCode(String mobile, String code, Date dateTime) {
		this.dateTime=dateTime;
		this.mobile = mobile;
		this.code = code;
	}

	private String mobile;
	
	private String code;


	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
