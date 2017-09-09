<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/permission.tld" prefix="authz"%>
<%
	UserVO userVO = (UserVO) request.getSession().getAttribute("SESSION_USER");
%>
<% if(userVO.getIsBd()==0){

%>
<option value="0">
	--所有商务--
</option>
<% }

%>
<s:iterator value="bdList" status="stat">
<option value="${id}" >
	${name}
</option>
</s:iterator>