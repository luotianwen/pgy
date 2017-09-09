package com.kkgame.hz.dwr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.BhVO;
import com.kkgame.hz.service.BhService;
import com.kkgame.hz.util.UtilHelper;

public class BhDwr {
	private static Log logger = LogFactory.getLog(BhDwr.class);

	public boolean checkBhName(BhVO bhVO) {
		try {
			String name = bhVO.getName();
			int id = bhVO.getId();
			logger.info("name: " + name);
			bhVO = new BhVO();
			if (name == null && name.equals("")) {
				return true;
			}
			if (id > 0) {
				bhVO.setId(id);
			}
			bhVO.setName(name);
			BhService bhService = (BhService) new BaseAction()
					.getServiceObject(UtilHelper.BH_SERVICE);
			return bhService.checkBhNameExist(bhVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

}
