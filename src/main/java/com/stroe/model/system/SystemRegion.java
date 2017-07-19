package com.stroe.model.system;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;

/**
 * 地区表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月19日下午1:16:03
 */
@TableBind(tableName="system_region")
public class SystemRegion extends BaseModel<SystemRegion>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final SystemRegion dao=new SystemRegion();

}
