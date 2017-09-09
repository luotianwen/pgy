package com.kkgame.feeop.sdkinfo.dao.impl;

import com.kkgame.feeop.sdkinfo.bean.DesktopInfoVO;
import com.kkgame.feeop.sdkinfo.dao.DesktopInfoDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

public class DesktopInfoDAOImpl extends SqlMapClientDaoSupport implements DesktopInfoDAO {

	@Override
	public List<DesktopInfoVO> getAllDesktopInfoVOList(DesktopInfoVO desktopInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("desktopInfoSqlMap.getAllDesktopInfoVOList", desktopInfoVO);
	}
	
	@Override
	public DesktopInfoVO getDesktopInfoVO(DesktopInfoVO desktopInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (DesktopInfoVO) getSqlMapClientTemplate().queryForObject("desktopInfoSqlMap.getDesktopInfoVO", desktopInfoVO);
	}
	
	@Override
	public List<DesktopInfoVO> getDesktopInfoVOList(DesktopInfoVO desktopInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("desktopInfoSqlMap.getDesktopInfoVOListCount",desktopInfoVO);
		desktopInfoVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("desktopInfoSqlMap.getDesktopInfoVOList", desktopInfoVO);
	}
	
	@Override
	public void insert(DesktopInfoVO desktopInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("desktopInfoSqlMap.insert", desktopInfoVO);

	}
	
	@Override
	public void update(DesktopInfoVO desktopInfoVO) throws DatabaseException {

		getSqlMapClientTemplate().update("desktopInfoSqlMap.update", desktopInfoVO);
	}
	
	@Override
	public void delete(DesktopInfoVO desktopInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("desktopInfoSqlMap.delete", desktopInfoVO);

	}
}
