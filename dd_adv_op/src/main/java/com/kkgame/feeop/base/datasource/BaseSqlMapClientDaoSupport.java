package com.kkgame.feeop.base.datasource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseSqlMapClientDaoSupport extends SqlMapClientDaoSupport
		implements ApplicationContextAware {

	protected ApplicationContext context;

	protected ApplicationContext getContext() {
		return context;
	}

	public void choseSqlClient(String name) {
		SqlMapClient client = (SqlMapClient) getContext().getBean(name);
		setSqlMapClientTemplate(new SqlMapClientTemplate(client));
		afterPropertiesSet();
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
}
