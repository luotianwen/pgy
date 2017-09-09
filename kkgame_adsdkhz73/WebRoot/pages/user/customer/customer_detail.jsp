<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>客户详情</h2>
</div>
<div class="modal-body">
<s:hidden id="id" name="customerVO.id"/>
	<table class="table table-bordered">
		<tr >	
			<td class="table_td_title">客户ID</td>			 
            <td>C<s:property value="customerVO.id"/></td>
			<td class="table_td_title">客户名称</td>			 
            <td><s:property value="customerVO.name"/></td>
		</tr>
		<tr >	
			<td class="table_td_title">联系人</td>			 
            <td><s:property value="customerVO.contactPerson"/></td>
			<td class="table_td_title">联系电话</td>			 
            <td><s:property value="customerVO.contactInfo"/></td>
		</tr>
		<tr >	
			<td class="table_td_title">城市</td>	
			<td colspan="3"><s:property value="customerVO.city"/></td>
		</tr>
			
		<tr >	
			<td class="table_td_title">所属代理商</td>	
			<td ><s:property value="customerVO.agentName"/></td>
			<td class="table_td_title">所属商务拓展人员</td>	
			<td><s:if test="customerVO.status==9">${customerVO.relayBdName}</s:if><s:else>${customerVO.businessDeveloperName}</s:else></td>
		</tr>
		
		<s:if test='(#session.SESSION_PORTALUSER.roleType.equals("BD") && #session.SESSION_PORTALUSER.roleId == customerVO.businessDeveloperId )
		|| #session.SESSION_PORTALUSER.roleType.equals("MG") || #session.SESSION_PORTALUSER.roleType.equals("SP") || #session.SESSION_PORTALUSER.roleType.equals("BH")
		||#session.SESSION_PORTALUSER.roleType.equals("AG")'>
		

		<s:if test="customerVO.status == 2 || customerVO.status == 7">
		<tr ><td class="table_td_title">拓展期限(总)</td>			 
            <td>${customerVO.expiryDate}天</td>
			<td class="table_td_title">剩余天数</td>			 
            <td><s:property value="customerVO.expiryDate-customerVO.leftDays" />天</td>
		</tr>	
		</s:if>
		<%@ include file="../common/user_detail.jsp" %>

		<tr ><td class="table_td_title">备注</td>			 
            <td colspan="3"><textarea id="description" name="customerVO.description" cols="50" rows="5" readonly="readonly">${customerVO.description}</textarea></td>
		</tr>
	</s:if>	
	
	<s:if test='customerVO.status == 9 && customerVO.relayStatus ==6 '>
	<tr ><td class="table_td_title">收回时间</td>			 
            <td width="85%" nowrap="nowrap"  colspan="3">${customerVO.callBackTime}</td>
	</tr>
	</s:if>
	<s:if test='customerVO.callBackReason != null && customerVO.callBackReason != ""'>
	<tr ><td class="table_td_title">收回原因</td>			 
            <td width="85%" nowrap="nowrap"  colspan="3"><textarea id="callBackReason" name="customerVO.callBackReason" cols="50" rows="5" readonly="readonly">${customerVO.callBackReason}</textarea></td>
	</tr>
	</s:if>	

	<s:if test='customerVO.abandonReason != null && customerVO.abandonReason != ""'>
	<tr ><td class="table_td_title">放弃原因</td>			 
            <td width="85%" nowrap="nowrap"  colspan="3"><textarea id="abandonReason" name="customerVO.abandonReason" cols="50" rows="5" readonly="readonly">${customerVO.abandonReason}</textarea></td>
	</tr>
	</s:if>	
	<s:if test='customerVO.status == 3 '>
	<tr ><td class="table_td_title">审核未通过原因</td>			 
            <td width="85%" nowrap="nowrap"  colspan="3"><textarea id="auditFailReason" name="customerVO.auditFailReason" cols="50" rows="5" readonly="readonly">${customerVO.auditFailReason}</textarea></td>
	</tr>
	</s:if>
	</table>
</div>
<div class="modal-footer">
</div>