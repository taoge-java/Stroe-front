package com.stroe.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	
    /**
     * 将日期转换成字符串型
     * @return
     */
	public String getDate(){
		return new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
	}
}
