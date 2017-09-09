package com.kokmobi.server.dao;

import com.kokmobi.server.bean.ProjectVO;

public interface ProjectDao {

	ProjectVO getProjectVOById(int projectId) throws Exception;

}
