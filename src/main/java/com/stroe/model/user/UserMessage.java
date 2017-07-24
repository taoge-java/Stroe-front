package com.stroe.model.user;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * 用户站内信表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月20日下午10:47:07
 */
@TableBind(tableName="user_message")
public class UserMessage extends BaseModel<UserMessage>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static UserMessage dao=new UserMessage();
}
