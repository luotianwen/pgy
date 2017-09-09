package com.kkgame.pay.stat.test;

import com.kkgame.pay.stat.bean.AdDataVO;
import com.kkgame.pay.stat.bean.UserData;
import com.kkgame.pay.stat.dao.StatisticsDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/** 
 * TODO 
 * @author cuiran 
 * @version TODO 
 */  
public class MongoTest {  
  
    private static Log log = LogFactory.getLog(MongoTest.class.getName());
      
    private StatisticsDao pr=null;
      
    /**
     *  
     *<b>function:</b> 
     * @author cuiran 
     * @createDate 2012-12-12 16:08:02 
     */  
    public void init(){  

         ApplicationContext ctx = new ClassPathXmlApplicationContext("/conf/spring/applicationContext-mongodb.xml");
          pr= (StatisticsDao)ctx.getBean("statisticsDao");
        System.out.println("开始启动");


    }  

    private void insertClick(){
        System.out.println("insertClick");
        List<AdDataVO> ad=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            AdDataVO a=new AdDataVO();
            a.setImei(i+"");
            a.setCoo_id(123749);
            a.setSdk(600501);
            a.setChannelid("75");
            a.setApkid(11400);
            a.setCou(33);
            a.setSdkversion(11);
            a.setXc_coo_id(75);
            a.setCountryLevel(4);
            a.setSdkstyle(600405);
             a.setPkgid("5eeb94ed9af84b32b5bf5c203cbd261d");
            a.setCdate(new Date().toLocaleString());
            ad.add(a);
        }
        pr.saveSentSucc(ad);
        System.out.println("insertClick成功");
    }
    private void insertShow(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<UserData> ad=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            UserData a=new UserData();
            a.setImei(i+"");
            a.setCoo_id(123749);
            a.setSdk(600501);
            a.setChannelid("75");
            a.setSdkStyle(600400);
            a.setXcou(33);
            a.setSdkversion(11);
            a.setXc_coo_id(75);
            a.setCountryLevel(4);
            a.setXdate(new Date().toLocaleString());
            a.setScoo_id(75);
            a.setIpaddr("192.165.25.25");
            a.setCdate(new Date().toLocaleString());
            ad.add(a);
        }
        pr.saveUser(ad);
        pr.saveActive(ad);
        pr.saveProjectActive(ad);
        System.out.println("insertShow成功");
    }
    private void query(){

        List<AdDataVO> a= pr.query();
        for (AdDataVO as: a
             ) {
            System.out.println(as.getImei()+as.getCdate());
        }

    }

    private void dbtomongodb(){

    }
    /**
     *
     *<b>function:</b>测试方法
     * @author cuiran
     * @createDate 2012-12-12 16:11:37
     */
    public void start(){
        init();
        //insertShow();
         //insertClick();
        query();

    }
      
    /** 
     *<b>function:</b>main函数 
     * @author cuiran 
     * @createDate 2012-12-12 11:54:30 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        MongoTest t=new MongoTest();  
        t.start();  
    }  
  
} 