<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.kkgame.hz.entities.PortalUserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<s:if test="specialDataVOList.size==0">
<font color="red">NO Data</font>
</s:if>
<s:else>
<%
	PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
	request.setAttribute("roleType",userVO.getRoleType());
%>
		<s:set name="pv" value="0"/>
		<s:set name="uv" value="0"/>
		<s:set name="income" value="0"/>
		<s:set name="dividedincome" value="0"/>
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="specialDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
				<thead>
					<tr >
					<s:if test="billSearchVO.rowFieldVO.isShowDate==1"><th>Date</th></s:if>
					<s:if test="billSearchVO.rowFieldVO.isShowAgent==1"><th>代理商</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProject==1"><th>Project</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProduct==1"><th>Product</th></s:if>
		      		<th >pv</th>
		      		<th >uv</th>
		      		<th >Revenue</th>
	      			<th >Shared Revenue</th>

		      		</tr>
		      	</thead>
		      	</s:if>
		      	<tr >
				<s:if test="billSearchVO.rowFieldVO.isShowDate==1"><td ><s:property value="time"/></td></s:if>
				<s:if test="billSearchVO.rowFieldVO.isShowAgent==1"><td ><s:property value="agentName"/></td></s:if>
		      	<s:if test="billSearchVO.rowFieldVO.isShowBd==1"><td ><s:property value="bdName"/></td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowCustomer==1"><td ><s:property value="customerName"/></td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowProject==1"><td ><s:property value="projectName"/>[<font color="red"><s:property value="projectId"/></font>]</td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowProduct==1"><td ><s:property value="productName"/>[<font color="red"><s:property value="productId"/></font>]</td>
      			</s:if>
      			<td ><s:property value="pv"/></td>
      			<td ><s:property value="uv"/></td>
      			<td ><s:property value="income"/></td>
      			<td ><s:property value="dividedincome"/></td>

				</tr>
       			<s:set name="pv" value="#pv + pv"/>
       			<s:set name="uv" value="#uv + uv"/>
				<s:set name="income" value="#income + income"/>
				<s:set name="dividedincome" value="#dividedincome + dividedincome"/>
			</s:iterator>
			<s:set name="row" value="billSearchVO.rowFieldLen-1"/>
			<tr >
				<td >Total</td>
				<s:if test="#row >0">
				<td  colspan="<s:property value="#row"/>"></td>
				</s:if>
				<td ><s:property value="#pv"/></td>
				<td ><s:property value="#uv"/></td>
				<td ><fmt:formatNumber value="${income}" pattern="#0.00" /></td>
				<td ><fmt:formatNumber value="${dividedincome}" pattern="#0.00" /></td>

			</tr>
		</table>
</s:else>