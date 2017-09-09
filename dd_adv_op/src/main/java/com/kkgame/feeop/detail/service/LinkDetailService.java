package com.kkgame.feeop.detail.service;

import com.kkgame.feeop.detail.bean.LinkDetailVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface LinkDetailService {

	List<LinkDetailVO> getLinkDetailVOList(LinkDetailVO linkDetailVO) throws DatabaseException;

}
