<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--所有产品--
</option>
<s:iterator value="productVOList" status="stat">
<option value="${id}" <s:if test="id == searchVO.productId">selected="selected"</s:if>>
	${name}-${id}
</option>
</s:iterator>