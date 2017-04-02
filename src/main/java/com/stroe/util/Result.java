package com.stroe.util;

/**
 * 返回结果工具类
 * @author ADMIN
 *
 */
public class Result<T>{

	private ResultCode resultCode;

	private T object;

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}
}
