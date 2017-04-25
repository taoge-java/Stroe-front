package com.stroe.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月25日 上午9:06:02
 */
public class NumberUtil {

	public static String getNumberCode(int number){
		String str="1234567890";
		StringBuilder sb=new StringBuilder();
		Random random=new Random();
		for(int i=0;i<number;i++){
			int num=random.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num)+""), "");
		}
		return sb.toString();
	}
	
	public static String getImagePath(){
		DateFormat format = new SimpleDateFormat("yyyy-MMdd");
		return format.format(new Date()).replaceAll("-", "/");
	}
 	public static void main(String[] args) {
		System.out.println(NumberUtil.getNumberCode(6));
	}
}
