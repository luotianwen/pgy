<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--所有代理商--
</option>
<s:iterator value="agentList" status="stat">
<option value="${id}" <s:if test="id == searchVO.agentId">selected="selected"</s:if>>
	${name}
</option>
</s:iterator>