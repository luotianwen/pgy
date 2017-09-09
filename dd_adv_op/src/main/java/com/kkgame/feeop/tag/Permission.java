package com.kkgame.feeop.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.user.bean.UserSession;
import com.kkgame.feeop.util.CheckUtilities;

@SuppressWarnings("serial")
public class Permission extends TagSupport {

	/**
	Tag.EVAL_BODY_INCLUDE 包含主体内容
    Tag.SKIP_BODY     不包含主体内容
    Tag.EVAL_PAGE      包含后面的页面内容 
    Tag.SKIP_PAGE     不包含主体的内容 
	**/
	
	private String resName;
	
	@Override
	public int doStartTag() throws JspException {
		HttpSession session = this.pageContext.getSession();
		if(CheckUtilities.isEmptyString(resName)){
			return SKIP_BODY;
		}
		UserSession us = (UserSession)session.getAttribute(BaseAction.USER_SESSION);
		if(us == null){
			return SKIP_BODY;
		}
		if(us.hasPermission(this.resName)){
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	
}
