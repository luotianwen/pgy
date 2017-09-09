package com.kkgame.feeop.adver.action;

import java.io.*;
import java.util.List;

import com.kkgame.feeop.util.DatabaseException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.adver.action.PromotionAction;
import com.kkgame.feeop.adver.bean.PromotionVO;
import com.kkgame.feeop.adver.service.PromotionService;
import org.springframework.util.StringUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class PromotionAction extends BaseAction {
    private static Log logger = LogFactory.getLog(PromotionAction.class);
    private PromotionVO promotionVO;
    private List<PromotionVO> promotionVOList;
    private PromotionService promotionService;


    public String doList() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        try {
            promotionVOList = promotionService.getPromotionVOList(promotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String doCreate() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        return ACTION_RESULT_CREATE;
    }

    //新增配置
    public String doSave() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        try {
            promotionService.create(promotionVO);
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
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        try {
            promotionService.update(promotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("更新配置信息成功");
        return null;
    }

    public String doModify() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        try {
            promotionVO = promotionService.getPromotionVO(promotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }

        return ACTION_RESULT_CREATE;
    }

    public String doDetail() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        try {
            promotionVO = promotionService.getPromotionVO(promotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_DETAIL;
    }


    public String doIframeList() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        try {
            promotionVOList = promotionService.getPromotionIframeVOList(promotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return "iframelist";
    }

    public String doIframeCreate() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        return "iframecreate";
    }

    //新增配置
    public String doIframeSave() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        if (patternJudge(promotionVO)) {
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }

        try {
            int id = promotionService.createIframe(promotionVO);
            doIframeDetail(id);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("创建配置信息和静态页面成功");
        return null;
    }

    /**
     * 判断url是否符合相应的格式
     *
     * @param promotionVO
     * @return
     */
    private boolean patternJudge(PromotionVO promotionVO) {
        String iframe1 = promotionVO.getIframe1();
        String iframe2 = promotionVO.getIframe2();
        String iframe3 = promotionVO.getIframe3();
        String iframe4 = promotionVO.getIframe4();
        String iframe5 = promotionVO.getIframe5();
        String top = promotionVO.getRedirectUrl();

        if (patternIsExist(iframe1)) return true;
        if (patternIsExist(iframe2)) return true;
        if (patternIsExist(iframe3)) return true;
        if (patternIsExist(iframe4)) return true;
        if (patternIsExist(iframe5)) return true;
        if (patternIsExist(top)) return true;
        return false;
    }

    private boolean patternIsExist(String iframe1) {
        if (StringUtils.hasLength(iframe1)) {
            int index = iframe1.indexOf("{tid}");
            int index1 = iframe1.indexOf("http://");
            int index2 = iframe1.indexOf("https://");
            if (index == -1 || (index1 == -1 && index2 == -1)) {
                return true;
            }
        }
        return false;
    }


    //修改通道
    public String doIframeUpdate() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        if (patternJudge(promotionVO)) {
            printMessage("配置链接不符合{tid}格式}");
            return null;
        }
        try {
            promotionService.updateIframe(promotionVO);
            doIframeDetail(promotionVO.getId());
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("更新配置信息和静态页面成功");
        return null;
    }

    public String doIframeModify() {
        if (promotionVO == null) {
            promotionVO = new PromotionVO();
        }
        try {
            promotionVO = promotionService.getPromotionVO(promotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return "iframecreate";
    }

    public void doIframeDetail(int id) throws DatabaseException, IOException, TemplateException {
        PromotionVO promotionVO = new PromotionVO();
        promotionVO.setId(id);
        String name = "";
        String outFile = "";
        promotionVO = promotionService.getPromotionIframeVO(promotionVO);
        String ftlPath = getRequest().getRealPath("/ftl");

        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(ftlPath));
        configuration.setDefaultEncoding("UTF-8");
        // 获取或创建一个模版。
        Template template = configuration.getTemplate("index.ftl");
        // 获取html静态页面文件
        String indexPath = getRequest().getRealPath("/html/" + promotionVO.getId() + ".html");
        //设置文件输入流编码，不然生成的html文件会中文乱码
        FileWriterWithEncoding out = new FileWriterWithEncoding(indexPath, "UTF-8");
        //将map中的数据输入到index.ftl这个模板文件中并遍历出来，最后再将整个模板的数据写入到index.html中。
        template.process(promotionVO, out);
        out.close();
    }

    public PromotionVO getPromotionVO() {
        return promotionVO;
    }

    public void setPromotionVO(PromotionVO promotionVO) {
        this.promotionVO = promotionVO;
    }

    public List<PromotionVO> getPromotionVOList() {
        return promotionVOList;
    }

    public void setPromotionVOList(List<PromotionVO> promotionVOList) {
        this.promotionVOList = promotionVOList;
    }

    public PromotionService getPromotionService() {
        return promotionService;
    }

    public void setPromotionService(PromotionService promotionService) {
        this.promotionService = promotionService;
    }


}
