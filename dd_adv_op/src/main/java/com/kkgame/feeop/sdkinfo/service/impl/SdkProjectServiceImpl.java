package com.kkgame.feeop.sdkinfo.service.impl;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.SdkProjectVO;
import com.kkgame.feeop.sdkinfo.dao.SdkProjectDAO;
import com.kkgame.feeop.sdkinfo.service.SdkProjectService;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class SdkProjectServiceImpl implements SdkProjectService {

	private SdkProjectDAO sdkProjectDAO;

	public SdkProjectDAO getSdkProjectDAO() {
		return sdkProjectDAO;
	}

	public void setSdkProjectDAO(SdkProjectDAO sdkProjectDAO) {
		this.sdkProjectDAO = sdkProjectDAO;
	}

	@Override
	public List<SdkProjectVO> getSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkProjectDAO.getSdkProjectVOList(sdkProjectVO);
	}

	@Override
	public List<SdkProjectVO> getAllSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkProjectDAO.getAllSdkProjectVOList(sdkProjectVO);
	}

	@Override
	public SdkProjectVO getSdkProjectVO(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkProjectDAO.getSdkProjectVO(sdkProjectVO);
	}

	@Override
	public int insert(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer apk = (Integer) sdkProjectDAO.insert(sdkProjectVO);
		SdkProjectVO sp = new SdkProjectVO();
		sp.setVersion(1);
		sp.setStatus(3200);
		sp.setMember(1);
		sp.setCreator(1);
		sp.setAdvType(100200);
		sp.setApk(apk);
		sp.setTimss(20);
		sp.setLower(20);
		sp.setYdownload(3201);
		sp.setType(600400);
		sp.setPass(3200);
		sp.setPassdate(CalendarFormat.getCurrentDate());
		sp.setPassnote("test");
//				sp.setIsNotice(3200);
		sp.setIsNotice(3201);
		sp.setIsPops(3201);
		sp.setIsReturnDebug(10800300);
		sp.setIsGame(5);
		sp.setCountry("");
		sp.setIsTablePlaque(3200);
		sp.setIsTablePlaqueDown(3200);
//				sp.setIsCjTablePlaque(10800401);
		sp.setIsCjTablePlaque(10800400);
//				sp.setIsCjPush(3201);
		sp.setIsCjPush(3200);
		sp.setTablePlaqueLower(20);
		sp.setTablePlaqueTimss(10);
		sp.setIsOpen(3200);
		insertAdv(sp);
		return apk;
	}

	@Override
	public void update(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		sdkProjectDAO.update(sdkProjectVO);
	}

	@Override
	public List<SdkProjectVO> getAdvSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkProjectDAO.getAdvSdkProjectVOList(sdkProjectVO);
	}

	@Override
	public SdkProjectVO getAdvSdkProjectVO(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkProjectDAO.getAdvSdkProjectVO(sdkProjectVO);
	}

	@Override
	public int insertAdv(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkProjectDAO.insertAdv(sdkProjectVO);
	}

	@Override
	public void updateAdv(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		sdkProjectDAO.updateAdv(sdkProjectVO);
	}
}
