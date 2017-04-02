package com.stroe.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.log.Logger;

public class DateUtil {
	
	private Logger log=Logger.getLogger(getClass());
	
	public String getDate(){
		return new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
	}
}
