package com.stroe.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式校验工具类
 * @author zengjintao
 *
 */
public class MatchUtil {

	/**
	 * 
	 * @param regEx
	 * @param target
	 * @return
	 */
	public static boolean checkMatch(String regEx,String target){
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(target);
		if(m.matches()){
			return true;
		}
		return false;
	}
}
