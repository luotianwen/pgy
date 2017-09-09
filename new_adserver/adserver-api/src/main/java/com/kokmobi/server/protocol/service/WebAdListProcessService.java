package com.kokmobi.server.protocol.service;

import com.kokmobi.server.bean.*;

import java.util.Map;

public interface WebAdListProcessService {

	WebProjectResp process(WebProjectReq req);

	String processSdkUrl(WebProjectReq reqInfo);

	String promotionUrl(String ddid);

	void processOnline(GetOnlineReq reqInfo);

	GetOnlineAdResp processOnlineWeb(WebProjectReq reqInfo);

	OfflineSdkResp processOfflineSdk(Map<String,String> map);

	OfferSdkResp processOfferSdk(GetAdListReq reqInfo);

	void processOnlinesdk(GetOnlineReq reqInfo);

	void offerSdkActive(ActiveInfo activeInfo);

	String promotionIframeUrl(String ddid, String fromAddr);
}
