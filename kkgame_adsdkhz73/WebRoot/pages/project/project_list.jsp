<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.hz.entities.PortalUserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%
PortalUserVO userVO = (PortalUserVO)request.getSession().getAttribute("SESSION_PORTALUSER");
%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />
<s:if test="projectVO.isPage==0">
<script src="<%=request.getContextPath()%>/js/bootbox.min.js"></script>
<table id="dataTable" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
	<tr>
	<th>编号</th>
	<th>名称</th>
	<th>产品名称</th>
	<th>所属客户</th>
	<th>拓展人员</th>
	<th>代理商</th>
	<th>状态</th>
	<th>版本号</th>
	<th>创建时间</th>
	<th>审核时间</th>
	<th>出包时间</th>
	<th>是否开发者</th>
	<th>出包时长</th>
	<s:if test="projectVO.priceStatus>0">
	<th>价格审核</th>
	</s:if>
	<th>状态流转</th>
	<th>操作</th>
    </tr>
</thead>
<tbody>
<s:iterator value="projectVOList" status="st">
<tr id="project_<s:property value="id"/>">
	<td><s:property value="id"/></td>
	<td><a href="javascript:detailProject('<s:property value="id"/>','<s:property value="projectVO.queryType"/>');"><s:property value="name"/></a></td>
	<td><s:property value="productName"></s:property> </td>
	<td><s:property value="customerName"/></td>
	<td><s:property value="bdName"/></td>
	<td><s:property value="agentName"/></td>
	<td>
		<s:if test="status==1">新建</s:if>
		<s:if test="status==2">待技术审核</s:if>
		<s:if test="status==3">待出包</s:if>
		<s:if test="status==4">已出包</s:if>
		<s:if test="status==5">测试通过</s:if>
		<s:if test="status==6">技术参数错误</s:if>
		<s:if test="status==7">审核未通过</s:if>
	</td>
	<td><s:property value="version"/></td>
	<td><s:property value="createTime"/></td>
	<td><s:property value="confirmTime"/></td>
	<td><s:property value="packageTime"/></td>
	<td>
		<s:if test="isDevCustomer==0">否</s:if>
		<s:else>是</s:else>
	</td>
	<td><s:property value="timeStep"/></td>
	<s:if test="projectVO.priceStatus>0">
		<td>
			<s:if test="priceStatus==1">待价格审核</s:if>
			<s:if test="priceStatus==2">审核通过</s:if>
			<s:if test="priceStatus==3">审核未通过</s:if>
		</td>
	</s:if>
	<td >
		<s:if test="projectVO.queryType<=7">
		<role:equal type="BD,AG,SP">
		<s:if test="status==1">
		<a class="btn btn-warning" href="javascript: updateProjectStatus('<s:property value="id"/>',2,1)">提交技术审核</a>&nbsp;&nbsp;
		</s:if>
		</role:equal>
		<role:equal type="TC,SP">
		<s:if test="status==2">
		<a class="btn btn-info" href="javascript: confirmProject('<s:property value="id"/>')">审核</a>&nbsp;&nbsp;
		</s:if>
		</role:equal>
		<role:equal type="TC,SP">
		<s:if test="status==3||status==4">
		<a class="btn btn-info" href="javascript: uploadProject('<s:property value="id"/>')">上传包</a>&nbsp;&nbsp;
		</s:if>
		</role:equal>
		<role:equal type="TC,SP">
		<s:if test="status==4">
		<a class="btn btn-success" href="javascript: testProject('<s:property value="id"/>')">测试</a>&nbsp;&nbsp;
		</s:if>
		</role:equal>
		<role:equal type="TC,SP,BD,AG">
		<s:if test="status==5">
		<a class="btn btn-success" href="javascript: renewProject('<s:property value="id"/>')">重新出包</a>&nbsp;&nbsp;
		</s:if>
		</role:equal>
		</s:if>
	</td>
	<td >
		<role:equal type="BD,AG,SP">
		<s:if test="status==1">
		<a class="btn btn-danger" title="修改"  href="javascript: updateProject('<s:property value="id"/>')" >
		修改</a>&nbsp;&nbsp;
		</s:if>
		</role:equal>
		<role:equal type="BD,AG,SP">
		<s:if test="projectVO.queryType==3">
			<a class="btn btn-primary" title="添加备注" href="javascript: addProjectInfo('<s:property value="id"/>')" >
			添加备注</a>&nbsp;&nbsp;
		</s:if>
		</role:equal>
		<!-- 
		<a title="删除" href="javascript: removeProduct('<s:property value="id"/>')">
		<img src="<%=request.getContextPath()%>/images/del.gif"  border="0" />
				</a>
		 -->
		 <s:if test="projectVO.queryType==8">
		 	<role:equal type="SP">
		 	<a class="btn btn-primary" title="修改" href="javascript: priceAudit('<s:property value="id"/>')" >
		 		价格审核
		 	</a>
		 	</role:equal>
		 </s:if>
	</td>
</tr>
</s:iterator>
</tbody>
<tr>
	<td colspan="<s:if test="projectVO.priceStatus>0">12</s:if><s:if test="projectVO.priceStatus==0">11</s:if>">
	<div align="right">
	<page:paginationAjax formName="dataForm" property="projectVO.page" operation="Project!list.action"/>
	</div>
	</td>
</tr>
<s:if test="projectVO.isPage==0">
</table>

</s:if>
	<script type="text/javascript">
		var queryType = $("#queryType").val();
		function updateProject(id) {
			jQuery.post("Project!modify.action",{"projectVO.id":id,"projectVO.queryType":queryType},function(data){				
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					$('#containerData').html(data);
					return ;
				}
			});
		}
		function addProjectInfo(id) {
			jQuery.post("Project!addInfo.action",{"projectVO.id":id,"projectVO.queryType":queryType},function(data){				
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
			jQuery.post("Project!detail.action",{"projectVO.id":id,"projectVO.queryType":queryType},function(data){				
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					$('#containerData').html(data);
					return ;
				}
			});
		}
		function updateProjectStatus(id,status,priceStatus) {
			jQuery.post("Project!updateStatus.action",{"projectVO.id":id,"projectVO.status":status,"projectVO.priceStatus":priceStatus},function(data){				
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					showInfoToastMiddle('<font color=\'red\'>项目['+id+']提交审核成功</font>');
					doPageBottom('turn');
				}
			});
		}
		function confirmProject(id) {
			jQuery.post("Project!confirmProject.action",{"projectVO.id":id,"projectVO.queryType":queryType},function(data){				
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					$('#containerData').html(data);
					return ;
				}
			});
		}
		function renewProject(id) {
			jQuery.post("Project!renewProject.action",{"projectVO.id":id,"projectVO.queryType":queryType},function(data){				
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					showErrorToastMiddle("提交重新出包成功");
					doPageBottom('turn');
					return ;
				}
			});
		}
		
		function uploadProject(id) {
			jQuery.post("Project!upload.action",{"projectVO.id":id,"projectVO.queryType":queryType},function(data){				
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					$('#containerData').html(data);
					return ;
				}
			});
		}
		function testProject(id) {
			jQuery.post("Project!testProject.action",{"projectVO.id":id,"projectVO.queryType":queryType},function(data){				
				if(data == "-1"){
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					return ;
				} else {
					$('#containerData').html(data);
					return ;
				}
			});
		}
		function priceAudit(id) {
			jQuery.post("Project!priceAudit.action",{"projectVO.id":id,"projectVO.queryType":queryType},function(data){				
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