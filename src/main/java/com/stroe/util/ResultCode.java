package com.stroe.util;
/**
 * ajax工具类
 * @author zengjintao
 * 2017年3月6日 21:59
 */
public class ResultCode {

    private int code;
	
    private String message="";
	
	public static final int SUCCESS=1;
	
	public static final int FAIL=0;
	
	public ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
