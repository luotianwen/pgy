<%@ page isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<s:if test="agentVO.isPage==0">
<script src="<%=request.getContextPath()%>/js/bootbox.min.js"></script>
<table id="dataTable" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
	<tr>
	<th>编号</th>				
	<th>名称</th>				
	<th>类型</th>
	<th>级别</th>
	<th>账号状态</th>
	<th>最后登录时间</th>
	<th>操作</th>
    </tr>
</thead>
<tbody>
<s:iterator value="agentList" status="stat"  >
<tr>
<td><s:property value="id"/></td>	
<td><a href="javascript:detailAgent('<s:property value="id"/>','<s:property value="portalUserId"/>')"><s:property value="name"/></a></td>	
<td><s:if test="type==1">企业代理 </s:if> 
	<s:elseif test="type == 2">个人代理</s:elseif></td>	
<td ><s:property value="level"/></td>	
<td>
	<s:if test="status==0">正常 </s:if> 
	<s:elseif test="status == 1"><font color="blue"> 受限</font></s:elseif>
	<s:elseif test="status == 2"><font color="red">锁定</font></s:elseif></td>
<td><s:property value="lastLogin"/></td>								
<td>
<a class="btn btn-info" href="javascript:modifyAgent('<s:property value="id"/>','<s:property value="portalUserId"/>')">修改</a>&nbsp;&nbsp;&nbsp;
<a class="btn btn-danger" href="javascript:removeAgent('<s:property value="id"/>','<s:property value="portalUserId"/>','<s:property value="name"/>')">删除</a>	
</td>
</tr>
</s:iterator>
<tr>
<td colspan="7">
<div align="right">
	<page:paginationAjax formName="dataForm" property="agentVO.page" operation="Agent!list.action"/>
</div>
</td>
</tr>
</tbody>
<s:if test="agentVO.isPage==0">
</table>

</s:if>
