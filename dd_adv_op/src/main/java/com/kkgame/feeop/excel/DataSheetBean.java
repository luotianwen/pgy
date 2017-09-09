package com.kkgame.feeop.excel;

import com.kkgame.feeop.util.ThreeRelate;
import com.kkgame.feeop.util.TwoRelate;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/3/17
 *          Time: 13:11
 * @author: Administrator
 * @since 3.0
 */
public class DataSheetBean {
    /** 查询条件 */
    private List<ThreeRelate<Boolean, String, String>> queryList;
    /** 列名、列数据类型 */
    private List<TwoRelate<String, Integer>> columes;
    /** 行数据 */
    private List<DataRowBean> rowsData;

    public List<ThreeRelate<Boolean, String, String>> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<ThreeRelate<Boolean, String, String>> queryList) {
        this.queryList = queryList;
    }

    public List<TwoRelate<String, Integer>> getColumes() {
        return columes;
    }

    public void setColumes(List<TwoRelate<String, Integer>> columes) {
        this.columes = columes;
    }

    public List<DataRowBean> getRowsData() {
        return rowsData;
    }

    public void setRowsData(List<DataRowBean> rowsData) {
        this.rowsData = rowsData;
    }
}
