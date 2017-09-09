package com.kkgame.feeop.tag.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.tag.bean.AdVO;
import com.kkgame.feeop.tag.bean.CountryVO;
import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.ddl.bean.DdlProductVO;
import com.kkgame.feeop.ddl.bean.DdlProjectVO;
import com.kkgame.feeop.record.bean.OperatorVO;
import com.kkgame.feeop.tag.bean.ProvinceVO;
import com.kkgame.feeop.tag.bean.VersionVO;
import com.kkgame.feeop.tag.dao.SystemDAO;
import com.kkgame.feeop.util.DatabaseException;

public class SystemDAOImpl extends SqlMapClientDaoSupport implements SystemDAO {
	
	public String getConfigureByKey(String key) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"productSqlMap.getConfigureByKey", key);
	}
	
	@Override
	public List<CountryVO> getCountryList() throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getCountryList");
	}
	
	public List<ProvinceVO> getProvinceList() throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getProvinceList");
	}
	
	@Override
	public List<OperatorVO> getOperatorList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("systemSqlMap.getOperatorList");
	}
	
	@Override
	public List<DdlProjectVO> getDdlProjectList() throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getDdlProjectList");
	}
	
	@Override
	public ProvinceVO getProvince(int id) throws DatabaseException {

		return (ProvinceVO)getSqlMapClientTemplate().queryForObject("systemSqlMap.getProvince", id);
	}
	
	
	
	@Override
	public List<AdVO> getAdList() throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getAdList");
	}
	
	@Override
	public List<DdlChannelVO> getDdlChannelList() throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getDdlChannelList");
	}
	
	@Override
	public List<DdlProductVO> getDdlProductList() throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getDdlProductList");
	}
	
	@Override
	public List<ProductVO> getProductList() throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getProductList");
	}
	
	@Override
	public List<VersionVO> getVersionList() throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getVersionList");
	}

	@Override
	public List<CountryVO> getPlatformList() {
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getPlatformList");
	}

	@Override
	public List<HashMap<String, String>> getEnuList(int i) {
		return getSqlMapClientTemplate().queryForList("systemSqlMap.getEnuList",i);
	}
}
