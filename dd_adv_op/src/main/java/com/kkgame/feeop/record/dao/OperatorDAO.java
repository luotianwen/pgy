package com.kkgame.feeop.record.dao;

import com.kkgame.feeop.record.bean.OperatorVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface OperatorDAO {

    List<OperatorVO> getOperatorVOList(OperatorVO operatorVO) throws DatabaseException;

    void create(OperatorVO operatorVO) throws DatabaseException;

    void update(OperatorVO operatorVO) throws DatabaseException;

    OperatorVO getOperatorVO(OperatorVO operatorVO) throws DatabaseException;

    List<OperatorVO> getAllOperatorVO();

    List<OperatorVO> getOperatorsByCou(OperatorVO operatorVO);
}

