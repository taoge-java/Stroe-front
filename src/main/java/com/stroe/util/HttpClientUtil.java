package com.stroe.util;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.jfinal.log.Logger;



/**
 * http请求工具类
 * @author zengjintao
 * 2017年3月6日 20:45
 */
public class HttpClientUtil {
	
	private static Logger log=Logger.getLogger(HttpClientUtil.class);
	
	public static String httpRequest(String url){
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
		CloseableHttpClient httpclient= builder.build();
		HttpGet httpGet=new HttpGet(url);
		try {
			HttpResponse response=httpclient.execute(httpGet);
			if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
				HttpEntity entity= response.getEntity();
				content=EntityUtils.toString(entity,"UTF-8");
				EntityUtils.consume(response.getEntity());
			}
			httpGet.abort();
			httpGet=null;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(httpclient!=null){
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
	
	public static String httpPostRequest(String url,String body){
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
		CloseableHttpClient httpClient=builder.build();
		HttpPost httpPost=new HttpPost(url);
		httpPost.setEntity(new StringEntity(body, "UTF-8"));//设置参数
		try {
		   HttpResponse response=httpClient.execute(httpPost);
		   if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
			  HttpEntity entity=response.getEntity();
			  content=EntityUtils.toString(entity, "UTF-8");
			  EntityUtils.consume(entity);
		   }
		   httpPost.abort();
		   httpPost=null;
		} catch (IOException e) {
			log.error("HHTP请求异常");
			e.printStackTrace();
		}finally {
			if(httpClient!=null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
	
	public static String httpPostRequest(String url,HashMap<String, String> param){
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
		CloseableHttpClient httpClient=builder.build();
		HttpPost httpPost=new HttpPost(url);
		List<NameValuePair> valuePair=new ArrayList<NameValuePair>();
		Iterator<String> iterator=param.keySet().iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			valuePair.add(new BasicNameValuePair(key,param.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(valuePair,"UTF-8"));
			try {
				CloseableHttpResponse response=httpClient.execute(httpPost);
				if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
					HttpEntity entity=response.getEntity();
					EntityUtils.toString(entity, "UTF-8");
					EntityUtils.consume(entity);
				}
				httpPost.abort();
				httpPost=null;
			} catch (IOException e) {
				log.error("HTTP请求异常....");
				e.printStackTrace();
			}finally {
				if(httpClient!=null){
					try {
						httpClient.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	public static void main(String[] args) {
		String content=HttpClientUtil.httpRequest("http://send.18sms.com/msg/HttpBatchSendSM");
		System.out.println(content);
	}
}
