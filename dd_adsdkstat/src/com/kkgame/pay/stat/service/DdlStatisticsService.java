package com.kkgame.pay.stat.service;

import java.util.List;

import com.kkgame.pay.stat.bean.DdlData;
import com.kkgame.pay.stat.util.DatabaseException;

public interface DdlStatisticsService {

	void statClickData(int i) throws DatabaseException;
	void statSaleData(int i) throws DatabaseException;
	void syncProductChannel(int i) throws DatabaseException;
	List<DdlData> getSendData(int i) throws DatabaseException;
	void statMonthSaleData(int i) throws DatabaseException;
	void updateTotalSaleData(int i) throws DatabaseException;
}
