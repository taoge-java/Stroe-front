package com.stroe.service.system;

import java.util.List;

import com.stroe.model.system.SystemRegion;

public class SystemRegionService {

	/**
	 * 根据parent_id查询地区
	 * @param code
	 * @return
	 */
	public List<SystemRegion> getSystemRegion(String code){
		SystemRegion systemRegion=SystemRegion.dao.findFirst("select * from system_region where code=?",code);
		if(systemRegion==null){
			return null;
		}
		int parent_id=systemRegion.getInt("id");
		return SystemRegion.dao.find("select * from system_region where parent_id=?",parent_id);
	}
}
