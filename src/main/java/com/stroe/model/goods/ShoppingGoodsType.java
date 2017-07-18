package com.stroe.model.goods;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * 商品类别表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月18日下午9:50:33
 */
@TableBind(tableName="shopping_goods_type")
public class ShoppingGoodsType extends BaseModel<ShoppingGoodsType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final ShoppingGoodsType dao=new ShoppingGoodsType();

}
