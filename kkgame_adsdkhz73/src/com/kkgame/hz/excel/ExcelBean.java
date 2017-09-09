package com.kkgame.hz.excel;

import com.kkgame.hz.util.ThreeRelate;

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
public class ExcelBean<T extends ExcelDataImp> {
    /** 查询条件 */
    private List<ThreeRelate<Boolean, String, String>> queryList;
    /** 列名 */
    private List<String> columes;
    /** 列数据类型 */
    private List<Integer> colsType;
    /** 行数据 */
    private List<T> rowsData;

    public List<ThreeRelate<Boolean, String, String>> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<ThreeRelate<Boolean, String, String>> queryList) {
        this.queryList = queryList;
    }

    public List<String> getColumes() {
        return columes;
    }

    public void setColumes(List<String> columes) {
        this.columes = columes;
    }

    public List<Integer> getColsType() {
        return colsType;
    }

    public void setColsType(List<Integer> colsType) {
        this.colsType = colsType;
    }

    public List<T> getRowsData() {
        return rowsData;
    }

    public void setRowsData(List<T> rowsData) {
        this.rowsData = rowsData;
    }
}
