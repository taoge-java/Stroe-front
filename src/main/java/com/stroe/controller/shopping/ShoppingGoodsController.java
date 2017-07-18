package com.stroe.controller.shopping;

import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.Kv;
import com.stroe.controller.base.BaseController;
import com.stroe.service.shopping.ShoppingGoodsService;

/**
 * 商品列表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月18日下午9:16:36
 */
@ControllerBind(controllerKey="/shopping/orderGoods")
public class ShoppingGoodsController extends BaseController{

	private ShoppingGoodsService shoppingGoodsService=enhance(ShoppingGoodsService.class);
	
	public void index(){
		renderView("/shopping/index.vm");
	}
	
	public void list(){
		int pageNumber=getParaToInt("pageNumber",0);
		String keyWord=getPara("keyWord");//关键字搜索
		Kv condition=Kv.by("keyWord", keyWord);
		shoppingGoodsService.shoppingGoodsList(pageNumber,pageSize,condition);
	}
}
