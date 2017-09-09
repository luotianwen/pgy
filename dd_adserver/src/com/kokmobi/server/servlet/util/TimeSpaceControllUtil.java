package com.kokmobi.server.servlet.util;

import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.RedisTool;
import com.kokmobi.server.util.CalendarFormat;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/2/18
 *          Time: 14:58
 * @author: Xiao Jia
 */
public class TimeSpaceControllUtil {
    private TimeSpaceControllUtil() {
    }

    /**
     * 用一定时间内请求广告列表的次数是否在规定次数内
     * @param imei
     * @param sdkType
     * @param coo_id
     * @return
     *  false 超过规定次数<br>
     *  true  在规定次数内
     */
    public static boolean isAdListInControll(String imei, int sdkType, String coo_id) {
        String userKey = String.format(Constants.KEY_AD_LAST_SENT, imei, String.valueOf(sdkType), coo_id);
        String numStr = RedisTool.get(userKey);
        if (ProtocolUtil.isNullOrEmpty(numStr)) {
            RedisTool.set(userKey, "1");
            RedisTool.expire(userKey, Constants.KEY_AD_LAST_SENT_EXPIRE);
        } else {
            int numInt = Integer.parseInt(numStr);
            if (numInt > Constants.KEY_AD_LAST_SENT_NUM) {
                return false;
            } else {
                RedisTool.incr(userKey);
            }
        }
        return true;
    }
}
