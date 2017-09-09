package com.kokmobi.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.Country;
import com.kokmobi.server.dao.SdkDao;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.util.IpUtil;


public class AreaServiceImpl implements AreaService {
	private static Log logger = LogFactory.getLog(AreaServiceImpl.class);
	
	private List<Country> countryMap = new ArrayList<Country>();
	
	private static String CHINA_PROVINCE = "安徽,北京,福建,福建,甘肃,广东,广西,贵州,海南,河北,河南,黑龙江,湖北,湖南,吉林,江苏,辽宁,内蒙古,宁夏,青海,山东,山西,陕西,上海,四川,天津,西藏,新疆,云南,浙江,重庆,江西";
	private static int CHINA_ID = 183;	//183	4	CN		中国(CN)		200100	1
	
	private SdkDao sdkDao;

	public SdkDao getSdkDao() {
		return sdkDao;
	}

	public void setSdkDao(SdkDao sdkDao) {
		this.sdkDao = sdkDao;
	}
	public void loadCountryMap() {
		try {
			countryMap = getSdkDao().getCountries();
		} catch (Exception e) {
			logger.error(e);
			countryMap = new ArrayList<Country>();
		}	
	}
	private Country getCountryByName(String countryName) {
		Country c = null;
		countryName = countryName==null?"":countryName;
		countryName=countryName.length()>1?countryName.substring(0, 2):countryName;	//
		if(countryName.length()>0) {
			for(Country _c : countryMap){
				if(_c.getName().contains(countryName)) {
					c = _c;
					break;
				}
			}	
		}
		if(c == null) {
			if(CHINA_PROVINCE.contains(countryName)) {
				c = new Country();
				c.setId(CHINA_ID);
				c.setName("中国");
			}
		}
		return c;
	}
	@Override
	public Country getCountry(String ipAddr) {
		logger.info("to get country---"+ipAddr);
		Country c = null;
		
		/*try{
			String countryName = IpUtil.getCountryBySeeker(ipAddr);
			logger.info("get country by seeker:"+countryName);
			c = getCountryByName(countryName);
		}
		catch(Exception ex){
			logger.error(ipAddr+""+ex);
		}*/

			try{
				String countryName = IpUtil.getCountryByIpGeo(ipAddr);
				logger.info("get country by ipgeo:"+countryName);
				c = getCountryByName(countryName);
			}
			catch(Exception ex) {
				logger.error(ipAddr+""+ex);
			}

		if(c == null) {
			try{
				String countryName = IpUtil.getCountryByIpnet(ipAddr);
				logger.info("get country by ipnet:"+countryName);
				c = getCountryByName(countryName);
			}
			catch(Exception ex) {
				logger.error(ipAddr+""+ex);
			}
		}
		
		return c;
	}
}
