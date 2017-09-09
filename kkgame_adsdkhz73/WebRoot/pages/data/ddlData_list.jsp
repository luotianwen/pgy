<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.kkgame.hz.entities.PortalUserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>

<%
	PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
	request.setAttribute("roleType",userVO.getRoleType());
%>
		<s:set name="totalClickCount" value="0"/>
		<s:set name="totalValidClickCount" value="0"/>
		<s:set name="totalSaleCount" value="0"/>
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="ddlDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
				<thead>
					<tr >
					<s:if test="billSearchVO.rowFieldVO.isShowDate==1"><th>Date</th></s:if>
					<s:if test="billSearchVO.rowFieldVO.isShowAgent==1"><th>代理商</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProject==1"><th>Project</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProduct==1"><th>Product</th></s:if>
		      		<th >Click</th>
		      		<th >Valid Click</th>
		      		<th >Conversions</th>
		      		<th >CR(%)</th>
		      		</tr>
		      	</thead>
		      	</s:if>
		      	<tr >
				<s:if test="billSearchVO.rowFieldVO.isShowDate==1"><td ><s:property value="time"/></td></s:if>
				<s:if test="billSearchVO.rowFieldVO.isShowAgent==1"><td ><s:property value="agentName"/></td></s:if>
		      	<s:if test="billSearchVO.rowFieldVO.isShowBd==1"><td ><s:property value="bdName"/></td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowCustomer==1"><td ><s:property value="customerName"/></td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowProject==1"><td ><s:property value="projectName"/>[<font color="red"><s:property value="projectId"/></font>]</td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowProduct==1"><td ><s:property value="productName"/>[<font color="red"><s:property value="productId"/></font>]</td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowAdType==1"><td ><s:property value="adTypeName"/></td></s:if>
      			<td ><s:property value="clickCount"/></td>
      			<td ><s:property value="validClickCount"/></td>
      			<td ><s:property value="saleCount"/></td>
      			<td ><s:property value="percent"/></td>
				</tr>
       			<s:set name="totalSaleCount" value="#totalSaleCount + saleCount"/>
       			<s:set name="totalClickCount" value="#totalClickCount + clickCount"/>
       			<s:set name="totalValidClickCount" value="#totalValidClickCount + validClickCount"/>
			</s:iterator>
			<s:set name="row" value="billSearchVO.rowFieldLen-1"/>
			<tr >
				<td >Total</td>
				<s:if test="#row >0">
				<td  colspan="<s:property value="#row"/>"></td>
				</s:if>
				<td ><s:property value="#totalClickCount"/></td>	
				<td ><s:property value="#totalValidClickCount"/></td>	
				<td ><s:property value="#totalSaleCount"/></td>	
				<td ></td>	
			</tr>
		</table>