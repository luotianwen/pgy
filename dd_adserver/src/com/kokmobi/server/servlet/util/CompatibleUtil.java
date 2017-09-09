package com.kokmobi.server.servlet.util;

import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.ProtocolUtil;

import java.util.UUID;

/**
 * Function:
 *    处理版本兼容的问题
 * @version dd_adserver 3.0
 *          Date: 2016/2/4
 *          Time: 14:29
 * @author: Xiao Jia
 */
public class CompatibleUtil {

    private CompatibleUtil() {
    }

    /**
     *  将客户端传送过来的sdkType解析为系统识别的sdkType
     *      SDK类型：sdk（sdk项目）、guide（引导项目）、down（下沉项目）、silence（静默项目）
     *      SDK1.0版本只有 sdk、down（通过sdkVersion与xCooId来兼容1.0版本）
     *
     * @param sdkTypeString 项目类型（2-sdk，3-guide，4-down ,5-silence）
     * @param sdkVersion SDK版本
     * @param xCooId 插件ID（有值为down，无值为sdk）
     * @return
     */
    public static int getSDKType(String sdkTypeString, String sdkVersion, String xCooId) {
        int sdkTypeInt;
        if (sdkVersion.equals("1.0") || sdkVersion.equals("1")) {
            if (!ProtocolUtil.isNullOrEmpty(xCooId) && !"0".equals(xCooId))
                sdkTypeInt = Constants.SDKSTYLE_DOWN;
            else
                sdkTypeInt = Constants.SDKSTYLE_SDK;
        } else {
            if (ProtocolUtil.isNullOrEmpty(sdkTypeString))
                return Constants.SDKSTYLE_SDK;   // 有的版本没有传sdkTypeString数值
            else  {
                try {
                    sdkTypeInt = Integer.parseInt(sdkTypeString);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            switch (sdkTypeInt) {
                case 1:
                case 2:
                    sdkTypeInt = Constants.SDKSTYLE_SDK;
                    break;
                case 3:
                    sdkTypeInt = Constants.SDKSTYLE_GUIDE;
                    break;
                case 4:
                    sdkTypeInt = Constants.SDKSTYLE_DOWN;
                    break;
                case 5:
                    sdkTypeInt = Constants.SDKSTYLE_SILENCE;
                    break;
                default:
                    sdkTypeInt = 0;
            }
        }
        return sdkTypeInt;
    }

    public static String uuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "") + (int) (Math.random() * 100000);
        return uuid;
    }
}
