package com.kkgame.feeop.sdkinfo.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.sdkinfo.bean.GuideInfoVO;
import com.kkgame.feeop.sdkinfo.dao.GuideInfoDAO;
import com.kkgame.feeop.util.DatabaseException;

public class GuideInfoDAOImpl extends SqlMapClientDaoSupport implements GuideInfoDAO {

	@Override
	public List<GuideInfoVO> getGuideInfoVOList(GuideInfoVO guideInfoVO) throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("guideInfoSqlMap.getGuideInfoVOListCount",guideInfoVO);
		guideInfoVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("guideInfoSqlMap.getGuideInfoVOList", guideInfoVO);
	}

	@Override
	public List<GuideInfoVO> getAllGuideInfoVOList(GuideInfoVO guideInfoVO) throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("guideInfoSqlMap.getAllGuideInfoVOList", guideInfoVO);
	}

	@Override
	public GuideInfoVO getGuideInfoVO(GuideInfoVO guideInfoVO) throws DatabaseException {
		return (GuideInfoVO) getSqlMapClientTemplate().queryForObject("guideInfoSqlMap.getGuideInfoVO", guideInfoVO);
	}

	@Override
	public void insert(GuideInfoVO guideInfoVO) throws DatabaseException {
		getSqlMapClientTemplate().insert("guideInfoSqlMap.insert", guideInfoVO);

	}

	@Override
	public void update(GuideInfoVO guideInfoVO) throws DatabaseException {
		getSqlMapClientTemplate().update("guideInfoSqlMap.update", guideInfoVO);
	}

	@Override
	public void delete(GuideInfoVO guideInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("guideInfoSqlMap.delete", guideInfoVO);

	}
	
}
