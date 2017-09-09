package com.kkgame.hz.tag;

import java.util.List;
import java.util.Map;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.NameVO;
import com.kkgame.hz.entities.ProductVO;

public class TagServiceImpl implements TagService {
	private TagDAO tagDAO;

	public TagDAO getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	public List<ProductVO> getProductList() throws DatabaseException {
		return tagDAO.getProductList();
	}
	
	public Map getProvinceMap() throws DatabaseException {
		return tagDAO.getProvinceMap();
	}

	public List getOperatorList() throws DatabaseException {
		return tagDAO.getOperatorList();
	}

	public Map getCityMap() throws DatabaseException {
		return tagDAO.getCityMap();
	}

	public List getCityListByProvinceId(int provincedId)
			throws DatabaseException {
		return tagDAO.getCityListByProvinceId(provincedId);
	}

	public List getCityList() throws DatabaseException {
		return tagDAO.getCityList();
	}

	public List getProvinceList() throws DatabaseException {
		return tagDAO.getProvinceList();
	}

	public List<NameVO> getAgentList() throws DatabaseException {
		return tagDAO.getAgentList();
	}

	public List<NameVO> getBdList() throws DatabaseException {
		return tagDAO.getBdList();
	}

	public List<NameVO> getBhList() throws DatabaseException {
		return tagDAO.getBhList();
	}

	public List<NameVO> getPortalList() throws DatabaseException {
		return tagDAO.getPortalList();
	}

	public List<NameVO> getLcdList() throws DatabaseException {
		return tagDAO.getLcdList();
	}


	public Map<Integer, String> getBdMap() throws DatabaseException {
		return tagDAO.getBdMap();
	}

	public Map<Integer, String> getAgentMap() throws DatabaseException {
		return tagDAO.getAgentMap();
	}

	public Map<Integer, String> getBhMap() throws DatabaseException {
		return tagDAO.getBhMap();
	}

	public List<NameVO> getCustomerListByBdId(int bdId)
			throws DatabaseException {
		return tagDAO.getCustomerListByBdId(bdId);
	}

	public List<NameVO> getCustomerListByAgentId(int agentId)
			throws DatabaseException {
		return tagDAO.getCustomerListByAgentId(agentId);
	}

	public List<NameVO> getProjectListByCustomerId(int customerId)
			throws DatabaseException {
		return tagDAO.getProjectListByCustomerId(customerId);
	}

	public List<NameVO> getBdListByAgentId(int agentId)
			throws DatabaseException {
		return tagDAO.getBdListByAgentId(agentId);
	}
	
	public List<NameVO> getBhListByBdId(int bdId) throws DatabaseException {
		return tagDAO.getBhListByBdId(bdId);
	}

	public List<NameVO> getBhListByAgentId(int agentId)
			throws DatabaseException {
		return tagDAO.getBhListByAgentId(agentId);
	}

	public List<NameVO> getProjectListByBhId(int bhId) throws DatabaseException {
		return tagDAO.getProjectListByBhId(bhId);
	}

	public List<CityVO> getCityAndProvinceName(List key) {
		return tagDAO.getCityAndProvinceName(key);
	}

	public List<NameVO> getTcList() throws DatabaseException {
		return tagDAO.getTcList();
	}

	public List<NameVO> getTcExitList() throws DatabaseException {
		return tagDAO.getTcExitList();
	}
}
