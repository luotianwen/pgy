package com.kokmobi.server.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.WebProjectReq;
import com.kokmobi.server.bean.WebProjectResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.WebAdListProcessService;
import com.kokmobi.server.service.ActUserTool;
import com.kokmobi.server.servlet.util.CompatibleUtil;
import com.kokmobi.server.util.BrowserUtil;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/21
 *          Time: 14:32
 * @author: mm
 * @since 3.0
 */
public class WebClickServlet extends BaseServlet {

    private static Log logger = LogFactory.getLog(WebClickServlet.class);
    private String fromAddr;

    public WebClickServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    private WebProjectReq parseRequest(HttpServletRequest req) {
        String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
        String imei = ProtocolUtil.getReqPara(req, "imei");
        String sdkVersion = ProtocolUtil.getReqPara(req, "sdkversion");
        String internet = ProtocolUtil.getReqPara(req, "internet");
        String operator = ProtocolUtil.getReqPara(req, "operator");
        String type = ProtocolUtil.getReqPara(req, "type");
        String imsi = ProtocolUtil.getReqPara(req, "imsi");
        String operatorn = ProtocolUtil.getReqPara(req, "operatorn");
        WebProjectReq wr = new WebProjectReq();
        wr.setCooId(coo_id);
        wr.setImei(imei);
        wr.setSdkVersion(sdkVersion);
        wr.setInternet(internet);
        wr.setOperator(operator);
        wr.setType(type);
        wr.setImsi(imsi);
        wr.setOperatorn(operatorn);
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
                    String sdkUrl = service.processSdkUrl(reqInfo);
                    if (!ProtocolUtil.isNullOrEmpty(sdkUrl)) {
                        resp.addHeader("location", sdkUrl);
                        resp.setStatus(302);
                    } else {
                        resp.addHeader("location", "http://orzmobi.com/in/mugua1/");
                        resp.setStatus(302);
                    }
                }
                AdUsersProcessService userService = this.getAdUsersProcessService();
                AdUserInfo userInfo = parseAdUserRequests(req);
                if (null != userInfo) {
                    userInfo.setIpaddr(this.fromAddr);
                    ActUserTool actTool = new ActUserTool(userService, userInfo);
                    actTool.start();
                }
            } catch (Exception ex) {
                logger.debug(ex);
                resp.addHeader("location", "http://orzmobi.com/in/mugua1/");
                resp.setStatus(302);
            }

            logger.info("et:" + (System.currentTimeMillis() - start) + "ms");
        }
    }
    private AdUserInfo parseAdUserRequests(HttpServletRequest req) throws Exception {
        AdUserInfo user = new AdUserInfo();
        String type = "2";
        String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
        String xc_coo_id ="0";
        String imei = ProtocolUtil.getReqPara(req, "imei");
        String channelId =coo_id;
        String xmodel = ProtocolUtil.getReqPara(req, "xmodel");
        String xversion = ProtocolUtil.getReqPara(req, "xversion");
        String ximsi = ProtocolUtil.getReqPara(req, "ximsi");
        String xinternet = ProtocolUtil.getReqPara(req, "xinternet");
        String xoperator = ProtocolUtil.getReqPara(req, "xoperator");
        String sdkversion = ProtocolUtil.getReqPara(req, "sdkversion");
        int sdkType = Constants.SDKSTYLE_WEB;
        sdkversion = sdkversion.equals("1.0") ? "1" : sdkversion;
        user.setCoo_id(coo_id);
        user.setImei(imei);
        user.setType(type);
        user.setXc_coo_id(xc_coo_id);
        user.setChannelid(channelId);
        user.setXmodel(xmodel);
        user.setXversion(xversion);
        user.setXimsi(ximsi);
        user.setXinternet(xinternet);
        user.setXoperator(xoperator);
        user.setSdkversion(sdkversion);
        user.setSdk(6);
        user.setSdkStyle(sdkType);
        return user;
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
