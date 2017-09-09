package com.kokmobi.server.protocol.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.util.DesUtils;

public class ProtocolUtil {

	/**
	 * 判断客户端发送的请求参数及值是否加密
	 * @param req
	 * @return
	 *//*
	public static boolean isEncrypt(HttpServletRequest req) {
		Enumeration<String> paraNames = req.getParameterNames();
		if(paraNames != null){
			if (paraNames.hasMoreElements()) {
				boolean isEncrypt;
				try {
					DesUtils.decryptDES(paraNames.nextElement());
					isEncrypt = true;
				} catch (Exception e) {
					isEncrypt = false;
				}
				req.setAttribute("isEncrypt");
				return isEncrypt;
			}
		}
		throw new RuntimeException("该请求没有请求参数，不存在参数加密与解密。");
	}*/

	/**
	 * 获得参数的值
	 * @param req 请求对象
	 * @param paraName	待获取值的未加密参数
	 * @param isEncrypt	参数是否加密了
	 * @return
	 * @throws Exception 解密失败
	 *//*
	public static String getReqPara(HttpServletRequest req, String paraName, boolean isEncrypt) {
		String paraValue;
		if (!isEncrypt) {
			paraValue = req.getParameter(paraName);
		} else {
			String encryptValue = req.getParameter(DesUtils.encryptDES(paraName));
			try {
				paraValue = encryptValue == null ? "" : DesUtils.decryptDES(encryptValue);
			} catch (Exception e) {
				throw new RuntimeException("解密请求参数的值出错！", e);
			}
		}
		return paraValue;
	}*/

	/**
	 * 获得请求参数，适用于0个或一个参数的情况
	 * @param req
	 * @param paraName
	 * @return
	 */
	public static String getOneOrNoneReqPara(HttpServletRequest req, String paraName) {
		String paraValue = "";
		Enumeration<String> paraNames = req.getParameterNames();
		if(paraNames != null){
			if (paraNames.hasMoreElements()) {
				try {
					String nextPara = paraNames.nextElement();
					String decryptPara = DesUtils.decryptDES(nextPara);
					if (paraName.equals(decryptPara)) {
						try {
							paraValue = DesUtils.decryptDES(req.getParameter(nextPara));
						} catch (Exception e) {
							throw new RuntimeException("解密请求参数的值出错！", e);
						}
					}
				} catch (Exception e) {
					paraValue = req.getParameter(paraName);
				}
			}
		}
		return paraValue;
	}

	/**
	 *
	 * @param req
	 * @param paraName
	 * @return SDK之前的接口对参数值进行了加密，返回解密后的内容
	 */
	public static String getReqPara(HttpServletRequest req, String paraName) {
		String paraVal = req.getParameter(paraName);
		paraVal = paraVal==null?"":paraVal;
		if (!DesUtils.flag) {
		      return paraVal;
	    }
		else{
			try {
				Enumeration<String> paraNames = req.getParameterNames();
				if(paraNames != null){
					while(paraNames.hasMoreElements()) {
						String enPName = paraNames.nextElement();
						String enPValue = req.getParameter(enPName);
						String pName = DesUtils.decryptDES(enPName);
						if(paraName.equals(pName)) {
							return DesUtils.decryptDES(enPValue);
						}
					}
				}

				return "";


			}
	        catch (Exception e)
	        {
	        	return paraVal;
	        }
		}
	}

	public static String getRespData(HttpServletRequest resp, String respData) {
		return getRespData(respData);
	}

 	public static String getRespData(String respData) {
 		if (!DesUtils.flag) {
 			return respData;
 		}
 		else{
 			return DesUtils.encryptDES(respData);
 		}
 	}

	public static boolean isNullOrEmpty(String s) {
		boolean rv = false;
		if(null == s || "".equals(s)){
			rv = true;
		}
		return rv;
	}

	public static boolean isExistNullOrEmpty(String... strs) {
		if (strs.length == 0) return false;
		for (String str : strs) {
			if(null == str || "".equals(str)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断参数是否可以全部正确解析为int, 兼容参数为空的情况
	 * @param strs
	 * @return
	 */
	public static boolean isIntegers (String... strs) {
		if (null != strs && strs.length > 0) {
			for (String s : strs) {
				try {
					if (!ProtocolUtil.isNullOrEmpty(s)) {
						Integer.parseInt(s);
					}
				} catch (Exception e) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
