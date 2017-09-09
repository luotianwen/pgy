package com.kokmobi.server.service;

import java.util.List;

import com.kokmobi.server.bean.*;

public interface SdkService {

	SdkInfo getSdkInfo(int version) throws Exception;

	AdUserInfo getAdUserInfo(String imei, int sdkType) throws Exception;
	AdUserInfo getAdUserInfoWithProjectId(String imei, int sdkType, String projectId) throws Exception;
	AdProjectInfo getAdProjectInfo(String projectId) throws Exception;

	CountryLevel getCountryLevel(int countryId, String projectId ) throws Exception;
	CountryLevel getDefaultCountryLevel(int countryId ) throws Exception;

	AdProjectSetting getAdProjectSetting(String projectId) throws Exception;
	SilentSetting getSilentSetting(int sdkVersion) throws Exception;
	List<ApkAdInfo> getAllApkAds(int adType) throws Exception;
	AdPluginInfo getPluginInfo(int version) throws Exception;
	String getAdjustKey(String channel) throws Exception;
	List<SilentPluginResp> getAllSilentPluginList() throws Exception;

	List<SdkInfo> getAllLinkAdUrl();
	List<UpdateJarResp> getUpdateJars(int version);

	List<ApkAdInfo> getAllWebApkAds();
}
