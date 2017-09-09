package com.kkgame.feeop.sdkinfo.dao;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.sdkinfo.bean.SilentPluginInfoVO;
import com.kkgame.feeop.util.DatabaseException;

public interface SilentPluginInfoDAO {

	List<SilentPluginInfoVO> getSilentPluginInfoVOList(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException;
	List<SilentPluginInfoVO> getAllSilentPluginInfoVOList(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException;

	SilentPluginInfoVO getSilentPluginInfoVO(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException;
	void insert(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException;
	void update(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException;
}
