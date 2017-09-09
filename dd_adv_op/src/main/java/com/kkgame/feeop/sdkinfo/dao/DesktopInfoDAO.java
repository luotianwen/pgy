package com.kkgame.feeop.sdkinfo.dao;

import com.kkgame.feeop.sdkinfo.bean.DesktopInfoVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface DesktopInfoDAO {

	List<DesktopInfoVO> getDesktopInfoVOList(DesktopInfoVO desktopInfoVO) throws DatabaseException;
	List<DesktopInfoVO> getAllDesktopInfoVOList(DesktopInfoVO desktopInfoVO) throws DatabaseException;

	DesktopInfoVO getDesktopInfoVO(DesktopInfoVO desktopInfoVO) throws DatabaseException;
	void insert(DesktopInfoVO desktopInfoVO) throws DatabaseException;
	void update(DesktopInfoVO desktopInfoVO) throws DatabaseException;
	void delete(DesktopInfoVO desktopInfoVO) throws DatabaseException;
}
