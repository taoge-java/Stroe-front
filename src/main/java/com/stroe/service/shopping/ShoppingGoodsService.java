package com.stroe.service.shopping;

import com.jfinal.kit.Kv;
import com.jfinal.log.Log;
import com.stroe.service.base.BaseService;
import com.stroe.service.base.DefaultResult;
import com.stroe.service.base.Result;
import com.stroe.util.ResultCode;

public class ShoppingGoodsService extends BaseService{

	private static final Log LOG=Log.getLog(ShoppingGoodsService.class);
	
	public Result shoppingGoodsList(int pageNumber,int pageSize,Kv condition) {
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS);
		try {
			
		} catch (Exception e) {
			resultCode=new ResultCode(ResultCode.FAIL);
			LOG.error("查询异常",e);
		}
		result.setResultCode(resultCode);
		return result;
	}
}
