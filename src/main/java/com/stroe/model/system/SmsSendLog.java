package com.stroe.model.system;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * 短信日志记录表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月19日下午1:15:42
 */
@TableBind(tableName="sms_send_log")
public class SmsSendLog extends BaseModel<SmsSendLog>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final SmsSendLog dao=new SmsSendLog();
}
