package com.kokmobi.server.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.GetAdListResp;
import com.kokmobi.server.bean.OfferSdkResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.GetAdListProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.WebAdListProcessService;
import com.kokmobi.server.service.ActUserTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kokmobi.server.protocol.service.ProtocolUtil.getReqPara;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/11/3
 *          Time: 10:48
 * @author: mm
 * @since 3.0
 */
public class OfferSdkServlet extends BaseServlet{
    private Log logger = LogFactory.getLog(OfferSdkServlet.class);
    private String fromAddr;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        long start = System.currentTimeMillis();

        this.fromAddr = this.getRemoteAddr(req);

        StringBuffer ipString = new StringBuffer();
        ipString.append("[from:").append(this.fromAddr).append("]");
        logger.info(ipString.toString());
        try{
            long begin=System.currentTimeMillis();
            WebAdListProcessService service = this.getWebAdListProcessService();
            long end=  System.currentTimeMillis();
            GetAdListReq reqInfo = parseRequest(req);
            if(null != reqInfo) {
                reqInfo.setIpaddr(this.fromAddr);
                OfferSdkResp respData = service.processOfferSdk(reqInfo);
                if(respData != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    String json=mapper.writeValueAsString(respData);
                    response(ProtocolUtil.getRespData(req, json), "text/plain; charset=UTF8",resp );
                } else {
                    response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
        }

        logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");
    }

    private GetAdListReq parseRequest(HttpServletRequest req) {
        GetAdListReq gar = new GetAdListReq();
        String imei = ProtocolUtil.getReqPara(req,"imei");
        String internet = ProtocolUtil.getReqPara(req,"internet");
        String xmodel = ProtocolUtil.getReqPara(req,"xmodel");
        String sdkversion = ProtocolUtil.getReqPara(req,"sdkversion");
        int version = 0;
        if(sdkversion != null && sdkversion != ""){
             version = Integer.valueOf(sdkversion);
        }
        String xoperator = ProtocolUtil.getReqPara(req,"xoperator");
        String cooid = ProtocolUtil.getReqPara(req,"cooid");
        String pholang = ProtocolUtil.getReqPara(req,"language");
        String displaysize = ProtocolUtil.getReqPara(req,"displaysize");
        String xbrand = ProtocolUtil.getReqPara(req,"xbrand");
        String xversion = ProtocolUtil.getReqPara(req,"xversion");
        gar.setImei(imei);
        gar.setInternet(internet);
        gar.setXmodel(xmodel);
        gar.setSdkversion(version);
        gar.setXoperator(xoperator);
        gar.setCoo_id(cooid);
        gar.setLanguage(pholang);
        gar.setDisplaySize(displaysize);
        gar.setBrand(xbrand);
        gar.setXversion(xversion);
        return gar;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         doGet(req, resp);
    }
}
