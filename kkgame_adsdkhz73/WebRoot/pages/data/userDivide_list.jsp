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
<s:if test="userDivideVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>		
		<s:set name="totalCount" value="0"/>
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="userDivideVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="billSearchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="billSearchVO.rowFieldVO.isShowAgent==1"><th>代理商</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProject==1"><th>项目</th>
		      		<s:if test="billSearchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		</s:if>
		      		<th >运营商</th>
		      		<th >金额</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="billSearchVO.rowFieldVO.isShowDate==1"><td><s:property value="time"/></td></s:if>
				<s:if test="billSearchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
	      		<s:if test="billSearchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
      			<s:if test="billSearchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<font color="red"><s:property value="projectId"/></font>]</td></s:if>
       			<s:if test="billSearchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<font color="red"><s:property value="productId"/></font>]</td>
      			</s:if>
      			<td><s:iterator value="#Policy.operator">
      				<s:if test="operator==key">
      					<s:property value="value"/>
      				</s:if>
      			</s:iterator></td>
      			<td><s:property value="count"/></td>
				</tr>
       			<s:set name="totalCount" value="#totalCount + count"/>
			</s:iterator>
			<s:set name="row" value="billSearchVO.rowFieldLen-1"/>
			<tr  height="25">
				<td>总计</td>
				<s:if test="#row >=0">
				<td colspan="<s:property value="#row + 1"/>"></td>
				</s:if>				
				<td><s:property value="#totalCount"/></td>			
			</tr>
		</table>
		</s:else>