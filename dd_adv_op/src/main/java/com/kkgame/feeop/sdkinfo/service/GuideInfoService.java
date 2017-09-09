package com.kkgame.feeop.sdkinfo.service;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.GuideInfoVO;
import com.kkgame.feeop.util.DatabaseException;

public interface GuideInfoService {

	List<GuideInfoVO> getGuideInfoVOList(GuideInfoVO guideInfoVO) throws DatabaseException;
	List<GuideInfoVO> getAllGuideInfoVOList(GuideInfoVO guideInfoVO) throws DatabaseException;
	GuideInfoVO getGuideInfoVO(GuideInfoVO guideInfoVO)  throws DatabaseException;
	void insert(GuideInfoVO guideInfoVO) throws DatabaseException;
	void update(GuideInfoVO guideInfoVO) throws DatabaseException;
	void delete(GuideInfoVO guideInfoVO) throws DatabaseException;

}
