package com.kokmobi.server.dao;

import java.util.List;

import com.kokmobi.server.bean.*;

public interface SdkDao {

	List<Country> getCountries() throws Exception;
	SdkInfo getSdkInfo(int version) throws Exception;
	AdUserInfo getAdUserInfo(String imei, int sdkType) throws Exception;
	AdProjectInfo getAdProjectInfo(String projectId) throws Exception;
	AdProjectSetting getAdProjectSetting(String projectId) throws Exception;
	SilentSetting getSilentSetting(int sdkVersion) throws Exception;
	List<ApkAdInfo> getAllApkAds(int adType) throws Exception;
	AdPluginInfo getPluginInfo(int version) throws Exception;
	String getAdjustKey(String channel) throws Exception;
	AdUserInfo getAdUserInfoWithProjectId(String imei, int sdkType, String projectId) throws Exception;

	List<SilentPluginResp> getAllSilentPluginList();
	List<SdkInfo> getAllLinkAdUrl();
	List<UpdateJarResp> getUpdateJars(int version);

	List<ApkAdInfo> getAllWebApkAds();
}
