package com.kkgame.feeop.sdkinfo.service.impl;

import com.kkgame.feeop.sdkinfo.bean.LinkAdverVO;
import com.kkgame.feeop.sdkinfo.dao.LinkAdverDAO;
import com.kkgame.feeop.sdkinfo.service.LinkAdverService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/22
 *          Time: 18:29
 * @author: mm
 * @since 3.0
 */
public class LinkAdverServiceImpl implements LinkAdverService {

    private LinkAdverDAO linkAdverDAO;
    @Override
    public List<LinkAdverVO> getLinkAdverVOList(LinkAdverVO linkAdverVO) throws DatabaseException {
        return linkAdverDAO.getLinkAdverVOList(linkAdverVO);
    }

    @Override
    public List<LinkAdverVO> getAllLinkAdverVOList(LinkAdverVO linkAdverVO) throws DatabaseException {
        return linkAdverDAO.getAllLinkAdverVOList(linkAdverVO);
    }

    @Override
    public LinkAdverVO getLinkAdverVO(LinkAdverVO linkAdverVO) throws DatabaseException {
        return linkAdverDAO.getLinkAdverVO(linkAdverVO);
    }

    @Override
    public void insert(LinkAdverVO linkAdverVO) throws DatabaseException {
        linkAdverDAO.insert(linkAdverVO);
    }

    @Override
    public void update(LinkAdverVO linkAdverVO) throws DatabaseException {
        linkAdverDAO.update(linkAdverVO);
    }

    @Override
    public void delete(LinkAdverVO linkAdverVO) throws DatabaseException {
        linkAdverDAO.delete(linkAdverVO);
    }

    public LinkAdverDAO getLinkAdverDAO() {
        return linkAdverDAO;
    }

    public void setLinkAdverDAO(LinkAdverDAO linkAdverDAO) {
        this.linkAdverDAO = linkAdverDAO;
    }
}
