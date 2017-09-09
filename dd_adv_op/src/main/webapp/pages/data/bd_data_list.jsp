<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="projectIncomeVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>		
		<div class="box box-primary" style="overflow:auto">

		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectIncomeVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<th>项目</th>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<th>SDK销量</th>
		      		<th>有效销量</th>
		      		<th>项目销量</th>
		      		<th>留存</th>
		      		<th>渠道转化数</th>
		      		<th>转化率</th>
		      		<th>排重率</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<td><s:property value="projectName"/>[<s:property value="projectId"/>]</td>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="country"/></td></s:if>
      			<td><s:property value="newUsers"/></td>
      			<td><s:property value="installHigh+installMid+installLow"/></td>
      			<td><s:property value="newProjectUsers"/></td>
	      		<td><s:property value="firstPercent"/></td>
      			<td><s:property value="highUsers+midUsers+lowUsers"/></td>
 				<td><s:property value="effectConversionPercent"/>%</td>
				<td><s:property value="distinctPercent"/></td>
				<tr>
			</s:iterator>
		</table>
		</div>
		</s:else>