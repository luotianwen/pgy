<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--所有广告--
</option>
<s:iterator value="offerSdkVOList" >
<option value="${id}" <s:if test="id == searchVO.adId">selected="selected"</s:if>>
	${adName}-${id}
</option>
</s:iterator>