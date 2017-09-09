package com.kkgame.pay.stat.service;

import java.util.List;

import com.kkgame.pay.stat.util.DatabaseException;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.InstallData;

public interface UserRetentionStatisticsService {


	void statRetentionData(int i,int type) throws DatabaseException;

}
