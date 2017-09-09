package com.kkgame.user.bolt;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.kkgame.user.bean.UserVO;
import com.kkgame.util.TpsCounter;
import java.util.Map;
import org.apache.log4j.Logger;

public class UserInfoProjectBolt
  implements IBasicBolt
{
  private static final long serialVersionUID = 5463945569834197697L;
  private static final Logger LOG = Logger.getLogger(UserInfoProjectBolt.class);
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
    if ((obj instanceof UserVO)) {
      UserVO userVO = (UserVO)obj;
      collector.emit("userInfo_project_count_stream", new Values(new Object[] { tupleId, userVO }));
      collector.emit("userInfo_project_version_count_stream", new Values(new Object[] { tupleId, userVO }));
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
    declarer.declareStream("userInfo_project_count_stream", new Fields(new String[] { "id", "UserInfo" }));
    declarer.declareStream("userInfo_project_version_count_stream", new Fields(new String[] { "id", "UserInfo" }));
  }

  public Map<String, Object> getComponentConfiguration()
  {
    return null;
  }
}