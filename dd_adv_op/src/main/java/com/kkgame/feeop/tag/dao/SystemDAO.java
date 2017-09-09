package com.kkgame.feeop.tag.dao;

import java.util.HashMap;
import java.util.List;

import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.tag.bean.AdVO;
import com.kkgame.feeop.tag.bean.CountryVO;
import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.ddl.bean.DdlProductVO;
import com.kkgame.feeop.ddl.bean.DdlProjectVO;
import com.kkgame.feeop.record.bean.OperatorVO;
import com.kkgame.feeop.tag.bean.ProvinceVO;
import com.kkgame.feeop.tag.bean.VersionVO;
import com.kkgame.feeop.util.DatabaseException;

public interface SystemDAO {

	/**
	 * 获取系统路径
	 * @param key
	 * @return
	 */
	public String getConfigureByKey(String key);

	public List<ProvinceVO> getProvinceList() throws DatabaseException;
	
	public List<OperatorVO> getOperatorList() throws DatabaseException;
	public ProvinceVO getProvince(int id) throws DatabaseException;
	public List<CountryVO> getCountryList() throws DatabaseException;
	public List<AdVO> getAdList() throws DatabaseException;
	public List<DdlChannelVO> getDdlChannelList() throws DatabaseException;
	public List<ProductVO> getProductList() throws DatabaseException;
	public List<DdlProjectVO> getDdlProjectList() throws DatabaseException;

	public List<DdlProductVO> getDdlProductList() throws DatabaseException;
	public List<VersionVO> getVersionList() throws DatabaseException;

	List<HashMap<String,String>> getEnuList(int i)throws DatabaseException;

	List<CountryVO> getPlatformList();
}
