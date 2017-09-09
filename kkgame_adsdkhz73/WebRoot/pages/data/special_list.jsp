<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.kkgame.hz.entities.PortalUserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<s:if test="specialDataVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>
<%
	PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
	request.setAttribute("roleType",userVO.getRoleType());
%>
		<s:set name="totalDownCount" value="0"/>
		<s:set name="totalUserCount" value="0"/>
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="specialDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
				<thead>
					<tr >
					<s:if test="billSearchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="billSearchVO.rowFieldVO.isShowAgent==1"><th>代理商</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<th >下载</th>
		      		<th >激活</th>
		      		<th >激活率</th>
	      			<th >新增付费</th>
	      			<th >新增付费率</th>
	      			<th >新增充值</th>
	      			<th >新增ARPU</th>
	      			<th >活跃付费</th>
	      			<th >活跃总充值</th>
	      			<th >活跃ARPU</th>
	      			<th >次留</th>
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
      			<td ><s:property value="downCount"/></td>
      			<td ><s:property value="registerCount"/></td>
      			<td ><s:property value="registerPercent"/></td>
      			<td ><s:property value="dayChargeCount"/></td>
      			<td ><s:property value="dayChargePercent"/></td>
      			<td ><s:property value="dayFee"/></td>
      			<td ><s:property value="dayArpu"/></td>
      			<td ><s:property value="chargeCount"/></td>
      			<td ><s:property value="fee"/></td>
      			<td ><s:property value="arpu"/></td>
      			<td ><s:property value="registerFirstPercent"/></td>
				</tr>
       			<s:set name="totalDownCount" value="#totalDownCount + downCount"/>
       			<s:set name="totalUserCount" value="#totalUserCount + registerCount"/>
			</s:iterator>
			<s:set name="row" value="billSearchVO.rowFieldLen-1"/>
			<tr >
				<td >总计</td>
				<s:if test="#row >0">
				<td  colspan="<s:property value="#row"/>"></td>
				</s:if>
				<td ><s:property value="#totalDownCount"/></td>			
				<td ><s:property value="#totalUserCount"/></td>			
    			<td  colspan="9"></td>
			</tr>
		</table>
</s:else>