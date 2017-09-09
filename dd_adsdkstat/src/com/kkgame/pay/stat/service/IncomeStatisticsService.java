package com.kkgame.pay.stat.service;

import java.util.List;

import com.kkgame.pay.stat.util.DatabaseException;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.InstallData;

public interface IncomeStatisticsService {

	void syncTempData(int i) throws DatabaseException;
	void statAdData(int i) throws DatabaseException;
	void statAdBackData(int i) throws DatabaseException;
	void statProjectIncomeData(int i) throws DatabaseException;
	void updateAdCpm(int i) throws DatabaseException;
	void deleteTempData(int i) throws DatabaseException;
	void statFirstPageData(int i) throws DatabaseException;
}
