package com.kkgame.user.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import com.kkgame.user.bean.UserVO;
import com.kkgame.util.CalendarFormat;
import com.kkgame.util.TpsCounter;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import com.wandoulabs.jodis.RoundRobinJedisPool.Builder;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class UserActiveCountBolt
  implements IRichBolt
{
  private static final long serialVersionUID = 1196785722853347197L;
  private static final Logger LOG = LoggerFactory.getLogger(UserActiveCountBolt.class);
  private OutputCollector collector;
  JedisResourcePool jedisPool;
  private String host;
  private int port;
  private String zkProxyDir;
  private TpsCounter tpsCounter;
  private List<String> dataList;

  public void execute(Tuple input)
  {
    UserVO userVO = (UserVO)input.getValueByField("UserInfo");
    int sdkStyle = userVO.getSdkStyle();
    int projectId = userVO.getCoo_id();
    String channelId = userVO.getChannelid();
    int pluginId = userVO.getXc_coo_id();
    int coutry = userVO.getXcou();
    int countryLevel = userVO.getCountryLevel();
    int scoo_id = userVO.getScoo_id();

    StringBuffer countSb = new StringBuffer();
    String statDate = CalendarFormat.switchFormatDate(userVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    countSb.append(statDate).append("|").append(sdkStyle).append("|").append(projectId).append("|").append(channelId).append("|").append(pluginId).append("|").append(coutry).append("|").append(countryLevel);

    int count = 1;
    StringBuffer sb = new StringBuffer();
    sb.append("user_active_project_stat").append("_");
    sb.append(statDate);
    sb.append("_").append(sdkStyle);
    try {
      Jedis jedis = this.jedisPool.getResource(); 
      try { jedis.zincrby(sb.toString(), 1.0D, countSb.toString());
        jedis.expire(sb.toString(), 172800);
        if (userVO.getDataType() == 1) {
          count = 1;
          sb = new StringBuffer();
          sb.append("user_active_stat").append("_");
          sb.append(statDate);
          sb.append("_").append(sdkStyle);
          jedis.zincrby(sb.toString(), 1.0D, countSb.toString());
          jedis.expire(sb.toString(), 172800);
        }
      }
      catch (Throwable localThrowable1)
      {
      }
      finally
      {
        if (jedis != null) try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();  
      } } catch (Exception e) { e.printStackTrace(); }


    String registerDate = CalendarFormat.switchFormatDate(userVO.getXdate().substring(0, 10), "yyyy-MM-dd", "yyyy-MM-dd");
    int subDay = CalendarFormat.daysBetween(registerDate, statDate);
    StringBuffer info = new StringBuffer();
    LOG.info(info.append(registerDate).append(subDay).toString());
    if ((subDay == 1) || (subDay == 2) || (subDay == 3) || (subDay == 4) || (subDay == 5) || (subDay == 6) || (subDay == 7) || (subDay == 15) || (subDay == 30)) {
      try {
        Jedis jedis = this.jedisPool.getResource(); 
        try { countSb = new StringBuffer();
          countSb.append(registerDate).append("|").append(sdkStyle).append("|").append(scoo_id).append("|").append(channelId).append("|").append(pluginId).append("|").append(coutry).append("|").append(countryLevel);

          sb = new StringBuffer();
          sb.append("user_active_register_stat").append("_").append(statDate).append("_").append(registerDate).append("_").append(userVO.getSdkStyle());

          count = 1;
          jedis.zincrby(sb.toString(), 1.0D, countSb.toString());
          LOG.info(sb.toString());
          LOG.info(countSb.toString());

          jedis.expire(sb.toString(), 172800);
        }
        catch (Throwable localThrowable2)
        {
        }
        finally
        {
          if (jedis != null)   try { jedis.close(); } catch (Throwable x2) {} else jedis.close();  
        } } catch (Exception e) { e.printStackTrace(); }

    }
    JSONObject jsonObject = JSONObject.fromObject(userVO);
    this.dataList.add(jsonObject.toString());
    if ((this.dataList != null) && (this.dataList.size() >= 20)) try {
        Jedis jedis = this.jedisPool.getResource(); 
        try { sb = new StringBuffer();
          sb.append("user_active_day");
          jedis.lpush(sb.toString(), (String[])this.dataList.toArray(new String[0]));
          this.dataList = new ArrayList();
        }
        catch (Throwable localThrowable5)
        {
        }
        finally
        {
          if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();  
        } } catch (Exception e) { e.printStackTrace(); }


    this.collector.ack(input);
  }

  public void prepare(Map conf, TopologyContext context, OutputCollector collector)
  {
    this.collector = collector;
    this.host = conf.get("redis_host_key").toString();
    this.port = Integer.valueOf(conf.get("zk_port_key").toString()).intValue();
    this.zkProxyDir = conf.get("zk_proxy_dir").toString();
    StringBuffer hostPort = new StringBuffer();
    hostPort.append(this.host).append(":").append(this.port);
    this.jedisPool = RoundRobinJedisPool.create().curatorClient(hostPort.toString(), 30000).zkProxyDir(this.zkProxyDir).build();

    this.dataList = new ArrayList();
    this.tpsCounter = new TpsCounter(context.getThisComponentId() + ":" + context.getThisTaskId());

    LOG.info("Finished preparation " + conf);
  }

  public void declareOutputFields(OutputFieldsDeclarer declarer)
  {
  }

  public void cleanup()
  {
    this.tpsCounter.cleanup();
    LOG.info("Finish cleanup");
  }

  public Map<String, Object> getComponentConfiguration()
  {
    return null;
  }

  public static void main(String[] args) {
    String s = "2015-12-23";
    System.out.println(s.substring(0, 10));
  }
}