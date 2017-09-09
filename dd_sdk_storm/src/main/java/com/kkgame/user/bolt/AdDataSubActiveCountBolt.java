package com.kkgame.user.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import com.kkgame.user.bean.DssdkDataVO;
import com.kkgame.user.bean.SubActiveVO;
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

import static com.alibaba.jstorm.container.cgroup.core.BlkioCore.RecordType.total;

public class AdDataSubActiveCountBolt
        implements IRichBolt {
    private static final long serialVersionUID = -5573683060557415895L;
    private static final Logger LOG = LoggerFactory.getLogger(AdDataSubActiveCountBolt.class);
    private OutputCollector collector;
    JedisResourcePool jedisPool;
    private String host;
    private int port;
    private String zkProxyDir;
    private TpsCounter tpsCounter;
    private List<String> dataList;

    public void execute(Tuple input) {
        SubActiveVO subActiveVO = (SubActiveVO) input.getValueByField("SubActive");

        String cdate = subActiveVO.getCdate();
        String key = subActiveVO.getKey();
        float payout = subActiveVO.getPayout();
        String statDate = CalendarFormat.switchFormatDate(cdate, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
        StringBuffer adActive = new StringBuffer();
        adActive.append("ad_active_day").append("|").append(statDate);
        StringBuffer cooActive = new StringBuffer();
        String adTotal = "ad_total_active";
        StringBuffer paydata = new StringBuffer();
        paydata.append("ad_pay_day").append("|").append(statDate);
        String[]keys = key.split("\\|");
        String totalPay = "pay_ad_total";
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                jedis.zincrby(adActive.toString(), 1.0D, key);
                jedis.expire(adActive.toString(), 172800);

                jedis.zincrby(paydata.toString(),payout, key) ;
                jedis.expire(paydata.toString(), 172800);

                jedis.zincrby(adTotal, 1.0, keys[0]);
                jedis.zincrby(totalPay, payout, keys[0]);
            } catch (Throwable localThrowable1) {

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