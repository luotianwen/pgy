package com.kkgame.feeop.data.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.ProjectIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.ProjectIncomeService;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DateUtils;

public class BdDataAction extends BaseAction {

	private static Log logger = LogFactory.getLog(BdDataAction.class);
	
	private SearchVO searchVO;
	
	private ProjectIncomeService projectIncomeService;
	
	private ProjectIncomeVO projectIncomeVO;
	
	private List<ProjectIncomeVO> projectIncomeVOList;

	
	public String doQuery() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserVO userVO = (UserVO) session.getAttribute(PkigConstants.SESSION_USER);
		searchVO.setBdId(userVO.getId());
		return ACTION_RESULT_QUERY;
	}
	
	public String doList() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserVO userVO = (UserVO) session.getAttribute(PkigConstants.SESSION_USER);
			searchVO.setBdId(userVO.getId());
			projectIncomeVOList = projectIncomeService.getEffectProjectVOList(searchVO);
			Map<String,ProjectIncomeVO> sdkMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> leadMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> sinkMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> silenceMap = new HashMap<String, ProjectIncomeVO>();
			for(ProjectIncomeVO projectIncomeVO:projectIncomeVOList) {
				StringBuffer sb = new StringBuffer();
				sb.append(projectIncomeVO.getProjectId());
				if(projectIncomeVO.getType()==600400) {
					sdkMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600403) {
					leadMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600404) {
					sinkMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600405) {
					silenceMap.put(sb.toString(), projectIncomeVO);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> sdkEntry:sdkMap.entrySet()) {
				String key = sdkEntry.getKey();
				ProjectIncomeVO sdkIncome = sdkEntry.getValue();
				if(leadMap.containsKey(key)) {
					ProjectIncomeVO leadIncome  = leadMap.get(key);
					sdkIncome.setLeadNewUsers(leadIncome.getNewUsers());
					sdkIncome.setLeadInstallTotal(leadIncome.getInstallTotal());
					sdkIncome.setIncome(leadIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setLinkIncome(leadIncome.getLinkIncome()+sdkIncome.getLinkIncome());
					sdkIncome.setThirdIncome(leadIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setProfit(leadIncome.getProfit()+sdkIncome.getProfit());
					sdkIncome.setTotalActUsers(leadIncome.getTotalActUsers()+sdkIncome.getTotalActUsers());
					sdkIncome.setInstallTotal(leadIncome.getInstallTotal()+sdkIncome.getInstallTotal());
				}
				if(sinkMap.containsKey(key)) {
					ProjectIncomeVO sinkIncome  = sinkMap.get(key);
					sdkIncome.setSinkNewUsers(sinkIncome.getNewUsers());
					sdkIncome.setSinkInstallTotal(sinkIncome.getInstallTotal());
					sdkIncome.setIncome(sinkIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setLinkIncome(sinkIncome.getLinkIncome()+sinkIncome.getLinkIncome());
					sdkIncome.setThirdIncome(sinkIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setProfit(sinkIncome.getProfit()+sdkIncome.getProfit());
					sdkIncome.setTotalActUsers(sinkIncome.getTotalActUsers()+sdkIncome.getTotalActUsers());
					sdkIncome.setInstallTotal(sinkIncome.getInstallTotal()+sdkIncome.getInstallTotal());
				}
				if(silenceMap.containsKey(key)) {
					ProjectIncomeVO silenceIncome  = silenceMap.get(key);
					sdkIncome.setSilenceNewUsers(silenceIncome.getNewUsers());
					sdkIncome.setSilenceInstallTotal(silenceIncome.getInstallTotal());
					sdkIncome.setIncome(silenceIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setLinkIncome(silenceIncome.getLinkIncome()+silenceIncome.getLinkIncome());
					sdkIncome.setThirdIncome(silenceIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setProfit(silenceIncome.getProfit()+sdkIncome.getProfit());
					sdkIncome.setTotalActUsers(silenceIncome.getTotalActUsers()+sdkIncome.getTotalActUsers());
					sdkIncome.setInstallTotal(silenceIncome.getInstallTotal()+sdkIncome.getInstallTotal());
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> leadEntry:leadMap.entrySet()) {
				String key = leadEntry.getKey();
				ProjectIncomeVO leadIncome = leadEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					leadIncome.setLeadNewUsers(leadIncome.getNewUsers());
					leadIncome.setLeadInstallTotal(leadIncome.getInstallTotal());
					sdkMap.put(key, leadIncome);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> sinkEntry:sinkMap.entrySet()) {
				String key = sinkEntry.getKey();
				ProjectIncomeVO sinkIncome = sinkEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					sinkIncome.setSinkNewUsers(sinkIncome.getNewUsers());
					sinkIncome.setSinkInstallTotal(sinkIncome.getInstallTotal());
					sdkMap.put(key, sinkIncome);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> silenceEntry:silenceMap.entrySet()) {
				String key = silenceEntry.getKey();
				ProjectIncomeVO silenceIncome = silenceEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					silenceIncome.setSilenceNewUsers(silenceIncome.getNewUsers());
					silenceIncome.setSilenceInstallTotal(silenceIncome.getInstallTotal());
					sdkMap.put(key, silenceIncome);
				}
			}
			projectIncomeVOList = new ArrayList<ProjectIncomeVO>();
			DecimalFormat df = new DecimalFormat("#####.000"); 
			for(Map.Entry<String, ProjectIncomeVO> sdkEntry:sdkMap.entrySet()) {
				String key = sdkEntry.getKey();
				ProjectIncomeVO sdkIncome = sdkEntry.getValue();
				sdkIncome.setIncome(Double.parseDouble(df.format(sdkIncome.getIncome())));
				sdkIncome.setTotalIncome(Double.parseDouble(df.format(sdkIncome.getIncome()+sdkIncome.getThirdIncome()+sdkIncome.getLinkIncome())));
				sdkIncome.setOutcome(Double.parseDouble(df.format(sdkIncome.getOutcome())));
				sdkIncome.setProfit(Double.parseDouble(df.format(sdkIncome.getProfit())));
				if(sdkIncome.getOutcome()>0) {
					sdkIncome.setIo(Double.parseDouble(df.format((sdkIncome.getIncome()+sdkIncome.getThirdIncome()+sdkIncome.getLinkIncome())/sdkIncome.getOutcome())));
				}else if(sdkIncome.getOutcome()==0&&sdkIncome.getIncome()>0) {
					sdkIncome.setIo(1.0);
				}
				if(sdkIncome.getTotalActUsers()>0) {
					sdkIncome.setActiveInstallPercent(Double.parseDouble(df.format(sdkIncome.getInstallTotal()*100.0/sdkIncome.getTotalActUsers())));
				}
				sdkIncome.setQualityWarn(Double.parseDouble(df.format(sdkIncome.getFirstPercent()/0.2*0.5+sdkIncome.getActiveInstallPercent()*0.01/0.06*0.5)));
				if(sdkIncome.getNewUsers()>0) {
					projectIncomeVOList.add(sdkIncome);

				}
			}
			if(projectIncomeVOList.size()>0) {
				Collections.sort(projectIncomeVOList, new Comparator<ProjectIncomeVO>() {
					@Override
					public int compare(ProjectIncomeVO o1, ProjectIncomeVO o2) {
						if(o2.getNewUsers()>o1.getNewUsers()) {
							return 1;
						}
						return -1;
					}
				});
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public ProjectIncomeService getProjectIncomeService() {
		return projectIncomeService;
	}

	public void setProjectIncomeService(ProjectIncomeService projectIncomeService) {
		this.projectIncomeService = projectIncomeService;
	}

	public ProjectIncomeVO getProjectIncomeVO() {
		return projectIncomeVO;
	}

	public void setProjectIncomeVO(ProjectIncomeVO projectIncomeVO) {
		this.projectIncomeVO = projectIncomeVO;
	}

	public List<ProjectIncomeVO> getProjectIncomeVOList() {
		return projectIncomeVOList;
	}

	public void setProjectIncomeVOList(List<ProjectIncomeVO> projectIncomeVOList) {
		this.projectIncomeVOList = projectIncomeVOList;
	}
	
	
}
