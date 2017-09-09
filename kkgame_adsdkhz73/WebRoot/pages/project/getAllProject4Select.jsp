<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<option value="0">
	--All Project--
</option>
<s:iterator value="projectList" status="stat">
<option value="${id}" <s:if test="id == billSearchVO.projectId">selected="selected"</s:if>>
	${name}-${id}
</option>
</s:iterator>