package com.kokmobi.server.dao;

import java.util.List;

import com.kokmobi.server.bean.ApkInfoVO;

public interface ApkInfoDao {

	List<ApkInfoVO> getApkInfoVOList() throws Exception;
	List<ApkInfoVO> getydApkInfoVOList() throws Exception;
	
}
