<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--All Product--
</option>
<s:iterator value="productVOList" status="stat">
<option value="${id}">
	${name}
</option>
</s:iterator>