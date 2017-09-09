package com.kkgame.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.TopologyAssignException;
import backtype.storm.topology.BoltDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import com.alibaba.jstorm.utils.JStormUtils;
import com.alibaba.jstorm.utils.LoadConf;
import com.kkgame.config.ConfValues;
import com.kkgame.user.bean.*;
import com.kkgame.user.bolt.*;
import com.kkgame.user.spout.*;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdStatTopology
{
  private TopologyBuilder builder = new TopologyBuilder();
  private LocalCluster cluster;
  private static Logger LOG = LoggerFactory.getLogger(AdStatTopology.class);
  public static final String TOPOLOGY_SPOUT_PARALLELISM_HINT = "spout.parallel";
  public static final String TOPOLOGY_BOLT_PARALLELISM_HINT = "bolt.parallel";
  public static final String TOPOLOGY_BOLT_COUNT_PARALLELISM_HINT = "bolt.count.parallel";
  public static final String TOPOLOGY_BOLT_SEND_COUNT_PARALLELISM_HINT = "bolt.send.count.parallel";
  private static Map conf = new HashMap();

  public static void SetBuilder(TopologyBuilder builder, Map conf)
  {
    int spout_Parallelism_hint = JStormUtils.parseInt(conf.get("spout.parallel"), 1).intValue();

    int bolt_Parallelism_hint = JStormUtils.parseInt(conf.get("bolt.parallel"), 2).intValue();

    int bolt_Count_Parallelism_hint = JStormUtils.parseInt(conf.get("bolt.count.parallel"), 1).intValue();

    int bolt_send_Count_Parallelism_hint = JStormUtils.parseInt(conf.get("bolt.send.count.parallel"), 1).intValue();

    builder.setSpout("adDataSendSpout", new AdDataSendSpout(), spout_Parallelism_hint);
    builder.setSpout("adDataSendSuccSpout", new AdDataSendSuccSpout(), spout_Parallelism_hint);
    builder.setSpout("adDataShowSpout", new AdDataShowSpout(), spout_Parallelism_hint);
    builder.setSpout("adDataClickSpout", new AdDataClickSpout(), spout_Parallelism_hint);
    builder.setSpout("adDataDownloadSpout", new AdDataDownloadSpout(), spout_Parallelism_hint);
    builder.setSpout("adDataInstalledSpout", new AdDataInstalledSpout(), spout_Parallelism_hint);
    builder.setSpout("adDataActivateSpout", new AdDataActivateSpout(), spout_Parallelism_hint);


    builder.setBolt("adDataSendBolt", new AdDataSendBolt(), bolt_Parallelism_hint).shuffleGrouping("adDataSendSpout");
    builder.setBolt("adDataSendCountBolt", new AdDataSendCountBolt(), (bolt_send_Count_Parallelism_hint)).fieldsGrouping("adDataSendBolt", "adData_send_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataSendVersionCountBolt", new AdDataSendVersionCountBolt(), (bolt_send_Count_Parallelism_hint)).fieldsGrouping("adDataSendBolt", "adData_send_version_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataSendProjectCountBolt", new AdDataSendProjectCountBolt(), (bolt_send_Count_Parallelism_hint)).fieldsGrouping("adDataSendBolt", "adData_send_project_count_stream", new Fields(new String[] { "imei" }));

    builder.setBolt("adDataSendSuccBolt", new AdDataSendSuccBolt(), bolt_Parallelism_hint).shuffleGrouping("adDataSendSuccSpout");
    builder.setBolt("adDataSendSuccCountBolt", new AdDataSendSuccCountBolt(), (bolt_send_Count_Parallelism_hint)).fieldsGrouping("adDataSendSuccBolt", "adData_sendSucc_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataSendSuccVersionCountBolt", new AdDataSendSuccVersionCountBolt(), (bolt_send_Count_Parallelism_hint)).fieldsGrouping("adDataSendSuccBolt", "adData_sendSucc_version_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataSendSuccProjectCountBolt", new AdDataSendSuccProjectCountBolt(), (bolt_send_Count_Parallelism_hint)).fieldsGrouping("adDataSendSuccBolt", "adData_sendSucc_project_count_stream", new Fields(new String[] { "imei" }));

    builder.setBolt("adDataShowBolt", new AdDataShowBolt(), bolt_Parallelism_hint).shuffleGrouping("adDataShowSpout");
    builder.setBolt("adDataShowCountBolt", new AdDataShowCountBolt(), (bolt_Count_Parallelism_hint)).fieldsGrouping("adDataShowBolt", "adData_show_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataShowVersionCountBolt", new AdDataShowVersionCountBolt(), (bolt_Count_Parallelism_hint)).fieldsGrouping("adDataShowBolt", "adData_show_version_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataShowProjectCountBolt", new AdDataShowProjectCountBolt(), (bolt_Count_Parallelism_hint)).fieldsGrouping("adDataShowBolt", "adData_show_project_count_stream", new Fields(new String[] { "imei" }));

    builder.setBolt("adDataClickBolt", new AdDataClickBolt(), bolt_Parallelism_hint).shuffleGrouping("adDataClickSpout");
    builder.setBolt("adDataClickCountBolt", new AdDataClickCountBolt(), (1)).fieldsGrouping("adDataClickBolt", "adData_click_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataClickVersionCountBolt", new AdDataClickVersionCountBolt(), (1)).fieldsGrouping("adDataClickBolt", "adData_click_version_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataClickProjectCountBolt", new AdDataClickProjectCountBolt(), (1)).fieldsGrouping("adDataClickBolt", "adData_click_project_count_stream", new Fields(new String[] { "imei" }));

    builder.setBolt("adDataDownloadBolt", new AdDataDownloadBolt(), bolt_Parallelism_hint).shuffleGrouping("adDataDownloadSpout");
    builder.setBolt("adDataDownloadCountBolt", new AdDataDownloadCountBolt(), (1)).fieldsGrouping("adDataDownloadBolt", "adData_download_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataDownloadVersionCountBolt", new AdDataDownloadVersionCountBolt(), (1)).fieldsGrouping("adDataDownloadBolt", "adData_download_version_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataDownloadProjectCountBolt", new AdDataDownloadProjectCountBolt(), (1)).fieldsGrouping("adDataDownloadBolt", "adData_download_project_count_stream", new Fields(new String[] { "imei" }));

    builder.setBolt("adDataInstalledBolt", new AdDataInstalledBolt(), bolt_Parallelism_hint).shuffleGrouping("adDataInstalledSpout");
    builder.setBolt("adDataInstalledCountBolt", new AdDataInstalledCountBolt(), (1)).fieldsGrouping("adDataInstalledBolt", "adData_installed_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataInstalledVersionCountBolt", new AdDataInstalledVersionCountBolt(), (1)).fieldsGrouping("adDataInstalledBolt", "adData_installed_version_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataInstalledProjectCountBolt", new AdDataInstalledProjectCountBolt(), (1)).fieldsGrouping("adDataInstalledBolt", "adData_installed_project_count_stream", new Fields(new String[] { "imei" }));

    builder.setBolt("adDataActivateBolt", new AdDataActivateBolt(), (1)).shuffleGrouping("adDataActivateSpout");
    builder.setBolt("adDataActivateCountBolt", new AdDataActivateCountBolt(), (1)).fieldsGrouping("adDataActivateBolt", "adData_activate_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataActivateVersionCountBolt", new AdDataActivateVersionCountBolt(), (1)).fieldsGrouping("adDataActivateBolt", "adData_show_version_count_stream", new Fields(new String[] { "imei" }));
    builder.setBolt("adDataActivateProjectCountBolt", new AdDataActivateProjectCountBolt(), (1)).fieldsGrouping("adDataActivateBolt", "adData_show_project_count_stream", new Fields(new String[] { "imei" }));

    builder.setSpout("adDataLinkSpout", new AdDataLinkSpout(), spout_Parallelism_hint);
    builder.setBolt("adDataLinkBolt", new AdDataLinkBolt(), (1)).shuffleGrouping("adDataLinkSpout");
    builder.setBolt("adDataLinkCountBolt", new AdDataLinkCountBolt(), (1)).fieldsGrouping("adDataLinkBolt", "adData_link_count_stream", new Fields(new String[] { "imei" }));

    builder.setSpout("adDataDssdkSpout", new AdDataDssdkSpout(), spout_Parallelism_hint);
    builder.setBolt("adDataDssdkBolt", new AdDataDssdkBolt(), (1)).shuffleGrouping("adDataDssdkSpout");
    builder.setBolt("adDataDssdkCountBolt", new AdDataDssdkCountBolt(), (1)).fieldsGrouping("adDataDssdkBolt", "adData_dssdk_count_stream", new Fields(new String[] { "imei" }));

    builder.setSpout("adDataSubClickSpout", new AdDataSubClickSpout(), spout_Parallelism_hint);
    builder.setBolt("adDataSubClickBolt", new AdDataSubClickBolt(), (1)).shuffleGrouping("adDataSubClickSpout");
    builder.setBolt("adDataSubClickCountBolt", new AdDataSubClickCountBolt(), (1)).fieldsGrouping("adDataSubClickBolt", "subData_click_count_stream", new Fields(new String[] { "imei" }));

    builder.setSpout("adDataSubActiveSpout", new AdDataSubActiveSpout(), spout_Parallelism_hint);
    builder.setBolt("adDataSubActiveBolt", new AdDataSubActiveBolt(), (1)).shuffleGrouping("adDataSubActiveSpout");
    builder.setBolt("adDataSubActiveCountBolt", new AdDataSubActiveCountBolt(), (1)).fieldsGrouping("adDataSubActiveBolt", "adData_active_count_stream", new Fields(new String[] { "clickid" }));

    builder.setSpout("adDataSubLinkSpout", new AdDataSubLinkSpout(), spout_Parallelism_hint);
    builder.setBolt("adDataSubLinkBolt", new AdDataSubLinkBolt(), (1)).shuffleGrouping("adDataSubLinkSpout");
    builder.setBolt("adDataSubLinkCountBolt", new AdDataSubLinkCountBolt(), (1)).fieldsGrouping("adDataSubLinkBolt", "subData_link_count_stream", new Fields(new String[] { "adId" }));

    boolean kryoEnable = JStormUtils.parseBoolean(conf.get("kryo.enable"), false);

    if (kryoEnable == true) {
      System.out.println("Use Kryo ");
      boolean useJavaSer = JStormUtils.parseBoolean(conf.get("fall.back.on.java.serialization"), true);

      Config.setFallBackOnJavaSerialization(conf, useJavaSer);
      Config.registerSerialization(conf, UserVO.class);
      Config.registerSerialization(conf, AdDataVO.class);
      Config.registerSerialization(conf, AdLinkDataVO.class);
      Config.registerSerialization(conf, DssdkDataVO.class);
      Config.registerSerialization(conf, SubActiveVO.class);
      Config.registerSerialization(conf, SubClickVO.class);
      Config.registerSerialization(conf, SubLinkVO.class);
    }

    int ackerNum = JStormUtils.parseInt(conf.get("topology.acker.executors"), 1).intValue();

    Config.setNumAckers(conf, ackerNum);

    int workerNum = JStormUtils.parseInt(conf.get("topology.workers"), 20).intValue();

    conf.put("topology.workers", (workerNum));
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
    conf.put("bolt.parallel", (1));
    SetBuilder(builder, conf);

    LOG.debug("test");
    LOG.info("Submit log");
    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology("kok_sdk_stat", conf, builder.createTopology());

    Thread.sleep(600000L);

    cluster.killTopology("kok_sdk_stat");

    cluster.shutdown();
  }

  public static void SetRemoteTopology()
    throws AlreadyAliveException, InvalidTopologyException, TopologyAssignException
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

  public static void main(String[] args) throws Exception
  {
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