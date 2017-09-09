<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0" style="align-content: center">
	--所有运营商--
</option>
<s:iterator value="operatorVOList" status="stat">
<option value="${operatorId}" <s:if test="id == searchVO.operatorId">selected="selected"</s:if>>
		${operatorName}-${code}
</option>
</s:iterator>