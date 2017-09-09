package com.kkgame.feeop.sdkinfo.service.impl;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.GuideInfoVO;
import com.kkgame.feeop.sdkinfo.dao.GuideInfoDAO;
import com.kkgame.feeop.sdkinfo.service.GuideInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class GuideInfoServiceImpl implements GuideInfoService {

	private GuideInfoDAO guideInfoDAO;

	public GuideInfoDAO getGuideInfoDAO() {
		return guideInfoDAO;
	}

	public void setGuideInfoDAO(GuideInfoDAO guideInfoDAO) {
		this.guideInfoDAO = guideInfoDAO;
	}

	@Override
	public List<GuideInfoVO> getGuideInfoVOList(GuideInfoVO guideInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return guideInfoDAO.getGuideInfoVOList(guideInfoVO);
	}

	@Override
	public List<GuideInfoVO> getAllGuideInfoVOList(GuideInfoVO guideInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return guideInfoDAO.getAllGuideInfoVOList(guideInfoVO);
	}

	@Override
	public GuideInfoVO getGuideInfoVO(GuideInfoVO guideInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return guideInfoDAO.getGuideInfoVO(guideInfoVO);
	}

	@Override
	public void insert(GuideInfoVO guideInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		guideInfoDAO.insert(guideInfoVO);
	}

	@Override
	public void update(GuideInfoVO guideInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		guideInfoDAO.update(guideInfoVO);
	}
	
	@Override
	public void delete(GuideInfoVO guideInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		guideInfoDAO.delete(guideInfoVO);
	}
}
