package com.stroe.model.goods;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * 商品信息表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月18日下午9:50:19
 */
@TableBind(tableName="shopping_goods_info")
public class ShoppingGoodsInfo extends BaseModel<ShoppingGoodsInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final ShoppingGoodsInfo dao=new ShoppingGoodsInfo();

}
