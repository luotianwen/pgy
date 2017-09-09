package com.kkgame.feeop.util;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/11
 *          Time: 11:37
 * @author: XJ
 * @since 3.0
 */
public class TwoRelate<A, B> {
    private A one;
    private B two;

    public TwoRelate(A one, B two) {
        this.one = one;
        this.two = two;
    }

    public A getOne() {
        return one;
    }

    public void setOne(A one) {
        this.one = one;
    }

    public B getTwo() {
        return two;
    }

    public void setTwo(B two) {
        this.two = two;
    }
}
