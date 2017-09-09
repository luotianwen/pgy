package com.kkgame.feeop.data.action;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.ProjectDistinctService;
import com.kkgame.feeop.util.DateUtils;

public class ProjectDistinctAction extends BaseAction {

	private static Log logger = LogFactory.getLog(ProjectDistinctAction.class);
	
	private ProjectDataVO projectDataVO;
	
	private List<ProjectDataVO> projectDataVOList;
	
	private ProjectDistinctService projectDistinctService;
		
	private SearchVO searchVO;
	
	public String doQuery() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY;
	}
	
	public String doList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			projectDataVOList = projectDistinctService.getProjectDataVOList(searchVO);
			int totalUserCount = 0;
			DecimalFormat df = new DecimalFormat("#####.00"); 
			for(ProjectDataVO p:projectDataVOList) {
				totalUserCount+=p.getUserCount();
			}
			for(ProjectDataVO p:projectDataVOList) {
				p.setTotalUserCount(totalUserCount);
				p.setShowPercent(Double.parseDouble(df.format(p.getUserCount()*100.0/totalUserCount)));
			}
			
		} catch (Exception e) {
			logger.debug(e);
		}
		
		
		return ACTION_RESULT_LIST;
	}
	

	public ProjectDataVO getProjectDataVO() {
		return projectDataVO;
	}

	public void setProjectDataVO(ProjectDataVO projectDataVO) {
		this.projectDataVO = projectDataVO;
	}

	public List<ProjectDataVO> getProjectDataVOList() {
		return projectDataVOList;
	}

	public void setProjectDataVOList(List<ProjectDataVO> projectDataVOList) {
		this.projectDataVOList = projectDataVOList;
	}

	public ProjectDistinctService getProjectDistinctService() {
		return projectDistinctService;
	}

	public void setProjectDistinctService(ProjectDistinctService projectDistinctService) {
		this.projectDistinctService = projectDistinctService;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}
}
