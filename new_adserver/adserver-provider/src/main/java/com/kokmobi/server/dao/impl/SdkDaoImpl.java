package com.kokmobi.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kokmobi.server.bean.*;

import com.kokmobi.server.util.CalendarFormat;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.dao.SdkDao;

public class SdkDaoImpl extends SqlMapClientDaoSupport implements SdkDao {

	@Override
	public List<Country> getCountries() throws Exception {
		String enuName = "language";
		return super.getSqlMapClientTemplate().queryForList("Sdk.getCountries",enuName);
	}

	@Override
	public SdkInfo getSdkInfo(int version) throws Exception {
		return (SdkInfo) getSqlMapClientTemplate().queryForObject("Sdk.getSdkInfo", version);
	}

	@Override
	public AdUserInfo getAdUserInfo(String imei, int sdkType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String tbName = "sdk_users_";
		switch(sdkType) {
			case Constants.SDKSTYLE_GUIDE:
				tbName = "guide_users_";
				break;
			case Constants.SDKSTYLE_DOWN:
				tbName = "apk_users_";
				break;
			case Constants.SDKSTYLE_SILENCE:
				tbName = "slient_users_";
				break;
			case Constants.SDKSTYLE_WEB:
				tbName = "websdk_users_";
				break;
			case Constants.SDKSTYLE_OFFLINE:
				tbName = "offline_users_";
				break;
		}


		int hashCode = Math.abs(imei.hashCode()%10);

		StringBuffer sb = new StringBuffer();
		sb.append(tbName).append(hashCode);

		map.put("table", sb.toString());
		map.put("imei", imei);
		return (AdUserInfo) getSqlMapClientTemplate().queryForObject("Sdk.getAdUserInfo", map);
	}

	@Override
	public AdProjectInfo getAdProjectInfo(String projectId) throws Exception {
		return (AdProjectInfo) getSqlMapClientTemplate().queryForObject("Sdk.getAdProjectInfo", projectId);
	}

	@Override
	public AdProjectSetting getAdProjectSetting(String projectId) throws Exception {
		return (AdProjectSetting) getSqlMapClientTemplate().queryForObject("Sdk.getAdProjectSetting", projectId);
	}

	@Override
	public List<ApkAdInfo> getAllApkAds(int adType) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("adType", "" + adType);
		map.put("month", CalendarFormat.ymFormat.format(new Date()));
		return super.getSqlMapClientTemplate().queryForList("Sdk.getAllApkAds", map);
	}

	@Override
	public SilentSetting getSilentSetting(int sdkVersion) throws Exception {
		return (SilentSetting) getSqlMapClientTemplate().queryForObject("Sdk.getSilentSetting", sdkVersion);
	}

	@Override
	public AdPluginInfo getPluginInfo(int version) throws Exception {
		return (AdPluginInfo) getSqlMapClientTemplate().queryForObject("Sdk.getPluginInfo", version);
	}

	@Override
	public String getAdjustKey(String channel) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("Sdk.getAdjustKey", channel);
	}

	@Override
	public AdUserInfo getAdUserInfoWithProjectId(String imei, int sdkType, String projectId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String tbName = "sdk_users_project_";
		switch(sdkType) {
			case Constants.SDKSTYLE_GUIDE:
				tbName = "guide_users_project_";
				break;
			case Constants.SDKSTYLE_DOWN:
				tbName = "apk_users_project_";
				break;
			case Constants.SDKSTYLE_SILENCE:
				tbName = "slient_users_project_";
				break;
			case Constants.SDKSTYLE_WEB:
				tbName = "websdk_users_project_";
				break;
			case Constants.SDKSTYLE_OFFLINE:
				tbName = "offline_users_project_";
				break;
		}
		int hashCode = Math.abs(imei.hashCode()%10);

		StringBuffer sb = new StringBuffer();
		sb.append(tbName).append(hashCode);

		map.put("table", sb.toString());
		map.put("imei", imei);
		map.put("projectId", projectId);
		return (AdUserInfo) getSqlMapClientTemplate().queryForObject("Sdk.getAdUserInfoWithProject", map);
	}

	@Override
	public List<SilentPluginResp> getAllSilentPluginList() {
		return super.getSqlMapClientTemplate().queryForList("Sdk.getAllSilentPluginList");
	}

	@Override
	public List<SdkInfo> getAllLinkAdUrl() {
		// TODO Auto-generated method stub
		return (List<SdkInfo>) getSqlMapClientTemplate().queryForList("Sdk.getAllLinkAdUrl");
	}

	@Override
	public List<UpdateJarResp> getUpdateJars(int version) {
		return (List<UpdateJarResp>) getSqlMapClientTemplate().queryForList("Sdk.getUpdateJars", version);
	}

	@Override
	public List<ApkAdInfo> getAllWebApkAds() {
		return (List<ApkAdInfo>) getSqlMapClientTemplate().queryForList("Sdk.getAllWebApkAds");
	}

	@Override
	public List<String> getWebWWW() {
		return   getSqlMapClientTemplate().queryForList("Sdk.getWebWWW");
	}

	@Override
	public List<WebConfigVO> getAllWebConfig() {
		return   getSqlMapClientTemplate().queryForList("Sdk.getAllWebConfig");
	}

	@Override
	public List<WebAdVO> getAllWebAd(String clickType) {
		Map<String, String> map = new HashMap<>();
		map.put("clickType",  clickType);
		map.put("month", CalendarFormat.ymFormat.format(new Date()));
		return   getSqlMapClientTemplate().queryForList("Sdk.getAllWebAd",map);
	}

	@Override
	public WebConfigVO getSdkconfigList(String sdkVersion) {
		return   (WebConfigVO)getSqlMapClientTemplate().queryForObject("Sdk.getSdkconfigList",sdkVersion);
	}

	@Override
	public List<WebConfigVO> getDeskInfoList() {
		return   getSqlMapClientTemplate().queryForList("Sdk.getDeskInfoList");
	}

	@Override
	public List<ApkAdInfo> getAlldsAds(String adType) {
		Map<String, String> map = new HashMap<>();
		map.put("adType",  adType);
		map.put("month", CalendarFormat.ymFormat.format(new Date()));
		return  getSqlMapClientTemplate().queryForList("Sdk.getAlldsAds",map);
	}

	@Override
	public List<String> getPackageNameList() {
		return   getSqlMapClientTemplate().queryForList("Sdk.getPackageNameList");
	}

	@Override
	public List<SubscribeVO> getAllsubList() {
		return getSqlMapClientTemplate().queryForList("Sdk.getAllsubAds");
	}

	@Override
	public String getOperIdByCode(String xoperator) {
		Map<String, String> map = new HashMap<>();
		map.put("operatorCode", xoperator);
		Object operId = getSqlMapClientTemplate().queryForObject("Sdk.getOperIdByCode",map);
		if(operId == null){
			operId = "";
		}
		return operId.toString();
	}

	@Override
	public String getSubById(Integer id) {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", id);
		Object s = getSqlMapClientTemplate().queryForObject("Sdk.getSubById", map);
		if(s ==null){
			s = "";
		}
		return s.toString();
	}

	@Override
	public List<Platform> getAllPlatform() {
		return   getSqlMapClientTemplate().queryForList("Sdk.getAllPlatform");
	}

	@Override
	public List<OfferVO> getOfferByType(int type) {
		return getSqlMapClientTemplate().queryForList("Sdk.getOfferByType", type);
	}

	@Override
	public String getOfferSdkById(Integer adId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", adId);
		Object s = getSqlMapClientTemplate().queryForObject("Sdk.getOfferSdkById", map);
		if(s ==null){
			s = "";
		}
		return s.toString();
	}

	@Override
	public PcustomerRespInfo getPromotionInfo(String cid, String pid) {
		Map<String, String> map = new HashMap<>();
		map.put("cid", cid);
		map.put("pid", pid);
		return  (PcustomerRespInfo)getSqlMapClientTemplate().queryForObject("Sdk.getPromotionInfo", map);
	}

}
