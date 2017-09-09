<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.kkgame.hz.entities.PortalUserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%
	PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
	request.setAttribute("roleId",userVO.getRoleId());
	if(!"BD".equals(userVO.getRoleType())){
%>
	<option value="0">
		--所有商务--
	</option>
<%
	}
%>
<s:iterator value="bdList" status="stat">
<option value="${id}" >
	${name}
</option>
</s:iterator>