package com.kokmobi.server.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.GetOnlineAdResp;
import com.kokmobi.server.bean.WebProjectReq;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.WebAdListProcessService;
import com.kokmobi.server.service.ActUserTool;
import com.kokmobi.server.util.BrowserUtil;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 20160719
 */
public class OnlinewebServlet extends BaseServlet {

    private static Log logger = LogFactory.getLog(OnlinewebServlet.class);
    private String fromAddr;

    public OnlinewebServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    private WebProjectReq parseRequest(HttpServletRequest req) {
        String coo_id = ProtocolUtil.getReqPara(req, "ooo_id");
        String imei = ProtocolUtil.getReqPara(req, "imei");
        String sdkVersion = ProtocolUtil.getReqPara(req, "sdkversion");
        WebProjectReq wr = new WebProjectReq();
        wr.setCooId(coo_id);
        wr.setImei(imei);
        wr.setSdkVersion(sdkVersion);
        return wr;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        {
            long start = System.currentTimeMillis();
            JSONObject disRespVOJson = new JSONObject();
            try {
                this.fromAddr = this.getRemoteAddr(req);
                String ua = req.getHeader("User-Agent");
                ua = ua == null ? "" : ua;
                int uaType = BrowserUtil.getAgentType(ua);

                WebProjectReq reqInfo = parseRequest(req);

                if (null != reqInfo) {
                    reqInfo.setIpaddr(this.fromAddr);
                    WebAdListProcessService service = this.getWebAdListProcessService();
                    GetOnlineAdResp respData=service.processOnlineWeb(reqInfo);
                    if(respData != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        String json=mapper.writeValueAsString(respData);
                        response(ProtocolUtil.getRespData(req, json), "text/plain; charset=UTF8",resp );
                    } else {
                        response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
                    }
                }

            } catch (Exception ex) {
                logger.debug(ex);


            }

            logger.info("et:" + (System.currentTimeMillis() - start) + "ms");
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
