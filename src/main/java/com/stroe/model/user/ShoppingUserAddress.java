package com.stroe.model.user;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * 用户地址表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月19日下午1:13:57
 */
@TableBind(tableName="shopping_user_address")
public class ShoppingUserAddress extends BaseModel<ShoppingUserAddress>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final ShoppingUserAddress dao=new ShoppingUserAddress();
}
