package com.kkgame.user.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import com.esotericsoftware.minlog.Log;
import com.kkgame.user.bean.UserVO;
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

public class UserInfoProjectCountBolt
  implements IRichBolt
{
  private static final long serialVersionUID = -5573683060557415895L;
  private static final Logger LOG = LoggerFactory.getLogger(UserInfoProjectCountBolt.class);
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
    StringBuffer countSb = new StringBuffer();
    String statDate = CalendarFormat.switchFormatDate(userVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    countSb.append(statDate).append("|").append(sdkStyle).append("|").append(projectId).append("|").append(channelId).append("|").append(pluginId).append("|").append(coutry).append("|").append(countryLevel);

    StringBuffer sb = new StringBuffer();
    sb.append("user_info_project_stat").append("_");
    sb.append(statDate);
    sb.append("_").append(sdkStyle);
    Log.info(sb.toString());
    Log.info(countSb.toString());
    try {
      Jedis jedis = this.jedisPool.getResource(); 
      try { jedis.zincrby(sb.toString(), 1.0D, countSb.toString());
        jedis.expire(sb.toString(), 172800);
      }
      catch (Throwable localThrowable1)
      {
        
      }
      finally {
        if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();  
      } } catch (Exception e) { e.printStackTrace(); }

    JSONObject jsonObject = JSONObject.fromObject(userVO);
    this.dataList.add(jsonObject.toString());
    if ((this.dataList != null) && (this.dataList.size() >= 20)) try {
        Jedis jedis = this.jedisPool.getResource(); 
        try { sb = new StringBuffer();
          sb.append("user_info_project_day");
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