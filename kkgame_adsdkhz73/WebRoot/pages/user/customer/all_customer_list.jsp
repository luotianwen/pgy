<%@ page   isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
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
		<tr>
		<th>编号</th>
		<th>客户名称</th>
		<th>代理商</th>
		<th>拓展人员</th>
		<th>城市</th>
			<th>类型</th>
			<th>状态</th>		
			<th>审核通过时间</th>	
			<th>收回时间</th>	
			<th>收回天数</th>	
			<th>操作</th>
		</tr>	
</thead>
<s:if test="customerList.size> 0"> 
		<s:iterator value="customerList" status="stat"  >
			<tr>
				<td>C<s:property value="id"/></td>	
				<td>&nbsp;
				<role:equal type="SP,MG,BH">
							<a href="javascript:detail('<s:property value="id"/>','<s:property value="portalUserId"/>')"><s:property value="name"/></a>
				</role:equal>
				<role:equal type="BD,AG"><s:property value="name"/></role:equal>
				</td>
			
				<td >${agentName} </td>	
				<td ><s:if test="status==9">${relayBdName}</s:if><s:else>${businessDeveloperName}</s:else></td>	
					
				<td >${city}</td>	
				<td>
					<s:if test="type==1">线上</s:if>
					<s:elseif test="type == 2">线下</s:elseif>
					<s:elseif test="type == 3">其他</s:elseif> </td>	
				<td>
					<s:if test="status==0">新建 </s:if> 
					<s:elseif test="status == 1">待审</s:elseif>
					<s:elseif test="status == 2">拓展中
						<role:equal type="SP,MG,BH">(剩<s:property value="expiryDate-leftDays" />天)
						<s:if test='(expiryDate-leftDays) > 0  && 3 >(expiryDate-leftDays)'><font color="red">预警</font></s:if></role:equal></s:elseif>
					<s:elseif test="status == 3">审核未通过</s:elseif>
					<s:elseif test="status == 4">已签约</s:elseif>
					<s:elseif test="status == 5">放弃</s:elseif>
					<s:elseif test="status == 6">被收回<role:equal type="SP,MG,BH">(<s:property value="callBackDays+1" />)</role:equal> </s:elseif>
					<s:elseif test="status == 7">已过期<role:equal type="SP,MG,BH">(<s:property value="leftDays-expiryDate+1" />)</role:equal></s:elseif>
					<s:elseif test="status == 8">延期</s:elseif>
					<s:elseif test="status == 9">空闲的</s:elseif></td>	
				<td>${confirmTime}</td>	
				<td><s:if test="relayStatus==6 && status==9">${callBackTime}</s:if></td>	
				<td><s:if test="relayStatus==6 && status==9">${callBackDays}</s:if></td>	
				
				<td>
			<role:equal type="SP,MG,BH">
			<a class="btn btn-info" href="javascript:modifyCustomer('${id}','${portalUserId}');">修改</a>&nbsp;	&nbsp;
			<a class="btn btn-danger" href="javascript:removeCustomer('${id}','${portalUserId}','${name}')">删除</a>					
			</role:equal>	
				</td>
			</tr>
		</s:iterator>
		<tr>
			<td colspan="11">
      	<div class="pagelist" id="pagelist1" align="right">
      	<page:paginationAjax formName="dataForm" property="customerVO.page"
    		operation="Customer!allCustomerList.action"/>
    	</div> 
			</td>
		</tr>
</s:if>
<s:if test="customerVO.isPage==0">
</table>
</s:if>

<script type="text/javascript">
if($("#searchId").val()==0) {
	$("#searchId").val('');
}
</script>
