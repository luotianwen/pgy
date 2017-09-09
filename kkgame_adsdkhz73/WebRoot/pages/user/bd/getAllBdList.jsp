<%@ page isELIgnored ="false" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--请选择商务人员--
</option>
<s:iterator value="bdList" status="stat">
<option value="${id }">
	${name }
</option>
</s:iterator>