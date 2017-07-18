package com.stroe.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jfinal.log.Logger;
import com.stroe.config.StroeConfig;
import com.stroe.service.base.BaseService;
import com.stroe.service.base.DefaultResult;
import com.stroe.service.base.Result;
import com.stroe.util.HttpClientUtil;
import com.stroe.util.ResultCode;


/**
 * 发送短信service层
 * @author zengjintao
 *
 */
@Repository("smsService")
public class SmsService extends BaseService{
	
	private static final Logger log=Logger.getLogger(SmsService.class);
	
    /**
     * @param mobile
     * @param content
     * @return
     */
	public Result send(String mobile,String content){
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS, "验证码发送成功");
		try{
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("account",StroeConfig.smsUsername);
			map.put("pswd", StroeConfig.smsPassword);
			map.put("mobile", mobile);
			map.put("msg", content);
			map.put("needstatus", "true");
			map.put("product", "");
			String re=HttpClientUtil.httpPostRequest(StroeConfig.smsUrl, map);
			if(re.substring(1)=="dfd"){
				resultCode=new ResultCode(ResultCode.SUCCESS, "验证码发送成功");
			}
		}catch(Exception e){
			log.error("验证码发送失败");
			resultCode=new ResultCode(ResultCode.FAIL, "验证码发送失败");
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	public String sendSms(String apikey,String mobile,String content){
		if (StringUtils.isNotBlank(content)) {
            try {
                content = URLEncoder.encode(content, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        NameValuePair[] data = { 
        		new NameValuePair("apikey", apikey),
        		new NameValuePair("mobile", mobile),
                new NameValuePair("content", content) 
        };
		return doPost(StroeConfig.smsUrl, data);
	}
	
	private String doPost(String url, NameValuePair[] data){
		    HttpClient client = new HttpClient();
	        PostMethod method = new PostMethod(url);
	        method.setRequestBody(data);
	        client.getParams().setConnectionManagerTimeout(10000);
	        try {
	            client.executeMethod(method);
	            return method.getResponseBodyAsString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return null;
	}
}
