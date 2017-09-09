package com.kkgame.hz.util;

import java.util.ArrayList;
import java.util.List;

import com.kkgame.hz.base.PkigConstants;

public class UtilHelper {
	public final static String AGENT_SERVICE = "agentService";
	public final static String PORTALUSER_SERVICE = "portalUserService";
	public final static String BD_SERVICE = "bdService";
	public final static String TAG_SERVICE = "tagService";
	public final static String BH_SERVICE = "bhService";
	public final static String CP_SERVICE = "cpService";
	public final static String CUSTOMER_SERVICE = "customerService";
	public final static String PROJECT_SERVICE = "projectService";
	public final static String CLOSEDMONTH_SERVICE = "closedMonthService";
	public final static String REPORT_SERVICE = "reportService";
	public final static String OS_SERVICE = "osService";

	public static String getPannerTypeDesc(String pannerType) {
		if (PkigConstants.USER_TYPE_AGENT.equals(pannerType)) {
			return "代理商";
		} else if (PkigConstants.USER_TYPE_BD.equals(pannerType)) {
			return "商务拓展人员";
		} else if (PkigConstants.USER_TYPE_CUSTOMER.equals(pannerType)) {
			return "客户";
		} else if (PkigConstants.USER_TYPE_BH.equals(pannerType)) {
			return "协助人员";
		}
		return "";
	}

	public static String getAbbcTableName(String pannerType) {
		if (PkigConstants.USER_TYPE_AGENT.equals(pannerType)) {
			return "AGENT";
		} else if (PkigConstants.USER_TYPE_BD.equals(pannerType)) {
			return "BUSINESS_DEVELOPER";
		} else if (PkigConstants.USER_TYPE_CUSTOMER.equals(pannerType)) {
			return "CUSTOMER";
		} else if (PkigConstants.USER_TYPE_BH.equals(pannerType)) {
			return "BUSINESS_HELPER";
		}
		return "";
	}

	public static List<String> getAbbcList() {
		List<String> abbcList = new ArrayList<String>();
		abbcList.add(PkigConstants.USER_TYPE_AGENT);
		abbcList.add(PkigConstants.USER_TYPE_BD);
		abbcList.add(PkigConstants.USER_TYPE_CUSTOMER);
		abbcList.add(PkigConstants.USER_TYPE_BH);
		return abbcList;
	}
}
