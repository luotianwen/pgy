package com.kokmobi.server.servlet;

import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.RedisTool;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class CacheUtilServlet
 */
public class CacheUtilServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(CacheUtilServlet.class);

    public CacheUtilServlet() {
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long start = System.currentTimeMillis();
		try{
			String act = req.getParameter("act");
			act = ProtocolUtil.isNullOrEmpty(act)?"set":act.toLowerCase();	//支持del、set、get操作
			String key = req.getParameter("key");
			String val = req.getParameter("val");	//set时需要
			String ts = req.getParameter("ts");		//set时可带入过期时间
			
			
			if(ProtocolUtil.isNullOrEmpty(key)) {
				response("{\"status\":0}", "text/plain; charset=UTF8", resp);
			}
			else {
				int exp = 0;
				try{
					exp = Integer.parseInt(ts);
				}
				catch(Exception ex){
					exp = 0;
				}
				String content = "{\"status\":0}";
				if("set".equals(act)) {
					if(!ProtocolUtil.isNullOrEmpty(val)){
						RedisTool.set(key, val);
					}
					if(exp>0) {
						RedisTool.expire(key, exp);
					}					
				}
				if("del".equals(act)){
					RedisTool.del(key);
				}
				content = "{\"status\":1}";
				if("get".equals(act)){
					String _v = RedisTool.get(key);
					content = "{\"status\":1, \"val\":\""+_v+"\"}";					
				}
				response(content, "text/plain; charset=UTF8", resp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
