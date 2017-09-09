package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.AdvSdkVO;
import com.kkgame.feeop.adver.dao.AdvSdkDAO;
import com.kkgame.feeop.adver.service.AdvSdkService;
import com.kkgame.feeop.util.DatabaseException;

public class AdvSdkServiceImpl implements AdvSdkService {

    private AdvSdkDAO advSdkDAO;

    public AdvSdkDAO getAdvSdkDAO() {
        return advSdkDAO;
    }

    public void setAdvSdkDAO(AdvSdkDAO advSdkDAO) {
        this.advSdkDAO = advSdkDAO;
    }

    @Override
    public List<AdvSdkVO> getAdvSdkVOList(AdvSdkVO advSdkVO) throws DatabaseException {
        // TODO Auto-generated method stub
        return advSdkDAO.getAdvSdkVOList(advSdkVO);
    }

    @Override
    public AdvSdkVO getAdvSdkVO(AdvSdkVO advSdkVO) throws DatabaseException {
        // TODO Auto-generated method stub
        return advSdkDAO.getAdvSdkVO(advSdkVO);
    }

    @Override
    public void insert(AdvSdkVO advSdkVO) throws DatabaseException {
        // TODO Auto-generated method stub
        advSdkDAO.insert(advSdkVO);
    }

    @Override
    public void update(AdvSdkVO advSdkVO) throws DatabaseException {
        // TODO Auto-generated method stub
        advSdkDAO.update(advSdkVO);
    }

    @Override
    public void updateUrl(AdvSdkVO advSdkVO) throws DatabaseException {
        // TODO Auto-generated method stub
        advSdkDAO.updateUrl(advSdkVO);
    }

    @Override
    public void delete(AdvSdkVO advSdkVO) throws DatabaseException {
        // TODO Auto-generated method stub
        advSdkDAO.delete(advSdkVO);
    }

    @Override
    public void batchAlter(AdvSdkVO advSdkVO) throws DatabaseException {
        advSdkDAO.batchAlter(advSdkVO);
    }

    @Override
    public List<AdvSdkVO> getAdvSdkVOCountryList(AdvSdkVO advSdkVO) {
        return advSdkDAO.getAdvSdkVOCountryList(advSdkVO);
    }

    @Override
    public void insertCopeAdvSdkVO(AdvSdkVO advSdkVO) {
        advSdkDAO.insertCopeAdvSdkVO(advSdkVO);
    }
}
