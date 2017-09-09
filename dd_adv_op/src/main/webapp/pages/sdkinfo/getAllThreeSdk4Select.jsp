<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0" style="align-content: center">
	--所有SDK--
</option>
<s:iterator value="threeSdkVOList">
<option value="${id}" <s:if test="id == searchVO.sdkId">selected="selected"</s:if>>
	${name}-${id}
</option>
</s:iterator>