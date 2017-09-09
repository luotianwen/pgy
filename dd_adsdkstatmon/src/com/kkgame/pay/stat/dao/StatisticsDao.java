package com.kkgame.pay.stat.dao;

import java.util.List;

import com.kkgame.pay.stat.bean.*;

public interface StatisticsDao {

	void saveUser(List<UserData> listUser);

	void saveActive(List<UserData> listUser);

	void saveProjectActive(List<UserData> listUser);

	void saveSent(List<AdDataVO> listUser);

	void saveSentSucc(List<AdDataVO> listUser);

	void saveShow(List<AdDataVO> listUser);

	void saveActivate(List<AdDataVO> listUser);

	void saveInstalled(List<AdDataVO> listUser);

	void saveDownload(List<AdDataVO> listUser);

	void saveClick(List<AdDataVO> listUser);
	List<AdDataVO> query();

	void saveLinkAdData(List<AdLinkDataVO> listUser);

	void saveDssdkAdData(List<AdDssdkDataVO> listUser);

	void saveSubAdData(List<AdSubDataVO> listUser);

	void saveSubLinkAdData(List<SubLinkDataVO> listUser);

	void saveIframeActive(List<IframeVO> iframeVO, String table);
}
