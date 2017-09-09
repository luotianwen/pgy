package com.kkgame.user.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.kkgame.user.bean.AdDataVO;
import com.kkgame.util.TpsCounter;
import java.util.Map;
import org.apache.log4j.Logger;

public class AdDataClickBolt
  implements IBasicBolt
{
  private static final long serialVersionUID = -1802023316459285901L;
  private static final Logger LOG = Logger.getLogger(AdDataClickBolt.class);
  private OutputCollector collector;
  private TpsCounter tpsCounter;

  public void cleanup()
  {
    this.tpsCounter.cleanup();
    LOG.info("Finish cleanup");
  }

  public void execute(Tuple tuple, BasicOutputCollector collector)
  {
    this.tpsCounter.count();
    Long tupleId = tuple.getLong(0);
    Object obj = tuple.getValue(1);
    if ((obj instanceof AdDataVO)) {
      AdDataVO adDataVO = (AdDataVO)obj;
      collector.emit("adData_click_count_stream", new Values(new Object[] { tupleId, adDataVO, adDataVO.getImei() }));
      collector.emit("adData_click_version_count_stream", new Values(new Object[] { tupleId, adDataVO, adDataVO.getImei() }));
      collector.emit("adData_click_project_count_stream", new Values(new Object[] { tupleId, adDataVO, adDataVO.getImei() }));
    } else if (obj != null) {
      LOG.info("Unknow type " + obj.getClass().getName());
    } else {
      LOG.info("Nullpointer ");
    }
  }

  public void prepare(Map stormConf, TopologyContext context)
  {
    this.tpsCounter = new TpsCounter(context.getThisComponentId() + ":" + context.getThisTaskId());

    LOG.info("Successfully do prepare");
  }

  public void declareOutputFields(OutputFieldsDeclarer declarer)
  {
    declarer.declareStream("adData_click_count_stream", new Fields(new String[] { "id", "AdData", "imei" }));
    declarer.declareStream("adData_click_version_count_stream", new Fields(new String[] { "id", "AdData", "imei" }));
    declarer.declareStream("adData_click_project_count_stream", new Fields(new String[] { "id", "AdData", "imei" }));
  }

  public Map<String, Object> getComponentConfiguration()
  {
    return null;
  }
}