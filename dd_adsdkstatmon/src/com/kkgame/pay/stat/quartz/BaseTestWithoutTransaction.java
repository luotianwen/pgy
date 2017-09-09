package com.kkgame.pay.stat.quartz;


import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;

public abstract class BaseTestWithoutTransaction extends TestCase
{    
    protected static ApplicationContext ctx;
    
    protected Object getBean(String beanname){
        return ctx.getBean(beanname);
    }
}
