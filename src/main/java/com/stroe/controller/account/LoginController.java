package com.stroe.controller.account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.stroe.constant.Constant;
import com.stroe.constant.SysEnumConstant.PlatformType;
import com.stroe.controller.base.BaseController;
import com.stroe.model.user.UserInfo;
import com.stroe.util.EncryptUtil;
import com.stroe.util.ResultCode;
/**
 * 会员登录
 * @author zengjintao
 * 2017年2月26日下午13:43
 */
@ControllerBind(controllerKey="/account/login")
public class LoginController extends BaseController{
	
	private Logger log=Logger.getLogger(getClass());
	/**
	 * 登录页面
	 */
	public void index(){
		HttpServletRequest request=getRequest();
		Cookie[] cookie=request.getCookies();
		if(cookie.length>0&&cookie!=null){
			 for(Cookie c:cookie){
				 if(c.getName().equals(Constant.COOKIE_USERNAME)){
					 setAttr("username", c.getValue());
				 }
				 if(c.getName().equals(Constant.COOKIE_PASSWORD)){
					 setAttr("password", c.getValue());
				 }
			 }
		}
		RenderView("/account/login.vm");
	}
	
	/**
	 * 出来用户登录
	 */
	public void dologin(){
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		String mobile=getPara("mobile");
		String password=getPara("password");
		if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
			renderJson(new ResultCode(ResultCode.FAIL, "用户名或密码不能为空"));
			return;
		}
		String[] remember=getParaValues("remember");//检查用户是否选择确认密码
		if(remember.length>0&&remember!=null){
			Cookie usercookie=new Cookie(Constant.COOKIE_USERNAME,mobile);//保存用户名
			Cookie pwdcookie=new Cookie(Constant.COOKIE_PASSWORD,password);//保存密码
			usercookie.setMaxAge(60*60*24*30);//设置cookie 30天内有效
			pwdcookie.setMaxAge(60*60*24*30);
			//保存cookie
			response.addCookie(usercookie);
			response.addCookie(pwdcookie);
		}else{
		     Cookie[] cookie=request.getCookies();
		     if(cookie!=null&&cookie.length>0){
		    	 for(Cookie c:cookie){
		    		 if(c.getName().equals(Constant.COOKIE_USERNAME)||c.getName().equals(Constant.COOKIE_PASSWORD)){
		    			 c.setMaxAge(0);
		    			 response.addCookie(c);
		    		 }
		    	 }
		     }
		}
		UserInfo userInfo=UserInfo.dao.findFirst("select * from user_info where mobile=?",mobile);
	    try{
	    	if(userInfo!=null&&userInfo.getStr("password").equals(EncryptUtil.getMd5(password, userInfo.getStr("encrypt")))){
	    	    if(userInfo.getBoolean("disabled_flag")){
	    	    	renderJson(new ResultCode(ResultCode.FAIL, "账户已被禁用"));
			    	return ;
	    	    }else{
	    	    	loginSuccess(userInfo,getRequest());
	    	    	renderJson(new ResultCode(ResultCode.SUCCESS, "登录成功"));
	    	    }
		    }else{
		    	renderJson(new ResultCode(ResultCode.FAIL, "用户名或密码错误"));
		    	return;
		    }
	    }catch(Exception e){
	    	renderJson(new ResultCode(ResultCode.FAIL, "登录失败"));
	    	e.printStackTrace();
	    	log.error("登录失败");
	    }
	}
	
	/**
	 * 用户注销
	 */
	public void loginExit(){
		if(getCurrentUser()!=null){
			systemLog(getCurrentUser().getUserName()+"登出系统",PlatformType.WEB.getValue());
			getRequest().getSession().removeAttribute(Constant.SESSION_ID);
			getRequest().getSession().invalidate();//用户注销
			redirect("/",false);
		}
	}
}
