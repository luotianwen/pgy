package com.kkgame.pay.stat.service.impl;


import com.kkgame.pay.stat.bean.DdlData;
import com.kkgame.pay.stat.bean.NameVO;
import com.kkgame.pay.stat.dao.DdlFeeDao;
import com.kkgame.pay.stat.dao.DdlStatisticsDao;
import com.kkgame.pay.stat.service.DdlStatisticsService;
import com.kkgame.pay.stat.util.DatabaseException;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DdlStatisticsServiceImpl implements DdlStatisticsService {

	private static final Log log = LogFactory.getLog(DdlStatisticsServiceImpl.class);
	
	private DdlStatisticsDao ddlStatisticsDao;
	
	private DdlFeeDao ddlFeeDao;
	
	private DdlFeeDao ddlMasterFeeDao;
	
	@Override
	public void syncProductChannel(int i) throws DatabaseException {
		List<NameVO> productList = ddlFeeDao.getProductList();
		if(null!=productList&&productList.size()>0) {
			ddlStatisticsDao.insertProductList(productList);
		}
		
		List<NameVO> channelList = ddlFeeDao.getChannelList();
		if(null!=channelList&&channelList.size()>0) {
			ddlStatisticsDao.insertChannelList(channelList);
		}
	}
	
	@Override
	public void statSaleData(int i) throws DatabaseException {
		List<DdlData> list = ddlFeeDao.getSaleDataList(i);
		if(null!=list&&list.size()>0) {
			ddlStatisticsDao.insertSaleData(i, list);
		}
		
		List<DdlData> sendlist = ddlFeeDao.getSendSaleDataList(i);
		if(null!=sendlist&&sendlist.size()>0) {
			ddlStatisticsDao.insertSendSaleData(i, sendlist);
		}
	}
	
	@Override
	public void statClickData(int i) throws DatabaseException {
		ddlStatisticsDao.deleteClickData(i);
		ddlStatisticsDao.deleteSaleData(i);
		for(int j=0;j<10;j++) {
			List<DdlData> list = ddlFeeDao.getClickDataList(i,j);
			if(list!=null&&list.size()!=0) {
				ddlStatisticsDao.insertClickData(i, list);
			}
			List<DdlData> validlist = ddlFeeDao.getValidClickDataList(i,j);
			if(validlist!=null&&validlist.size()!=0) {
				ddlStatisticsDao.insertValidClickData(i, validlist);
			}
			
		}
		List<DdlData> totalList = ddlStatisticsDao.getTotalClickList(i);
		if(totalList!=null&&totalList.size()!=0) {
			ddlStatisticsDao.insertTotalClickData(i, totalList);
		}
	}
	
	@Override
	public void statMonthSaleData(int i) throws DatabaseException {
		ddlStatisticsDao.deleteMonthSaleData(i);
		List<DdlData> list = ddlStatisticsDao.getMonthSaleData(i);
		if(null!=list&&list.size()>0) {
			ddlStatisticsDao.insertMonthSaleData(i,list);
		}
	}
	
	@Override
	public void updateTotalSaleData(int i) throws DatabaseException {
		// TODO Auto-generated method stub
		List<DdlData> list = ddlStatisticsDao.getTotalSaleData(i);
		if(null!=list&&list.size()>0) {
			for(DdlData data:list) {
				ddlMasterFeeDao.updateTotalSaleData(data);
			}
			
		}
	}

	@Override
	public List<DdlData> getSendData(int i) throws DatabaseException {
		return ddlStatisticsDao.getSendData(i);
	}

	public DdlStatisticsDao getDdlStatisticsDao() {
		return ddlStatisticsDao;
	}

	public void setDdlStatisticsDao(DdlStatisticsDao ddlStatisticsDao) {
		this.ddlStatisticsDao = ddlStatisticsDao;
	}

	public DdlFeeDao getDdlFeeDao() {
		return ddlFeeDao;
	}

	public void setDdlFeeDao(DdlFeeDao ddlFeeDao) {
		this.ddlFeeDao = ddlFeeDao;
	}

	public DdlFeeDao getDdlMasterFeeDao() {
		return ddlMasterFeeDao;
	}

	public void setDdlMasterFeeDao(DdlFeeDao ddlMasterFeeDao) {
		this.ddlMasterFeeDao = ddlMasterFeeDao;
	}
	
}
