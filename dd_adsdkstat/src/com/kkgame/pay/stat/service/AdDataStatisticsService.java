package com.kkgame.pay.stat.service;

import com.kkgame.pay.stat.util.DatabaseException;

public interface AdDataStatisticsService {

	void statData(int i,int type) throws DatabaseException;
	void statSendData(int i,int type) throws DatabaseException;
	void updateDaySendData(int i) throws DatabaseException;
	void statProjectData(int i,int type) throws DatabaseException;
	void statVersionProjectData(int i, int type) throws DatabaseException;
	void statLinkData(int i, int type) throws DatabaseException;
	void statAdLinkData(int i) throws DatabaseException;
	void statAdLinkDataUV(int i)throws DatabaseException;
	void insertLinkPv(int i) throws DatabaseException ;

    void statDSData(int i, int type)throws DatabaseException ;

	void statDSData(int i)throws DatabaseException ;

	void insertDsPv(int i)throws DatabaseException ;

	void statSubAdData(int i);

	void statTotalSubAdData(int i);

	void statOfferShowDate(int i) throws DatabaseException;

	void offerSdkSendData(int i) throws DatabaseException;

	void offerSdkActiveData(int i);
}
