package com.kkgame.feeop.detail.service;

import com.kkgame.feeop.detail.bean.IframeDetailVO;
import com.kkgame.feeop.detail.bean.IframeVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface IframeDetailService {

	List<IframeDetailVO> getIframeDetailVOList(IframeDetailVO iframeDetailVO) throws DatabaseException;

    List<IframeVO> getIframeDataVOList(IframeDetailVO iframeDataVO);
}
