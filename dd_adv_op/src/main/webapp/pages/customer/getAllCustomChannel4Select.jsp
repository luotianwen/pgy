<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="">
	--所有渠道--
</option>
<s:iterator value="customChannelVOList" status="stat">
<option value="${customChannelId}" <s:if test="id == searchVO.customChannelId">selected="selected"</s:if>>
	${customChannelId}
</option>
</s:iterator>