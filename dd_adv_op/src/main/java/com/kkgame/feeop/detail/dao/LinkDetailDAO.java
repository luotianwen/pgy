package com.kkgame.feeop.detail.dao;

import com.kkgame.feeop.detail.bean.LinkDetailVO;
import com.kkgame.feeop.detail.bean.SubDetailVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface LinkDetailDAO {

	List<LinkDetailVO> getLinkDetailVOList(LinkDetailVO linkDetailVO) throws DatabaseException;
}
