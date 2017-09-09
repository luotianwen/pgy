package com.kkgame.hz.excel;

import java.util.LinkedList;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/3/17
 *          Time: 13:56
 * @author: XJ
 * @since 3.0
 */
public abstract class BasicExcelData<T1, T2> implements ExcelDataImp {
    private LinkedList<String> list = new LinkedList<String>();

    public BasicExcelData(T1 t1, T2 t2) {
        addAllShowData(t1, t2);
    }

    public abstract void addAllShowData(T1 t1, T2 t2);

    public void addData(String str) {
        list.addLast(str);
    }

    @Override
    public String nextColumn() {
        String first = null;
        if (0 < list.size()) {
            first = list.removeFirst();
        }
        return first;
    }

    @Override
    public boolean hashNext() {
        return list.size() > 0;
    }

    @Override
    public int getCurSize() {
        return list.size();
    }
}
