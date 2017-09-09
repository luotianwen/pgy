package com.kkgame.pay.stat.dao;

import java.util.List;

import com.kkgame.pay.stat.bean.DdlData;
import com.kkgame.pay.stat.bean.NameVO;
import com.kkgame.pay.stat.util.DatabaseException;

public interface DdlStatisticsDao {

	void insertSaleData(int i, List<DdlData> list) throws DatabaseException;

	void deleteClickData(int i) throws DatabaseException;

	void insertClickData(int i, List<DdlData> list) throws DatabaseException;

	void insertValidClickData(int i, List<DdlData> list) throws DatabaseException;

	void deleteSaleData(int i) throws DatabaseException;

	List<DdlData> getTotalClickList(int i) throws DatabaseException;

	void insertTotalClickData(int i, List<DdlData> list) throws DatabaseException;

	void insertSendSaleData(int i, List<DdlData> list) throws DatabaseException;

	void insertChannelList(List<NameVO> channelList) throws DatabaseException;

	void insertProductList(List<NameVO> productList) throws DatabaseException;
	List<DdlData> getSendData(int i) throws DatabaseException;

	void deleteMonthSaleData(int i) throws DatabaseException;

	List<DdlData> getMonthSaleData(int i) throws DatabaseException;

	void insertMonthSaleData(int i, List<DdlData> list) throws DatabaseException;

	List<DdlData> getTotalSaleData(int i) throws DatabaseException;

}
