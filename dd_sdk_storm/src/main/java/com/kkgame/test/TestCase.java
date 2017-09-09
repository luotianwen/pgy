package com.kkgame.test;

import com.kkgame.user.bean.AdDataVO;
import com.kkgame.util.CalendarFormat;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.sf.json.JSONObject;

public class TestCase
{
  private static Random random = new Random();
  public static List<Integer> AD_TYPE_LIST = new ArrayList();
  public static List<Integer> TYPE_LIST = new ArrayList();

  public static void main(String[] args)
  {
    AdDataVO adDataVO = new AdDataVO();
    adDataVO.setCoo_id(10804);
    adDataVO.setImei("86752702501");
    adDataVO.setSdk(((Integer)AD_TYPE_LIST.get(random.nextInt(5))).intValue());
    adDataVO.setChannelid("10804");
    adDataVO.setXc_coo_id(0);
    adDataVO.setSdkstyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
    adDataVO.setSdkversion(2);
    adDataVO.setCou(random.nextInt(100) + 1);
    adDataVO.setCountryLevel(1);
    adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
    adDataVO.setPkgid("1111111111");
    adDataVO.setPkgstatus(3200);
    adDataVO.setApkid(random.nextInt(1000) + 1);
    JSONObject jsonObject = JSONObject.fromObject(adDataVO);
    System.out.println(jsonObject);
  }

  static
  {
    AD_TYPE_LIST.add(Integer.valueOf(600500));
    AD_TYPE_LIST.add(Integer.valueOf(600506));
    AD_TYPE_LIST.add(Integer.valueOf(600508));
    AD_TYPE_LIST.add(Integer.valueOf(600509));
    AD_TYPE_LIST.add(Integer.valueOf(600501));

    TYPE_LIST.add(Integer.valueOf(600400));
    TYPE_LIST.add(Integer.valueOf(600403));
    TYPE_LIST.add(Integer.valueOf(600404));
    TYPE_LIST.add(Integer.valueOf(600405));
  }
}