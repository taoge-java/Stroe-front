package com.stroe.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期工具类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月26日 上午9:57:08
 */

public class DateUtil {
	
    /**
     * 将日期转换成字符串型
     * @return
     */
	public String getDate(){
		return new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
	}
}
