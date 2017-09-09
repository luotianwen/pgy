package com.kkgame.feeop.data.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.UserGuardService;
import com.kkgame.feeop.util.DateUtils;

public class UserGuardAction extends BaseAction {

	private static Log logger = LogFactory.getLog(UserGuardAction.class);
	
	private ProjectDataVO projectDataVO;
	
	private List<ProjectDataVO> projectDataVOList;
	
	private UserGuardService userGuardService;
	
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
			List<ProjectDataVO> userList = userGuardService.getUserGuardDataList(searchVO);
			List<ProjectDataVO> succList = userGuardService.getUserGuardSuccDataList(searchVO);
			for(ProjectDataVO pvo:userList) {
				for(ProjectDataVO svo:succList) {
					StringBuffer svoSb = new StringBuffer();
					StringBuffer pvoSb = new StringBuffer();
					if(searchVO.getRowFieldVO().getIsShowDate()==1) {
						svoSb.append(svo.getStatDate());
						pvoSb.append(pvo.getStatDate());
					}
					if(searchVO.getRowFieldVO().getIsShowProject()==1) {
						svoSb.append(svo.getProjectId());
						pvoSb.append(pvo.getProjectId());
					}
					if(svoSb.toString().equals(pvoSb.toString())) {
						if(pvo.getGuardList()==null) {
							List<ProjectDataVO> list = new ArrayList<>();
							list.add(svo);
							pvo.setGuardList(list);
						} else {
							pvo.getGuardList().add(svo);
						}
					}
				}
			}
			projectDataVOList=userList;
		} catch(Exception e) {
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

	public UserGuardService getUserGuardService() {
		return userGuardService;
	}

	public void setUserGuardService(UserGuardService userGuardService) {
		this.userGuardService = userGuardService;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}
}
