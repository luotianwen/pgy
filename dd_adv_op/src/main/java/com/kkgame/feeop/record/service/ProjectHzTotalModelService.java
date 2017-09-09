	package com.kkgame.feeop.record.service;
	import java.util.List;

	import com.kkgame.feeop.record.bean.ProjectHzTotalModelVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface ProjectHzTotalModelService {

		List<ProjectHzTotalModelVO> getProjectHzTotalModelVOList(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException;
		

		void create(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException;
		
		void update(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException;
		

		ProjectHzTotalModelVO getProjectHzTotalModelVO(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException;
		
		
		
	}

