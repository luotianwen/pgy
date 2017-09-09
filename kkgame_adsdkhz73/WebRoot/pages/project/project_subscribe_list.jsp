<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.hz.entities.PortalUserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>

<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />
<s:if test="projectVO.isPage==0">
<script src="<%=request.getContextPath()%>/js/bootbox.min.js"></script>
<table id="dataTable" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
	<tr>
	<th>编号</th>
	<th>名称</th>				
	<th>所属客户</th>
	<th>拓展人员</th>
	<th>代理商</th>
	<th>订阅渠道</th>
	<th>状态</th>
	<th>时间</th>
	<th>操作</th>
    </tr>
</thead>
<tbody>
<s:iterator value="projectVOList" status="st">
<tr id="project_<s:property value="id"/>">
	<td><s:property value="id"/></td>
	<td><a href="javascript:detailProject('<s:property value="id"/>');"><s:property value="name"/></a></td>
	<td><s:property value="customerName"/></td>					
	<td><s:property value="bdName"/></td>
	<td><s:property value="agentName"/></td>
	<td><s:property value="schemeName"/></td>
	<td>
		<s:if test="status==1">是</s:if>
		<s:if test="status==2">否</s:if>

	</td>
	<td><s:property value="CreateTime"/></td>

	<td >

		<a class="btn btn-danger" title="修改"  href="javascript: updateProject('<s:property value="id"/>')" >
		修改</a>&nbsp;&nbsp;

	</td>
</tr>
</s:iterator>
</tbody>
<tr>
	<td colspan="9">
	<div align="right">
	<page:paginationAjax formName="dataForm" property="projectVO.page" operation="Project!subscribeList.action"/>
	</div>
	</td>
</tr>
<s:if test="projectVO.isPage==0">
</table>

</s:if>
	<script type="text/javascript">

		function updateProject(id) {
			jQuery.post("Project!subscribeModify.action",{"projectVO.id":id},function(data){
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					$('#containerData').html(data);
					return ;
				}
			});
		}

		function detailProject(id,queryType) {
			jQuery.post("Project!subscribeDetail.action",{"projectVO.id":id},function(data){
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					$('#containerData').html(data);
					return ;
				}
			});
		}

		
	</script>