<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="productVOList" status="stat">
	<label class="checkbox inline" for="productId_<s:property value="id"/>">
	<input id="productId_<s:property value="id"/>" type="radio" name="productId"  value="<s:property value="id"/>" class="inline">
		<s:property value="name"/>
	</label>
	<s:if test="(#stat.index+1)%3==0"><br/></s:if>
</s:iterator>
