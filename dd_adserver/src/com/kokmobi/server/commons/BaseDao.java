package com.kokmobi.server.commons;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public abstract class BaseDao extends SqlMapClientDaoSupport {
	
	@SuppressWarnings("unchecked")
	public Object getLastInsertId(Class clazz) {
		String checkSqlId = clazz.getSimpleName() + ".getLastInsertId";
		return super.getSqlMapClientTemplate().queryForObject(checkSqlId);
	}
}
