package com.stroe.model.system;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.model.BaseModel;

@TableBind(tableName="system_region")
public class SystemRegion extends BaseModel<SystemRegion>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final SystemRegion dao=new SystemRegion();

}
