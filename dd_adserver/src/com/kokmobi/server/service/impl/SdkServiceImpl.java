package com.kokmobi.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kokmobi.server.bean.*;
import com.kokmobi.server.dao.SdkDao;
import com.kokmobi.server.dao.SdkDataDao;
import com.kokmobi.server.service.SdkService;

public class SdkServiceImpl implements SdkService {
//	private static Log logger = LogFactory.getLog(SdkServiceImpl.class);
   private static List<UpdateJarResp> updateJarRespList;
	private static long lastExecuteTime = 0;
	private static long executeStep=5*60*1000;
	private SdkDao sdkDao;
	private SdkDataDao sdkDataDao;
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
		long nowTime = System.currentTimeMillis();
		if((nowTime-lastExecuteTime)>executeStep) {
			List<UpdateJarResp> apkInfoVOList = sdkDao.getUpdateJars(version);
			if(apkInfoVOList!=null&&apkInfoVOList.size()>0) {
				updateJarRespList = new ArrayList<UpdateJarResp>();
				updateJarRespList.addAll(apkInfoVOList);
			}
			lastExecuteTime = nowTime;
		}
		return   updateJarRespList;
	}

	@Override
	public List<ApkAdInfo> getAllWebApkAds() {
		return sdkDao.getAllWebApkAds();
	}

}
