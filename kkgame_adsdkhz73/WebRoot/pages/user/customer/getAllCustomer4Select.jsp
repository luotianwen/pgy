<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--所有客户--
</option>
<s:iterator value="customerList" status="stat">
<s:if test="id != 80090">
<option value="${id}" <s:if test="id == billSearchVO.provinceId">selected="selected"</s:if>>
	${name}
</option>
</s:if>
</s:iterator>