package com.kokmobi.server.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.GetOnlineReq;
import com.kokmobi.server.bean.OfflineSdkResp;
import com.kokmobi.server.bean.WebProjectResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.WebAdListProcessService;
import com.kokmobi.server.service.ActUserTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by martins on 2016/9/8.
 */
public class OfflineSdkServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;
    private static Log logger = LogFactory.getLog(OfflineSdkServlet.class);
    private String fromAddr;

    /**
     *
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();

        this.fromAddr = this.getRemoteAddr(req);

        StringBuffer ipString = new StringBuffer();
        ipString.append("[from:").append(this.fromAddr).append("]");
        logger.info(ipString.toString());
        this.fromAddr = this.getRemoteAddr(req);
        try{

            Map<String,String> map = parseRequest(req);
            map.put("fromAddr",fromAddr);
            WebAdListProcessService service = this.getWebAdListProcessService();
            OfflineSdkResp respData=service.processOfflineSdk(map);
            if(respData != null) {
                ObjectMapper mapper = new ObjectMapper();
                String json=mapper.writeValueAsString(respData);

                response(ProtocolUtil.getRespData(req, json), "text/plain; charset=UTF8",resp );
            } else {
                response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
            }

            req.setAttribute("sdk",7);
            req.setAttribute("xc_coo_id",0);
            req.setAttribute("channelId",map.get("coo_id"));
            req.setAttribute("type",2);

            AdUsersProcessService userService = this.getAdUsersProcessService();
            AdUserInfo userInfo =parseAdUserRequest(req);
            if(null != userInfo) {
                userInfo.setIpaddr(this.fromAddr);
                ActUserTool actTool = new ActUserTool(userService, userInfo);
                actTool.start();
            }
        }
        catch(Exception e) {
            logger.error("onlie back data error"+e);
            response(ProtocolUtil.getRespData(req, "{\"status\":0}"), "text/plain; charset=UTF8", resp);
        }

        logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    private Map parseRequest(HttpServletRequest req) throws Exception {
        Map map=new HashMap();
        String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
        String imei = ProtocolUtil.getReqPara(req, "imei");
        String version = ProtocolUtil.getReqPara(req, "sdkversion");
        String leftSize = ProtocolUtil.getReqPara(req, "leftSize");
        if(ProtocolUtil.isNullOrEmpty(coo_id)||ProtocolUtil.isNullOrEmpty(imei)||ProtocolUtil.isNullOrEmpty(version)||ProtocolUtil.isNullOrEmpty(leftSize)){
            return null;
        }
        map.put("coo_id",coo_id);
        map.put("imei",imei);
        map.put("sdkversion",version);
        map.put("leftSize",leftSize);
        return map;
    }

}
