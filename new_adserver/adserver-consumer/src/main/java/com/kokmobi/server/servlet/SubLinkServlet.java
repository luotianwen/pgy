package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.SubLinkVO;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.SubAdProcessService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/8/17
 *          Time: 15:03
 * @author: mm
 * @since 3.0
 */
public class SubLinkServlet extends BaseServlet {
    private static Log logger = LogFactory.getLog(SubLinkServlet.class);
    private String fromAddr;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        fromAddr = getRemoteAddr(req);

        StringBuffer ipString = new StringBuffer();
        ipString.append("[from:").append(this.fromAddr).append("]");
        logger.info(ipString.toString());

        SubLinkVO sl = parseSubcribeRequest(req);
        sl.setIpaddr(ipString.toString());
        if (sl != null) {
            sl.setIpaddr(this.fromAddr);
            SubAdProcessService subAdProcessService = this.getSubProcessService();
            String subUrl = subAdProcessService.processSubLink(sl);
            resp.sendRedirect(subUrl);
        }

    }

    private SubLinkVO parseSubcribeRequest(HttpServletRequest req) throws IOException, ServletException {
        SubLinkVO subLinkVO = new SubLinkVO();
        String userAgent = req.getHeader("user-agent");
        String refer = req.getHeader("referer");
        String cooid = ProtocolUtil.getReqPara(req, "cooId");
        String type = ProtocolUtil.getReqPara(req, "type");
        UserAgent usrgent = new UserAgent(userAgent);
        OperatingSystem system = usrgent.getOperatingSystem();
        Browser browser = usrgent.getBrowser();
        Version version = usrgent.getBrowserVersion();
        subLinkVO.setUseragent(userAgent);
        subLinkVO.setReferer(refer);
        subLinkVO.setBrowser(browser.getName());
        subLinkVO.setDevice(system.getDeviceType().getName());
        subLinkVO.setOs(system.getName());
        subLinkVO.setCooid(cooid);
        subLinkVO.setAdType(Integer.valueOf(type));
        subLinkVO.setBrowserVersion("");     /*version.getVersion()*/
        subLinkVO.setXoperator("-1");
        subLinkVO.setDataSpeed("-1");
        return subLinkVO;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
