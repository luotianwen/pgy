package com.kkgame.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.TopologyAssignException;
import backtype.storm.topology.BoltDeclarer;
import backtype.storm.topology.TopologyBuilder;
import com.alibaba.jstorm.utils.JStormUtils;
import com.alibaba.jstorm.utils.LoadConf;
import com.kkgame.config.ConfValues;
import com.kkgame.user.bean.AdDataVO;
import com.kkgame.user.bean.UserVO;
import com.kkgame.user.bolt.UserActiveBolt;
import com.kkgame.user.bolt.UserActiveCountBolt;
import com.kkgame.user.bolt.UserActiveVersionCountBolt;
import com.kkgame.user.bolt.UserAdBolt;
import com.kkgame.user.bolt.UserAdCountBolt;
import com.kkgame.user.bolt.UserInfoBolt;
import com.kkgame.user.bolt.UserInfoCountBolt;
import com.kkgame.user.bolt.UserInfoProjectBolt;
import com.kkgame.user.bolt.UserInfoProjectCountBolt;
import com.kkgame.user.bolt.UserInfoProjectVersionCountBolt;
import com.kkgame.user.bolt.UserInfoVersionCountBolt;
import com.kkgame.user.spout.UserActiveSpout;
import com.kkgame.user.spout.UserAdSpout;
import com.kkgame.user.spout.UserInfoProjectSpout;
import com.kkgame.user.spout.UserInfoSpout;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserStatTopology
{
  private TopologyBuilder builder = new TopologyBuilder();
  private LocalCluster cluster;
  private static Logger LOG = LoggerFactory.getLogger(UserStatTopology.class);
  public static final String TOPOLOGY_SPOUT_PARALLELISM_HINT = "spout.parallel";
  public static final String TOPOLOGY_BOLT_PARALLELISM_HINT = "bolt.parallel";
  public static final String TOPOLOGY_BOLT_COUNT_PARALLELISM_HINT = "bolt.count.parallel";
  private static Map conf = new HashMap();

  public static void SetBuilder(TopologyBuilder builder, Map conf)
  {
    int spout_Parallelism_hint = JStormUtils.parseInt(conf.get("spout.parallel"), 1).intValue();

    int bolt_Parallelism_hint = JStormUtils.parseInt(conf.get("bolt.parallel"), 2).intValue();

    int bolt_Count_Parallelism_hint = JStormUtils.parseInt(conf.get("bolt.count.parallel"), 1).intValue();

    builder.setSpout("userInfoSpout", new UserInfoSpout(), Integer.valueOf(spout_Parallelism_hint));
    builder.setSpout("userInfoProjectSpout", new UserInfoProjectSpout(), Integer.valueOf(spout_Parallelism_hint));
    builder.setSpout("userActiveSpout", new UserActiveSpout(), Integer.valueOf(spout_Parallelism_hint));
    builder.setSpout("userAdSpout", new UserAdSpout(), Integer.valueOf(spout_Parallelism_hint));

    builder.setBolt("userInfoBolt", new UserInfoBolt(), Integer.valueOf(bolt_Parallelism_hint)).shuffleGrouping("userInfoSpout");
    builder.setBolt("userInfoCountBolt", new UserInfoCountBolt(), Integer.valueOf(bolt_Count_Parallelism_hint)).shuffleGrouping("userInfoBolt", "userInfo_count_stream");
    builder.setBolt("userInfoVersionCountBolt", new UserInfoVersionCountBolt(), Integer.valueOf(bolt_Count_Parallelism_hint)).shuffleGrouping("userInfoBolt", "userInfo_version_count_stream");

    builder.setBolt("userInfoProjectBolt", new UserInfoProjectBolt(), Integer.valueOf(bolt_Parallelism_hint)).shuffleGrouping("userInfoProjectSpout");
    builder.setBolt("userInfoProjectCountBolt", new UserInfoProjectCountBolt(), Integer.valueOf(bolt_Count_Parallelism_hint)).shuffleGrouping("userInfoProjectBolt", "userInfo_project_count_stream");
    builder.setBolt("userInfoProjectVersionCountBolt", new UserInfoProjectVersionCountBolt(), Integer.valueOf(bolt_Count_Parallelism_hint)).shuffleGrouping("userInfoProjectBolt", "userInfo_project_version_count_stream");

    builder.setBolt("userActiveBolt", new UserActiveBolt(), Integer.valueOf(bolt_Parallelism_hint)).shuffleGrouping("userActiveSpout");
    builder.setBolt("userActiveCountBolt", new UserActiveCountBolt(), Integer.valueOf(bolt_Count_Parallelism_hint)).shuffleGrouping("userActiveBolt", "userActive_count_stream");
    builder.setBolt("userActiveVersionCountBolt", new UserActiveVersionCountBolt(), Integer.valueOf(bolt_Count_Parallelism_hint)).shuffleGrouping("userActiveBolt", "userActive_version_count_stream");

    builder.setBolt("userAdBolt", new UserAdBolt(), Integer.valueOf(bolt_Parallelism_hint)).shuffleGrouping("userAdSpout");
    builder.setBolt("userAdCountBolt", new UserAdCountBolt(), Integer.valueOf(bolt_Count_Parallelism_hint)).shuffleGrouping("userAdBolt", "userAd_count_stream");

    boolean kryoEnable = JStormUtils.parseBoolean(conf.get("kryo.enable"), false);

    if (kryoEnable == true) {
      System.out.println("Use Kryo ");
      boolean useJavaSer = JStormUtils.parseBoolean(conf.get("fall.back.on.java.serialization"), true);

      Config.setFallBackOnJavaSerialization(conf, useJavaSer);
      Config.registerSerialization(conf, UserVO.class);
      Config.registerSerialization(conf, AdDataVO.class);
    }

    int ackerNum = JStormUtils.parseInt(conf.get("topology.acker.executors"), 1).intValue();

    Config.setNumAckers(conf, ackerNum);

    int workerNum = JStormUtils.parseInt(conf.get("topology.workers"), 20).intValue();

    conf.put("topology.workers", Integer.valueOf(workerNum));
    conf.put("redis_host_key", ConfValues.REDIS_HOST);
    conf.put("zk_port_key", ConfValues.ZK_PORT);
    conf.put("zk_proxy_dir", ConfValues.ZK_PROXY_DIR);
  }

  public TopologyBuilder getBuilder()
  {
    return this.builder;
  }

  public LocalCluster getLocalCluster() {
    return this.cluster;
  }

  public static void LoadConf(String arg)
  {
    if (arg.endsWith("yaml"))
      conf = LoadConf.LoadYaml(arg);
    else
      conf = LoadConf.LoadProperty(arg);
  }

  public static boolean local_mode(Map conf)
  {
    String mode = (String)conf.get("storm.cluster.mode");
    if ((mode != null) && 
      (mode.equals("local"))) {
      return true;
    }

    return false;
  }

  public static void SetLocalTopology() throws Exception {
    TopologyBuilder builder = new TopologyBuilder();
    conf.put("bolt.parallel", Integer.valueOf(1));
    SetBuilder(builder, conf);
    LOG.debug("test");
    LOG.info("Submit log");
    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology("kok_sdk_stat", conf, builder.createTopology());
    Thread.sleep(600000L);
    cluster.killTopology("kok_sdk_stat");
    cluster.shutdown();
  }

  public static void SetRemoteTopology() throws AlreadyAliveException, InvalidTopologyException, TopologyAssignException
  {
    String streamName = (String)conf.get("topology.name");
    if (streamName == null) {
      streamName = "kok_sdk_stat";
    }
    TopologyBuilder builder = new TopologyBuilder();
    SetBuilder(builder, conf);
    conf.put("storm.cluster.mode", "distributed");
    StormSubmitter.submitTopology(streamName, conf, builder.createTopology());
  }

  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.err.println("Please input configuration file");
      System.exit(-1);
    }
    LoadConf(args[0]);
    if (local_mode(conf))
      SetLocalTopology();
    else
      SetRemoteTopology();
  }
}