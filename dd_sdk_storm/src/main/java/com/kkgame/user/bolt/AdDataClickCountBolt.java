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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class AdDataClickCountBolt
  implements IRichBolt
{
  private static final long serialVersionUID = -7933651465878898110L;
  private static final Logger LOG = LoggerFactory.getLogger(AdDataClickCountBolt.class);
  private OutputCollector collector;
  JedisResourcePool jedisPool;
  private String host;
  private int port;
  private String zkProxyDir;
  private TpsCounter tpsCounter;
  private List<String> dataList;

  public void execute(Tuple input)
  {
    AdDataVO adDataVO = (AdDataVO)input.getValueByField("AdData");

    boolean flag = isRepeat(adDataVO, true);

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
    countSb.append(statDate).append("|").append(sdkStyle).append("|").append(projectId).append("|").append(channelId).append("|").append(pluginId).append("|").append(1).append("|").append(1).append("|").append(adId).append("|").append(adType);

    StringBuffer sb = new StringBuffer();
    sb.append("adData_click_ad_stat_times").append("_");
    sb.append(statDate);
    sb.append("_").append(adType);
    try
    {
      Jedis jedis = this.jedisPool.getResource(); 
      try { jedis.zincrby(sb.toString(), 1.0D, countSb.toString());
        jedis.expire(sb.toString(), 172800);
        if (adDataVO.getIsRepeat() == 1) {
          sb = new StringBuffer();
          sb.append("adData_click_ad_stat_users").append("_");
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

    JSONObject jsonObject = JSONObject.fromObject(adDataVO);
    this.dataList.add(jsonObject.toString());
    if ((this.dataList != null) && (this.dataList.size() >= 20)) try {
        Jedis jedis = this.jedisPool.getResource(); 
        try { sb = new StringBuffer();
          sb.append("ad_click_data_day");
          jedis.lpush(sb.toString(), (String[])this.dataList.toArray(new String[0]));
          this.dataList = new ArrayList();
        }
        catch (Throwable localThrowable4)
        {
          
        }
        finally
        {
          if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();  
        } } catch (Exception e) { e.printStackTrace(); }


    this.collector.ack(input);
  }

  private boolean isRepeat(AdDataVO adDataVO, boolean flag) {
    int sdkStyle = adDataVO.getSdkstyle();
    int projectId = adDataVO.getCoo_id();
    String channelId = adDataVO.getChannelid();
    int pluginId = adDataVO.getXc_coo_id();
    int coutry = adDataVO.getCou();
    int countryLevel = adDataVO.getCountryLevel();
    String imei = adDataVO.getImei();
    int adType = adDataVO.getSdk();
    int adId = adDataVO.getApkid();
    StringBuffer sb = new StringBuffer();
    String statDate = CalendarFormat.switchFormatDate(adDataVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    sb.append("adData_click_detail").append(statDate).append("_").append(sdkStyle).append("_").append(projectId).append("_").append(channelId).append("_").append(pluginId).append("_").append(coutry).append("_").append(countryLevel).append("_").append(adType).append("_").append(adId).append("_").append(imei);
    try
    {
      Jedis jedis = this.jedisPool.getResource(); 
      try { String isRepeat = jedis.get(sb.toString());
        if ((isRepeat == null) || ("nil".equals(isRepeat))) {
          adDataVO.setIsRepeat(1);
          if (flag) {
            jedis.set(sb.toString(), "1");
            jedis.expire(sb.toString(), 86400);
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
}