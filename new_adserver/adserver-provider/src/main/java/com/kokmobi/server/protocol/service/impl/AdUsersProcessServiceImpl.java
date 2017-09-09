package com.kokmobi.server.protocol.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.servlet.util.CompatibleUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.AdProjectInfo;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.AdUsersProcessResp;
import com.kokmobi.server.bean.Country;
import com.kokmobi.server.bean.CountryLevel;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.CacheInfoUtil;
import com.kokmobi.server.service.RedisTool;
import com.kokmobi.server.service.SdkService;
import com.kokmobi.server.util.CalendarFormat;
import com.kokmobi.server.util.HttpUtils;

import net.sf.json.JSONObject;

public class AdUsersProcessServiceImpl implements AdUsersProcessService {
	private static Log logger = LogFactory.getLog(AdUsersProcessServiceImpl.class);
	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	private SdkService sdkService;
	private AreaService areaService;
	


	@Override
	public AdUsersProcessResp process(AdUserInfo userInfo) {
		return process(sdkService, areaService, userInfo);
	}

	@Override
	public AdUsersProcessResp process(SdkService sdkService, AreaService areaService, AdUserInfo userInfo) {

		//1、检查项目、及传入参数是否异常，有异常直接返回
		//2、检查是否注册过，如无注册过则为销量数据，并将销量数据保存到redis中；
		//3、如果为活跃数据，直接塞入活跃数据中；
		//4、检查项目的配置信息及
		AdUsersProcessResp resp = new AdUsersProcessResp();
		try {
			/*判断传入的SDKStyle是否正确、项目id是否为空、imei是否为空*/
			if (userInfo.getSdkStyle() == 0 || ProtocolUtil.isNullOrEmpty(userInfo.getCoo_id())
					|| ProtocolUtil.isNullOrEmpty(userInfo.getImei())) {
				logger.error("error: （sdkversion not 1.0 or 1 ）or sdk value is out of range（2、3、4、5）or cooid or imei can not be empty");
		        resp.setStatus(0);
		        resp.setIsfull100(0);
		        resp.setDay(0);
		        return resp;
		    }
			if (!ProtocolUtil.isIntegers(userInfo.getCoo_id(), userInfo.getXc_coo_id(), userInfo.getChannelid(),
					userInfo.getSdkversion(), userInfo.getType())) {
				logger.error("error: param is not int");
				resp.setStatus(0);
				resp.setIsfull100(0);
				resp.setDay(0);
				return resp;
			}
			/*判断当前项目id是否存在，不存在则传入数据有错误，存在则存入缓存*/
			AdProjectInfo project = CacheInfoUtil.getAdProjectInfo(sdkService, userInfo.getCoo_id());
			if(project == null) {
				project = CacheInfoUtil.getAdProjectInfo(sdkService, "68");
			}
			if(project == null) {
				logger.error(String.format("error: can not find project with id: %s", userInfo.getCoo_id()));
				resp.setStatus(0);
		        resp.setIsfull100(0);
		        resp.setDay(0);
		        return resp;
			}
			// 获取国家id和国家等级
			/*等级,1高2中3低 默认为3 国家等级与结算有关*/
			int countryId = -1;
			int countryLevel = 4;
			Country c = CacheInfoUtil.getCountry(areaService,userInfo.getIpaddr());
			if(c != null) {
				countryId = c.getId();
				CountryLevel cl = CacheInfoUtil.getCountryLevel(sdkService, userInfo.getCoo_id(), c.getId());
				if(cl != null) {
					countryLevel = cl.getLevel();
				}
			}
			userInfo.setXcou(countryId);
			userInfo.setCountryLevel(countryLevel);
			Date curDate = new Date();
			userInfo.setCdate(CalendarFormat.getDateString(curDate.getTime()));
			userInfo.setSdate(CalendarFormat.getDateString(curDate.getTime()));

			/*总活跃、总销量的数据统计*/
			AdUserInfo rawUser = CacheInfoUtil.getActUserInfo(sdkService, userInfo.getImei(), userInfo.getSdkStyle());
			boolean isReg = false;
			if(rawUser == null) {
				// 属于需要注册的用户.

				userInfo.setXdate(userInfo.getSdate());
				userInfo.setScoo_id(userInfo.getCoo_id());
				CacheInfoUtil.addAdUserInfoToCache(userInfo);

				isReg = true;
			}
			else {
				userInfo.setXdate(rawUser.getSdate());
				userInfo.setScoo_id(rawUser.getCoo_id());
			}

			/*项目总活跃、项目总销量的数据统计*/
			//增加处理加入项目注册用户
			AdUserInfo projectUser = CacheInfoUtil.getActUserInfoWithProject(sdkService, userInfo.getImei(), userInfo.getSdkStyle(), userInfo.getCoo_id());
			if(projectUser == null) {
				// 属于需要注册的项目用户.
				if(rawUser == null) {
					userInfo.setXdate(userInfo.getSdate());
					userInfo.setScoo_id(userInfo.getCoo_id());
				}
				else {
					userInfo.setXdate(rawUser.getSdate());
					userInfo.setScoo_id(rawUser.getCoo_id());
				}
				CacheInfoUtil.addAdUserInfoWithProjectIdToCache(userInfo);
			}

			/*将每日的总活跃、明细活跃保存到缓存中。将活跃数据放到待保存列表中*/
			// 处理活跃用户
			// 先检查是否总活跃，如果没有，则插入到总活跃key中，返回是否为总活跃标记
			// 再检查是否已存在待保存队列里，不存在，则放到待保存活跃列表里
			CacheInfoUtil.addActUserInfoToCache(userInfo);

			// 根据项目配置信息-返回数据

			// 是否需要转发，如果需要进行转发
			if(userInfo.getSdkStyle() == Constants.SDKSTYLE_SDK) {
				if(project.getIssale() == Constants.STATUS_YES) {
					if(isReg
							|| !ProtocolUtil.isNullOrEmpty(RedisTool.get(String.format(Constants.KEY_WHITELIST_USER, userInfo.getImei())))) {
						//加线程处理
						Map<String, String> para = new HashMap<String, String>();
						para.put("sip", userInfo.getIpaddr());
						para.put("simei", userInfo.getImei());

						 logger.info(String.format("sale to ddl:%s:%s:%s", project.getSaleurl(), userInfo.getIpaddr(), userInfo.getImei()));
						/*HttpGetTool tool = new HttpGetTool(project.getSaleurl(), para);
						tool.start();		*/
						processDDLUser(project.getSaleurl(), para);
					}
				}
			}
			resp.setStatus(1);
	        resp.setIsfull100(0);
	        resp.setDay(project.getDay());
	        if(project.getIsopen() == Constants.STATUS_YES) {
	        	if(project.getIsfull100() == Constants.STATUS_NO) {
	        		resp.setIsfull100(1);
	        	}
	        }
	        return resp;
		}
		catch(Exception ex) {
			logger.error(String.format("error: aduser process with error: %s", ex.getMessage()));
			resp.setStatus(0);
	        resp.setIsfull100(0);
	        resp.setDay(0);
	        return resp;
		}
	}

	private void processDDLUser(final String url, final Map<String, String> paras) {
		executorService.execute(new Runnable() {
			public void run() {
				try {
					String r = HttpUtils.sendGet(url, paras);
					//logger.info(String.format("sale to ddl:%s,result:%s", url, r));

				}
				catch(Exception e){
					
				}
			}
		});
	}

	public void setSdkService(SdkService sdkService) {
		this.sdkService = sdkService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
}
