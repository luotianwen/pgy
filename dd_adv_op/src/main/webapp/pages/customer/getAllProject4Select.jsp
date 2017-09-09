<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--所有项目--
</option>
<s:iterator value="projectVOList" status="stat">
<option value="${id}" <s:if test="id == searchVO.projectId">selected="selected"</s:if>>
	${name}-${id}
</option>
</s:iterator>