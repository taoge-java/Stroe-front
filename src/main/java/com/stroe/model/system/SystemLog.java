package com.stroe.model.system;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;
/**
 * @author zengjintao
 * 系统操作日志表
 * @version 1.0
 */
@TableBind(tableName="system_log")
public class SystemLog extends BaseModel<SystemLog>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final SystemLog dao=new SystemLog();

}
