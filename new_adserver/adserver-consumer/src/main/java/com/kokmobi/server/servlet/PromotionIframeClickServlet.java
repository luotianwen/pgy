package com.kokmobi.server.servlet;

import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.WebAdListProcessService;
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
public class PromotionIframeClickServlet extends BaseServlet {

    private static Log logger = LogFactory.getLog(PromotionIframeClickServlet.class);
    private String fromAddr;

    public PromotionIframeClickServlet() {
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
                this.fromAddr = this.getRemoteAddr(req);
               String ddid=req.getParameter("ddid");
                WebAdListProcessService service = this.getWebAdListProcessService();
                String sdkUrl = service.promotionIframeUrl(ddid,fromAddr);
                if (null != sdkUrl&&!("").equals(sdkUrl)) {
                    resp.addHeader("location", sdkUrl);
                    resp.setStatus(302);
                } else {

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
