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

public class AdDataSendSuccVersionCountBolt
  implements IRichBolt
{
  private static final long serialVersionUID = -2423161712394018893L;
  private static final Logger LOG = LoggerFactory.getLogger(AdDataSendSuccVersionCountBolt.class);
  private OutputCollector collector;
  JedisResourcePool jedisPool;
  private String host;
  private int port;
  private String zkProxyDir;
  private TpsCounter tpsCounter;

  public void execute(Tuple input)
  {
    AdDataVO adDataVO = (AdDataVO)input.getValueByField("AdData");
    String statDate = CalendarFormat.switchFormatDate(adDataVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    boolean flag = isVersionRepeat(adDataVO, true);
    int sdkStyle = adDataVO.getSdkstyle();
    int sdkVersion = adDataVO.getSdkversion();
    int adType = adDataVO.getSdk();
    StringBuffer versionSb = new StringBuffer();
    versionSb.append(statDate).append("|").append(sdkStyle).append("|").append(sdkVersion).append("|").append(adType);

    StringBuffer sb = new StringBuffer();
    sb.append("adData_sendSucc_version_stat_times").append("_");
    sb.append(statDate);
    sb.append("_").append(adType);
    try {
      Jedis jedis = this.jedisPool.getResource(); 
      try { jedis.zincrby(sb.toString(), 1.0D, versionSb.toString());
        jedis.expire(sb.toString(), 172800);
        if (adDataVO.getIsVersionRepeat() == 1) {
          sb = new StringBuffer();
          sb.append("adData_sendSucc_version_stat_users").append("_");
          sb.append(statDate);
          sb.append("_").append(adType);
          jedis.zincrby(sb.toString(), 1.0D, versionSb.toString());
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

  private boolean isVersionRepeat(AdDataVO adDataVO, boolean flag) {
    int sdkStyle = adDataVO.getSdkstyle();
    int sdkVersion = adDataVO.getSdkversion();
    int adType = adDataVO.getSdk();
    String imei = adDataVO.getImei();
    String statDate = CalendarFormat.switchFormatDate(adDataVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    try { Jedis jedis = this.jedisPool.getResource(); 
      try { StringBuffer sb = new StringBuffer();
        sb.append("adData_sendSucc_version_detail").append("_").append(statDate).append("_").append(sdkStyle).append("_").append(sdkVersion).append("_").append(adType).append("_").append(imei);

        String isRepeat = jedis.get(sb.toString());
        if ((isRepeat == null) || ("nil".equals(isRepeat))) {
          adDataVO.setIsVersionRepeat(1);
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