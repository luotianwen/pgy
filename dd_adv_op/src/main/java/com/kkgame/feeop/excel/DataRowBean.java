package com.kkgame.feeop.excel;

import java.util.LinkedList;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/11
 *          Time: 12:13
 * @author: XJ
 * @since 3.0
 */
public class DataRowBean {
    private LinkedList<String> columnValues;

    public int getColumnNum() {
        return null == columnValues ? 0 : columnValues.size();
    }

    public LinkedList<String> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(LinkedList<String> columnValues) {
        this.columnValues = columnValues;
    }
}
