package com.kkgame.pay.stat.service;

import java.util.List;

import com.kkgame.pay.stat.util.DatabaseException;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.InstallData;

public interface UserDataStatisticsService {


	void statUserData(int i,int type) throws DatabaseException;
	void statUserActiveData(int i,int type) throws DatabaseException;
	void statUserProjectActiveData(int i, int type) throws DatabaseException;
	void statTotalUserActiveData(int i,int type) throws DatabaseException;
	void statVersionUserData(int i, int type) throws DatabaseException;
	void statVersionUserProjectActiveData(int i, int type) throws DatabaseException;
	void statTotalVersionUserActiveData(int i,int type) throws DatabaseException;
	void statVersionUserActiveData(int i, int type) throws DatabaseException;
	void statMonthUserData(int i) throws DatabaseException;
	void updateTotalUserData(int i) throws DatabaseException;
	void statProjectUserData(int i, int type) throws DatabaseException;
	void statVersionProjectUserData(int i, int type) throws DatabaseException;
}
