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
		<s:set name="totalDownCount" value="0"/>
		<s:set name="totalUserCount" value="0"/>
		<s:set name="totalRegisterHighCount" value="0"/>
		<s:set name="totalRegisterMidCount" value="0"/>
		<s:set name="totalRegisterLowCount" value="0"/>
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="userRegisterVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
				<thead>
					<tr >
					<s:if test="billSearchVO.rowFieldVO.isShowDate==1"><th>Date</th></s:if>
					<s:if test="billSearchVO.rowFieldVO.isShowAgent==1"><th>代理商</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProject==1"><th>Project</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProduct==1"><th>Product</th></s:if>
		      		<th >Conversions-high</th>
		      		<th >Conversions-Medium</th>
		      		<th >Conversions-Low</th>
		      		<th >Total</th>
		      		<s:if test="billSearchVO.queryType==3">
		      			<th >1日</th>
		      			<th >2日</th>
		      			<th >3日</th>
		      			<th >4日</th>
		      			<th >5日</th>
		      			<th >6日</th>
		      			<th >7日</th>
		      		</s:if>
		      		
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
      			<td ><s:property value="registerHighCount"/></td>
      			<td ><s:property value="registerMidCount"/></td>
      			<td ><s:property value="registerLowCount"/></td>
      			<td ><s:property value="registerHighCount+registerMidCount+registerLowCount"/></td>
      			<s:if test="billSearchVO.queryType==3">
      			<td ><s:property value="firstPercent"/></td>
      			<td ><s:property value="secondPercent"/></td>
      			<td ><s:property value="thirdPercent"/></td>
      			<td ><s:property value="fourthPercent"/></td>
      			<td ><s:property value="fifthPercent"/></td>
      			<td ><s:property value="sixthPercent"/></td>
      			<td ><s:property value="sevenPercent"/></td>
	      		</s:if>
				</tr>
       			<s:set name="totalUserCount" value="#totalUserCount + registerCount"/>
       			<s:set name="totalRegisterHighCount" value="#totalRegisterHighCount + registerHighCount"/>
       			<s:set name="totalRegisterMidCount" value="#totalRegisterMidCount + registerMidCount"/>
       			<s:set name="totalRegisterLowCount" value="#totalRegisterLowCount + registerLowCount"/>
			</s:iterator>
			<s:set name="row" value="billSearchVO.rowFieldLen-1"/>
			<tr >
				<td >Total</td>
				<s:if test="#row >0">
				<td  colspan="<s:property value="#row"/>"></td>
				</s:if>
				<td ><s:property value="#totalRegisterHighCount"/></td>			
				<td ><s:property value="#totalRegisterMidCount"/></td>			
				<td ><s:property value="#totalRegisterLowCount"/></td>			
				<td ><s:property value="#totalRegisterHighCount+#totalRegisterMidCount+#totalRegisterLowCount"/></td>			
				<s:if test="billSearchVO.queryType==3">
      				<td  colspan="7"></td>
      			</s:if>
			</tr>
		</table>