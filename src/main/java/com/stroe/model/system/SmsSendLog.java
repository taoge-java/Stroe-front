package com.stroe.model.system;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
@TableBind(tableName="sms_send_log")
public class SmsSendLog extends BaseModel<SmsSendLog>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final SmsSendLog dao=new SmsSendLog();
}
