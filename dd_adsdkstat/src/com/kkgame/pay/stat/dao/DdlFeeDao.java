package com.kkgame.pay.stat.dao;

import java.util.List;

import com.kkgame.pay.stat.bean.DdlData;
import com.kkgame.pay.stat.bean.NameVO;
import com.kkgame.pay.stat.util.DatabaseException;
public interface DdlFeeDao {

	List<DdlData> getSaleDataList(int i) throws DatabaseException;
	List<DdlData> getClickDataList(int i,int j) throws DatabaseException;
	List<DdlData> getValidClickDataList(int i, int j) throws DatabaseException;
	List<DdlData> getSendSaleDataList(int i) throws DatabaseException;
	List<NameVO> getProductList() throws DatabaseException;
	List<NameVO> getChannelList() throws DatabaseException;
	void updateTotalSaleData(DdlData data) throws DatabaseException;

}
