package com.kkgame.hz.tag;

import java.util.List;
import java.util.Map;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.NameVO;
import com.kkgame.hz.entities.ProductVO;

public interface TagDAO {
	Map getProvinceMap() throws DatabaseException;

	List getOperatorList() throws DatabaseException;

	Map getCityMap() throws DatabaseException;

	List getCityListByProvinceId(int provinceId) throws DatabaseException;

	List getCityList() throws DatabaseException;

	List getProvinceList() throws DatabaseException;

	List<NameVO> getAgentList() throws DatabaseException;

	List<NameVO> getBdList() throws DatabaseException;

	List<NameVO> getBhList() throws DatabaseException;

	List<NameVO> getPortalList() throws DatabaseException;

	List<NameVO> getLcdList() throws DatabaseException;

	Map<Integer, String> getBdMap() throws DatabaseException;

	Map<Integer, String> getAgentMap() throws DatabaseException;

	Map<Integer, String> getBhMap() throws DatabaseException;

	/**
	 * 通过商务人员Id查询客户(2:拓展中的；4：签约合作的)
	 * 
	 * @author lotteLiu
	 * @param bdId
	 * @return
	 * @throws DatabaseException
	 */
	List<NameVO> getCustomerListByBdId(int bdId) throws DatabaseException;

	List<NameVO> getCustomerListByAgentId(int agentId) throws DatabaseException;

	/**
	 * 根据客户Id查询客户的项目
	 * 
	 * @author lotteLiu
	 * @param customerId
	 * @return
	 * @throws DatabaseException
	 */
	List<NameVO> getProjectListByCustomerId(int customerId)
			throws DatabaseException;

	/**
	 * 根据代理商Id查询商务人员信息
	 * 
	 * @author lotteLiu
	 * @param agentId
	 * @return
	 * @throws DatabaseException
	 */
	List<NameVO> getBdListByAgentId(int agentId) throws DatabaseException;

	List<NameVO> getBhListByBdId(int bdId) throws DatabaseException;

	List<NameVO> getBhListByAgentId(int agentId) throws DatabaseException;

	/**
	 * 根据中间人Id查询中间人的项目信息
	 * 
	 * @author lotteLiu
	 * @param bhId
	 * @return
	 * @throws DatabaseException
	 */
	List<NameVO> getProjectListByBhId(int bhId) throws DatabaseException;

	List<CityVO> getCityAndProvinceName(List key);

	List<NameVO> getTcList() throws DatabaseException;

	List<NameVO> getTcExitList() throws DatabaseException;

	List<ProductVO> getProductList() throws DatabaseException;

}
