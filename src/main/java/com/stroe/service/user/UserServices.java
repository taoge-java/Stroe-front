package com.stroe.service.user;

import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.jfinal.log.Logger;
import com.stroe.dto.MobileCode;
import com.stroe.model.user.UserInfo;
import com.stroe.service.base.BaseService;
import com.stroe.service.base.DefaultResult;
import com.stroe.service.base.Result;
import com.stroe.util.EncryptUtil;
import com.stroe.util.IpUitls;
import com.stroe.util.MatchUtil;
import com.stroe.util.NumberUtil;
import com.stroe.util.ResultCode;

/**
 * 会员登录,注册services层
 * @author zengjintao
 * 2017年3月4号 19:19
 */
public class UserServices extends BaseService{


	private static final Logger log=Logger.getLogger(UserServices.class);
	
	/**
	 * 会员注册
	 * @param mobile
	 * @param code
	 * @param password
	 * @param passwordRepeat
	 * @return
	 */
    public  Result regist(String mobile,String code,String password,String passwordRepeat,MobileCode mobilekey,HttpServletRequest request){
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS, "注册成功");
		if(StringUtils.isEmpty(mobile)){
			resultCode=new ResultCode(ResultCode.FAIL, "手机号不能为空");
			result.setResultCode(resultCode);
			return result;
		}
		if(!MatchUtil.checkMatch("13\\d{9}|15\\d{9}|18\\d{9}|17\\d{9}", mobile)){
			resultCode=new ResultCode(ResultCode.FAIL, "请输入正确手机号");
			result.setResultCode(resultCode);
			return result;
		}
		if(StringUtils.isEmpty(code)){
			resultCode=new ResultCode(ResultCode.FAIL, "验证码不能为空");
			result.setResultCode(resultCode);
			return result;
		}
		if(StringUtils.isEmpty(password)){
			resultCode=new ResultCode(ResultCode.FAIL, "密码不能为空");
			result.setResultCode(resultCode);
			return result;
		}
		if(!MatchUtil.checkMatch("[a-zA-Z0-9]{8,16}",password)){
			resultCode=new ResultCode(ResultCode.FAIL, "密码必须为8到16位的数字和字母组合");
			result.setResultCode(resultCode);
			return result;
		}
		if(!password.equals(passwordRepeat)){
			resultCode=new ResultCode(ResultCode.FAIL, "确认密码和密码不一致");
			result.setResultCode(resultCode);
			return result;
		}
		if(mobilekey==null){
			resultCode=new ResultCode(ResultCode.FAIL, "请发送短信验证码");
			result.setResultCode(resultCode);
			return result;
		}else{
			if(!mobilekey.getCode().equals(code)){
				resultCode=new ResultCode(ResultCode.FAIL, "验证码错误");
				result.setResultCode(resultCode);
				return result;
			}
		}
		boolean timeout=System.currentTimeMillis()-mobilekey.getDateTime().getTime()>10*60*1000;
		if(timeout){
			resultCode=new ResultCode(ResultCode.FAIL, "验证码已超时,请重新获取验证码!");
			result.setResultCode(resultCode);
			return result;
		}
		UserInfo userInfo=new UserInfo();
		String encrypt=EncryptUtil.encodeSalt(System.currentTimeMillis()+""+new Random().nextInt(1000));
		try{
			userInfo.set("mobile", mobile);
			userInfo.set("encrypt", encrypt);
			userInfo.set("code", NumberUtil.getNumberCode(4));
			userInfo.set("password", EncryptUtil.getMd5(password, encrypt));
			userInfo.set("ip", IpUitls.getAddressIp(request));
			userInfo.set("regist_time", new Date());
			userInfo.save();
			userInfo.set("login_name", 10000+userInfo.getInt("id"));
			userInfo.update();
			result.setModel("userInfo", userInfo);
		}catch(Exception e){
			resultCode=new ResultCode(ResultCode.FAIL, "注册失败,请联系网站管理员");
			e.printStackTrace();
			log.error("注册失败,请联系网站管理员");
		}
		result.setResultCode(resultCode);
		return result;
	}
}
