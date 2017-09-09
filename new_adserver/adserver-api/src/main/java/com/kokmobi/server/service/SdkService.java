package com.kokmobi.server.service;

import java.util.List;

import com.kokmobi.server.bean.*;

public interface SdkService {

	SdkInfo getSdkInfo(int version) throws Exception;

	AdUserInfo getAdUserInfo(String imei, int sdkType) throws Exception;
	AdUserInfo getAdUserInfoWithProjectId(String imei, int sdkType, String projectId) throws Exception;
	AdProjectInfo getAdProjectInfo(String projectId) throws Exception;

	CountryLevel getCountryLevel(int countryId, String projectId) throws Exception;
	CountryLevel getDefaultCountryLevel(int countryId) throws Exception;

	AdProjectSetting getAdProjectSetting(String projectId) throws Exception;
	SilentSetting getSilentSetting(int sdkVersion) throws Exception;
	List<ApkAdInfo> getAllApkAds(int adType) throws Exception;
	AdPluginInfo getPluginInfo(int version) throws Exception;
	String getAdjustKey(String channel) throws Exception;
	List<SilentPluginResp> getAllSilentPluginList() throws Exception;

	List<SdkInfo> getAllLinkAdUrl();
	List<UpdateJarResp> getUpdateJars(int version);

	List<ApkAdInfo> getAllWebApkAds();

	String getLinkAdUrl(int adId);

	List<String> getWebWWW();

	List<WebConfigVO> getAllWebConfig();

	List<WebAdVO> getAllWebAd(String adType);

	WebConfigVO getSdkconfigList(String sdkVersion);

	List<WebConfigVO> getDeskInfoList();

	List<ApkAdInfo> getAlldsAds(String adType);

	List<String> getPackageNameList();

	List<SubscribeVO> getAllsubAds();

	String getOperIdByCode(String xoperator);
	List<SilentPluginResp> getOfflineJarsList();

	List<SilentPluginResp> getOfflineApksList();

	int getOfflineSdkTimeStep(String sdkversion);

	String getSubById(Integer adId);

	List<Platform> getAllPlatform();

	List<OfferVO> getOfferByType(int type);

	String getOfferSdkById(Integer adId);

	PcustomerRespInfo getPromotionInfo(String arg, String arg1);
}
