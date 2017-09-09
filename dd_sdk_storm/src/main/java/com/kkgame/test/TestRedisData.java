package com.kkgame.test;

import com.kkgame.user.bean.AdDataVO;
import com.kkgame.user.bean.AdLinkDataVO;
import com.kkgame.user.bean.UserVO;
import com.kkgame.util.CalendarFormat;
import com.kkgame.util.TpsCounter;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import com.wandoulabs.jodis.RoundRobinJedisPool.Builder;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class TestRedisData extends Thread
{
    private static Random random = new Random();
    public static List<Integer> AD_TYPE_LIST = new ArrayList();
    public static List<Integer> TYPE_LIST = new ArrayList();
    private TpsCounter tpsCounter;

    public void run()
    {
        //setAdLinkData(10);

        zrank();
    }

    public void zrank(){
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                 String value=jedis.get("aaa");
                System.out.println(value);
                //Long a=jedis.zrank("adData_link_uv_2016-06-30","2016-06-30|864566020118548|4");
                jedis.del("adData_link_stat_4_2016-06-30");
               // System.out.println(a);
            }
            catch (Throwable localThrowable1)
            {
                localThrowable1.printStackTrace();
            }
            finally
            {
                if (jedis != null)   try { jedis.close(); } catch (Throwable x2) { } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void popData(String key)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { boolean flag = true;
                while (flag) {
                    String adDataRedis = jedis.rpop(key);
                    if ((null == adDataRedis) || ("nil".equals(adDataRedis)))
                        flag = false;
                    else
                        System.out.println(adDataRedis);
                }
            }
            catch (Throwable localThrowable1)
            {
                localThrowable1.printStackTrace();
            }
            finally
            {
                if (jedis != null)   try { jedis.close(); } catch (Throwable x2) { } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setAdLinkData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                AdLinkDataVO adDataVO = new AdLinkDataVO();
                adDataVO.setCoo_id(11081);
                adDataVO.setImei("867527025206" + k);
                adDataVO.setAdId(i);
                adDataVO.setCou(i % 200 + 1+"");
                adDataVO.setClickType(i % 4 + 1);
                adDataVO.setLinkType(i % 2 + 1);
                adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
                JSONObject jsonObject = JSONObject.fromObject(adDataVO);

                jedis.lpush("ad_link_data", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setUserAdData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                UserVO userVO = new UserVO();
                userVO.setImei("8675270250" + k);
                userVO.setCdate(CalendarFormat.getCurrentDateTime());
                userVO.setSdkStyle(i % 3 + 1);
                userVO.setDataType(i % 3 + 1);
                userVO.setXcou(1);
                JSONObject jsonObject = JSONObject.fromObject(userVO);

                jedis.lpush("user_ad", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setAdSendData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                AdDataVO adDataVO = new AdDataVO();
                adDataVO.setCoo_id(10804);
                adDataVO.setImei("8675270250" + k);
                adDataVO.setSdk(600500);
                adDataVO.setChannelid("10804");
                adDataVO.setXc_coo_id(0);
                adDataVO.setSdkstyle(600400);
                adDataVO.setSdkversion(i % 3 + 1);
                adDataVO.setCou(random.nextInt(100) + 1);
                adDataVO.setCountryLevel(1);
                adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
                adDataVO.setPkgid("111111111" + k);
                adDataVO.setPkgstatus(3200);
                adDataVO.setApkid(random.nextInt(1000) + 1);
                JSONObject jsonObject = JSONObject.fromObject(adDataVO);

                jedis.lpush("ad_send_data", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setAdSendSuccData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }

                int k = 10000 + i;
                k++;
                AdDataVO adDataVO = new AdDataVO();
                adDataVO.setCoo_id(10804);
                adDataVO.setImei("8675270250" + k);
                adDataVO.setSdk(((Integer)AD_TYPE_LIST.get(random.nextInt(5))).intValue());
                adDataVO.setChannelid("10804");
                adDataVO.setXc_coo_id(0);
                adDataVO.setSdkstyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
                adDataVO.setSdkversion(i % 3 + 1);
                adDataVO.setCou(random.nextInt(100) + 1);
                adDataVO.setCountryLevel(1);
                adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
                adDataVO.setPkgid("111111111" + k);
                adDataVO.setPkgstatus(3200);
                adDataVO.setApkid(random.nextInt(1000) + 1);
                JSONObject jsonObject = JSONObject.fromObject(adDataVO);

                jedis.lpush("ad_send_succ_data", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }

    }

    public void setAdShowData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                AdDataVO adDataVO = new AdDataVO();
                adDataVO.setCoo_id(10804);
                adDataVO.setImei("8675270250" + k);
                adDataVO.setSdk(((Integer)AD_TYPE_LIST.get(random.nextInt(5))).intValue());
                adDataVO.setChannelid("10804");
                adDataVO.setXc_coo_id(0);
                adDataVO.setSdkstyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
                adDataVO.setSdkversion(i % 3 + 1);
                adDataVO.setCou(random.nextInt(100) + 1);
                adDataVO.setCountryLevel(1);
                adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
                adDataVO.setPkgid("11111111111111");
                adDataVO.setPkgstatus(3200);
                adDataVO.setApkid(random.nextInt(1000) + 1);
                JSONObject jsonObject = JSONObject.fromObject(adDataVO);

                jedis.lpush("ad_show_data", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setAdClickData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                AdDataVO adDataVO = new AdDataVO();
                adDataVO.setCoo_id(10804);
                adDataVO.setImei("8675270250" + k);
                adDataVO.setSdk(((Integer)AD_TYPE_LIST.get(random.nextInt(5))).intValue());

                adDataVO.setChannelid("10804");
                adDataVO.setXc_coo_id(0);
                adDataVO.setSdkstyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
                adDataVO.setSdkversion(i % 3 + 1);
                adDataVO.setCou(random.nextInt(100) + 1);
                adDataVO.setCountryLevel(1);
                adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
                adDataVO.setPkgid("11111111111111");
                adDataVO.setPkgstatus(3200);
                adDataVO.setApkid(random.nextInt(1000) + 1);
                JSONObject jsonObject = JSONObject.fromObject(adDataVO);

                jedis.lpush("ad_click_data", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setAdDownloadData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                AdDataVO adDataVO = new AdDataVO();
                adDataVO.setCoo_id(10804);
                adDataVO.setImei("8675270250" + k);
                adDataVO.setSdk(((Integer)AD_TYPE_LIST.get(random.nextInt(5))).intValue());

                adDataVO.setChannelid("10804");
                adDataVO.setXc_coo_id(0);
                adDataVO.setSdkstyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
                adDataVO.setSdkversion(i % 3 + 1);
                adDataVO.setCou(random.nextInt(100) + 1);
                adDataVO.setCountryLevel(1);
                adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
                adDataVO.setPkgid("11111111111111");
                adDataVO.setPkgstatus(3200);
                adDataVO.setApkid(random.nextInt(1000) + 1);
                JSONObject jsonObject = JSONObject.fromObject(adDataVO);

                jedis.lpush("ad_download_data", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setAdInstalledData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                AdDataVO adDataVO = new AdDataVO();
                adDataVO.setCoo_id(10804);
                adDataVO.setImei("8675270250" + k);
                adDataVO.setSdk(((Integer)AD_TYPE_LIST.get(random.nextInt(5))).intValue());

                adDataVO.setChannelid("10804");
                adDataVO.setXc_coo_id(0);
                adDataVO.setSdkstyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
                adDataVO.setSdkversion(i % 3 + 1);
                adDataVO.setCou(random.nextInt(100) + 1);
                adDataVO.setCountryLevel(1);
                adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
                adDataVO.setPkgid("11111111111111");
                adDataVO.setPkgstatus(3200);
                adDataVO.setApkid(random.nextInt(1000) + 1);
                JSONObject jsonObject = JSONObject.fromObject(adDataVO);

                jedis.lpush("ad_installed_data", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setAdActivateData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                AdDataVO adDataVO = new AdDataVO();
                adDataVO.setCoo_id(10804);
                adDataVO.setImei("8675270250" + k);
                adDataVO.setSdk(600501);
                adDataVO.setChannelid("10804");
                adDataVO.setXc_coo_id(0);
                adDataVO.setSdkstyle(600405);
                adDataVO.setSdkversion(i % 3 + 1);
                adDataVO.setCou(random.nextInt(100) + 1);
                adDataVO.setCountryLevel(1);
                adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
                adDataVO.setPkgid("11111111111111");
                adDataVO.setPkgstatus(3200);
                adDataVO.setApkid(random.nextInt(1000) + 1);
                JSONObject jsonObject = JSONObject.fromObject(adDataVO);

                jedis.lpush("ad_activate_data", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }

    }

    public void setUserData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                StringBuffer sb = new StringBuffer();
                UserVO userVO = new UserVO();
                userVO.setCoo_id(10804);
                userVO.setImei("8675270250" + k);
                userVO.setSdk(5);
                userVO.setChannelid("10804");
                userVO.setXc_coo_id(0);
                userVO.setSdkStyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
                userVO.setXmodel("Lenovo K30T");
                userVO.setXversion("4.4.4");
                userVO.setXimsi("460070527209738");
                userVO.setXinternet(1);
                userVO.setXoperator("46007");
                userVO.setSdkversion(i % 3 + 1);
                userVO.setXcou(1);
                userVO.setCountryLevel(1);
                userVO.setCdate(CalendarFormat.getCurrentDateTime());
                String registerDate = CalendarFormat.getYmd(i);
                System.out.println(registerDate + " 00:10:11");
                userVO.setXdate(registerDate + " 00:10:11");
                userVO.setScoo_id(10804);
                userVO.setDataType(i % 2);
                userVO.setIpaddr("127.0.0.1");
                JSONObject jsonObject = JSONObject.fromObject(userVO);

                long lastTime = System.currentTimeMillis();
                jedis.lpush("user_info", new String[] { jsonObject.toString() });
                System.out.println(System.currentTimeMillis() - lastTime);
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setProjectUserData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                StringBuffer sb = new StringBuffer();
                UserVO userVO = new UserVO();
                userVO.setCoo_id(10804);
                userVO.setImei("8675270250" + k);
                userVO.setSdk(5);
                userVO.setChannelid("10804");
                userVO.setXc_coo_id(0);
                userVO.setSdkStyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
                userVO.setXmodel("Lenovo K30T");
                userVO.setXversion("4.4.4");
                userVO.setXimsi("460070527209738");
                userVO.setXinternet(1);
                userVO.setXoperator("46007");
                userVO.setSdkversion(i % 3 + 1);
                userVO.setXcou(1);
                userVO.setCountryLevel(1);
                userVO.setCdate(CalendarFormat.getCurrentDateTime());
                String registerDate = CalendarFormat.getYmd(i);
                System.out.println(registerDate + " 00:10:11");
                userVO.setXdate(registerDate + " 00:10:11");
                userVO.setScoo_id(10804);
                userVO.setDataType(i % 2);
                userVO.setIpaddr("127.0.0.1");
                JSONObject jsonObject = JSONObject.fromObject(userVO);

                jedis.lpush("user_info_project", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }
    }

    public void setUserActiveData(int j)
    {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181", 30000).zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
        try {
            Jedis jedis = jedisPool.getResource();
            try { for (int i = 1; i <= j; i++) {
                if (i % 500 == 0) {
                    System.out.println(i);
                }
                int k = 10000 + i;
                k++;
                StringBuffer sb = new StringBuffer();
                UserVO userVO = new UserVO();
                userVO.setCoo_id(10804);
                userVO.setImei("8675270250" + k);
                userVO.setSdk(5);
                userVO.setChannelid("10804");
                userVO.setXc_coo_id(0);
                userVO.setSdkStyle(((Integer)TYPE_LIST.get(random.nextInt(4))).intValue());
                userVO.setXmodel("Lenovo K30T");
                userVO.setXversion("4.4.4");
                userVO.setXimsi("460070527209738");
                userVO.setXinternet(1);
                userVO.setXoperator("46007");
                userVO.setSdkversion(i % 3 + 1);
                userVO.setXcou(1);
                userVO.setCountryLevel(1);
                userVO.setCdate(CalendarFormat.getCurrentDateTime());
                String registerDate = CalendarFormat.getYmd(i % 100);
                System.out.println(registerDate + " 00:10:11");
                userVO.setXdate(registerDate + " 00:10:11");
                userVO.setScoo_id(10804);
                userVO.setDataType(i % 2);
                JSONObject jsonObject = JSONObject.fromObject(userVO);

                jedis.lpush("user_active", new String[] { jsonObject.toString() });
            }
            }
            catch (Throwable localThrowable1)
            {

            }
            finally
            {
                if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
            } } catch (Exception e) { e.printStackTrace(); }

    }

    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
       // for (int i = 0; i < 10; i++) {
            TestRedisData test1 = new TestRedisData();
            test1.start();
      //  }
        long end = System.currentTimeMillis();
        System.out.println((start - end) / 1000L);
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