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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class UserAdCountBolt
  implements IRichBolt
{
  private static final long serialVersionUID = 1196785722853347197L;
  private static final Logger LOG = LoggerFactory.getLogger(UserAdCountBolt.class);
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
    String statDate = CalendarFormat.switchFormatDate(userVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    int sdkStyle = userVO.getSdkStyle();
    int coutry = userVO.getXcou();
    int dataType = userVO.getDataType();
    StringBuffer sb = new StringBuffer();
    sb.append("user_ad_stat").append("_");
    sb.append(statDate);
    sb.append("_").append(dataType);

    StringBuffer countSb = new StringBuffer();
    countSb.append(statDate).append("|").append(sdkStyle).append("|").append(coutry).append("|").append(dataType);

    LOG.info(sb.toString());
    LOG.info(countSb.toString());
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