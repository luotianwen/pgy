package com.kokmobi.server.dao;

import com.kokmobi.server.bean.CountryLevel;

public interface SdkDataDao {

	CountryLevel getCountryLevel(int countryId, String projectId ) throws Exception;
	CountryLevel getDefaultCountryLevel(int countryId ) throws Exception;
}
