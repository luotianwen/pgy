package com.kkgame.feeop.inter.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.inter.bean.InterVO;
import com.kkgame.feeop.inter.dao.InterDAO;
import com.kkgame.feeop.util.DatabaseException;

public class InterDAOImpl extends SqlMapClientDaoSupport implements InterDAO {

	@Override
	public void create(InterVO interVO) throws DatabaseException {

		if(interVO.getType()==1) {
			//代理
			getSqlMapClientTemplate().insert("interSqlMap.insertAgent", interVO);
		} else if(interVO.getType()==2) {
			//商务
			getSqlMapClientTemplate().insert("interSqlMap.insertBd", interVO);
		} else if(interVO.getType()==3) {
			//客户
			getSqlMapClientTemplate().insert("interSqlMap.insertCustomer", interVO);
		} else if(interVO.getType()==4) {
			//项目
			getSqlMapClientTemplate().insert("interSqlMap.insertProject", interVO);
		} else if(interVO.getType()==5) {
			//合作者平台产品
			getSqlMapClientTemplate().insert("interSqlMap.insertProduct", interVO);
		} else if(interVO.getType()==6) {
			//开发者平台产品
			getSqlMapClientTemplate().insert("interSqlMap.insertDevProduct", interVO);
		}
	}
}
