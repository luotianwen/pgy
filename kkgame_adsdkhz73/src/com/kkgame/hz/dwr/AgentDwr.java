package com.kkgame.hz.dwr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.AgentVO;
import com.kkgame.hz.service.AgentService;
import com.kkgame.hz.util.UtilHelper;

public class AgentDwr {
	private static Log logger = LogFactory.getLog(AgentDwr.class);

	public boolean checkAgentName(AgentVO agentVO) {
		try {
			String name = agentVO.getName();
			int id = agentVO.getId();
			logger.info("name: " + name);
			agentVO = new AgentVO();
			if (name == null && name.equals("")) {
				return true;
			}
			if (id > 0) {
				agentVO.setId(id);
			}
			agentVO.setName(name);
			AgentService agentService = (AgentService) new BaseAction()
					.getServiceObject(UtilHelper.AGENT_SERVICE);
			return agentService.checkAgentNameExist(agentVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

}
