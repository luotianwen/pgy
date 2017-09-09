	package com.kkgame.feeop.adver.dao.impl;

	import com.kkgame.feeop.adver.bean.SpromotionVO;
    import com.kkgame.feeop.adver.dao.SpromotionDAO;
	import com.kkgame.feeop.util.DatabaseException;
	import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

	import java.util.List;

	public class SpromotionDAOImpl extends SqlMapClientDaoSupport implements
            SpromotionDAO { 
    
        //新增
        public void create(SpromotionVO spromotionVO) throws DatabaseException {
            int id = (Integer)getSqlMapClientTemplate().insert("spromotionSqlMap.create", spromotionVO);
        }
         
        
        public SpromotionVO getSpromotionVO(SpromotionVO spromotionVO) throws DatabaseException {
            return (SpromotionVO) getSqlMapClientTemplate().queryForObject("spromotionSqlMap.getSpromotionVO", spromotionVO);
        }
    
    
    
        public List<SpromotionVO> getSpromotionVOList(SpromotionVO spromotionVO)
                throws DatabaseException {
            int count = (Integer) getSqlMapClientTemplate().queryForObject("spromotionSqlMap.getSpromotionVOListCount", spromotionVO);
            spromotionVO.getPage().setTotalRow(count);
            return getSqlMapClientTemplate().queryForList("spromotionSqlMap.getSpromotionVOList", spromotionVO);
        }
        
        
        //修改
        public void update(SpromotionVO spromotionVO) throws DatabaseException {
            getSqlMapClientTemplate().update("spromotionSqlMap.update", spromotionVO);
        }
        
     
    
         
         
    
    }
