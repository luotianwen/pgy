package com.kkgame.hz.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.NameVO;
import com.kkgame.hz.entities.ProductVO;

public class TagDAOImpl extends SqlMapClientDaoSupport implements TagDAO {

	public Map getProvinceMap() throws DatabaseException {
		return getSqlMapClientTemplate().queryForMap(
				"tagSqlMap.getProvinceMap", "", "id", "name");
	}

	public List getOperatorList() throws DatabaseException {
		return getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getOperatorList", "");
	}

	public Map getCityMap() throws DatabaseException {
		return getSqlMapClientTemplate().queryForMap("tagSqlMap.getCityMap",
				"", "id");
	}

	public List getCityListByProvinceId(int provinceId)
			throws DatabaseException {

		return getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getCityListByProvinceId", provinceId);
	}

	public List getCityList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("tagSqlMap.getCityList",
				"");
	}

	public List getProvinceList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getProvinceList", "");
	}

	public List<NameVO> getAgentList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("tagSqlMap.getAgentList");
	}

	public List<NameVO> getBdList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("tagSqlMap.getBdList");
	}

	public List<NameVO> getBhList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("tagSqlMap.getBhList");
	}

	public List<NameVO> getPortalList() throws DatabaseException {

		return getSqlMapClientTemplate()
				.queryForList("tagSqlMap.getPortalList");
	}

	public List<NameVO> getLcdList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("tagSqlMap.getLcdList");
	}

	public Map<Integer, String> getBdMap() throws DatabaseException {

		return getSqlMapClientTemplate().queryForMap("tagSqlMap.getBdMap", "",
				"id", "name");
	}

	public Map<Integer, String> getAgentMap() throws DatabaseException {

		return getSqlMapClientTemplate().queryForMap("tagSqlMap.getAgentMap",
				"", "id", "name");
	}

	public Map<Integer, String> getBhMap() throws DatabaseException {

		return getSqlMapClientTemplate().queryForMap("tagSqlMap.getBhMap", "",
				"id", "name");
	}

	/**
	 * 通过商务人员Id查询客户
	 * 
	 * @author lotteLiu
	 * @param bdId
	 * @return
	 * @throws DatabaseException
	 */
	public List<NameVO> getCustomerListByBdId(int bdId)
			throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getCustomerListByBdId", bdId);
	}

	public List<NameVO> getCustomerListByAgentId(int agentId)
			throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getCustomerListByAgentId", agentId);
	}

	/**
	 * 根据客户Id查询客户的项目
	 * 
	 * @author lotteLiu
	 * @param customerId
	 * @return
	 * @throws DatabaseException
	 */
	public List<NameVO> getProjectListByCustomerId(int customerId)
			throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getProjectListByCustomerId", customerId);
	}

	/**
	 * 根据代理商Id查询商务人员信息
	 * 
	 * @author lotteLiu
	 * @param agentId
	 * @return
	 * @throws DatabaseException
	 */
	public List<NameVO> getBdListByAgentId(int agentId)
			throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getBdListByAgentId", agentId);
	}

	public List<NameVO> getBhListByBdId(int bdId) throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getBhListByBdId", bdId);
	}

	public List<NameVO> getBhListByAgentId(int agentId)
			throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getBhListByAgentId", agentId);
	}

	/**
	 * 根据中间人Id查询中间人的项目信息
	 * 
	 * @author lotteLiu
	 * @param bhId
	 * @return
	 * @throws DatabaseException
	 */
	public List<NameVO> getProjectListByBhId(int bhId) throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getProjectListByBhId", bhId);
	}

	/**
	 * 查询省份和城市名
	 * 
	 */
	public List<CityVO> getCityAndProvinceName(List key) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", key);
		return getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getCityAndProvinceName", map);
	}

	public List<NameVO> getTcList() throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getTcList");
	}

	public List<NameVO> getTcExitList() throws DatabaseException {
		return (List<NameVO>) getSqlMapClientTemplate().queryForList(
				"tagSqlMap.getTcExitList");
	}

	public List<ProductVO> getProductList() throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("productSqlMap.getAllProductVOList");
	}
}
