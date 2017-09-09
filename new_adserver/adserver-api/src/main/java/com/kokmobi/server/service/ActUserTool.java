package com.kokmobi.server.service;

import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.AdUsersProcessResp;
import com.kokmobi.server.protocol.service.AdUsersProcessService;


public class ActUserTool extends Thread {
	private AdUsersProcessService userService; 
	private SdkService sdkService;
	private AreaService areaService;
	private AdUserInfo userInfo;
	
	public ActUserTool(AdUsersProcessService userService, 
			SdkService sdkService, 
			AreaService areaService,
			AdUserInfo userInfo){
		this.userService = userService;
		this.sdkService = sdkService;
		this.areaService = areaService;
		this.userInfo = userInfo;
				
	}

	public ActUserTool(AdUsersProcessService userService, AdUserInfo userInfo){
		this.userService = userService;
		this.userInfo = userInfo;
	}
	
	public void run() {
		try{
//			AdUsersProcessResp respData = userService.process(sdkService, areaService, userInfo);
			AdUsersProcessResp respData = userService.process(userInfo);
			if(respData == null) {
				System.out.println(String.format("add act userInfo backend...error:%s", userInfo.getImei()));
			}
		}
		catch(Exception e) {
			System.out.println(String.format("add act userInfo backend...error:%s", userInfo.getImei()));
		}
	}
}
