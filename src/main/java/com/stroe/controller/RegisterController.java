package com.stroe.controller;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import com.jfinal.aop.Duang;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.stroe.config.StroeConfig;
import com.stroe.constant.Constant;
import com.stroe.constant.SysEnumConstant.FlagType;
import com.stroe.controller.base.BaseController;
import com.stroe.dto.MobileCode;
import com.stroe.model.user.UserInfo;
import com.stroe.service.SmsService;
import com.stroe.service.user.UserServices;
import com.stroe.util.MatchUtil;
import com.stroe.util.NumberUtil;
import com.stroe.util.Result;
import com.stroe.util.ResultCode;

import net.sf.json.JSONObject;
/**
 * 用户注册
 * @author zengjintao
 *2017年3月14号下午21:59
 */
@ControllerBind(controllerKey="/user/regist")
public class RegisterController extends BaseController{

	private Logger log=Logger.getLogger(getClass());
	
	private SmsService smsService=Duang.duang(SmsService.class);
	
	private UserServices userService=Duang.duang(UserServices.class);
	/**
	 * 访问注册页面
	 */
	public void index(){
		RenderView("/user/regist.vm");
	}
	
    /**
     * ajax校验用户注册信息
     */
	@SuppressWarnings("rawtypes")
	public void ajaxregist(){
		String mobile=getPara("mobile");
		String code=getPara("code");
		String password=getPara("password");
		String passwordRepeat=getPara("passwordRepeat");
		MobileCode mobileKey=(MobileCode) getSession().getAttribute(Constant.MOBILE_KEY);
		Result result=userService.regist(mobile, code, password, passwordRepeat, mobileKey,getRequest());
		renderJson(result.getResultCode());
	}
	
	/**
	 * 发送短信验证码
	 */
	public void sendCode(){
		String mobile=getPara("mobile");
		if(StringUtils.isEmpty(mobile)){
			renderJson(new ResultCode(ResultCode.FAIL, "手机号不能为空"));
			return;
		}
		if(!MatchUtil.checkMatch("13\\d{9}|15\\d{9}|18\\d{9}|17\\d{9}", mobile)){
			renderJson(new ResultCode(ResultCode.FAIL, "请输入正确手机号"));
			return;
		}
		UserInfo userInfo=UserInfo.dao.findFirst("select mobile from user_info where mobile=?",mobile);
		if(userInfo!=null){
			renderJson(new ResultCode(ResultCode.FAIL, "手机号已被注册"));
			return;
		}
		try{
			String code=NumberUtil.getNumberCode(6);
			String content="【天下淘网络商城】您的验证码是:"+code+",十分钟内有效";
			String result=smsService.sendSms(StroeConfig.ApiKey, mobile, content);
			System.err.println(content);
			System.err.println(result);
			MobileCode mobileCode=new MobileCode(mobile,code,new Date());
			setSessionAttr(Constant.MOBILE_KEY, mobileCode);//将验证码信息存入session
			if(JSONObject.fromObject(result).getInt("code")==FlagType.YES.getValue()){
				renderJson(new ResultCode(ResultCode.SUCCESS, "验证码发送成功"));
			}else{
				renderJson(new ResultCode(ResultCode.FAIL, "验证码发送失败"));
			}
		}catch(Exception e){
			renderJson(new ResultCode(ResultCode.FAIL, "系统异常,请联系网站管理员"));
			e.printStackTrace();
			log.error("验证码发送失败");
		}
	}
}
