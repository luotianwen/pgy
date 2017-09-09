package com.kkgame.feeop.tag;

import com.kkgame.feeop.adver.bean.*;
import com.kkgame.feeop.adver.service.*;
import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.customer.bean.AgentVO;
import com.kkgame.feeop.customer.bean.CustomerVO;
import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.customer.service.AgentService;
import com.kkgame.feeop.customer.service.CustomerService;
import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.ddl.bean.DdlProductVO;
import com.kkgame.feeop.ddl.bean.DdlProjectVO;
import com.kkgame.feeop.ddl.service.DdlChannelService;
import com.kkgame.feeop.ddl.service.DdlProductService;
import com.kkgame.feeop.record.bean.OperatorVO;
import com.kkgame.feeop.record.service.OperatorService;
import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.sdkinfo.service.ApkInfoService;
import com.kkgame.feeop.tag.bean.AdVO;
import com.kkgame.feeop.tag.bean.CountryVO;
import com.kkgame.feeop.tag.bean.ProvinceVO;
import com.kkgame.feeop.tag.bean.VersionVO;
import com.kkgame.feeop.tag.service.SystemService;
import com.kkgame.feeop.user.bean.RoleVO;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.user.service.RoleService;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.ServiceBeanID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Option extends BaseAction {

    public static final String KEY = "key";

    public static final String VALUE = "value";

    public List<PromotionVO> getPromotionList() {
        PromotionService advLinkmanService = (PromotionService) ServiceBeanID.getServiceObject(ServiceBeanID.PROMOTIONSERVICE_SERVICE);
        List<PromotionVO> list = null;
        if (null != getSession("promotionList") && getSession("promotionListTimeOut") != null
                && (System.currentTimeMillis() - (Long) getSession("promotionListTimeOut")) < 1000 * 60 * 15) {
            list = (List<PromotionVO>) getSession("promotionList");
        } else {
            try {
                list = (List<PromotionVO>) advLinkmanService.getPromotionVOList(new PromotionVO());
                setSession("promotionList", list);
                setSession("promotionListTimeOut", System.currentTimeMillis());
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public List<PromotionVO> getPromotionIframeList() {
        PromotionService advLinkmanService = (PromotionService) ServiceBeanID.getServiceObject(ServiceBeanID.PROMOTIONSERVICE_SERVICE);
        List<PromotionVO> list = null;
        try {
            list = (List<PromotionVO>) advLinkmanService.getPromotionIframeVOList(new PromotionVO());
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<PromotionCustomerVO> getPcustomerList() {
        PromotionCustomerService advLinkmanService = (PromotionCustomerService) ServiceBeanID.getServiceObject(ServiceBeanID.PROMOTIONCUSTOMERSERVICE_SERVICE);
        List<PromotionCustomerVO> list = null;
        if (null != getSession("promotionCustomerList") && getSession("promotionCustomerTimeOut") != null
                && (System.currentTimeMillis() - (Long) getSession("promotionCustomerTimeOut")) < 1000 * 60 * 15) {
            list = (List<PromotionCustomerVO>) getSession("promotionCustomerList");
        } else {
            try {
                list = (List<PromotionCustomerVO>) advLinkmanService.getPromotionCustomerVOList(new PromotionCustomerVO());
                setSession("promotionCustomerList", list);
                setSession("promotionCustomerTimeOut", System.currentTimeMillis());
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public List<AdvLinkmanVO> getAdvLinkmanList() {
        AdvLinkmanService advLinkmanService = (AdvLinkmanService) ServiceBeanID.getServiceObject(ServiceBeanID.ADVLINKMAN_SERVICE);
        List<AdvLinkmanVO> list = null;
        if (null != getSession("advLinkmanList") && getSession("advLinkmanTimeOut") != null
                && (System.currentTimeMillis() - (Long) getSession("advLinkmanTimeOut")) < 1000 * 60 * 15) {
            list = (List<AdvLinkmanVO>) getSession("advLinkmanList");
        } else {
            try {
                list = (List<AdvLinkmanVO>) advLinkmanService.getAllAdvLinkman();
                setSession("advLinkmanList", list);
                setSession("advLinkmanTimeOut", System.currentTimeMillis());
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<AgentVO> getAgentList() {
        AgentService agent = (AgentService) ServiceBeanID
                .getServiceObject(ServiceBeanID.AGENT_SERVICE);
        List<AgentVO> list = null;
        try {
            if (getSession("agentList") != null
                    && getSession("agentTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("agentTimeOut")) < 1000 * 60 * 15) {
                list = (List<AgentVO>) getSession("agentList");
            } else {
                list = agent.getAllAgentList();
                setSession("agentList", list);
                setSession("agentTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<AgentVO>();
    }

    public List<AdVO> getAdList() {
        SystemService agent = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<AdVO> list = null;
        try {
            if (getSession("ad") != null
                    && getSession("adTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("adTimeOut")) < 1000 * 60 * 15) {
                list = (List<AdVO>) getSession("ad");
            } else {
                list = agent.getAdList();
                for (AdVO adVO : list) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(adVO.getName()).append("[").append(adVO.getId()).append("]");
                    adVO.setName(sb.toString());
                }
                setSession("ad", list);
                setSession("adTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<AdVO>();
    }

    public List<CountryVO> getCountryList() {
        SystemService agent = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<CountryVO> list = null;
        try {
            if (getSession("country") != null
                    && getSession("countryTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("countryTimeOut")) < 1000 * 60 * 15) {
                list = (List<CountryVO>) getSession("country");
            } else {
                list = agent.getCountryList();
                setSession("country", list);
                setSession("countryTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CountryVO>();
    }

    public List<CountryVO> getPlatformList() {
        SystemService agent = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<CountryVO> list = null;
        try {
            if (getSession("platform") != null
                    && getSession("platformTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("platformTimeOut")) < 1000 * 60 * 15) {
                list = (List<CountryVO>) getSession("platform");
            } else {
                list = agent.getPlatformList();
                setSession("platform", list);
                setSession("platformTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CountryVO>();
    }

    public List<ProductVO> getProductList() {
        SystemService product = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<ProductVO> list = null;
        try {
            if (getSession("product") != null
                    && getSession("productTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("productTimeOut")) < 1000 * 60 * 15) {
                list = (List<ProductVO>) getSession("product");
            } else {
                list = product.getProductList();
                setSession("product", list);
                setSession("productTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<ProductVO>();
    }

    public List<DdlProductVO> getDdlProductList() {
        SystemService systemService = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<DdlProductVO> list = null;
        try {
            if (getSession("ddlProduct") != null
                    && getSession("ddlProductTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("ddlProductTimeOut")) < 1000 * 60 * 15) {
                list = (List<DdlProductVO>) getSession("ddlProduct");
            } else {
                list = systemService.getDdlProductList();
                setSession("ddlProduct", list);
                setSession("ddlProductTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DdlProductVO>();
    }


    public List<ApkInfoVO> getGuardPluginIdList() {
        ApkInfoService systemService = (ApkInfoService) ServiceBeanID
                .getServiceObject(ServiceBeanID.APKINFO_SERVICE);
        List<ApkInfoVO> list = null;
        try {
            if (getSession("apkinfo") != null
                    && getSession("apkinfoTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("apkinfoTimeOut")) < 1000 * 60 * 15) {
                list = (List<ApkInfoVO>) getSession("apkinfo");
            } else {
                list = systemService.getAllApkInfoVOList(new ApkInfoVO());
                for (ApkInfoVO s : list) {
                    s.setNote(s.getNote() + s.getApkId());
                }
                setSession("apkinfo", list);
                setSession("apkinfoTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<ApkInfoVO>();
    }

    public List<DdlChannelVO> getDdlServerChannelList() {
        DdlChannelService systemService = (DdlChannelService) ServiceBeanID
                .getServiceObject(ServiceBeanID.DDL_CHANNEL_SERVICE);
        List<DdlChannelVO> list = null;
        try {
            list = systemService.getAllDdlChannel(new DdlChannelVO());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DdlChannelVO>();
    }


    public List<DdlProductVO> getDdlServerProductList() {
        DdlProductService systemService = (DdlProductService) ServiceBeanID
                .getServiceObject(ServiceBeanID.DDL_PRODUCT_SERVICE);
        List<DdlProductVO> list = null;
        try {
            list = systemService.getAllDdlProduct(new DdlProductVO());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DdlProductVO>();
    }

    public List<VersionVO> getVersionList() {
        SystemService systemService = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<VersionVO> list = null;
        try {
            if (getSession("version") != null
                    && getSession("versionTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("versionTimeOut")) < 1000 * 60 * 15) {
                list = (List<VersionVO>) getSession("version");
            } else {
                list = systemService.getVersionList();
                setSession("version", list);
                setSession("versionTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<VersionVO>();
    }

    public List<DdlProjectVO> getDdlProjectList() {
        SystemService systemService = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<DdlProjectVO> list = null;
        try {
            if (getSession("ddlProject") != null
                    && getSession("ddlProjectTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("ddlProjectTimeOut")) < 1000 * 60 * 15) {
                list = (List<DdlProjectVO>) getSession("ddlProject");
            } else {
                list = systemService.getDdlProjectList();
                for (DdlProjectVO p : list) {
                    p.setName(p.getName() + "—" + p.getId() + "");
                }
                setSession("ddlProject", list);
                setSession("ddlProjectTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DdlProjectVO>();
    }

    public List<AdverVO> getAdverList() {
        AdverService adv = (AdverService) ServiceBeanID
                .getServiceObject(ServiceBeanID.ADVER_SERVICE);
        List<AdverVO> list = null;
        try {
            if (getSession("adverList") != null
                    && getSession("adverTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("adverTimeOut")) < 1000 * 60 * 15) {
                list = (List<AdverVO>) getSession("adverList");
            } else {
                list = adv.getAllAdver(new AdverVO());
                setSession("adverList", list);
                setSession("adverTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<AdverVO>();
    }


    public List<AdvVO> getAdvList() {
        AdvService adv = (AdvService) ServiceBeanID
                .getServiceObject(ServiceBeanID.ADV_SERVICE);
        List<AdvVO> list = null;
        try {
            if (getSession("advList") != null
                    && getSession("advTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("advTimeOut")) < 1000 * 60 * 15) {
                list = (List<AdvVO>) getSession("advList");
            } else {
                list = adv.getAllAdvVOList(new AdvVO());
                setSession("advList", list);
                setSession("advTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<AdvVO>();
    }

    public List<DdlChannelVO> getDdlChannelList() {
        SystemService systemService = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<DdlChannelVO> list = null;
        try {
            if (getSession("ddlChannel") != null
                    && getSession("ddlChannelTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("ddlChannelTimeOut")) < 1000 * 60 * 15) {
                list = (List<DdlChannelVO>) getSession("ddlChannel");
            } else {
                list = systemService.getDdlChannelList();
                setSession("ddlChannel", list);
                setSession("ddlChannelTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DdlChannelVO>();
    }

    public List<CustomerVO> getCustomerList() {
        CustomerService customer = (CustomerService) ServiceBeanID
                .getServiceObject(ServiceBeanID.CUSTOMER_SERVICE);
        List<CustomerVO> list = null;
        try {
            if (getSession("customerList") != null
                    && getSession("customerTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("customerTimeOut")) < 1000 * 60 * 15) {
                list = (List<CustomerVO>) getSession("customerList");
            } else {
                CustomerVO customerVO = new CustomerVO();
                UserVO userVO = (UserVO) getSession(PkigConstants.SESSION_USER);
                if (userVO.getAgentId() > 0) {
                    customerVO.setAgentId(userVO.getAgentId());
                }
                list = customer.getAllCustomerList(customerVO);
                setSession("customerList", list);
                setSession("customerTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CustomerVO>();
    }

    public List<RoleVO> getRoleVOList() {
        RoleService roleService = (RoleService) ServiceBeanID
                .getServiceObject(ServiceBeanID.ROLE_SERVICE);
        List<RoleVO> list = null;
        try {
            list = roleService.getProjectRoleVOList();
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<RoleVO>();
    }

    public List<ProvinceVO> getProvinceList() {
        SystemService systemService = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<ProvinceVO> list = null;
        try {
            if (getSession("provinceList") != null
                    && getSession("provinceTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("provinceTimeOut")) < 1000 * 60 * 15) {
                list = (List<ProvinceVO>) getSession("provinceList");
            } else {
                list = systemService.getProvinceList();
                setSession("provinceList", list);
                setSession("provinceTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<ProvinceVO>();
    }

    public List<OperatorVO> getGlobalOperatorList() {
        OperatorService operatorService = (OperatorService) ServiceBeanID.getServiceObject(ServiceBeanID.OPERATOR_SERVICE);
        List<OperatorVO> list = null;

        if (getSession("operatorList") != null
                && getSession("operatorTimeOut") != null
                && (System.currentTimeMillis() - (Long) getSession("operatorTimeOut") < 1000 * 60 * 10)) {
            list = (List<OperatorVO>) getSession("operatorList");
        } else {
            list = operatorService.getAllOperatorVOList();
            setSession("operatorList", list);
        }
        setSession("operatorTimeOut", System.currentTimeMillis());
        return list;
    }

    public List<HashMap<String, String>> getOperator() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "0");
        item.put(VALUE, "所有--");
        list.add(item);
        item = new HashMap<String, String>();
        item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "移动");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "联通");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "电信");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getAdverType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "APK");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "API");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "网页");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getSelectList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "是");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "0");
        item.put(VALUE, "否");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getTypeList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "600400");
        item.put(VALUE, "SDK");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600403");
        item.put(VALUE, "引导");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600404");
        item.put(VALUE, "下沉");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600405");
        item.put(VALUE, "线下");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600406");
        item.put(VALUE, "网页链接");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600601");
        item.put(VALUE, "PUSH");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600602");
        item.put(VALUE, "插屏");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600603");
        item.put(VALUE, "浏览器劫持");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600604");
        item.put(VALUE, "APP启动劫持");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600605");
        item.put(VALUE, "桌面浮动按钮");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600606");
        item.put(VALUE, "桌面图标推广");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600607");
        item.put(VALUE, "桌面图标下载");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600608");
        item.put(VALUE, "FB/GP升级");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getUaTypeList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "200201");
        item.put(VALUE, "android");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "200202");
        item.put(VALUE, "iphone");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "200203");
        item.put(VALUE, "ipad");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "200204");
        item.put(VALUE, "web");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getCountryLevel() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "高");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "中");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "低");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "4");
        item.put(VALUE, "无效");
        list.add(item);
        return list;
    }


    public List<HashMap<String, String>> getNetType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "wifi");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "0");
        item.put(VALUE, "gprs");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "不限制");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getAdvNetType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "wifi");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "gprs");
        list.add(item);

        return list;
    }

    public List<HashMap<String, String>> getOperatorList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "移 动");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "联 通");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "电 信");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getLinkType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "应用");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "游戏");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "成人");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "4");
        item.put(VALUE, "首页");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getAtype() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "10600500");
        item.put(VALUE, "精品");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10600501");
        item.put(VALUE, "应用");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10600502");
        item.put(VALUE, "游戏");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getUserType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "10800100");
        item.put(VALUE, "系统");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800101");
        item.put(VALUE, "代理商");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800102");
        item.put(VALUE, "开发者");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getSubType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "ADULT");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "NON_ADULT");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "MY");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "4");
        item.put(VALUE, "TH");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "5");
        item.put(VALUE, "IN");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "6");
        item.put(VALUE, "CPL");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "7");
        item.put(VALUE, "CPI");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "8");
        item.put(VALUE, "LATAM");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "9");
        item.put(VALUE, "TOPOFFER");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10");
        item.put(VALUE, "CPS");
        list.add(item);

        return list;
    }

    public List<HashMap<String, String>> getPercentTypeList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "客户端金额成功率");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "客户端次数成功率");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "客户端人数成功率");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getAdvEffect() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "10800300");
        item.put(VALUE, "客户端随机");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800301");
        item.put(VALUE, "第1种方案");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800302");
        item.put(VALUE, "第2种方案");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getEffectType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "10800400");
        item.put(VALUE, "0");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800401");
        item.put(VALUE, "1");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800402");
        item.put(VALUE, "2");
        list.add(item);
        return list;
    }


    public List<HashMap<String, String>> getAdType() {


        SystemService agent = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<HashMap<String, String>> list = null;
        try {
            if (getSession("adType") != null
                    && getSession("adTypeTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("adTypeTimeOut")) < 1000 * 60 * 15) {
                list = (List<HashMap<String, String>>) getSession("adType");
            } else {
                list = agent.getEnuList(100200);

                setSession("adType", list);
                setSession("adTypeTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<HashMap<String, String>>();
    }

    public List<HashMap<String, String>> getSdkType() {


        SystemService agent = (SystemService) ServiceBeanID
                .getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
        List<HashMap<String, String>> list = null;

        try {
            if (getSession("sdkType") != null
                    && getSession("sdkTypeTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("sdkTypeTimeOut")) < 1000 * 60 * 15) {
                list = (List<HashMap<String, String>>) getSession("sdkType");
            } else {
                list = agent.getEnuList(600500);

                setSession("sdkType", list);
                setSession("sdkTypeTimeOut", System.currentTimeMillis());
            }
            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<HashMap<String, String>>();
    }

    public List<HashMap<String, String>> getApkStatus() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "10800200");
        item.put(VALUE, "新建状态");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800201");
        item.put(VALUE, "审核中");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800202");
        item.put(VALUE, "审核通过");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800203");
        item.put(VALUE, "审核不通过");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "10800204");
        item.put(VALUE, "已删除");
        list.add(item);
        return list;
    }

    //1定时，2桌面，3push,4 浏览器
    public List<HashMap<String, String>> getClickType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "定时");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "桌面");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "push");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "4");
        item.put(VALUE, "浏览器");
        list.add(item);

        return list;
    }

    //1定时，2悬浮，3插屏, 4订阅
    public List<HashMap<String, String>> getPushType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "定时");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "悬浮");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "插屏");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "4");
        item.put(VALUE, "订阅");
        list.add(item);
        return list;
    }

    //1桌面，2悬浮，3push, 4电商类型拦截， 5其他类型拦截
    public List<HashMap<String, String>> getPromotionType() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "1");
        item.put(VALUE, "桌面");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "2");
        item.put(VALUE, "悬浮");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3");
        item.put(VALUE, "push");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "4");
        item.put(VALUE, "电商类型拦截");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "5");
        item.put(VALUE, "其他类型拦截");
        list.add(item);

        return list;
    }


    public List<HashMap<String, String>> getEnumStatus() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "3200");
        item.put(VALUE, "是");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "3201");
        item.put(VALUE, "否");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getApkTypeList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "8200500");
        item.put(VALUE, "普通插件");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "8200501");
        item.put(VALUE, "线下插件");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getContinentList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "亚洲");
        item.put(VALUE, "亚洲");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "欧洲");
        item.put(VALUE, "欧洲");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "北美洲");
        item.put(VALUE, "北美洲");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "南美洲");
        item.put(VALUE, "南美洲");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "非洲");
        item.put(VALUE, "非洲");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "大洋洲");
        item.put(VALUE, "大洋洲");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getOfferSdkList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(KEY, "600601");
        item.put(VALUE, "PUSH");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600602");
        item.put(VALUE, "插屏");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600603");
        item.put(VALUE, "浏览器劫持");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600604");
        item.put(VALUE, "APP启动劫持");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600605");
        item.put(VALUE, "桌面浮动按钮");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600606");
        item.put(VALUE, "桌面图标推广");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600607");
        item.put(VALUE, "桌面图标下载");
        list.add(item);
        item = new HashMap<String, String>();
        item.put(KEY, "600608");
        item.put(VALUE, "FB/GP升级");
        list.add(item);
        return list;
    }

    public List<HashMap<String, String>> getOperatorsList() {
        OperatorService operatorService = (OperatorService) ServiceBeanID
                .getServiceObject(ServiceBeanID.OPERATOR_SERVICE);
        List<OperatorVO> list = null;
        List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        if (getSession("Operators") != null) {
            list = (List<OperatorVO>) getSession("Operators");
        } else {
            list = operatorService.getAllOperatorVOList();
            setSession("Operators", list);
        }
        for (OperatorVO o : list) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put(KEY, String.valueOf(o.getOperatorId()));
            item.put(VALUE, o.getOperatorName() + "-" + o.getCode());
            data.add(item);
        }
        return data;

    }

    public List<CountryVO> getAsiaCountryList() {
        DdlProductService agent = (DdlProductService) ServiceBeanID
                .getServiceObject(ServiceBeanID.DDL_PRODUCT_SERVICE);
        List<CountryVO> list = null;
        try {
            if (getSession("AsiaList") != null
                    && getSession("AsiaTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("AsiaTimeOut")) < 1000 * 60 * 15) {
                list = (List<CountryVO>) getSession("AsiaList");
            } else {
                list = agent.getDdlCountryList("亚洲");
                setSession("AsiaList", list);
                setSession("AsiaTimeOut", System.currentTimeMillis());
            }

            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CountryVO>();
    }

    public List<CountryVO> getEuropeCountryList() {
        DdlProductService agent = (DdlProductService) ServiceBeanID
                .getServiceObject(ServiceBeanID.DDL_PRODUCT_SERVICE);
        List<CountryVO> list = null;
        try {

            if (getSession("EuropeList") != null
                    && getSession("EuropeTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("EuropeTimeOut")) < 1000 * 60 * 15) {
                list = (List<CountryVO>) getSession("EuropeList");
            } else {
                list = agent.getDdlCountryList("欧洲");
                setSession("EuropeList", list);
                setSession("EuropeTimeOut", System.currentTimeMillis());
            }

            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CountryVO>();
    }

    public List<CountryVO> getAfricaCountryList() {
        DdlProductService agent = (DdlProductService) ServiceBeanID
                .getServiceObject(ServiceBeanID.DDL_PRODUCT_SERVICE);
        List<CountryVO> list = null;
        try {

            if (getSession("AfricaList") != null
                    && getSession("AfricaTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("AfricaTimeOut")) < 1000 * 60 * 15) {
                list = (List<CountryVO>) getSession("AfricaList");
            } else {
                list = agent.getDdlCountryList("非洲");
                setSession("AfricaList", list);
                setSession("AfricaTimeOut", System.currentTimeMillis());
            }


            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CountryVO>();
    }

    public List<CountryVO> getNorthAmericaCountryList() {
        DdlProductService agent = (DdlProductService) ServiceBeanID
                .getServiceObject(ServiceBeanID.DDL_PRODUCT_SERVICE);
        List<CountryVO> list = null;
        try {

            if (getSession("NorthAmericaList") != null
                    && getSession("NorthAmericaTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("NorthAmericaTimeOut")) < 1000 * 60 * 15) {
                list = (List<CountryVO>) getSession("NorthAmericaList");
            } else {
                list = agent.getDdlCountryList("北美洲");
                setSession("NorthAmericaList", list);
                setSession("NorthAmericaTimeOut", System.currentTimeMillis());
            }


            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CountryVO>();
    }

    public List<CountryVO> getOceaniaCountryList() {
        DdlProductService agent = (DdlProductService) ServiceBeanID
                .getServiceObject(ServiceBeanID.DDL_PRODUCT_SERVICE);
        List<CountryVO> list = null;
        try {

            if (getSession("OceaniaList") != null
                    && getSession("OceaniaTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("OceaniaTimeOut")) < 1000 * 60 * 15) {
                list = (List<CountryVO>) getSession("OceaniaList");
            } else {
                list = agent.getDdlCountryList("大洋洲");
                setSession("OceaniaList", list);
                setSession("OceaniaTimeOut", System.currentTimeMillis());
            }


            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CountryVO>();
    }

    public List<CountryVO> getSouthAmericaCountryList() {
        DdlProductService agent = (DdlProductService) ServiceBeanID
                .getServiceObject(ServiceBeanID.DDL_PRODUCT_SERVICE);
        List<CountryVO> list = null;
        try {

            if (getSession("SouthAmericaList") != null
                    && getSession("SouthAmericaTimeOut") != null
                    && (System.currentTimeMillis() - (Long) getSession("SouthAmericaTimeOut")) < 1000 * 60 * 15) {
                list = (List<CountryVO>) getSession("SouthAmericaList");
            } else {
                list = agent.getDdlCountryList("南美洲");
                setSession("SouthAmericaList", list);
                setSession("SouthAmericaTimeOut", System.currentTimeMillis());
            }


            return list;
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CountryVO>();
    }
}
