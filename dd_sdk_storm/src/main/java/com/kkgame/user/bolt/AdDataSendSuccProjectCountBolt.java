package com.kkgame.user.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import com.kkgame.user.bean.AdDataVO;
import com.kkgame.util.CalendarFormat;
import com.kkgame.util.TpsCounter;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import com.wandoulabs.jodis.RoundRobinJedisPool.Builder;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class AdDataSendSuccProjectCountBolt
  implements IRichBolt
{
  private static final long serialVersionUID = -3926627349551252947L;
  private static final Logger LOG = LoggerFactory.getLogger(AdDataSendSuccProjectCountBolt.class);
  private OutputCollector collector;
  JedisResourcePool jedisPool;
  private String host;
  private int port;
  private String zkProxyDir;
  private TpsCounter tpsCounter;

  public void execute(Tuple input)
  {
    AdDataVO adDataVO = (AdDataVO)input.getValueByField("AdData");

    boolean projectFlag = isProjectRepeat(adDataVO, true);
    int sdkStyle = adDataVO.getSdkstyle();
    int projectId = adDataVO.getCoo_id();
    String channelId = adDataVO.getChannelid();
    int pluginId = adDataVO.getXc_coo_id();
    int coutry = adDataVO.getCou();
    int countryLevel = adDataVO.getCountryLevel();
    int adType = adDataVO.getSdk();
    int adId = adDataVO.getApkid();
    String statDate = CalendarFormat.switchFormatDate(adDataVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    StringBuffer countSb = new StringBuffer();
    countSb.append(statDate).append("|").append(sdkStyle).append("|").append(projectId).append("|").append(channelId).append("|").append(pluginId).append("|").append(coutry).append("|").append(countryLevel).append("|").append("|").append(adType);

    StringBuffer sb = new StringBuffer();

    countSb = new StringBuffer();
    countSb.append(statDate).append("|").append(sdkStyle).append("|").append(projectId).append("|").append(channelId).append("|").append(pluginId).append("|").append(coutry).append("|").append(countryLevel).append("|").append(adType);

    sb = new StringBuffer();
    sb.append("adData_sendSucc_project_stat_times").append("_");
    sb.append(statDate);
    sb.append("_").append(adType);
    try {
      Jedis jedis = this.jedisPool.getResource(); 
      try { jedis.zincrby(sb.toString(), 1.0D, countSb.toString());
        jedis.expire(sb.toString(), 172800);
        if (adDataVO.getIsProjectRepeat() == 1) {
          sb = new StringBuffer();
          sb.append("adData_sendSucc_project_stat_users").append("_");
          sb.append(statDate);
          sb.append("_").append(adType);

          jedis.zincrby(sb.toString(), 1.0D, countSb.toString());
          jedis.expire(sb.toString(), 172800);
        }
      }
      catch (Throwable localThrowable1)
      {
        
      }
      finally
      {
        if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();  
      } } catch (Exception e) { e.printStackTrace(); }


    this.collector.ack(input);
  }

  private boolean isProjectRepeat(AdDataVO adDataVO, boolean flag) {
    int sdkStyle = adDataVO.getSdkstyle();
    int projectId = adDataVO.getCoo_id();
    String channelId = adDataVO.getChannelid();
    int pluginId = adDataVO.getXc_coo_id();
    int coutry = adDataVO.getCou();
    int countryLevel = adDataVO.getCountryLevel();
    String imei = adDataVO.getImei();
    int adType = adDataVO.getSdk();
    StringBuffer sb = new StringBuffer();
    String statDate = CalendarFormat.switchFormatDate(adDataVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    sb.append("adData_sendSucc_project_detail").append(statDate).append("_").append(sdkStyle).append("_").append(projectId).append("_").append(channelId).append("_").append(pluginId).append("_").append(coutry).append("_").append(countryLevel).append("_").append(adType).append("_").append(imei);
    try
    {
      Jedis jedis = this.jedisPool.getResource(); 
      try { String isRepeat = jedis.get(sb.toString());
        if ((isRepeat == null) || ("nil".equals(isRepeat))) {
          adDataVO.setIsProjectRepeat(1);
          int expireTime;
          if (flag) {
            expireTime = CalendarFormat.getSecondsBetweenNowAndTomorrow();
            jedis.set(sb.toString(), "1");
            jedis.expire(sb.toString(), expireTime);
          }
          return true;
        }
      }
      catch (Throwable localThrowable1)
      {
        
      }
      finally
      {
        if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();  
      } } catch (Exception e) { e.printStackTrace(); }

    return false;
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
}