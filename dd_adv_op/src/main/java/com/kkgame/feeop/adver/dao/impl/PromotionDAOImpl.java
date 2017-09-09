	package com.kkgame.feeop.adver.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.PromotionVO;
import com.kkgame.feeop.adver.dao.PromotionDAO;
import com.kkgame.feeop.util.DatabaseException;

public class PromotionDAOImpl extends SqlMapClientDaoSupport implements
		PromotionDAO { 

	//新增
	public void create(PromotionVO promotionVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("promotionSqlMap.create", promotionVO);
	}
	 
	
	public PromotionVO getPromotionVO(PromotionVO promotionVO) throws DatabaseException {
		return (PromotionVO) getSqlMapClientTemplate().queryForObject("promotionSqlMap.getPromotionVO", promotionVO);
	}
	
	public List<PromotionVO> getPromotionVOList(PromotionVO promotionVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("promotionSqlMap.getPromotionVOListCount", promotionVO);
		promotionVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("promotionSqlMap.getPromotionVOList", promotionVO);
	}
	
	
	//修改
	public void update(PromotionVO promotionVO) throws DatabaseException {
		getSqlMapClientTemplate().update("promotionSqlMap.update", promotionVO);
	}





	//新增
	public int createIframe(PromotionVO promotionVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("promotionSqlMap.createIframe", promotionVO);
		return id;
	}


	public PromotionVO getPromotionIframeVO(PromotionVO promotionVO) throws DatabaseException {
		return (PromotionVO) getSqlMapClientTemplate().queryForObject("promotionSqlMap.getPromotionIframeVO", promotionVO);
	}

	public List<PromotionVO> getPromotionIframeVOList(PromotionVO promotionVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("promotionSqlMap.getPromotionIframeVOListCount", promotionVO);
		promotionVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("promotionSqlMap.getPromotionIframeVOList", promotionVO);
	}


	//修改
	public void updateIframe(PromotionVO promotionVO) throws DatabaseException {
		getSqlMapClientTemplate().update("promotionSqlMap.updateIframe", promotionVO);
	}


}
