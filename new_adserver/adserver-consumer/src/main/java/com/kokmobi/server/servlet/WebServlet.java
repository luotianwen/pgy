package com.kokmobi.server.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.WebProjectReq;
import com.kokmobi.server.bean.WebProjectResp;
import com.kokmobi.server.commons.BaseServlet;
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

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/21
 *          Time: 14:32
 * @author: mm
 * @since 3.0
 */
public class WebServlet extends BaseServlet {

    private static Log logger = LogFactory.getLog(WebServlet.class);
    private String fromAddr;
    public WebServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    private WebProjectReq parseRequest(HttpServletRequest req){
        String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
        String imei =  ProtocolUtil.getReqPara(req, "imei");
        String sdkVersion =  ProtocolUtil.getReqPara(req, "sdkversion");
        WebProjectReq wr=new WebProjectReq();
        wr.setCooId(coo_id);
        wr.setImei(imei);
        wr.setSdkVersion(sdkVersion);
        return wr;
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        {
            long start = System.currentTimeMillis();
            JSONObject  disRespVOJson=new JSONObject();
            try{
                this.fromAddr = this.getRemoteAddr(req);
                String ua = req.getHeader("User-Agent");
                ua = ua == null?"":ua;
                int uaType = BrowserUtil.getAgentType(ua);
                WebProjectReq reqInfo= parseRequest(req);



                if(null != reqInfo) {
                    reqInfo.setIpaddr(this.fromAddr);
                    WebAdListProcessService service = this.getWebAdListProcessService();
                    WebProjectResp respData=service.process(reqInfo);
                    if(respData != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        String json=mapper.writeValueAsString(respData);

                        response(ProtocolUtil.getRespData(req, json), "text/plain; charset=UTF8",resp );
                    } else {
                        response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
                    }
                }
                req.setAttribute("sdk",6);
                req.setAttribute("xc_coo_id",0);
                req.setAttribute("channelId",reqInfo.getCooId());
                req.setAttribute("type",2);

                AdUsersProcessService userService = this.getAdUsersProcessService();
                AdUserInfo userInfo =parseAdUserRequest(req);
                if(null != userInfo) {
                    userInfo.setIpaddr(this.fromAddr);
                    ActUserTool actTool = new ActUserTool(userService, userInfo);
                    actTool.start();
                }
            }
            catch(Exception ex) {
                logger.debug(ex);
                response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
            }

            logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
