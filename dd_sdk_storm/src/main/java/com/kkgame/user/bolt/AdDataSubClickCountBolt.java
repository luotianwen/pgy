package com.kkgame.user.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import com.kkgame.user.bean.SubClickVO;
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

public class AdDataSubClickCountBolt
        implements IRichBolt {
    private static final long serialVersionUID = -5573683060557415895L;
    private static final Logger LOG = LoggerFactory.getLogger(AdDataSubClickCountBolt.class);
    private OutputCollector collector;
    JedisResourcePool jedisPool;
    private String host;
    private int port;
    private String zkProxyDir;
    private TpsCounter tpsCounter;
    private List<String> dataList;

    public void execute(Tuple input) {
        SubClickVO subClickVO = (SubClickVO) input.getValueByField("SubClick");
        String cou = subClickVO.getCou();
        String imei = subClickVO.getImei();
        String cdate = subClickVO.getCdate();
        String xmodel = subClickVO.getXmodel();
        String xoperator = subClickVO.getXoperator();
        String xversion = subClickVO.getXversion();
        long clickId = subClickVO.getClickId();
        int adId = subClickVO.getAdId();
        int projectId = subClickVO.getCoo_id();
        int internet = subClickVO.getInternet();
        int platformId = subClickVO.getPlatId();
        int adType = subClickVO.getAdType();
        String statDate = CalendarFormat.switchFormatDate(cdate, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
        StringBuffer sb1 = new StringBuffer();
        sb1.append("ad_click_day").append("|").append(statDate);

        StringBuffer sb2 = new StringBuffer();
        sb2.append(adId).append("|").append(cou).append("|").append(projectId).append("|").append(platformId)
                .append("|").append(xoperator).append("|").append(adType);

        StringBuffer sb = new StringBuffer();
        sb.append("adData_sub_adId");

        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                jedis.zincrby(sb.toString(), 1.0D, String.valueOf(adId));
                jedis.zincrby(sb1.toString(), 1.0D, sb2.toString());
                jedis.expire(sb1.toString(), 172800);

            } catch (Exception e) {

            } finally {
                if (jedis != null) try {
                    jedis.close();
                } catch (Throwable x2) {
                }
                else jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cou", cou);
        jsonObject.put("imei", imei);
        jsonObject.put("xmodel", xmodel);
        jsonObject.put("adId", adId);
        jsonObject.put("cooid", projectId);
        jsonObject.put("xoperator", xoperator);
        jsonObject.put("internet", internet);
        jsonObject.put("xversion", xversion);
        jsonObject.put("cdate", cdate);
        jsonObject.put("clickId", clickId);
        jsonObject.put("platformId", platformId);
        jsonObject.put("adType", adType);
        this.dataList.add(jsonObject.toString());
//        if ((this.dataList != null) && (this.dataList.size() >= 20))
            try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                sb = new StringBuffer();
                sb.append("ad_sub_data_day");
                jedis.lpush(sb.toString(), (String[]) this.dataList.toArray(new String[0]));
                this.dataList = new ArrayList();
            } catch (Throwable localThrowable4) {

            } finally {
                if (jedis != null) try {
                    jedis.close();
                } catch (Throwable x2) {
                }
                else jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        this.collector.ack(input);
    }

    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
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

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }

    public void cleanup() {
        this.tpsCounter.cleanup();
        LOG.info("Finish cleanup");
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}