package com.kkgame.feeop.detail.dao;

import com.kkgame.feeop.detail.bean.IframeDetailVO;
import com.kkgame.feeop.detail.bean.IframeVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface IframeDetailDAO {

	List<IframeDetailVO> getIframeDetailVOList(IframeDetailVO iframeDetailVO) throws DatabaseException;

    List<IframeVO> getIframeDataVOList(IframeDetailVO iframeDataVO);
}
