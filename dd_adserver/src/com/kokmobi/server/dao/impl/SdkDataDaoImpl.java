package com.kokmobi.server.dao.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.kokmobi.server.bean.CountryLevel;
import com.kokmobi.server.dao.SdkDataDao;

@SuppressWarnings("deprecation")
public class SdkDataDaoImpl extends SqlMapClientDaoSupport implements SdkDataDao {

	@Override
	public CountryLevel getCountryLevel(int countryId, String projectId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("countryId", countryId);
		map.put("projectId", projectId);
		return (CountryLevel) getSqlMapClientTemplate().queryForObject("SdkData.getCountryLevel", map);
	}

	@Override
	public CountryLevel getDefaultCountryLevel(int countryId) throws Exception {
		return (CountryLevel) getSqlMapClientTemplate().queryForObject("SdkData.getCountryLevelOfDefault", countryId);
	}
	

}
