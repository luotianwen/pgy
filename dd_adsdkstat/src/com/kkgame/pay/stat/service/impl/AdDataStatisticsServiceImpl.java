package com.kkgame.pay.stat.service.impl;

import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.dao.FeeDao;
import com.kkgame.pay.stat.dao.MasterDao;
import com.kkgame.pay.stat.dao.StatisticsDao;
import com.kkgame.pay.stat.service.AdDataStatisticsService;
import com.kkgame.pay.stat.util.Constants;
import com.kkgame.pay.stat.util.DatabaseException;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.text.DecimalFormat;
import java.util.*;

import static com.kkgame.pay.stat.util.CalendarFormat.getYmd;
import static com.sun.tools.javac.util.Constants.format;

public class AdDataStatisticsServiceImpl implements AdDataStatisticsService {

    private static final Log log = LogFactory.getLog(AdDataStatisticsServiceImpl.class);
    private StatisticsDao statisticsDao;

    private MasterDao masterDao;
    private FeeDao feeDao;
    private static JedisResourcePool jedisPool;

    static {
        StringBuffer hostPort = new StringBuffer();
        hostPort.append(Constants.REDIS_HOST).append(":").append(Constants.ZK_PORT);
        jedisPool = RoundRobinJedisPool.create().curatorClient(hostPort.toString(), 30000)
                .zkProxyDir(Constants.ZK_PROXY_DIR).build();
    }

    @Override
    public void statDSData(int i, int type) throws DatabaseException {
        {
            // TODO Auto-generated method stub
            long nowTime = System.currentTimeMillis();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_DSSDK_STAT).append("_").append(type).append("_").append(getYmd(i));
            List<Data> list = new ArrayList<Data>();

            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                list = new ArrayList<Data>();
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setAdId(Integer.parseInt(key[1]));
                            data.setCountry(Integer.parseInt(key[3]));
                            data.setClickTimes(score);
                            list.add(data);
                        }
                    }
                }
                if (null != list && list.size() > 0) {
                    int size = list.size();
                    List<Data> insertList = new ArrayList<Data>();
                    for (int k = 0; k < size; k++) {
                        insertList.add(list.get(k));
                        if (k > 0 && k % Constants.SQL_COUNT == 0) {
                            statisticsDao.insertDssdkData(i, Constants.LINK_TYPE_MAP.get(type), insertList);
                            insertList = new ArrayList<Data>();
                        }
                    }
                    if (insertList.size() > 0) {
                        statisticsDao.insertDssdkData(i, Constants.LINK_TYPE_MAP.get(type), insertList);
                    }
                }
            }

            long lastTime = System.currentTimeMillis();
            log.info("time is" + (lastTime - nowTime) / 1000);
        }
    }

    @Override
    public void statDSData(int i) throws DatabaseException {
        statisticsDao.updateDsData(i);
    }

    @Override
    public void insertDsPv(int i) throws DatabaseException {
        long nowTime = System.currentTimeMillis();
        List<Data> sendDataList = statisticsDao.getDsPv(i);
        if (sendDataList != null && sendDataList.size() > 0) {
            masterDao.insertDsPv(i, sendDataList);
        }
        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    @Override
    public void statSubAdData(int i) {

        long nowTime = System.currentTimeMillis();

        statisticsDao.deleteTempAdSubData(i);

        String day = getYmd(i);
        StringBuffer sb = new StringBuffer();
        List<Data> adPay = new ArrayList<Data>();
        sb.append(Constants.AD_CLICK_DAY).append("|").append(day);
        try (Jedis jedis = jedisPool.getResource()) {

            Map<String, Data> map = new HashMap<String, Data>();
            long count = jedis.zcard(sb.toString());

            int times = (int) (count / Constants.RECORD_COUNT);
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                for (Tuple tuple : adDataList) {
                    String key = tuple.getElement();
                    int clickTimes = (int) tuple.getScore();
                    String[] keys = key.split("\\|");
                    Data data = new Data();
                    data.setAdId(Integer.valueOf(keys[0]));
                    data.setCountry(Integer.valueOf(keys[1]));
                    data.setProjectId(Integer.valueOf(keys[2]));
                    data.setPlatformId(Integer.valueOf(keys[3]));
                    data.setOperatorId(Integer.valueOf(keys[4]));
                    data.setAdType(Integer.valueOf(keys[5]));
                    data.setClickTimes(clickTimes);
                    data.setStatDate(day);
                    Double activeTimes = jedis.zscore(Constants.AD_ACTIVE_DAY + "|" + day, key);
                    int activecount = 0;
                    if (activeTimes != null) {
                        activecount = (int) activeTimes.doubleValue();
                    }
                    data.setActivateTimes(activecount);
                    Double income = jedis.zscore(Constants.AD_PAY_DAY + "|" + day, key);
                    if (income == null) {
                        income = 0.0;
                    }
                    data.setIncome(income);
                    adPay.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e);
        }


        if (null != adPay && adPay.size() > 0) {
            int size = adPay.size();
            List<Data> insertList = new ArrayList<Data>();
            for (int k = 0; k < size; k++) {
                insertList.add(adPay.get(k));
                if (k > 0 && k % Constants.SQL_COUNT == 0) {
                    statisticsDao.insertAdSubData(i, insertList);
                    insertList = new ArrayList<Data>();
                }
            }
            if (insertList.size() > 0) {
                statisticsDao.insertAdSubData(i, insertList);
            }
        }

        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    @Override
    public void statTotalSubAdData(int i) {
        long nowTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        String day = getYmd(i);
        sb.append(Constants.AD_TOTAL_SUB_ACTIVE);
        StringBuffer sb2 = new StringBuffer();
        sb2.append(Constants.AD_TOTAL_SUB_STAT);
        try (Jedis jedis = jedisPool.getResource()) {

            Map<String, Data> map = new HashMap<String, Data>();
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            List<Data> total = null;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb2.toString(), start, end);
                total = adDataTotal(adDataList, day, jedis);
            }

            DecimalFormat df = new DecimalFormat("0.00");
            if (null != total && total.size() > 0) {
                int size = total.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(total.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertSubTotal(i, insertList);
                        List<Data> datas = new ArrayList<Data>();
                        datavoToSubvo(df, insertList, datas);
                        masterDao.putDataToSubscribe(datas);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertSubTotal(i, insertList);
                    List<Data> datas = new ArrayList<Data>();
                    datavoToSubvo(df, insertList, datas);
                    masterDao.putDataToSubscribe(datas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e);
        }


        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);


    }

    @Override
    public void statOfferShowDate(int i) throws DatabaseException {
        statisticsDao.deleteOfferSdkData(i);

        long nowTime = System.currentTimeMillis();

        String day = getYmd(i);
        //展示量
        StringBuffer sb = new StringBuffer();
        List<Data> list = new ArrayList<Data>();
        List<Data> adPay = new ArrayList<Data>();
        Map<String, Data> dataMap = new HashMap<String, Data>();
        sb.append(Constants.OFFERSDK_SHOW_STAT_USERS).append("|").append(day);
        try (Jedis jedis = jedisPool.getResource()) {
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                for (Tuple tuple : adDataList) {
                    String keys = tuple.getElement();
                    int score = (int) tuple.getScore();
                    if (keys != null) {
                        String[] key = keys.split("\\|");
                        Data data = new Data();
                        data.setStatDate(day);
                        data.setProjectId(Integer.parseInt(key[0]));
                        data.setAdId(Integer.parseInt(key[1]));
                        data.setAdType(Integer.parseInt(key[2]));
                        data.setCountry(Integer.parseInt(key[3]));
                        data.setOperatorId(Integer.parseInt(key[4]));
                        data.setShowCount(score);

                        Double activeTimes = jedis.zscore(Constants.OFFERSDK_ACTIVE_DAY + "|" + day, keys);
                        int activecount = 0;
                        if (activeTimes != null) {
                            activecount = (int) activeTimes.doubleValue();
                        }
                        data.setActivateTimes(activecount);
                        Double income = jedis.zscore(Constants.OFFERSDK_PAY_DAY + "|" + day, keys);
                        if (income == null) {
                            income = 0.0;
                        }
                        data.setIncome(income);

                        dataMap.put(keys, data);
                    }
                }
            }
        }
        sb = new StringBuffer();
        sb.append(Constants.OFFERSDK_SHOW_DAY).append("|").append(day);
        try (Jedis jedis = jedisPool.getResource()) {
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                for (Tuple tuple : adDataList) {
                    String keys = tuple.getElement();
                    int score = (int) tuple.getScore();
                    if (keys != null) {
                        if (dataMap.containsKey(keys)) {
                            dataMap.get(keys).setShowTimes(score);
                        }
                    }
                }
            }

            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertOfferSdkShowData(i, insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertOfferSdkShowData(i,insertList);
                    insertList = new ArrayList<Data>();
                }
            }
        }

        //点击量
        sb = new StringBuffer();
        sb.append(Constants.OFFERSDK_CLICK_DAY).append("|").append(day);
        try (Jedis jedis = jedisPool.getResource()) {
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                for (Tuple tuple : adDataList) {
                    String keys = tuple.getElement();
                    int score = (int) tuple.getScore();
                    if (keys != null) {
                        String[] key = keys.split("\\|");
                        Data data = new Data();
                        data.setStatDate(day);
                        data.setProjectId(Integer.parseInt(key[0]));
                        data.setAdId(Integer.parseInt(key[1]));
                        data.setAdType(Integer.parseInt(key[2]));
                        data.setCountry(Integer.parseInt(key[3]));
                        data.setOperatorId(Integer.parseInt(key[4]));
                        data.setClickCount(score);

                        Double activeTimes = jedis.zscore(Constants.OFFERSDK_ACTIVE_DAY + "|" + day, keys);
                        int activecount = 0;
                        if (activeTimes != null) {
                            activecount = (int) activeTimes.doubleValue();
                        }
                        data.setActivateTimes(activecount);
                        Double income = jedis.zscore(Constants.OFFERSDK_PAY_DAY + "|" + day, keys);
                        if (income == null) {
                            income = 0.0;
                        }
                        data.setIncome(income);

                        dataMap.put(keys, data);
                    }
                }
            }
        }
        sb = new StringBuffer();
        sb.append(Constants.OFFERSDK_CLICK_DAY).append("|").append(day);
        try (Jedis jedis = jedisPool.getResource()) {
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                for (Tuple tuple : adDataList) {
                    String keys = tuple.getElement();
                    int score = (int) tuple.getScore();
                    if (keys != null) {
                        if (dataMap.containsKey(keys)) {
                            dataMap.get(keys).setClickTimes(score);
                        }
                    }
                }
            }

            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertOfferSdkClickData(i,  insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertOfferSdkClickData(i,  insertList);
                    insertList = new ArrayList<Data>();
                }
            }
        }

        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);


    }

    @Override
    public void offerSdkSendData(int i) throws DatabaseException {
        long nowTime = System.currentTimeMillis();
        // 下发数据
        List<Data> list = new ArrayList<Data>();
        Map<String, Data> dataMap = new HashMap<String, Data>();
        String day = getYmd(i);
        StringBuffer sb = new StringBuffer();
        sb.append(Constants.OFFERSDK_SEND_AD_STAT_USERS).append("_").append(day);
        try (Jedis jedis = jedisPool.getResource()) {
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                for (Tuple tuple : adDataList) {
                    String keys = tuple.getElement();
                    int score = (int) tuple.getScore();
                    if (keys != null) {
                        String[] key = keys.split("_");
                        Data data = new Data();
                        data.setStatDate(day);
                        data.setProjectId(Integer.parseInt(key[0]));
                        data.setAdId(Integer.parseInt(key[1]));
                        data.setAdType(Integer.valueOf(key[2]));
                        data.setCountry(Integer.parseInt(key[3]));
                        data.setOperatorId(Integer.parseInt(key[4]));
                        data.setSendCount(score);
                        dataMap.put(keys, data);
                    }
                }
            }
        } catch (Exception e) {
            log.debug(e);
        }
        sb = new StringBuffer();
        sb.append(Constants.OFFERSDK_SEND_AD_STAT_TIMES).append("_").append(getYmd(i));
        try (Jedis jedis = jedisPool.getResource()) {
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                for (Tuple tuple : adDataList) {
                    String keys = tuple.getElement();
                    int score = (int) tuple.getScore();
                    if (keys != null) {
                        if (dataMap.containsKey(keys)) {
                            dataMap.get(keys).setSendTimes(score);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.debug(e);
        }
        if (dataMap != null && dataMap.size() > 0) {
            for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                list.add(entry.getValue());
            }
            int size = list.size();
            List<Data> insertList = new ArrayList<Data>();
            for (int k = 0; k < size; k++) {
                insertList.add(list.get(k));
                if (k > 0 && k % Constants.SQL_COUNT == 0) {
                    statisticsDao.insertOfferSdkSendData(i, insertList);
                    insertList = new ArrayList<Data>();
                }
            }
            if (insertList.size() > 0) {
                statisticsDao.insertOfferSdkSendData(i, insertList);

            }
        }
        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    @Override
    public void offerSdkActiveData(int i) {
        long nowTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        String day = getYmd(i);
        sb.append(Constants.OFFERSDK_TOTAL_ACTIVE);
        StringBuffer sb2 = new StringBuffer();
        sb2.append(Constants.AD_TOTAL_OFFERSDK_STAT);
        try (Jedis jedis = jedisPool.getResource()) {

            Map<String, Data> map = new HashMap<String, Data>();
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            List<Data> total = null;
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb2.toString(), start, end);
                total = adOfferSdkDataTotal(adDataList, day, jedis);
            }

            DecimalFormat df = new DecimalFormat("0.00");
            if (null != total && total.size() > 0) {
                int size = total.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(total.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
//                        statisticsDao.insertOfferSdkTotal(i, insertList);
                        List<Data> datas = new ArrayList<Data>();
                        datavoToSubvo(df, insertList, datas);
                        masterDao.putDataToOfferSdk(datas);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
//                    statisticsDao.insertOfferSdkTotal(i, insertList);
                    List<Data> datas = new ArrayList<Data>();
                    datavoToSubvo(df, insertList, datas);
                    masterDao.putDataToOfferSdk(datas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e);
        }


        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }


    private List<Data> adOfferSdkDataTotal(Set<Tuple> adDataList, String day, Jedis jedis) {
        List<Data> datas = new ArrayList<Data>();
        if (adDataList != null && adDataList.size() > 0) {
            for (Tuple tuple : adDataList) {
                String adId = tuple.getElement();
                int clickTimes = (int) tuple.getScore();
                Double actives = jedis.zscore(Constants.OFFERSDK_TOTAL_ACTIVE, adId);
                int activeTimes = 0;
                if (actives != null) {
                    activeTimes = (int) actives.doubleValue();
                }
                Double incomes = jedis.zscore(Constants.AD_TOTAL_OFFERSDK_INCOME, adId);
                if (incomes == null) {
                    incomes = 0.0;
                }
                Data data = new Data();
                data.setClickCount(clickTimes);
                data.setAdId(Integer.valueOf(adId));
                data.setActivateCount(activeTimes);
                data.setStatDate(day);
                data.setIncome(incomes);
                datas.add(data);
            }
        }
        return datas;
    }


    private void datavoToSubvo(DecimalFormat df, List<Data> insertList, List<Data> datas) {
        int a;
        int b;
        double c;
        for (Data d : insertList) {
            Data data = new Data();
            a = d.getActivateCount();
            b = d.getClickCount();
            c = d.getIncome();
            float cr = (float) a / b;
            float ecpm = (float) (c * 1000 / b);
            String ctstr = df.format(cr);
            data.setCr(Double.valueOf(ctstr));
            data.setAdId(d.getAdId());
            String ecpmstr = df.format(ecpm);
            data.setCpm(Double.valueOf(ecpmstr));
            datas.add(data);
        }
    }

    private List<Data> adDataTotal(Set<Tuple> adDataList, String day, Jedis jedis) {
        List<Data> datas = new ArrayList<Data>();
        if (adDataList != null && adDataList.size() > 0) {
            for (Tuple tuple : adDataList) {
                String adId = tuple.getElement();
                int clickTimes = (int) tuple.getScore();
                Double actives = jedis.zscore(Constants.AD_TOTAL_SUB_ACTIVE, adId);
                int activeTimes = 0;
                if (actives != null) {
                    activeTimes = (int) actives.doubleValue();
                }
                Double incomes = jedis.zscore(Constants.AD_TOTAL_SUB_INCOME, adId);
                if (incomes == null) {
                    incomes = 0.0;
                }
                Data data = new Data();
                data.setClickCount(clickTimes);
                data.setAdId(Integer.valueOf(adId));
                data.setActivateCount(activeTimes);
                data.setStatDate(day);
                data.setIncome(incomes);
                datas.add(data);
            }
        }
        return datas;
    }

    private List<Data> adAndPay(Set<Tuple> adDataList, String day, Jedis jedis) {
        List<Data> datas = new ArrayList<Data>();
        if (adDataList != null && adDataList.size() > 0) {
            for (Tuple tuple : adDataList) {
                String adId = tuple.getElement();
                int clickTimes = (int) tuple.getScore();
                Double adDayPay = jedis.zscore(Constants.AD_PAY_DAY + "|" + day, adId);
                if (adDayPay == null) {
                    adDayPay = 0.0D;
                }
                Data data = new Data();
                data.setClickTimes(clickTimes);
                data.setAdId(Integer.valueOf(adId));
                data.setIncome(adDayPay);
                data.setStatDate(day);
                Double activeTimes = jedis.zscore(Constants.AD_ACTIVE_DAY + "|" + day, adId);
                int activecount = 0;
                if (activeTimes != null) {
                    activecount = (int) activeTimes.doubleValue();
                }
                data.setActivateTimes(activecount);
                datas.add(data);
            }
        }
        return datas;
    }

    private void getDataList(List<Data> list, Map<String, Data> map, Map<String, Data> map1, String day) {
        Data data = null;
        for (String key : map1.keySet()) {
            if (map.get(key) != null) {
                map.get(key).setClickCount(map1.get(key).getClickCount());
                data = map.get(key);
            } else {
                data = map1.get(key);
            }
            data.setStatDate(day);
            list.add(data);
        }
    }

    private Map<String, Data> getSubDay(StringBuffer sb, Jedis jedis, int i, int type) {
        Map<String, Data> map = new HashMap<String, Data>();
        long count = jedis.zcard(sb.toString());
        int times = (int) count / Constants.RECORD_COUNT;
        long remainder = count % Constants.RECORD_COUNT;
        long start = 0;
        long end = 0;
        int flag = 0;
        if (remainder > 0) {
            flag = 1;
        }
        flag += times;
        for (int j = 0; j < flag; j++) {
            start = (j * Constants.RECORD_COUNT);
            end = (start + Constants.RECORD_COUNT - 1);
            Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
            map = parseData(adDataList, jedis, i, type);
        }
        return map;
    }

    private Map<String, Data> parseData(Set<Tuple> adDataList, Jedis jedis, int i, int type) {
        Map<String, Data> map = new HashMap<String, Data>();
        if (adDataList != null && adDataList.size() > 0) {
            for (Tuple tuple : adDataList) {
                String keys = tuple.getElement();
                int score = (int) tuple.getScore();
                if (keys != null) {
                    Data data = new Data();
                    if (type == 0) {
                        data.setAdId(Integer.valueOf(keys));
                    } else {
                        data.setProjectId(Integer.valueOf(keys));
                    }
                    if (i == 0) {
                        data.setActivateCount(score);
                    } else {
                        data.setClickCount(score);
                    }
                    map.put(keys, data);
                }
            }
        }
        return map;
    }


    private List<Data> getSubData(Map<String, Integer> aclickTotal) {
        List<Data> datas = new ArrayList<Data>();
        for (String s : aclickTotal.keySet()) {
            Data data = new Data();
            data.setActivateCount(aclickTotal.get(s));
            data.setClickCount(aclickTotal.get(s));
            data.setAdId(Integer.valueOf(s));
            datas.add(data);
        }
        return datas;
    }

    private Map getSubTotal(Jedis jedis, String jedKey) {
        long count = jedis.zcard(jedKey);
        Set<Tuple> tuples = jedis.zrangeWithScores(jedKey, 0, count - 1);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Tuple tuple : tuples) {
            String key = tuple.getElement();
            int score = (int) tuple.getScore();
            if (key != null) {
                map.put(key, score);
            }
        }
        return map;
    }

    @Override
    public void statLinkData(int i, int type) throws DatabaseException {
        long nowTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        sb.append(Constants.AD_DATA_LINK_STAT).append("_").append(type).append("_").append(getYmd(i));
        List<Data> list = new ArrayList<Data>();

        try (Jedis jedis = jedisPool.getResource()) {
            long count = jedis.zcard(sb.toString());
            int times = (int) count / Constants.RECORD_COUNT;
            long remainder = count % Constants.RECORD_COUNT;
            long start = 0;
            long end = 0;
            int flag = 0;
            if (remainder > 0) {
                flag = 1;
            }
            flag += times;
            list = new ArrayList<Data>();
            for (int j = 0; j < flag; j++) {
                start = (j * Constants.RECORD_COUNT);
                end = (start + Constants.RECORD_COUNT - 1);
                Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                for (Tuple tuple : adDataList) {
                    String keys = tuple.getElement();
                    int score = (int) tuple.getScore();
                    if (keys != null) {
                        String[] key = keys.split("\\|");
                        Data data = new Data();
                        data.setStatDate(key[0]);
                        data.setProjectId(Integer.parseInt(key[1]));
                        data.setAdId(Integer.parseInt(key[2]));
                        data.setLevel(Integer.parseInt(key[4]));
                        data.setClickTimes(score);
                        list.add(data);
                    }
                }
            }
            if (null != list && list.size() > 0) {
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertAdLinkData(i, Constants.LINK_TYPE_MAP.get(type), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertAdLinkData(i, Constants.LINK_TYPE_MAP.get(type), insertList);
                }
            }
        }

        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    @Override
    public void statAdLinkData(int i) throws DatabaseException {
        statisticsDao.updateLinkData(i);
        statisticsDao.insertAdTotalLinkData(i);
    }

    @Override
    public void statAdLinkDataUV(int i) throws DatabaseException {
        {
            // TODO Auto-generated method stub
            long nowTime = System.currentTimeMillis();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_LINK_UV).append("_").append(getYmd(i));
            List<Data> list = new ArrayList<Data>();

            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                list = new ArrayList<Data>();
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setAdId(Integer.parseInt(key[1]));
                            data.setClickCount(score);
                            list.add(data);
                        }
                    }
                }
                if (null != list && list.size() > 0) {
                    int size = list.size();
                    List<Data> insertList = new ArrayList<Data>();
                    for (int k = 0; k < size; k++) {
                        insertList.add(list.get(k));
                        if (k > 0 && k % Constants.SQL_COUNT == 0) {
                            statisticsDao.insertAdLinkDataUv(i, insertList);
                            insertList = new ArrayList<Data>();
                        }
                    }
                    if (insertList.size() > 0) {
                        statisticsDao.insertAdLinkDataUv(i, insertList);
                    }
                }
            }

            long lastTime = System.currentTimeMillis();
            log.info("time is" + (lastTime - nowTime) / 1000);
        }
    }


    // 广告发送接收数据AD_DATA_
    @Override
    public void statSendData(int i, int type) throws DatabaseException {
        long nowTime = System.currentTimeMillis();
        if (type == Constants.SDK) {
            statisticsDao.deleteTempAdSendData(i);
        }
        // 发送数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_AD_STAT_USERS).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdId(Integer.parseInt(key[7]));
                            data.setAdType(Integer.parseInt(key[8]));
                            data.setSendCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_AD_STAT_TIMES).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setSendTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertAdSendData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertAdSendData(i, Constants.AD_TYPE_MAP.get(adType), insertList);

                }
            }
        }
        // 接收数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_SUCC_AD_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdId(Integer.parseInt(key[7]));
                            data.setAdType(Integer.parseInt(key[8]));
                            data.setReceiveCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_SUCC_AD_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();

                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setReceiveTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertAdReceiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertAdReceiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    // 展示，点击，下载，安装AD_DATA_
    @Override
    public void statData(int i, int type) throws DatabaseException {
        long nowTime = System.currentTimeMillis();
        // 展示数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SHOW_AD_STAT_USERS).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdId(Integer.parseInt(key[7]));
                            data.setAdType(Integer.parseInt(key[8]));
                            data.setShowCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SHOW_AD_STAT_TIMES).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setShowTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertShowData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertShowData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }

            }
        }

        // 点击数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_CLICK_AD_STAT_USERS).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdId(Integer.parseInt(key[7]));
                            data.setAdType(Integer.parseInt(key[8]));
                            data.setClickCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_CLICK_AD_STAT_TIMES).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setClickTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertClickData(i, Constants.AD_TYPE_MAP.get(adType), insertList);

                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertClickData(i, Constants.AD_TYPE_MAP.get(adType), insertList);

                }
            }
        }

        // 下载数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_DOWNLOAD_AD_STAT_USERS).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdId(Integer.parseInt(key[7]));
                            data.setAdType(Integer.parseInt(key[8]));
                            data.setDownloadCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_DOWNLOAD_AD_STAT_TIMES).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setDownloadTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertDownloadData(i, Constants.AD_TYPE_MAP.get(adType), insertList);

                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertDownloadData(i, Constants.AD_TYPE_MAP.get(adType), insertList);

                }
            }
        }
        // 安装数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_INSTALLED_AD_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                log.info("install users count" + count);
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdId(Integer.parseInt(key[7]));
                            data.setAdType(Integer.parseInt(key[8]));
                            data.setInstallCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_INSTALLED_AD_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                log.info("install users times" + count);
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setInstallTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                log.info("insert intall size" + size);
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertInstallData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertInstallData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        // 激活数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_ACTIVATE_AD_STAT_USERS).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdId(Integer.parseInt(key[7]));
                            data.setAdType(Integer.parseInt(key[8]));
                            data.setActivateCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_ACTIVATE_AD_STAT_TIMES).append("_").append(getYmd(i)).append("_")
                    .append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setActivateTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertActiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertActiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    // 项目发送，接收，点击，展示，下载，安装数据REGISTER_DATA_
    @Override
    public void statProjectData(int i, int type) throws DatabaseException {
        long nowTime = System.currentTimeMillis();
        // statisticsDao.deleteProjectData(i);
        // 展示
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_PROJECT_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdType(Integer.parseInt(key[7]));
                            data.setSendCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_PROJECT_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setSendTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertProjectSendData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertProjectSendData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        // 接收数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_SUCC_PROJECT_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdType(Integer.parseInt(key[7]));
                            data.setReceiveCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_SUCC_PROJECT_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setReceiveTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertProjectReceiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertProjectReceiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }

        // 展示数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SHOW_PROJECT_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdType(Integer.parseInt(key[7]));
                            data.setShowCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SHOW_PROJECT_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setShowTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertProjectShowData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertProjectShowData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }

        // 点击数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_CLICK_PROJECT_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdType(Integer.parseInt(key[7]));
                            data.setClickCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_CLICK_PROJECT_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setClickTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertProjectClickData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertProjectClickData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }

        // 下载数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_DOWNLOAD_PROJECT_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdType(Integer.parseInt(key[7]));
                            data.setDownloadCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_DOWNLOAD_PROJECT_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setDownloadTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertProjectDownloadData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertProjectDownloadData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        // 安装数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_INSTALLED_PROJECT_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdType(Integer.parseInt(key[7]));
                            data.setInstallCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_INSTALLED_PROJECT_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setInstallTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertProjectInstallData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertProjectInstallData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        // 激活数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_ACTIVATE_PROJECT_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setProjectId(Integer.parseInt(key[2]));
                            data.setChannelId(key[3]);
                            data.setPluginId(Integer.parseInt(key[4]));
                            data.setCountry(Integer.parseInt(key[5]));
                            data.setCountryLevel(Integer.parseInt(key[6]));
                            data.setAdType(Integer.parseInt(key[7]));
                            data.setActivateCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_ACTIVATE_PROJECT_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setActivateTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertProjectActiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertProjectActiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    // 项目发送，接收，点击，展示，下载，安装数据REGISTER_DATA_
    @Override
    public void statVersionProjectData(int i, int type) throws DatabaseException {
        // statisticsDao.deleteProjectData(i);
        // 展示
        long nowTime = System.currentTimeMillis();
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_VERSION_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setVersion(key[2]);
                            data.setAdType(Integer.parseInt(key[3]));
                            data.setSendCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_VERSION_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setSendTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertVersionProjectSendData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertVersionProjectSendData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        // 接收数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_SUCC_VERSION_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setVersion(key[2]);
                            data.setAdType(Integer.parseInt(key[3]));
                            data.setReceiveCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SEND_SUCC_VERSION_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setReceiveTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertVersionProjectReceiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertVersionProjectReceiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }

        // 展示数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SHOW_VERSION_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setVersion(key[2]);
                            data.setAdType(Integer.parseInt(key[3]));
                            data.setShowCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_SHOW_VERSION_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setShowTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertVersionProjectShowData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertVersionProjectShowData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }

        // 点击数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_CLICK_VERSION_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setVersion(key[2]);
                            data.setAdType(Integer.parseInt(key[3]));
                            data.setClickCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_CLICK_VERSION_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setClickTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertVersionProjectClickData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertVersionProjectClickData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }

        // 下载数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_DOWNLOAD_VERSION_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setVersion(key[2]);
                            data.setAdType(Integer.parseInt(key[3]));
                            data.setDownloadCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_DOWNLOAD_VERSION_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setDownloadTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertVersionProjectDownloadData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertVersionProjectDownloadData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        // 安装数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_INSTALLED_VERSION_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setVersion(key[2]);
                            data.setAdType(Integer.parseInt(key[3]));
                            data.setInstallCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_INSTALLED_VERSION_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setInstallTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertVersionProjectInstallData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertVersionProjectInstallData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        // 激活数据
        for (Integer adType : Constants.AD_TYPE_LIST) {
            List<Data> list = new ArrayList<Data>();
            Map<String, Data> dataMap = new HashMap<String, Data>();
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.AD_DATA_ACTIVATE_VERSION_STAT_USERS).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            String[] key = keys.split("\\|");
                            Data data = new Data();
                            data.setStatDate(key[0]);
                            data.setType(Integer.parseInt(key[1]));
                            data.setVersion(key[2]);
                            data.setAdType(Integer.parseInt(key[3]));
                            data.setActivateCount(score);
                            dataMap.put(keys, data);
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            sb = new StringBuffer();
            sb.append(Constants.AD_DATA_ACTIVATE_VERSION_STAT_TIMES).append("_").append(getYmd(i))
                    .append("_").append(adType);
            try (Jedis jedis = jedisPool.getResource()) {
                long count = jedis.zcard(sb.toString());
                int times = (int) count / Constants.RECORD_COUNT;
                long remainder = count % Constants.RECORD_COUNT;
                long start = 0;
                long end = 0;
                int flag = 0;
                if (remainder > 0) {
                    flag = 1;
                }
                flag += times;
                for (int j = 0; j < flag; j++) {
                    start = (j * Constants.RECORD_COUNT);
                    end = (start + Constants.RECORD_COUNT - 1);
                    Set<Tuple> adDataList = jedis.zrangeWithScores(sb.toString(), start, end);
                    for (Tuple tuple : adDataList) {
                        String keys = tuple.getElement();
                        int score = (int) tuple.getScore();
                        if (keys != null) {
                            if (dataMap.containsKey(keys)) {
                                dataMap.get(keys).setActivateTimes(score);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            if (dataMap != null && dataMap.size() > 0) {
                for (Map.Entry<String, Data> entry : dataMap.entrySet()) {
                    list.add(entry.getValue());
                }
                int size = list.size();
                List<Data> insertList = new ArrayList<Data>();
                for (int k = 0; k < size; k++) {
                    insertList.add(list.get(k));
                    if (k > 0 && k % Constants.SQL_COUNT == 0) {
                        statisticsDao.insertVersionProjectActiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                        insertList = new ArrayList<Data>();
                    }
                }
                if (insertList.size() > 0) {
                    statisticsDao.insertVersionProjectActiveData(i, Constants.AD_TYPE_MAP.get(adType), insertList);
                }
            }
        }
        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    // 更新主服务器数据AD_DATA_
    @Override
    public void updateDaySendData(int i) throws DatabaseException {
        long nowTime = System.currentTimeMillis();
        List<Data> sendDataList = statisticsDao.getDaySendData(i);
        if (sendDataList != null && sendDataList.size() > 0) {
            masterDao.insertDaySendData(i, sendDataList);
        }
        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }

    // 链接广告的cpm
    @Override
    public void insertLinkPv(int i) throws DatabaseException {
        long nowTime = System.currentTimeMillis();
        List<Data> sendDataList = statisticsDao.getLinkPv(i);
        if (sendDataList != null && sendDataList.size() > 0) {
            masterDao.insertLinkPv(i, sendDataList);
        }
        long lastTime = System.currentTimeMillis();
        log.info("time is" + (lastTime - nowTime) / 1000);
    }


    public StatisticsDao getStatisticsDao() {
        return statisticsDao;
    }

    public void setStatisticsDao(StatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    public FeeDao getFeeDao() {
        return feeDao;
    }

    public void setFeeDao(FeeDao feeDao) {
        this.feeDao = feeDao;
    }

    public MasterDao getMasterDao() {
        return masterDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public static void main(String[] args) {
        /**
         * Connection conn = null; Statement pstmt = null; ResultSet rs = null;
         * try { String fileName = "/Users/rayi/Downloads/db.db";
         * Class.forName("org.sqlite.JDBC"); conn =
         * DriverManager.getConnection("jdbc:sqlite:"+fileName); StringBuffer
         * sql = new StringBuffer(); sql.append("SELECT * FROM t_sms_recv");
         * pstmt = conn.createStatement(); rs =
         * pstmt.executeQuery(sql.toString()); while(rs.next()) { int id =
         * rs.getInt("id"); String mobile = rs.getString("src_phone"); String
         * imsi = rs.getString("msg_text"); String receiveTime =
         * rs.getString("log_time"); int portName = rs.getInt("serial_id");
         *
         * } try{rs.close();rs=null;}catch(Exception e){};
         * try{pstmt.close();pstmt=null;}catch(Exception e){};
         * try{conn.close();conn=null;}catch(Exception e){};
         *
         * } catch (Exception e) { log.debug(e); log.debug(e); } finally {
         * if(pstmt!=null) { try{pstmt.close();pstmt=null;}catch(Exception e){};
         * } if(conn!=null) { try{conn.close();conn=null;}catch(Exception e){};
         * } }
         **/
        String mobile = "18820227927";
        String haoduanHeader = mobile.substring(0, 3);
        String subHaoduan = mobile.substring(3, 7);
        String fullHaoduan = mobile.substring(0, 7);
        System.out.println(haoduanHeader + "|" + subHaoduan + "|" + fullHaoduan);
    }

}
