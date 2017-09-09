package com.kkgame.feeop.util;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/3/17
 *          Time: 13:13
 * @author: XJ
 * @since 3.0
 */
public class ThreeRelate<A, B, C> extends TwoRelate<A, B> {
    private C three;

    public ThreeRelate(A one, B two, C three) {
        super(one, two);
        this.three = three;
    }

    public C getThree() {
        return three;
    }

    public void setThree(C three) {
        this.three = three;
    }
}
