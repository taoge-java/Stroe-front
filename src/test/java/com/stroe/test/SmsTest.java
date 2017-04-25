package com.stroe.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.stroe.util.HttpClientUtil;
import com.stroe.util.MatchUtil;

import net.sf.json.JSONObject;

public class SmsTest {

	private String url="https://api.dingdongcloud.com/v1/sms/sendyzm";
	public String smsSend(String apikey, String mobile, String content){
		if (StringUtils.isNotBlank(content)) {
            try {
                content = URLEncoder.encode(content, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
 
        NameValuePair[] data = { new NameValuePair("apikey", apikey),
 
        new NameValuePair("mobile", mobile),
 
        new NameValuePair("content", content) };
 
        return doPost(url, data);
	}
	
	 private static String doPost(String url, NameValuePair[] data) {
		 
	        HttpClient client = new HttpClient();
	        PostMethod method = new PostMethod(url);
	        // method.setRequestHeader("ContentType",
	        // "application/x-www-form-urlencoded;charset=UTF-8");
	        method.setRequestBody(data);
	       // client.getParams().setContentCharset("UTF-8");
	        client.getParams().setConnectionManagerTimeout(10000);
	        try {
	            client.executeMethod(method);
	            return method.getResponseBodyAsString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	 }
	 
	// @Test
	public void test(){
		 String apikey = "47bbe8d61e18ad295c0acc8eeafbcc80";
		 String mobile = "18296640717";
		 String content = "【叮咚云】您的验证码是：1234";
		String result= smsSend(apikey,mobile,content);
		JSONObject json=JSONObject.fromObject(result);
		System.out.println("dfdf：；；"+json.get("code"));
		System.err.println(result);
	}
	 
	 @Test
	 public void sms(){
		 HashMap<String, String> map=new HashMap<String, String>();
		 map.put("apikey", "47bbe8d61e18ad295c0acc8eeafbcc80");
		 map.put("mobile", "18296640717");
		 map.put("content", "【叮咚云】您的验证码是：1234");
		 String result=HttpClientUtil.httpPostRequest(url, map);
		 System.out.println(result);
	 }
	 
	 @Test
	 public void testPassword(){
		System.out.println(MatchUtil.checkMatch("[a-zA-Z0-9]{8,16}", "aa123456C")); ;
	 }
}
