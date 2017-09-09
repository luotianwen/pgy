package com.kkgame.pay.stat.service;

import com.kkgame.pay.stat.bean.AdDataVO;
import com.kkgame.pay.stat.bean.UserData;

public interface StatisticsService {
    void saveUser( );

	void saveActive( );

	void saveProjectActive( );

	void saveSent( );

	void saveSentSucc( );

	void saveShow( );

	void saveActivate( );

	void saveInstalled( );

	void saveDownload( );

	void saveClick( );


	void saveLinkAdData();

	void saveDssdkAdData();

	void saveSubData();

	void saveSubLinkData();

	void saveOffersdkAdData();

    void statIframeAdData();

	void statIframeActiveAdData();
}
