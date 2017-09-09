package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.WebProjectReq;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.SubAdProcessService;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
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
 *          Date: 2016/8/17
 *          Time: 15:03
 * @author: mm
 * @since 3.0
 */
public class SubscribeServlet extends BaseServlet {
    private static Log logger = LogFactory.getLog(SubscribeServlet.class);
    private String fromAddr;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        fromAddr = getRemoteAddr(req);

        StringBuffer ipString = new StringBuffer();
        ipString.append("[from:").append(this.fromAddr).append("]");
        logger.info(ipString.toString());

        GetAdListReq sr = parseSubcribeRequest(req);
        if (sr != null) {
            sr.setIpaddr(this.fromAddr);
            SubAdProcessService subAdProcessService = this.getSubProcessService();
            String subUrl = subAdProcessService.processSubCli(sr);
            resp.sendRedirect(subUrl);
        }

    }

    private GetAdListReq parseSubcribeRequest(HttpServletRequest req) {
        GetAdListReq subscribe = new GetAdListReq();
        String imei = ProtocolUtil.getReqPara(req, "imei");
        String internet = ProtocolUtil.getReqPara(req, "internet");
        String xversion = ProtocolUtil.getReqPara(req, "xversion");
        String xoperator = ProtocolUtil.getReqPara(req, "xoperator");
        String xmodel = ProtocolUtil.getReqPara(req, "xmodel");
        String cooid =   ProtocolUtil.getReqPara(req, "cooId");
        String userAgent = req.getHeader("user-agent");
        UserAgent usrgent = new UserAgent(userAgent);
        OperatingSystem system = usrgent.getOperatingSystem();
        subscribe.setDevice(system.getDeviceType().getName());
        subscribe.setOs(system.getName());
        subscribe.setImei(imei);
        subscribe.setInternet(internet);
        subscribe.setXmodel(xmodel);
        subscribe.setXoperator(xoperator);
        subscribe.setXversion(xversion);
        subscribe.setCoo_id(cooid);
        return subscribe;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
