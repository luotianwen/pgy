package com.kkgame.user.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import com.esotericsoftware.minlog.Log;
import com.kkgame.user.bean.DssdkDataVO;
import com.kkgame.util.CalendarFormat;
import com.kkgame.util.TpsCounter;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdDataDssdkCountBolt
        implements IRichBolt
{
  private static final long serialVersionUID = -5573683060557415895L;
  private static final Logger LOG = LoggerFactory.getLogger(AdDataDssdkCountBolt.class);
  private OutputCollector collector;
  JedisResourcePool jedisPool;
  private String host;
  private int port;
  private String zkProxyDir;
  private TpsCounter tpsCounter;
  private List<String> dataList;

  public void execute(Tuple input)
  {
    DssdkDataVO adLinkDataVO = (DssdkDataVO)input.getValueByField("AdLink");
    String cou = adLinkDataVO.getCou();
    int adId = adLinkDataVO.getAdId();
    int clickType = adLinkDataVO.getClickType();
    String pkgid=adLinkDataVO.getLinkType();
    String imei=adLinkDataVO.getImei();
    String cdate=adLinkDataVO.getCdate();
    //pv
    StringBuffer countSb = new StringBuffer();
    String statDate = CalendarFormat.switchFormatDate(adLinkDataVO.getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    countSb.append(statDate).append("|").append(adId).append("|").append(clickType).append("|").append(cou);

    StringBuffer sb = new StringBuffer();
    sb.append("adData_dssdk_stat").append("_").append(clickType).append("_").append(statDate);
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




    StringBuffer uk = new StringBuffer();
    uk.append(statDate).append("|").append(imei).append("|").append(adId);


    // uv
    StringBuffer ucountSb = new StringBuffer();
    ucountSb.append(statDate).append("|").append(adId);


    StringBuffer usb = new StringBuffer();
    usb.append("adData_dssdk_uv").append("_");
    usb.append(statDate);

    try {
      Jedis jedis = this.jedisPool.getResource();
      try {
        String value=jedis.get(uk.toString());
        if(null==value||"".equals(value)) {
          jedis.zincrby(usb.toString(), 1.0D, ucountSb.toString());
          jedis.expire(usb.toString(), 172800);
          jedis.set(uk.toString(),"1");
          jedis.expire(uk.toString(), 24*60*60);
        }
      }
      catch (Throwable localThrowable1)
      {

      }
      finally {
        if (jedis != null)  try { jedis.close(); } catch (Throwable x2) {  } else jedis.close();
      } } catch (Exception e) { e.printStackTrace(); }

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("cou", cou);
    jsonObject.put("imei", imei);
    jsonObject.put("adId", adId);
    jsonObject.put("type", clickType);
    jsonObject.put("pkgid", pkgid);
    jsonObject.put("cdate",cdate);
    this.dataList.add(jsonObject.toString());
    if ((this.dataList != null) && (this.dataList.size() >= 20)) try {
      Jedis jedis = this.jedisPool.getResource();
      try { sb = new StringBuffer();
        sb.append("ad_dssdk_data_day");
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

    this.tpsCounter = new TpsCounter(context.getThisComponentId() + ":" + context.getThisTaskId());
    this.dataList = new ArrayList();
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