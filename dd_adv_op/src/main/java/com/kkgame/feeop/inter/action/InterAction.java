package com.kkgame.feeop.inter.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.ddl.bean.DdlProductVO;
import com.kkgame.feeop.ddl.bean.DdlProjectVO;
import com.kkgame.feeop.ddl.service.DdlChannelService;
import com.kkgame.feeop.ddl.service.DdlProductService;
import com.kkgame.feeop.ddl.service.DdlProjectService;
import com.kkgame.feeop.inter.bean.InterVO;
import com.kkgame.feeop.inter.service.InterService;
import com.kkgame.feeop.sdkinfo.bean.SdkProjectVO;
import com.kkgame.feeop.sdkinfo.service.SdkProjectService;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class InterAction extends BaseAction {

	private InterService interService;
	
	private DdlProductService ddlProductService;
	
	private DdlProjectService ddlProjectService;
	
	private DdlChannelService ddlChannelService;
	
	private SdkProjectService sdkProjectService;
	
	private InterVO interVO;
	
	private int id;
	
	private String name;
	
	private int coo_id;
	
	private int cid;
	
	private int pid;
	
	public int getCoo_id() {
		return coo_id;
	}

	public void setCoo_id(int coo_id) {
		this.coo_id = coo_id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String doData() {
		if(interVO==null) {
			printMessage("error");
			return null;
		}
		try {
			interService.create(interVO);
		} catch (Exception e) {
			printMessage("error");
			return null;
		}
		printMessage("ok");
		return null;
	}
	
	public String doSaveSdkProject() {
		if(coo_id==0||name==null||"".equals(name)) {
			printMessage("error");
			return null;
		}
		try {
			SdkProjectVO sdkProjectVO = new SdkProjectVO();
			sdkProjectVO.setCoo_id(coo_id);
			sdkProjectVO.setApkName(name);
			sdkProjectVO.setMember(1);
			sdkProjectVO.setVersion(1);
			sdkProjectVO.setApkType(200200);
			sdkProjectVO.setAdvType(100200);
			sdkProjectVO.setNote("");
			sdkProjectVO.setKlbl(0.2d);
			sdkProjectVO.setHtdownload("");
			sdkProjectVO.setXuhao(1);
			sdkProjectVO.setStauts(3200);
			sdkProjectVO.setPass(3200);
			sdkProjectVO.setExe(3201);
			sdkProjectVO.setIsOpen(3201);
			sdkProjectVO.setChangeState(3201);
			sdkProjectVO.setDeletes(3201);
			sdkProjectVO.setIssyndata(3201);
			sdkProjectVO.setIsopen100(3201);
			sdkProjectVO.setIsfull100(3201);
			sdkProjectVO.setFulls(50);
			sdkProjectVO.setIssale(3201);
			sdkProjectVO.setSaleurl("http://104.250.130.218:8085/ddlserver/kok/ac?coo_id="+coo_id);
			sdkProjectVO.setDalyTime(0);
			sdkProjectVO.setDay(0);
			sdkProjectVO.setCreator(1);
			sdkProjectService.insert(sdkProjectVO);
//			SdkProjectVO sp = new SdkProjectVO();
//			sp.setVersion(1);
//			sp.setStatus(3200);
//			sp.setMember(1);
//			sp.setCreator(1);
//			sp.setAdvType(100200);
//			sp.setApk(apk);
//			sp.setTimss(20);
//			sp.setLower(20);
//			sp.setYdownload(3201);
//			sp.setType(600400);
//			sp.setPass(3200);
//			sp.setPassdate(CalendarFormat.getCurrentDate());
//			sp.setPassnote("test");
//			sp.setIsNotice(3200);
//			sp.setIsPops(3201);
//			sp.setIsReturnDebug(10800300);
//			sp.setIsGame(5);
//			sp.setCountry("");
//			sp.setIsTablePlaque(3200);
//			sp.setIsTablePlaqueDown(3200);
//			sp.setIsCjTablePlaque(10800401);
//			sp.setIsCjPush(3201);
//			sp.setTablePlaqueLower(20);
//			sp.setTablePlaqueTimss(10);
//			sp.setIsOpen(3200);
//			sdkProjectService.insertAdv(sp);
		} catch (Exception e) {
			printMessage("error");
			return null;
		}
		printMessage("ok");
		return null;
	}
	
	public String doSaveCustomer() {
		if(id==0||name==null||"".equals(name)) {
			printMessage("error");
			return null;
		}
		try {
			DdlChannelVO ddlChannelVO = new DdlChannelVO();
			ddlChannelVO.setId(id);
			ddlChannelVO.setName(name);
			ddlChannelVO.setVersion(1);
			ddlChannelVO.setStatus(3200);
			ddlChannelService.insert(ddlChannelVO);
		} catch (Exception e) {
			printMessage("error");
			return null;
		}
		printMessage("ok");
		return null;
	}
	
	public String doSaveProject() {
		if(coo_id==0||name==null||"".equals(name)||cid==0||pid==0) {
			printMessage("error");
			return null;
		}
		try {
			DdlProductVO ddlProductVO = new DdlProductVO();
			ddlProductVO.setId(pid);
			ddlProductVO.setName(name);
			ddlProductVO.setPrice(0.1);
			ddlProductVO.setCategory("200200");
			ddlProductVO.setStatus(3201);
			ddlProductVO.setCallbackUrl("");
			ddlProductVO.setCountry("");
			ddlProductService.insert(ddlProductVO);

			
			DdlProjectVO ddlProjectVO = new DdlProjectVO();
			ddlProjectVO.setCoo_id(coo_id);
			ddlProjectVO.setChannelId(cid);
			ddlProjectVO.setProductId(pid);
			ddlProjectVO.setVersion(1);
			ddlProjectVO.setStatus(3200);
			ddlProjectVO.setCountSale(20);
			ddlProjectVO.setIsAllSale(3201);
			ddlProjectVO.setUaTypes("200201");
			ddlProjectVO.setRate(0.2f);
			ddlProjectVO.setClickUrl("http://ad.kokmedia.com/kok/click?kk_pid="+coo_id+"&kk_clkid=");
			ddlProjectVO.setSaleUrl("");
			ddlProjectVO.setRedirectUrl("http://kokddl.b0.upaiyun.com/upload/ddl/"+coo_id+".apk");
			ddlProjectService.insert(ddlProjectVO);
			
		} catch (Exception e) {
			printMessage("error");
			return null;
		}
		printMessage("ok");
		return null;
	}

	public InterService getInterService() {
		return interService;
	}

	public void setInterService(InterService interService) {
		this.interService = interService;
	}

	public InterVO getInterVO() {
		return interVO;
	}

	public void setInterVO(InterVO interVO) {
		this.interVO = interVO;
	}

	public DdlProductService getDdlProductService() {
		return ddlProductService;
	}

	public void setDdlProductService(DdlProductService ddlProductService) {
		this.ddlProductService = ddlProductService;
	}

	public DdlProjectService getDdlProjectService() {
		return ddlProjectService;
	}

	public void setDdlProjectService(DdlProjectService ddlProjectService) {
		this.ddlProjectService = ddlProjectService;
	}

	public DdlChannelService getDdlChannelService() {
		return ddlChannelService;
	}

	public void setDdlChannelService(DdlChannelService ddlChannelService) {
		this.ddlChannelService = ddlChannelService;
	}

	public SdkProjectService getSdkProjectService() {
		return sdkProjectService;
	}

	public void setSdkProjectService(SdkProjectService sdkProjectService) {
		this.sdkProjectService = sdkProjectService;
	}
}
