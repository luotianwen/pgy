package com.kokmobi.server.protocol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.ApkAdRespInfo;
import com.kokmobi.server.bean.GetAdListFeebackReq;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.GetAdListFeebackService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.RedisTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetAdListFeebackServiceImpl implements GetAdListFeebackService {
	private static Log logger = LogFactory.getLog(GetAdListFeebackServiceImpl.class);
	
	@Override
	public GetAdListFeebackReq parseRequest(HttpServletRequest req) throws Exception {
		
		String imei = ProtocolUtil.getReqPara(req, "imei");
		String pkgId = ProtocolUtil.getReqPara(req, "pkgid");

		GetAdListFeebackReq reqInfo = new GetAdListFeebackReq();
//		String imei = ProtocolUtil.getReqPara(req, "imei");
//		String pkgId = ProtocolUtil.getReqPara(req, "pkgid");
		reqInfo.setImei(imei);
		reqInfo.setPkgId(pkgId);
		return reqInfo;
	}

	@Override
	public void process(GetAdListFeebackReq req) {
		//找到pkgid对应的明细数据，取出来，更改其接受状态，再扔回redis保存队列
		if(ProtocolUtil.isNullOrEmpty(req.getImei()) || ProtocolUtil.isNullOrEmpty(req.getPkgId())) {
			logger.error(String.format("sent list feeback error:imei-%s, pkgId-%s",req.getImei(), req.getPkgId()));
			return;
		}
		String lkey = String.format(Constants.KEY_AD_SENT_PACKAGE, req.getPkgId());
		String list = RedisTool.get(lkey);
		if(ProtocolUtil.isNullOrEmpty(list)) {
			logger.info(String.format("sent list feeback dealed:imei-%s, pkgId-%s",req.getImei(), req.getPkgId()));
			return;
		}
		try {
			JSONArray jsarr = JSONArray.fromObject(list);
			List<ApkAdRespInfo> dataList = JSONArray.toList(jsarr, ApkAdRespInfo.class);
			if(dataList != null) {
				List<String> toList = new ArrayList<String>();
				for(ApkAdRespInfo adinfo:dataList) {
					adinfo.setPkgstatus(Constants.STATUS_YES);
					JSONObject jsobj = JSONObject.fromObject(adinfo);				
					toList.add(jsobj.toString());					
					logger.info(String.format("push log to redis:%s.",jsobj.toString()));
				}
				RedisTool.lpush(Constants.KEY_AD_SENTBACK_TOSAVE, toList);
				RedisTool.del(lkey);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
