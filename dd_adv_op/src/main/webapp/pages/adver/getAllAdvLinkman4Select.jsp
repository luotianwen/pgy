<%--
  User: XJ
  Date: 2016/4/15
  Time: 15:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<option value="0">
    --所有接入人员--
</option>
<s:iterator value="advLinkmanVOList">
    <option value="${id}" <s:if test="id==search.advLinkmanId">selected="selected"</s:if>>
        ${name}-${id}
    </option>
</s:iterator>
