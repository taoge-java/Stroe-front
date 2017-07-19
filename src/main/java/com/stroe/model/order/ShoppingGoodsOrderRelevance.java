package com.stroe.model.order;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * 订单关联货品表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月18日下午9:53:19
 */
@TableBind(tableName="shopping_goods_order_relevance")
public class ShoppingGoodsOrderRelevance extends BaseModel<ShoppingGoodsOrderRelevance>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final ShoppingGoodsOrderRelevance dao=new ShoppingGoodsOrderRelevance();
}
