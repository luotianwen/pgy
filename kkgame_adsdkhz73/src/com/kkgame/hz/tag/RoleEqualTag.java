package com.kkgame.hz.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.entities.PortalUserVO;

public class RoleEqualTag extends BodyTagSupport {

	private String type;
	private String name;

	@Override
	public int doStartTag() throws JspException {
		name = PkigConstants.SESSION_PORTALUSER;
		HttpSession session = pageContext.getSession();
		PortalUserVO userVO = (PortalUserVO) session.getAttribute(name);
		String roteTypes = "," + type + ",";
		String userType = "," + userVO.getRoleType() + ",";
		if (roteTypes.indexOf(userType) > -1) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	public int doAfterBodyTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		type = null;
		name = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}