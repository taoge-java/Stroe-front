package com.stroe.model.user;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * 会员信息表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月19日下午1:14:30
 */
@TableBind(tableName="user_info")
public class UserInfo extends BaseModel<UserInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final UserInfo dao=new UserInfo();
}
