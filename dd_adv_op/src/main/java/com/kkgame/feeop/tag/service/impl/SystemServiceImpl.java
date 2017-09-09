package com.kkgame.feeop.tag.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

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
import com.kkgame.feeop.tag.service.SystemService;
import com.kkgame.feeop.util.DatabaseException;

public class SystemServiceImpl implements SystemService {

	private SystemDAO systemDAO;
	
	private SystemDAO systemServerDAO;
	
	public SystemDAO getSystemDAO() {
		return systemDAO;
	}

	public void setSystemDAO(SystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}

	public String getConfigureByKey(String key)throws DataAccessException{
		
		return systemDAO.getConfigureByKey(key);
	}

	public List<ProvinceVO> getProvinceList() throws DatabaseException {
		return systemDAO.getProvinceList();
	}
	
	@Override
	public ProvinceVO getProvince(int id) throws DatabaseException {
		// TODO Auto-generated method stub
		return systemDAO.getProvince(id);
	}
	
	@Override
	public List<VersionVO> getVersionList() throws DatabaseException {
		// TODO Auto-generated method stub
		return systemDAO.getVersionList();
	}

	@Override
	public List<HashMap<String, String>> getEnuList(int i) throws DatabaseException {
		return systemDAO.getEnuList(i);
	}

	@Override
	public List<CountryVO> getPlatformList() {
		return systemDAO.getPlatformList();
	}

	@Override
	public List<CountryVO> getCountryList() throws DatabaseException {
		// TODO Auto-generated method stub
		return systemDAO.getCountryList();
	}
	
	@Override
	public List<OperatorVO> getOperatorList() throws DatabaseException {

		return systemDAO.getOperatorList();
	}
	
	@Override
	public List<DdlProjectVO> getDdlProjectList() throws DatabaseException {
		// TODO Auto-generated method stub
		return systemDAO.getDdlProjectList();
	}
	
	@Override
	public List<AdVO> getAdList() throws DatabaseException {
		// TODO Auto-generated method stub
		return systemDAO.getAdList();
	}
	
	@Override
	public List<DdlChannelVO> getDdlChannelList() throws DatabaseException {
		// TODO Auto-generated method stub
		return systemDAO.getDdlChannelList();
	}
	
	@Override
	public List<DdlProductVO> getDdlProductList() throws DatabaseException {
		// TODO Auto-generated method stub
		return systemDAO.getDdlProductList();
	}
	
	@Override
	public List<ProductVO> getProductList() throws DatabaseException {
		// TODO Auto-generated method stub
		return systemDAO.getProductList();
	}
	
	public SystemDAO getSystemServerDAO() {
		return systemServerDAO;
	}

	public void setSystemServerDAO(SystemDAO systemServerDAO) {
		this.systemServerDAO = systemServerDAO;
	}
}
