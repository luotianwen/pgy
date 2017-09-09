package com.kkgame.hz.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.service.CustomerService;
import com.kkgame.hz.service.OsService;
import com.kkgame.hz.util.UtilHelper;

public class Policy {
	private static Log logger = LogFactory.getLog(Policy.class);
	
	private BaseAction baseAction = new BaseAction();
	
	public static final String KEY ="key";
	
	public static final String VALUE ="value";
		
	public List getAgentList() {
		try {
			TagService tagService = (TagService)baseAction.getServiceObject(UtilHelper.TAG_SERVICE);
			return tagService.getAgentList();
		} catch (Exception e) {
			logger.error(e);
		}
		return new ArrayList();
	}
	
	public List getBdList() {
		try {
			TagService tagService = (TagService)baseAction.getServiceObject(UtilHelper.TAG_SERVICE);
			return tagService.getBdList();
		} catch (Exception e) {
			logger.error(e);
		}
		return new ArrayList();
	}
	
	public List getBhList() {
		try {
			TagService tagService = (TagService)baseAction.getServiceObject(UtilHelper.TAG_SERVICE);
			return tagService.getBhList();
		} catch (Exception e) {
			logger.error(e);
		}
		return new ArrayList();
	}
		
	public List getPortalList() {
		try {
			TagService tagService = (TagService)baseAction.getServiceObject(UtilHelper.TAG_SERVICE);
			return tagService.getPortalList();
		} catch (Exception e) {
			logger.error(e);
		}
		return new ArrayList();
	}
	
	public List getLcdList() {
		try {
			TagService tagService = (TagService)baseAction.getServiceObject(UtilHelper.TAG_SERVICE);
			return tagService.getLcdList();
		} catch (Exception e) {
			logger.error(e);
		}
		return new ArrayList();
	}
	
	public List getCustomerList() {
		try {
			CustomerService customerService = (CustomerService)baseAction.getServiceObject(UtilHelper.CUSTOMER_SERVICE);
			return customerService.getValidCustomerList(new CustomerVO());
		} catch (Exception e) {
			logger.error(e);
		}
		return new ArrayList();
	}
		
	public List getProvinceList() {
		try {
			TagService tagService = (TagService)baseAction.getServiceObject(UtilHelper.TAG_SERVICE);
			return tagService.getProvinceList();
		} catch (Exception e) {
			logger.error(e);
		}
		return new ArrayList();
	}
	public List getProductList() {
		try {
			TagService tagService = (TagService)baseAction.getServiceObject(UtilHelper.TAG_SERVICE);
			return tagService.getProductList();
		} catch (Exception e) {
			logger.error(e);
		}
		return new ArrayList();
	}
	
	public List<HashMap<String,String>> getCooperateTypeList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "联运");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "2");
		item.put(VALUE, "换量");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "3");
		item.put(VALUE, "自营");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "4");
		item.put(VALUE, "友盟结算");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "5");
		item.put(VALUE, "联盟产品");
		list.add(item);

		return list;
	}
	
	public List<HashMap<String,String>> getCooperateList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "激活");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "2");
		item.put(VALUE, "分成");
		list.add(item);
		return list;
	}
	
	public List<HashMap<String,String>> getVersionList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY, "0");
		item.put(VALUE, "版本0");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "版本1");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "2");
		item.put(VALUE, "版本2");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"3");
		item.put(VALUE, "版本3");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "4");
		item.put(VALUE, "版本4");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"5");
		item.put(VALUE, "版本5");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "6");
		item.put(VALUE, "版本6");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"7");
		item.put(VALUE, "版本7");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "8");
		item.put(VALUE, "版本8");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"9");
		item.put(VALUE, "版本9");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "10");
		item.put(VALUE, "版本10");
		list.add(item);
		return list;
	}
	
	public List<HashMap<String,String>> getAdType() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "高");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "2");
		item.put(VALUE, "中");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "3");
		item.put(VALUE, "低");
		list.add(item);
		return list;
	}
	
	public List<HashMap<String,String>> getPopulariseTypeList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "线上");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "2");
		item.put(VALUE, "线下");
		list.add(item);
		return list;
	}
	
	public List<HashMap<String,String>> getBigList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "大包");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "2");
		item.put(VALUE, "小包");
		list.add(item);
		return list;
	}
	
	public List<HashMap<String,String>> getOperator() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "移动");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "2");
		item.put(VALUE, "联通");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "3");
		item.put(VALUE, "电信");
		list.add(item);
		return list;
	}
	
	public List<HashMap<String,String>> getOsList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "Android OS2.3x");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"2");
		item.put(VALUE, "IOS 5.0X ");
		list.add(item);
		return list;
	}
	
	public List<HashMap<String,String>> getPriceList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"300-599");
		item.put(VALUE, "300-599");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"600-999");
		item.put(VALUE, "600-999");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"1000-1499");
		item.put(VALUE, "1000-1499");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"1500-1999");
		item.put(VALUE, "1500-1999");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"2000-2499");
		item.put(VALUE, "2000-2499");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"2500-2999");
		item.put(VALUE, "2500-2999");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"3000-3499");
		item.put(VALUE, "3000-3499");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY,"3500-3999");
		item.put(VALUE, "3500-3999");
		list.add(item);
		return list;
	}
	
	public List<HashMap<String,String>> getScreenList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> item =new HashMap<String, String>();
		item.put(KEY,"1");
		item.put(VALUE, "240x320");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "2");
		item.put(VALUE, "240x400");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "3");
		item.put(VALUE, "240x432");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "4");
		item.put(VALUE, "272x480");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "5");
		item.put(VALUE, "320x240");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "6");
		item.put(VALUE, "320x400");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "7");
		item.put(VALUE, "320x480");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "8");
		item.put(VALUE, "360x600");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "9");
		item.put(VALUE, "360x640");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "10");
		item.put(VALUE, "400x240");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "11");
		item.put(VALUE, "400x320");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "12");
		item.put(VALUE, "480x800");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "13");
		item.put(VALUE, "480x854");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "14");
		item.put(VALUE, "640x800");
		list.add(item);
		item =new HashMap<String, String>();
		item.put(KEY, "15");
		item.put(VALUE, "1280x800");
		list.add(item);
		return list;
	}

	public List<HashMap<String,String>> getPackageStatusList() {
		List<HashMap<String,String>> list = new ArrayList<>();
		HashMap<String, String> item =new HashMap<>();
		item.put(KEY,"1");
		item.put(VALUE, "新建");
		list.add(item);
		item =new HashMap<>();
		item.put(KEY, "2");
		item.put(VALUE, "待技术审核");
		list.add(item);
		item =new HashMap<>();
		item.put(KEY,"3");
		item.put(VALUE, "待出包");
		list.add(item);
		item =new HashMap<>();
		item.put(KEY, "4");
		item.put(VALUE, "已出包");
		list.add(item);
		item =new HashMap<>();
		item.put(KEY,"5");
		item.put(VALUE, "测试通过");
		list.add(item);
		item =new HashMap<>();
		item.put(KEY, "6");
		item.put(VALUE, "技术参数错误");
		list.add(item);
		item =new HashMap<>();
		item.put(KEY,"7");
		item.put(VALUE, "审核未通过");
		list.add(item);
		return list;
	}
}
