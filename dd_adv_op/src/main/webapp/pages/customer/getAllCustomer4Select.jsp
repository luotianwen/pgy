<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--所有客户--
</option>
<s:iterator value="customerList" status="stat">
<option value="${id}" <s:if test="id == searchVO.customId">selected="selected"</s:if>>
	${name}
</option>
</s:iterator>