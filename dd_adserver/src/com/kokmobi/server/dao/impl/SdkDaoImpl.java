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
import sun.util.calendar.CalendarUtils;

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

}
