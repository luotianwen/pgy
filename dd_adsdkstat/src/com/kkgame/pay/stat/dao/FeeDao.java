package com.kkgame.pay.stat.dao;

import java.util.List;

import com.kkgame.pay.stat.util.DatabaseException;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.InstallData;
import com.kkgame.pay.stat.bean.UserData;

public interface FeeDao {

	List<UserData> getUserDataList(int i, int type) throws DatabaseException;

	List<UserData> getUserActiveDataList(int i, int type, int j)
			throws DatabaseException;

	List<Data> getShowData(int i, int type, int j) throws DatabaseException;

	List<Data> getClickData(int i, int type, int j) throws DatabaseException;

	List<Data> getDownloadData(int i, int type, int j) throws DatabaseException;

	List<Data> getInstallData(int i, int type, int j) throws DatabaseException;

	List<Data> getActiveData(int i, int type, int j) throws DatabaseException;

	List<UserData> getRetentionUserDataList(int day, int i, int type, int j)
			throws DatabaseException;

	List<Data> getAdSendData(int i, int type, int j) throws DatabaseException;

	List<Data> getAdReceiveData(int i, int type, int j)
			throws DatabaseException;

	List<Data> getProjectSendData(int i, int type, int j)
			throws DatabaseException;

	List<Data> getProjectReceiveData(int i, int type, int j)
			throws DatabaseException;

	List<Data> getProjectShowData(int i, int type, int j)
			throws DatabaseException;

	List<Data> getProjectClickData(int i, int type, int j)
			throws DatabaseException;

	List<Data> getProjectDownloadData(int i, int type, int j)
			throws DatabaseException;

	List<Data> getProjectInstallData(int i, int type, int j)
			throws DatabaseException;

	List<Data> getProjectActiveData(int i, int type, int j)
			throws DatabaseException;

	List<Data> getAdBackData(int i) throws DatabaseException;

	List<UserData> getTotalUserActiveDataList(int i, int j)
			throws DatabaseException;

	List<UserData> getUserProjectActiveDataList(int i, int type, int j) throws DatabaseException;

	List<UserData> getVersionUserDataList(int i, int type) throws DatabaseException;

	List<UserData> getVersionUserActiveDataList(int i, int type, int j) throws DatabaseException;

	List<UserData> getVersionUserProjectActiveDataList(int i, int type, int j) throws DatabaseException;

	List<UserData> getTotalVersionUserActiveDataList(int i, int j) throws DatabaseException;

	List<Data> getVersionProjectSendData(int i, int type, int j) throws DatabaseException;

	List<Data> getVersionProjectReceiveData(int i, int type, int j) throws DatabaseException;

	List<Data> getVersionProjectShowData(int i, int type, int j) throws DatabaseException;

	List<Data> getVersionProjectClickData(int i, int type, int j) throws DatabaseException;

	List<Data> getVersionProjectDownloadData(int i, int type, int j) throws DatabaseException;

	List<Data> getVersionProjectInstallData(int i, int type, int j) throws DatabaseException;

	List<Data> getVersionProjectActiveData(int i, int type, int j) throws DatabaseException;

}
