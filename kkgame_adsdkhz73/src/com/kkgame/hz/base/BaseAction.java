package com.kkgame.hz.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kkgame.hz.entities.AgentVO;
import com.kkgame.hz.entities.BdVO;
import com.kkgame.hz.entities.BhVO;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.entities.PortalUserVO;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	private static Log logger = LogFactory.getLog(BaseAction.class);
	
	public final static String ACTION_RESULT_LIST="list";
	public final static String ACTION_RESULT_CREATE="create";
	public final static String ACTION_RESULT_MODIFY="modify";
	public final static String ACTION_RESULT_DETAIL="detail";
	public final static String ACTION_RESULT_QUERY="query";
	public final static String ACTION_RESULT_APPLY="apply";
	public final static String ACTION_RESULT_MESSAGE="message";
	public final static String ACTION_RESULT_AUDIT_APPLY="auditApply";
	protected static final String PlAIN_MESSAGE = "plainMessage";	
	public String flag;	
	public String errorMsg;
	protected HttpSession session;
	public String urlRedirect;
	private static volatile Map<String, Object> serviceMap = new HashMap<String, Object>();
	
	private static final int BUFFER_SIZE = 16*1024;
	
	public String basePath;
	
	public String contentProviderName;
	
	public String getContentProviderName()
    {
        return contentProviderName;
    }

    public void setContentProviderName(String contentProviderName)
    {
        this.contentProviderName = contentProviderName;
    }

    public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
	public PortalUserVO getUser() {
		return (PortalUserVO) ServletActionContext.getRequest().getSession().getAttribute(PkigConstants.SESSION_PORTALUSER);
	}

	public static Object getServiceObject(String beanID) {
		Object service = serviceMap.get(beanID);
		if (null == service) {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:../conf/applicationContext-common.xml");
		
			WebApplicationContext wac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(PkigConstants.KKFUN_WEB_CONTEXT);
			service = wac.getBean(beanID);
			serviceMap.put(beanID, service);
		}
		return service;
	}
	
	public String getLoginUserType() {
		PortalUserVO userVO = null;
		try {
			userVO = getSessionUserVO();
			if (userVO == null) {
				logger.debug("userVO is null");
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return userVO.getRoleType();
	}
	
	public int getLoginUserId()  {
		String userType = getLoginUserType();
		session = ServletActionContext.getRequest().getSession();
		PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
//		PortalUserVO vo = null;
//		if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
//			vo = (AgentVO) getSessionAgentVO();
//		} else if (PkigConstants.USER_TYPE_BD.equals(userType)) {
//			vo = (BdVO)getSessionBdVO();
//		} else if (PkigConstants.USER_TYPE_BH.equals(userType)) {
//			vo = (BhVO) getSessionBhVO();
//		} else if (PkigConstants.USER_TYPE_CUSTOMER.equals(userType)) {
//			vo = (CustomerVO)getSessionCustomerVO();
//		}
//		if (vo == null) {
//			return -1;
//		}
		return userVO.getId();
	}
	
	public int getLoginUserRoleId()  {
		session = ServletActionContext.getRequest().getSession();
		PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
		return userVO.getRoleId();
	}
	
	// return PortalUserVO
	public PortalUserVO getSessionUserVO()  {
		session = ServletActionContext.getRequest().getSession();
		PortalUserVO userVO = (PortalUserVO) session.getAttribute(PkigConstants.SESSION_PORTALUSER);
		if (null == userVO) {
		  logger.debug("userVO is null");
			return  null;
		}	

		return userVO;
	}
	
	public BdVO getSessionBdVO()  {
		session = ServletActionContext.getRequest().getSession();
		BdVO bdVO = (BdVO) session.getAttribute(PkigConstants.SESSION_BD);
		if (null == bdVO) {
			logger.debug("bdVO is null");
			return  null;
		}	
		return bdVO;
	}
	
	public BhVO getSessionBhVO()  {
		session = ServletActionContext.getRequest().getSession();
		BhVO bhVO = (BhVO) session.getAttribute(PkigConstants.SESSION_BH);
		if (null == bhVO) {
		  logger.debug("bhVO is null");
			return  null;
		}	
		return bhVO;
	}
	
	public AgentVO getSessionAgentVO()  {
		session = ServletActionContext.getRequest().getSession();
		AgentVO agentVO = (AgentVO) session.getAttribute(PkigConstants.SESSION_AGENT);
		if (null == agentVO) {
			logger.debug("agentVO is null");
			return  null;
		}
		return agentVO;
	}
	
	public CustomerVO getSessionCustomerVO()  {
		session = ServletActionContext.getRequest().getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute(PkigConstants.SESSION_CUSTOMER);
		if (null == customerVO) {
			logger.debug("CustomerVO is null");
			return  null;
		}	
		return customerVO;
	}
	
	
	public String getSystemBashPath(){
//		SystemSettingService ssService = (SystemSettingService)getServiceObject(UtilHelper.SYSTEM_SETTING_SERVICE);
//		try {
//			List ssList = ssService.getSystemSettingList();
//			if (ssList != null && ssList.size()>0){
//				SystemSettingVO ssVo = (SystemSettingVO)ssList.get(0);
//				return ssVo.getBasePath();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}
	
	/**
	 * 判断文件是否存在，如果不存在则创建
	 * @param path
	 */
	public void mkdir(String path){
		File file = new File(path);
		if (!file.exists()){
			file.mkdirs();
		}
	}
	
	public void copyFile(File src,File sdt){
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(sdt),BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0){
					out.write(buffer);
				}
			} finally {
				if (null != in)
					in.close();
				if (null != out)
					out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * 导出excel
     * @param filename
     * @param workbook
     */
    public void exportExcel(String filename, HSSFWorkbook workbook)
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        try
        {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        response.setHeader("Content-disposition","inline; filename=" + filename + ".xls");
        response.setContentType("application/msexcel; charset=utf-8");
        OutputStream os = null;
        try
        {
            os = response.getOutputStream();
            
            workbook.write(os);
            
            os.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(os != null)
            {
                try{
                    os.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static JavaMailSender getMailSender()
    {
        return (JavaMailSender) getServiceObject("mailSender");
    }
    
    protected void setSession(String name, Object val){
		this.getRequest().getSession().setAttribute(name, val);
	}
	protected Object getSession(String name){
		return this.getRequest().getSession().getAttribute(name);
	}
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	protected String getParameter(String name){
		return getRequest().getParameter(name);
	}
    
    protected int getParameterInt(String name, int errorInt){
		try {
			return Integer.parseInt(getRequest().getParameter(name));
		} catch (Exception e) {
			return errorInt;
		}
	}
	
	protected void printMessage(String message){
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().setCharacterEncoding("utf-8");
			getResponse().getWriter().print(message);
			getResponse().getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getUrlRedirect() {
		return urlRedirect;
	}

	public void setUrlRedirect(String urlRedirect) {
		this.urlRedirect = urlRedirect;
	}

}
