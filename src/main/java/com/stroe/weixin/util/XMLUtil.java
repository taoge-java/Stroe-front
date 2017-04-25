package com.stroe.weixin.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

public class XMLUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlTomap(HttpServletRequest request){
		Map<String,String> map=new HashMap<String, String>();
		try {
		    InputStream in=request.getInputStream();
		    SAXReader reader=new SAXReader();
		    Document document= reader.read(in);
		    Element element= document.getRootElement();
		    List<Element> list=element.elements();
		    for(Element e:list){
		    	map.put(e.getName(), e.getTextTrim());
		    }
		    return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将数据转换成xml
	 */
	public static String messageToXml(Object obj){
		XStream xstream=new XStream();
//		if(obj instanceof ResponseNewsMessage){
//			xstream.alias("item",new Articles().getClass());
//		}
		xstream.alias("xml", obj.getClass());
		return xstream.toXML(obj);
	}
}
