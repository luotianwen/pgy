package com.kokmobi.server.protocol.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.bean.*;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.dao.ApkInfoDao;
import com.kokmobi.server.dao.ProjectDao;
import com.kokmobi.server.protocol.service.DisListService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.CacheInfoUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DisListServiceImpl implements DisListService {

	private ProjectDao projectDao;
	
	private ApkInfoDao apkInfoDao;
	private static Log logger = LogFactory.getLog(DisListServiceImpl.class);
	private static Map<Integer,Long> updateTimeMap=new HashMap<Integer, Long>();
	private static Map<Integer,ProjectVO> projectVOMap=new HashMap<Integer, ProjectVO>();
	private static List<ApkInfoVO> apkInfoList;
	private static long lastExecuteTime = 0;
	private static long executeStep=5*60*1000;
	private AreaService areaService;
	
	public DistReqVO adapter(HttpServletRequest req) throws Exception {
		
		String imei = ProtocolUtil.getReqPara(req, "imei");
		String cooIdStr = ProtocolUtil.getReqPara(req, "cooId");
		String xcooIdStr = ProtocolUtil.getReqPara(req, "xcooId");
		String sdkVersionStr = ProtocolUtil.getReqPara(req, "sdkVersion");
		int cooId = ProtocolUtil.isNullOrEmpty(cooIdStr) ? 0 : Integer.parseInt(cooIdStr);
		int xcooId = ProtocolUtil.isNullOrEmpty(xcooIdStr) ? 0 : Integer.parseInt(xcooIdStr);
		int sdkVersion = ProtocolUtil.isNullOrEmpty(sdkVersionStr) ? 0 : Integer.parseInt(sdkVersionStr);

		DistReqVO reqVO = new DistReqVO();
		reqVO.setImei(imei);
		reqVO.setXcooId(xcooId);
		reqVO.setCooId(cooId);
		reqVO.setSdkVersion(sdkVersion);
		return reqVO;
	}
	
	public DisRespVO processs(DistReqVO distReqVO) throws Exception {
		int projectId =  distReqVO.getCooId();
		long nowTime = System.currentTimeMillis();
		ProjectVO projectVO;
		//获取项目是否获取过数据
		if(updateTimeMap.containsKey(projectId)) {
			long lastUpdateTime = updateTimeMap.get(projectId);
			if((nowTime-lastUpdateTime)>executeStep) {
				//更新项目信息
				projectVO = projectDao.getProjectVOById(projectId);
				updateTimeMap.put(projectId, nowTime);
				projectVOMap.put(projectId, projectVO);
			} else {
				projectVO = projectVOMap.get(projectId);
			}
		} else {
			//新项目
			projectVO = projectDao.getProjectVOById(projectId);
			updateTimeMap.put(projectId, nowTime);
			projectVOMap.put(projectId, projectVO);
		}
		if(projectVO==null) {
			return null;
		} else {
			if((nowTime-lastExecuteTime)>executeStep) {
				List<ApkInfoVO> apkInfoVOList = apkInfoDao.getApkInfoVOList();
				if(apkInfoVOList!=null&&apkInfoVOList.size()>0) {
					apkInfoList = new ArrayList<ApkInfoVO>();
					apkInfoList.addAll(apkInfoVOList);
				}
				lastExecuteTime = nowTime;
			}
			DisRespVO respVO = new DisRespVO();
			respVO.setStatus(Constants.statusMap.get(projectVO.getStatus()));
			respVO.setIsDown(Constants.statusMap.get(projectVO.getIsDown()));
			respVO.setIsGuide(Constants.statusMap.get(projectVO.getIsGuide()));
			respVO.setGuideInterval(projectVO.getGuideInterval());
			respVO.setGuideTimes(projectVO.getGuideTimes());
			if(apkInfoList!=null) {
				respVO.setApks(apkInfoList);
			}
			return respVO;
		}
	}
	public ProjectVO getProject(String coo_id) throws Exception {
		long nowTime = System.currentTimeMillis();
		return getProjectVOs(Integer.parseInt(coo_id),nowTime);
	}
	private  ProjectVO getProjectVOs(int projectId,long nowTime)throws Exception{
		ProjectVO  projectVO=null;
		//获取项目是否获取过数据
		if(updateTimeMap.containsKey(projectId)) {
			long lastUpdateTime = updateTimeMap.get(projectId);
			if((nowTime-lastUpdateTime)>executeStep) {
				//更新项目信息
				projectVO = projectDao.getProjectVOById(projectId);
				updateTimeMap.put(projectId, nowTime);
				projectVOMap.put(projectId, projectVO);
			} else {
				projectVO = projectVOMap.get(projectId);
			}
		} else {
			//新项目
			projectVO = projectDao.getProjectVOById(projectId);
			updateTimeMap.put(projectId, nowTime);
			projectVOMap.put(projectId, projectVO);
		}
		return projectVO;
	}
	public DisRespVO ydprocesss(DistReqVO distReqVO) throws Exception {
		{

//			int countryId = -1;
//			int countryLevel = 4;
//
//			Country c = CacheInfoUtil.getCountry(areaService,distReqVO.getIpaddr());
//			if(c != null) {
//				countryId = c.getId();
//			}
			int projectId =  distReqVO.getCooId();
			long nowTime = System.currentTimeMillis();
			ProjectVO projectVO=getProjectVOs(projectId,nowTime);
			if(projectVO==null) {
				return null;
			} else {
//				if((nowTime-lastExecuteTime)>executeStep) {
//					List<ApkInfoVO> apkInfoVOList = apkInfoDao.getydApkInfoVOList();
//					if(apkInfoVOList!=null&&apkInfoVOList.size()>0) {
//						apkInfoList = new ArrayList<ApkInfoVO>();
//						//国家过滤
//						for(ApkInfoVO ai : apkInfoVOList){
//							boolean isInZone = false;
//							if (null != ai.getExtensionContry()) {
//								String[] cous = ai.getExtensionContry().split(",");
//								for (String co : cous) {
//									if (!"".equals(co) && Integer.parseInt(co) == countryId) {
//										isInZone = true;
//										break;
//									}
//								}
//							}
//							if (!isInZone) {
//								logger.info(String.format("this apk %s is not in Country %s.", ai.getApkId(), countryId));
//								continue;
//							}
//							apkInfoList.add(ai);
//						}
//					}
//					lastExecuteTime = nowTime;
//				}
				if((nowTime-lastExecuteTime)>executeStep) {
					List<ApkInfoVO> apkInfoVOList = apkInfoDao.getydApkInfoVOList();
					if(apkInfoVOList!=null&&apkInfoVOList.size()>0) {
						apkInfoList = new ArrayList<ApkInfoVO>();
						apkInfoList.addAll(apkInfoVOList);
					}
					lastExecuteTime = nowTime;
				}
				DisRespVO respVO = new DisRespVO();
				respVO.setStatus(Constants.statusMap.get(projectVO.getStatus()));
				respVO.setIsDown(Constants.statusMap.get(projectVO.getIsDown()));
				respVO.setIsGuide(Constants.statusMap.get(projectVO.getIsGuide()));
				respVO.setGuideInterval(projectVO.getGuideInterval());
				respVO.setGuideTimes(projectVO.getGuideTimes());
				if(apkInfoList!=null) {
					respVO.setApks(apkInfoList);
				}
				return respVO;
			}
		}
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public ApkInfoDao getApkInfoDao() {
		return apkInfoDao;
	}

	public void setApkInfoDao(ApkInfoDao apkInfoDao) {
		this.apkInfoDao = apkInfoDao;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
}
