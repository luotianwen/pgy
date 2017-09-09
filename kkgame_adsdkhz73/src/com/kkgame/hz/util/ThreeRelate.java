package com.kkgame.hz.util;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/3/17
 *          Time: 13:13
 * @author: XJ
 * @since 3.0
 */
public class ThreeRelate<A, B, C> {
    private A one;
    private B two;
    private C three;

    public ThreeRelate(A one, B two, C three) {
        this.one = one;
        this.two = two;
        this.three = three;
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

    public C getThree() {
        return three;
    }

    public void setThree(C three) {
        this.three = three;
    }
}
