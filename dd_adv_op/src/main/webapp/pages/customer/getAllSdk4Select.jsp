<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="productSdkVOList.size==0">
请先为产品配置SDK
</s:if>
<s:else>
<s:iterator value="productSdkVOList" status="stat">
	<s:property value="sdkName"/> 概率:<input id="cconfig_<s:property value="sdkName" />" type="text" class="form-control"  size="30" />
</s:iterator></s:else>