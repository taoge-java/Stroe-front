package com.stroe.util;

/**
 * 返回结果工具类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月25日 上午9:05:32
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
