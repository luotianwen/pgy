package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.AdUserInfo;
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
import java.net.URLDecoder;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/21
 *          Time: 14:32
 * @author: mm
 * @since 3.0
 */
public class PromotionClickServlet extends BaseServlet {

    private static Log logger = LogFactory.getLog(PromotionClickServlet.class);
    private String fromAddr;

    public PromotionClickServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        {
            long start = System.currentTimeMillis();

            try {
               /* this.fromAddr = this.getRemoteAddr(req);
                String ua = req.getHeader("User-Agent");
                ua = ua == null ? "" : ua;
                int uaType = BrowserUtil.getAgentType(ua);*/
               String ddid=req.getParameter("ddid");
                WebAdListProcessService service = this.getWebAdListProcessService();
                String sdkUrl = service.promotionUrl(ddid);
                if (null != sdkUrl&&!("").equals(sdkUrl)) {
                    resp.addHeader("location", sdkUrl);
                    resp.setStatus(302);
                } else {
                    System.out.println("ddid"+ddid);
                    resp.addHeader("location", "http://orzmobi.com/in/mugua1/");
                    resp.setStatus(302);
                }


            } catch (Exception ex) {
                logger.debug(ex);
                ex.printStackTrace();
                resp.addHeader("location", "http://orzmobi.com/in/mugua1/");
                resp.setStatus(302);
            }

            logger.info("et:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
