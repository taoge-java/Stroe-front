package com.stroe.model.order;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * 商品订单表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月18日下午9:51:33
 */
@TableBind(tableName="shopping_goods_order")
public class ShoppingGoodsOrder extends BaseModel<ShoppingGoodsOrder>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final ShoppingGoodsOrder dao=new ShoppingGoodsOrder();
}
