import com.kokmobi.server.util.CalendarFormat;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.List;

public class TestRedisData extends Thread {

	@Override
	public void run() {
		//setUserData();
		//setUserActiveData();
		 setAdShowData();
	}

	
	public void setAdShowData() {
		JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.222.5:2181,192.168.222.6:2181,192.168.222.7:2181", 30000)
				.zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
		try(Jedis jedis = jedisPool.getResource();) {
			  for(int i=1;i<=10;i++) {
				System.out.println(i);
				int k = 10000+i;
				k++;
				AdDataVO adDataVO = new AdDataVO();
				adDataVO.setCoo_id(10804);
				adDataVO.setImei("8675270250"+k);
				adDataVO.setSdk(5);
				adDataVO.setChannelid("10804");
				adDataVO.setXc_coo_id(0);
				adDataVO.setSdkstyle(600400);
				adDataVO.setSdkversion(i%3+1);
				adDataVO.setCou(1);
				adDataVO.setCountryLevel(1);
				adDataVO.setCdate(CalendarFormat.getCurrentDateTime());
				adDataVO.setPkgid("11111111111111");
				adDataVO.setPkgstatus(3200);
				adDataVO.setApkid(1);
				JSONObject jsonObject = JSONObject.fromObject(adDataVO);
				//System.out.println(jsonObject);
				//jedis.lpush(ConfKeys.USER_INFO,jsonObject.toString());
				jedis.lpush(ConfKeys.AD_SHOW_DATA,jsonObject.toString());
			} 
			//Set<String> set= jedis.smembers(ConfKeys.AD_SHOW_DATA);
			 List<String>set=jedis.lrange(ConfKeys.AD_SHOW_DATA, 0, -1);
			 //jedis.lpush(ConfKeys.AD_SHOW_DATA,jsonObject.toString());
			// jedis.del(ConfKeys.AD_SHOW_DATA);
			// System.out.println(set.size());
			 for (String object : set) {
				System.out.println(object);
			}  
			/*for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				System.out.println(object.toString());
			}*/
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\"acceptsNull\":false,\"cdate\":\"2015-11-17 07:07:11\",\"channelid\":\"10804\",\"coo_id\":10804,\"countryLevel\":1,\"creator\":0,\"id\":0,\"imei\":\"867527025100001\",\"immutable\":false,\"ipaddr\":\"\",\"scoo_id\":0,\"sdk\":5,\"sdkStyle\":0,\"sdkversion\":2,\"xcoo_id\":0,\"xcou\":1,\"xdate\":\"\",\"ximsi\":\"460070527209738\",\"xinternet\":1,\"xmodel\":\"Lenovo K30T\",\"xoperator\":\"46007\",\"xversion\":\"4.4.4\"}");
//			JSONObject jsonObject = JSONObject.fromObject(sb.toString());
//			UserVO userVO = (UserVO) JSONObject.toBean(jsonObject,UserVO.class);
//			System.out.println(userVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setUserData() {
		JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181,192.168.199.206:2181,192.168.199.123:2181", 30000)
				.zkProxyDir("/zk/codis/db_ddsdk/proxy").build();
		try(Jedis jedis = jedisPool.getResource();) {
			for(int i=1;i<=100;i++) {
				System.out.println(i);
				int k = 10000+i;
				k++;
				StringBuffer sb = new StringBuffer();
				UserVO userVO = new UserVO();
				userVO.setCoo_id(10804);
				userVO.setImei("8675270250"+k);
				userVO.setSdk(5);
				userVO.setChannelid("10804");
				userVO.setXc_coo_id(0);
				userVO.setSdkStyle(600400);
				userVO.setXmodel("Lenovo K30T");
				userVO.setXversion("4.4.4");
				userVO.setXimsi("460070527209738");
				userVO.setXinternet(1);
				userVO.setXoperator("46007");
				userVO.setSdkversion(2);
				userVO.setXcou(1);
				userVO.setCountryLevel(1);
				userVO.setCdate(CalendarFormat.getCurrentDateTime());
				String registerDate = CalendarFormat.getYmd(i);
				//System.out.println(registerDate+" 00:10:11");
				userVO.setXdate(registerDate+" 00:10:11");
				userVO.setScoo_id(10804);
				userVO.setDataType(i%2);
				JSONObject jsonObject = JSONObject.fromObject(userVO);
				//System.out.println(jsonObject);
				//jedis.lpush(ConfKeys.USER_INFO,jsonObject.toString());
				jedis.lpush(ConfKeys.USER_INFO,jsonObject.toString());
			}
			
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\"acceptsNull\":false,\"cdate\":\"2015-11-17 07:07:11\",\"channelid\":\"10804\",\"coo_id\":10804,\"countryLevel\":1,\"creator\":0,\"id\":0,\"imei\":\"867527025100001\",\"immutable\":false,\"ipaddr\":\"\",\"scoo_id\":0,\"sdk\":5,\"sdkStyle\":0,\"sdkversion\":2,\"xcoo_id\":0,\"xcou\":1,\"xdate\":\"\",\"ximsi\":\"460070527209738\",\"xinternet\":1,\"xmodel\":\"Lenovo K30T\",\"xoperator\":\"46007\",\"xversion\":\"4.4.4\"}");
//			JSONObject jsonObject = JSONObject.fromObject(sb.toString());
//			UserVO userVO = (UserVO) JSONObject.toBean(jsonObject,UserVO.class);
//			System.out.println(userVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void setUserActiveData() {
		JedisResourcePool jedisPool = RoundRobinJedisPool.create().curatorClient("192.168.199.172:2181,192.168.199.206:2181,192.168.199.123:2181", 30000)
				.zkProxyDir("/zk/codis/db_kok_adv/proxy").build();	
		try(Jedis jedis = jedisPool.getResource();) {
			for(int i=1;i<=10;i++) {
				System.out.println(i);
				int k = 10000+i;
				k++;
				StringBuffer sb = new StringBuffer();
				UserVO userVO = new UserVO();
				userVO.setCoo_id(10804);
				userVO.setImei("8675270250"+k);
				userVO.setSdk(5);
				userVO.setChannelid("10804");
				userVO.setXc_coo_id(0);
				userVO.setSdkStyle(600400);
				userVO.setXmodel("Lenovo K30T");
				userVO.setXversion("4.4.4");
				userVO.setXimsi("460070527209738");
				userVO.setXinternet(1);
				userVO.setXoperator("46007");
				userVO.setSdkversion(2);
				userVO.setXcou(1);
				userVO.setCountryLevel(1);
				userVO.setCdate(CalendarFormat.getCurrentDateTime());
				String registerDate = CalendarFormat.getYmd(i);
				System.out.println(registerDate+" 00:10:11");
				userVO.setXdate(registerDate+" 00:10:11");
				userVO.setScoo_id(10804);
				userVO.setDataType(i%2);
				JSONObject jsonObject = JSONObject.fromObject(userVO);
				System.out.println(jsonObject);
				//jedis.lpush(ConfKeys.USER_INFO,jsonObject.toString());
				jedis.lpush(ConfKeys.USER_ACTIVE,jsonObject.toString());
			}
			
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\"acceptsNull\":false,\"cdate\":\"2015-11-17 07:07:11\",\"channelid\":\"10804\",\"coo_id\":10804,\"countryLevel\":1,\"creator\":0,\"id\":0,\"imei\":\"867527025100001\",\"immutable\":false,\"ipaddr\":\"\",\"scoo_id\":0,\"sdk\":5,\"sdkStyle\":0,\"sdkversion\":2,\"xcoo_id\":0,\"xcou\":1,\"xdate\":\"\",\"ximsi\":\"460070527209738\",\"xinternet\":1,\"xmodel\":\"Lenovo K30T\",\"xoperator\":\"46007\",\"xversion\":\"4.4.4\"}");
//			JSONObject jsonObject = JSONObject.fromObject(sb.toString());
//			UserVO userVO = (UserVO) JSONObject.toBean(jsonObject,UserVO.class);
//			System.out.println(userVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestRedisData test1 = new TestRedisData();
		test1.start();
		/*TestRedisData test2 = new TestRedisData();
		test2.start();
		TestRedisData test3 = new TestRedisData();
		test3.start();
		TestRedisData test4 = new TestRedisData();
		test4.start();
		TestRedisData test5 = new TestRedisData();
		test5.start();
		TestRedisData test6 = new TestRedisData();
		test6.start();
		TestRedisData test7 = new TestRedisData();
		test7.start();
		TestRedisData test8 = new TestRedisData();
		test8.start();
		TestRedisData test9 = new TestRedisData();
		test9.start();*/
	}
}
