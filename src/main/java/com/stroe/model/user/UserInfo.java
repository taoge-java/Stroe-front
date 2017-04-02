package com.stroe.model.user;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;

@TableBind(tableName="user_info")
public class UserInfo extends BaseModel<UserInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final UserInfo dao=new UserInfo();
}
