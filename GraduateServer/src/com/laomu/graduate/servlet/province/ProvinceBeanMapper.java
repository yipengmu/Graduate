package com.laomu.graduate.servlet.province;

import java.util.Map;

public interface ProvinceBeanMapper {
	public ProvinceBean findProvinceByName(Map<String, String> param);

	public ProvinceBean findProvinceById(Map<String, String> param);

}
