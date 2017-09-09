<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="com.kkgame.hz.entities.*" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:if test="customerVO.isPage==0">
<script src="<%=request.getContextPath()%>/js/bootbox.min.js"></script>
<table class="table table-bordered table-striped table-hover" id="dataTable">
</s:if>
<!-- 商务助理   客户栏目 -->
<thead>

<role:equal type="SP,MG,BH">
<tr>
	<th>编号</th>
	<th>客户名称</th>
	<th>代理商</th>
	<th>拓展人员</th>
	<th>城市</th>
	<th>状态</th>
	<th>
	<s:if test="customerVO.status ==0">创建时间</s:if>
	<s:elseif test="customerVO.status ==1">提交审核时间</s:elseif>
	<s:elseif test="customerVO.status !=1">审核通过时间</s:elseif>
	</th>
	<th>状态流转</th>
	<th>操作</th>
</tr>
</role:equal>

<!-- 代理商   客户栏目 -->
<role:equal type="AG,XJ">
<tr>
	<th>编号</th>
	<th>客户名称</th>
	<th>拓展人员</th>
	<th>城市</th>
	<th>状态</th>
	<th>
	<s:if test="customerVO.status ==1">提交审核时间</s:if>
	<s:if test="customerVO.status !=1">审核通过时间</s:if>
	</th>
	<th>状态流转</th>
	<th>操作</th>
</tr>
</role:equal>

<!-- 拓展人员   客户栏目 -->
<role:equal type="BD">
<tr>
	<th>编号</th>
	<th>客户名称</th>
	<th>城市</th>
	<th>状态</th>
	<th>
	<s:if test="customerVO.status ==1">提交审核时间</s:if>
	<s:if test="customerVO.status !=1">审核通过时间</s:if>
	</th>
	<th>状态流转</th>
	<th>操作</th>
</tr>
</role:equal>
</thead>
<tbody>
<s:if test="customerList.size> 0"> 
<s:iterator value="customerList" status="stat"  >
	<tr>
		<td>C<s:property value="id"/></td>	
		<td><a href="javascript:detailCustomer('<s:property value="id"/>','<s:property value="portalUserId"/>')"><s:property value="name"/></a></td>	
		<role:equal type="SP,MG,BH">
		<td>${agentName} </td>	
		<td><s:if test="status==9">${relayBdName}</s:if><s:else>${businessDeveloperName}</s:else><s:if test="relayStatus==10"> --> ${relayBdName}</s:if></td>	
		</role:equal>
		<role:equal type="AG,XJ">
		<td>${businessDeveloperName}</td>	
		</role:equal>
		<td>${city}</td>	
		<td>
			<s:if test="status==0">新建 </s:if> 
			<s:elseif test="status == 1">待审</s:elseif>
			<s:elseif test="status == 2">拓展中(剩<s:property value="expiryDate-leftDays" />天)
			<s:if test='(expiryDate-leftDays) > 0  && 3 >(expiryDate-leftDays)'><font color="red">预警</font> </s:if>
			</s:elseif>
			<s:elseif test="status == 3">审核未通过</s:elseif>
			<s:elseif test="status == 4">已签约</s:elseif>
			<s:elseif test="status == 5">放弃</s:elseif>
			<s:elseif test="status == 6">被收回(<s:property value="callBackDays+1" />)</s:elseif>
			<s:elseif test="status == 7">已过期(<s:property value="leftDays-expiryDate+1" />)</s:elseif>
			<s:elseif test="status == 8">延期</s:elseif>
			<s:elseif test="status == 9">空闲的</s:elseif> <s:if test="relayStatus==10">(移交中)</s:if>
		</td>
		<s:if test="status ==0">
		<td>${createTime}</td>	
		</s:if>				
		<s:elseif test="status ==1">
		<td>${submitTime}</td>	
		</s:elseif>
		<s:elseif  test="status >1 || relayStatus == 6 ">
		<td>${confirmTime}</td>	
		</s:elseif>
	<!-- 流程控制开始 -->	
	<td>	
	<role:equal type="SP,MG">
	<s:if test="status ==1">	
		<a class="btn" href="javascript:auditCustomer('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">进入审核页面</a>&nbsp;	
	</s:if>
	<s:if test="status ==2">
		<a class="btn btn-warning" href="javascript:call_back('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}','${name}')">收回</a>&nbsp;	
		<a class="btn btn-success" href="javascript:sign_up('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">签约</a>&nbsp;	
	</s:if>
	<s:if test="status ==4">
		<a class="btn btn-warning" href="javascript:call_back('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}','${name}')" id="gogo">收回</a>&nbsp;	
	</s:if>
	<s:if test='status ==7 '>		
		<s:if test="expiryDate > 45">
			<a class="btn btn-success" href="javascript:sign_up('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">签约</a>&nbsp;
		</s:if>
		<s:if test="relayStatus ==8">
			<a class="btn" href="javascript:agree_deferred('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}','${expiryDate}')">同意延期</a>&nbsp;	
		</s:if>
		<s:if test="expiryDate > 45 || relayStatus ==8">
			<a class="btn btn-warning" href="javascript:call_back('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}','${name}')">收回</a>&nbsp;
		</s:if>
	</s:if>	
	</role:equal>
<!--  移交中的客户不做操作 -->	
	<s:if test='relayStatus !=10 '>	
	<role:equal type="SP,MG,AG,BH">	
	<s:if test='(status ==0 ||  status ==3) '>
		<a class="btn" href="javascript:submitConfirm('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">提交审核</a>&nbsp;
	</s:if>
	</role:equal>
	<role:equal type="BD">	
	<s:if test='(status ==0 ||  status ==3) && isMaster == "Y"'>
		<a class="btn" href="javascript:submitConfirm('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">提交审核</a>&nbsp;
	</s:if>
	<s:elseif test="status == 9">
		<!-- 客户收回后 原商务拓展人员 3 天内不能申请 -->
	<s:if test="#session.SESSION_BD.id == relayBdId && callBackDays <=3 ">		
	</s:if>
	<s:else>
		<a class="btn" href="javascript:doCustomerApply('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">申请</a>&nbsp;
	</s:else>
	</s:elseif>	
	</role:equal>
		
	<role:equal type="BD">
	<!-- 商务人员 在客户非闲置状态 才有权移交放弃 -->
	<!-- relayStatus=8 申请延期状态 -->
	<s:if test='status ==7 && relayStatus !=8 && isMaster == "Y"'>
		<s:if test="expiryDate < 60 ">
		<a class="btn" href="javascript:deferred('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">申请延期</a>&nbsp;	
		</s:if>
	</s:if>
	<!-- isMaster ，是否是客户的所有者 -->
	<s:if test='status !=9 && isMaster == "Y"'>
		<s:if test="status !=1 ">
			<a class="btn" href="javascript:devolve('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">移交</a>&nbsp;	
		</s:if>
		<s:elseif test="status == 1">审核中</s:elseif>	
		<a class="btn" href="javascript:abandon('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}','${name}')">放弃</a>&nbsp;	
	</s:if>		
	</role:equal>
	</s:if>

	<!-- 转移状态的特殊处理 -->
	<s:if test="relayStatus ==10 ">
	<role:equal type="AG,SP">客户移交中</role:equal>
	<role:equal type="BD">
		<s:if test='isMaster.equals("Y")'>
			<a class="btn" href="javascript:cancel_devolve('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">取消转移</a>	
		</s:if>
		<s:elseif test='isMaster.equals("N")'>待审核移交</s:elseif>	
	</role:equal>
	<role:equal type="MG,SP">
		<a class="btn" href="javascript:agree_devolve('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">同意</a>
		<a class="btn" href="javascript:disagree_devolve('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">不同意</a>
	</role:equal>			
	</s:if>
	</td>			
<!-- 流程控制结束 -->							
	<td>
		<role:equal type="BD">		
			<s:if test='isMaster.equals("Y")'>	
				<a class="btn btn-info" href="javascript:modifyCustomer('${id}','${portalUserId}');">修改</a>&nbsp;	
			</s:if>		
		</role:equal>
		<role:equal type="AG">	
			<a class="btn btn-info" href="javascript:modifyCustomer('${id}','${portalUserId}');">修改</a>&nbsp;			
		</role:equal>
		<!-- 新建和通过状态 -->
		<s:if test="(status == 0 ||  status == 3) ">
			<role:equal type="BD">	
			<!-- 只有自己的客户才能删除 -->
	 			<s:if test='isMaster.equals("Y")'>			
					<a class="btn btn-danger" href="javascript:removeCustomer('${id}','${portalUserId}','${name}')">删除</a>					
				</s:if>			
			</role:equal>		
			<role:equal type="AG">			
				<a class="btn btn-danger" href="javascript:removeCustomer('${id}','${portalUserId}','${name}')">删除</a>					
			</role:equal>			
		</s:if>						
		<role:equal type="SP,MG">
			<a class="btn btn-info" href="javascript:modifyCustomer('${id}','${portalUserId}');">修改</a>&nbsp;	
			<a class="btn btn-danger" href="javascript:removeCustomer('${id}','${portalUserId}','${name}')">删除</a>						
		</role:equal>			
	</td>
	</tr>
</s:iterator>
</s:if>
</tbody>
<tr>
<role:equal type="SP,MG,BH">
<td colspan="9">
</role:equal>
<role:equal type="AG,XJ">
<td colspan="8">
</role:equal>
<role:equal type="BD">
<td colspan="7">
</role:equal>
<div align="right">
<page:paginationAjax formName="customerForm" property="customerVO.page" operation="Customer!list.action"/>
</div> 
</td>
</tr>
<s:if test="customerVO.isPage==0">
</table>
</s:if>
<script type="text/javascript">
if($("#searchId").val() == 0){
	$("#searchId").val('');
}
</script>
