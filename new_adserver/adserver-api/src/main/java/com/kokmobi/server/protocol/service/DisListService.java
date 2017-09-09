package com.kokmobi.server.protocol.service;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.bean.DisRespVO;
import com.kokmobi.server.bean.DistReqVO;
import com.kokmobi.server.bean.ProjectVO;

public interface DisListService {

	DistReqVO adapter(HttpServletRequest req) throws Exception;

	DisRespVO processs(DistReqVO distReqVO) throws Exception;
	DisRespVO ydprocesss(DistReqVO distReqVO)throws Exception;
	ProjectVO getProject(String coo_id)throws Exception;
}
