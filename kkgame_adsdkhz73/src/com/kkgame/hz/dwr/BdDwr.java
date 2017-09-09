package com.kkgame.hz.dwr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.kkgame.hz.base.BaseAction;

import com.kkgame.hz.entities.BdVO;
import com.kkgame.hz.service.BdService;
import com.kkgame.hz.util.UtilHelper;

public class BdDwr {
	private static Log logger = LogFactory.getLog(BdDwr.class);

	public boolean checkBdName(BdVO bdVO) {
		try {
			String name = bdVO.getName();
			int id = bdVO.getId();
			logger.info("name: " + name);
			bdVO = new BdVO();
			if (name == null && name.equals("")) {
				return true;
			}
			if (id > 0) {
				bdVO.setId(id);
			}
			bdVO.setName(name);
			BdService bdService = (BdService) new BaseAction()
					.getServiceObject(UtilHelper.BD_SERVICE);
			return bdService.checkBdNameExist(bdVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

}
