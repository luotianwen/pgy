	package com.kkgame.feeop.adver.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.PromotionDomainVO;
import com.kkgame.feeop.adver.dao.PromotionDomainDAO;
import com.kkgame.feeop.util.DatabaseException;

public class PromotionDomainDAOImpl extends SqlMapClientDaoSupport implements
		PromotionDomainDAO { 

	//新增
	public void create(PromotionDomainVO promotionDomainVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("promotionDomainSqlMap.create", promotionDomainVO);
	}
	 
	
	public PromotionDomainVO getPromotionDomainVO(PromotionDomainVO promotionDomainVO) throws DatabaseException {
		return (PromotionDomainVO) getSqlMapClientTemplate().queryForObject("promotionDomainSqlMap.getPromotionDomainVO", promotionDomainVO);
	}

	@Override
	public void delete(PromotionDomainVO promotionDomainVO) {
		getSqlMapClientTemplate().delete("promotionDomainSqlMap.delete", promotionDomainVO);
	}

	public List<PromotionDomainVO> getPromotionDomainVOList(PromotionDomainVO promotionDomainVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("promotionDomainSqlMap.getPromotionDomainVOListCount", promotionDomainVO);
		promotionDomainVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("promotionDomainSqlMap.getPromotionDomainVOList", promotionDomainVO);
	}
	
	
	//修改
	public void update(PromotionDomainVO promotionDomainVO) throws DatabaseException {
		getSqlMapClientTemplate().update("promotionDomainSqlMap.update", promotionDomainVO);
	}



	//新增
	public void createIframe(PromotionDomainVO promotionDomainVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("promotionDomainSqlMap.createIframe", promotionDomainVO);
	}


	public PromotionDomainVO getPromotionDomainIframeVO(PromotionDomainVO promotionDomainVO) throws DatabaseException {
		return (PromotionDomainVO) getSqlMapClientTemplate().queryForObject("promotionDomainSqlMap.getPromotionDomainIframeVO", promotionDomainVO);
	}

	@Override
	public void deleteIframe(PromotionDomainVO promotionDomainVO) {
		getSqlMapClientTemplate().delete("promotionDomainSqlMap.deleteIframe", promotionDomainVO);
	}

	public List<PromotionDomainVO> getPromotionDomainIframeVOList(PromotionDomainVO promotionDomainVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("promotionDomainSqlMap.getPromotionDomainIframeVOListCount", promotionDomainVO);
		promotionDomainVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("promotionDomainSqlMap.getPromotionDomainIframeVOList", promotionDomainVO);
	}


	//修改
	public void updateIframe(PromotionDomainVO promotionDomainVO) throws DatabaseException {
		getSqlMapClientTemplate().update("promotionDomainSqlMap.updateIframe", promotionDomainVO);
	}




}
