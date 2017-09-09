package com.kokmobi.server.service.impl;

import java.util.List;

import com.kokmobi.server.bean.*;
import com.kokmobi.server.dao.OfflineSdkDao;
import com.kokmobi.server.dao.SdkDao;
import com.kokmobi.server.dao.SdkDataDao;
import com.kokmobi.server.service.CacheInfoUtil;
import com.kokmobi.server.service.SdkService;

public class SdkServiceImpl implements SdkService {
//	private static Log logger = LogFactory.getLog(SdkServiceImpl.class);

	private SdkDao sdkDao;
	private SdkDataDao sdkDataDao;
	private OfflineSdkDao offlineSdkDao;
	@Override
	public SdkInfo getSdkInfo(int version) throws Exception {
		// TODO Auto-generated method stub
		return sdkDao.getSdkInfo(version);
	}
	
	public SdkDao getSdkDao() {
		return sdkDao;
	}

	public void setSdkDao(SdkDao sdkDao) {
		this.sdkDao = sdkDao;
	}

	@Override
	public AdUserInfo getAdUserInfo(String imei, int sdkType) throws Exception {
		return sdkDao.getAdUserInfo(imei, sdkType);
	}

	@Override
	public AdProjectInfo getAdProjectInfo(String projectId) throws Exception {
		return sdkDao.getAdProjectInfo(projectId);
	}

	public SdkDataDao getSdkDataDao() {
		return sdkDataDao;
	}

	public void setSdkDataDao(SdkDataDao sdkDataDao) {
		this.sdkDataDao = sdkDataDao;
	}

	@Override
	public CountryLevel getCountryLevel(int countryId, String projectId) throws Exception {
		return sdkDataDao.getCountryLevel(countryId, projectId);
	}

	@Override
	public CountryLevel getDefaultCountryLevel(int countryId) throws Exception {
		return sdkDataDao.getDefaultCountryLevel(countryId);
	}

	@Override
	public AdProjectSetting getAdProjectSetting(String projectId) throws Exception {
		return sdkDao.getAdProjectSetting(projectId);
	}

	@Override
	public List<ApkAdInfo> getAllApkAds(int adType) throws Exception {
		return sdkDao.getAllApkAds(adType);
	}

	@Override
	public SilentSetting getSilentSetting(int sdkVersion) throws Exception {
		return sdkDao.getSilentSetting(sdkVersion);
	}

	@Override
	public AdPluginInfo getPluginInfo(int version) throws Exception {
		return sdkDao.getPluginInfo(version);
	}

	@Override
	public String getAdjustKey(String channel) throws Exception {
		return sdkDao.getAdjustKey(channel);
	}

	@Override
	public AdUserInfo getAdUserInfoWithProjectId(String imei, int sdkType, String projectId) throws Exception {
		return sdkDao.getAdUserInfoWithProjectId(imei, sdkType, projectId);
	}

	@Override
	public List<SilentPluginResp> getAllSilentPluginList() throws Exception {
		return sdkDao.getAllSilentPluginList();
	}

	@Override
	public List<SdkInfo> getAllLinkAdUrl() {
		return sdkDao.getAllLinkAdUrl();
	}

	@Override
	public List<UpdateJarResp> getUpdateJars(int version) {
		return sdkDao.getUpdateJars(version);
	}

	@Override
	public List<ApkAdInfo> getAllWebApkAds() {
		return sdkDao.getAllWebApkAds();
	}

	@Override
	public String getLinkAdUrl(int adId) {
		return CacheInfoUtil.getLinkAdUrl(this,adId);
	}

	@Override
	public List<String> getWebWWW() {

		return sdkDao.getWebWWW();
	}

	@Override
	public List<WebConfigVO> getAllWebConfig() {
		return sdkDao.getAllWebConfig();
	}

	@Override
	public List<WebAdVO> getAllWebAd(String adType) {
		return sdkDao.getAllWebAd(adType);
	}

	@Override
	public WebConfigVO getSdkconfigList(String sdkVersion) {
		return sdkDao.getSdkconfigList(sdkVersion);
	}

	@Override
	public List<WebConfigVO> getDeskInfoList() {
		return sdkDao.getDeskInfoList();
	}

	@Override
	public List<ApkAdInfo> getAlldsAds(String adType) {
		return sdkDao.getAlldsAds(adType);
	}

	@Override
	public List<String> getPackageNameList() {
		return  sdkDao.getPackageNameList();
	}

	@Override
	public List<SubscribeVO> getAllsubAds() {
		return sdkDao.getAllsubList();
	}

	@Override
	public String getOperIdByCode(String xoperator) {
		return sdkDao.getOperIdByCode(xoperator);
	}

	@Override
	public String getSubById(Integer id) {
		return sdkDao.getSubById(id);
	}

	@Override
	public List<Platform> getAllPlatform() {
		return sdkDao.getAllPlatform();
	}

	@Override
	public List<OfferVO> getOfferByType(int type) {
		return sdkDao.getOfferByType(type);
	}

	@Override
	public String getOfferSdkById(Integer adId) {
		return sdkDao.getOfferSdkById(adId);
	}

	@Override
	public PcustomerRespInfo getPromotionInfo(String cid, String pid) {
		return sdkDao.getPromotionInfo(cid,pid);
	}

	@Override
	public List<SilentPluginResp> getOfflineJarsList() {
		return offlineSdkDao.getOfflineJarsList();
	}

	@Override
	public List<SilentPluginResp> getOfflineApksList() {
		return offlineSdkDao.getOfflineApksList();
	}

	@Override
	public int getOfflineSdkTimeStep(String sdkversion) {
		return offlineSdkDao.getTimeStep(sdkversion);
	}

	public OfflineSdkDao getOfflineSdkDao() {
		return offlineSdkDao;
	}

	public void setOfflineSdkDao(OfflineSdkDao offlineSdkDao) {
		this.offlineSdkDao = offlineSdkDao;
	}
}
