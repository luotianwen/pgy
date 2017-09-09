package com.kkgame.feeop.adver.action;

import com.kkgame.feeop.adver.bean.PcustomerVO;
import com.kkgame.feeop.adver.bean.PromotionDomainVO;
import com.kkgame.feeop.adver.bean.PromotionVO;
import com.kkgame.feeop.adver.service.PcustomerService;
import com.kkgame.feeop.adver.service.PromotionDomainService;
import com.kkgame.feeop.adver.service.PromotionService;
import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URLEncoder;
import java.util.List;
import java.util.Random;
/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class PcustomerAction extends BaseAction {
	private static Log logger = LogFactory.getLog(PcustomerAction.class);	
	private PcustomerVO pcustomerVO;
	private List<PcustomerVO> pcustomerVOList;
	private PcustomerService pcustomerService;
	private PromotionDomainService promotionDomainService;
	private PromotionService promotionService;

	public String doList() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {	
			pcustomerVOList = pcustomerService.getPcustomerVOList(pcustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		return ACTION_RESULT_CREATE;
	}

	private  PcustomerVO setUrl(PcustomerVO pcustomerVO)throws Exception {
		if(0!=pcustomerVO.getCustomerId()&&0!=pcustomerVO.getPromotionId()) {
			List<PromotionDomainVO> a = promotionDomainService.getPromotionDomainVOList(new PromotionDomainVO());
			if(null!=a&&a.size()>0) {
				PromotionDomainVO b = a.get(new Random().nextInt(a.size()));
				pcustomerVO.setLinkUrl("http://"+b.getDownload()+"/promotion?ddid="+pcustomerVO.getCustomerId()+"-"+pcustomerVO.getPromotionId());
			}
		}
		if(null==pcustomerVO.getRedirectUrl()||("").equals(pcustomerVO.getRedirectUrl())) {
			if(0!=pcustomerVO.getPromotionId()) {
				PromotionVO p=new PromotionVO();
				p.setId(pcustomerVO.getPromotionId());
				p=promotionService.getPromotionVO(p);
				if(null!=p) {
					pcustomerVO.setRedirectUrl(p.getRedirectUrl()+"?sub="+pcustomerVO.getCustomerId()+"&tid={tid}");
				}
			}
		}
		pcustomerVO.setDredirectUrl(URLEncoder.encode(pcustomerVO.getRedirectUrl(), "UTF-8"));
		return  pcustomerVO;
	}
	//新增配置
	public String doSave() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {
			setUrl(pcustomerVO);
				pcustomerService.create(pcustomerVO);

		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}
	 
	
	//修改通道
	public String doUpdate() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {
			setUrl(pcustomerVO);
			pcustomerService.update(pcustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {
			pcustomerVO = pcustomerService.getPcustomerVO(pcustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doDetail() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {
			pcustomerVO = pcustomerService.getPcustomerVO(pcustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}




	public String doIframeList() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {
			pcustomerVOList = pcustomerService.getPcustomerIframeVOList(pcustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return "iframelist";
	}

	public String doIframeCreate() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		return "createiframe";
	}

	private  PcustomerVO setIframeUrl(PcustomerVO pcustomerVO)throws Exception {
		if(0!=pcustomerVO.getCustomerId()&&0!=pcustomerVO.getPromotionId()) {
			List<PromotionDomainVO> a = promotionDomainService.getPromotionDomainIframeVOList(new PromotionDomainVO());
			if(null!=a&&a.size()>0) {
				PromotionDomainVO b = a.get(new Random().nextInt(a.size()));
				pcustomerVO.setLinkUrl("http://"+b.getDownload()+"/pi?ddid="+pcustomerVO.getCustomerId()+"-"+pcustomerVO.getPromotionId()+"&channelid=&subid=&sub1id=");
			}
		}
		if(null==pcustomerVO.getRedirectUrl()||("").equals(pcustomerVO.getRedirectUrl())) {
			if(0!=pcustomerVO.getPromotionId()) {
				PromotionVO p=new PromotionVO();
				p.setId(pcustomerVO.getPromotionId());
				p=promotionService.getPromotionVO(p);
				if(null!=p) {
					pcustomerVO.setRedirectUrl(p.getRedirectUrl()+"/"+pcustomerVO.getPromotionId()+".html");
				}
			}
		}
		pcustomerVO.setDredirectUrl(URLEncoder.encode(pcustomerVO.getRedirectUrl(), "UTF-8"));
		return  pcustomerVO;
	}
	//新增配置
	public String doIframeSave() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {
			setIframeUrl(pcustomerVO);
			pcustomerService.createIframe(pcustomerVO);

		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}


	//修改通道
	public String doIframeUpdate() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {
			setIframeUrl(pcustomerVO);
			pcustomerService.updateIframe(pcustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doIframeModify() {
		if(pcustomerVO == null) {
			pcustomerVO = new PcustomerVO();
		}
		try {
			pcustomerVO = pcustomerService.getPcustomerIframeVO(pcustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return "createiframe";
	}


	public PcustomerVO getPcustomerVO() {
		return pcustomerVO;
	}

	public void setPcustomerVO(PcustomerVO pcustomerVO) {
		this.pcustomerVO = pcustomerVO;
	}

	public List<PcustomerVO> getPcustomerVOList() {
		return pcustomerVOList;
	}

	public void setPcustomerVOList(List<PcustomerVO> pcustomerVOList) {
		this.pcustomerVOList = pcustomerVOList;
	}

	public PcustomerService getPcustomerService() {
		return pcustomerService;
	}

	public void setPcustomerService(PcustomerService pcustomerService) {
		this.pcustomerService = pcustomerService;
	}
	public PromotionDomainService getPromotionDomainService() {
		return promotionDomainService;
	}

	public void setPromotionDomainService(PromotionDomainService promotionDomainService) {
		this.promotionDomainService = promotionDomainService;
	}
	public PromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

}
