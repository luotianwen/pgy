<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="projectDataVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>	
		<div class="box box-info" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>销量</th>
		      		<th>项目销量</th>
		      		<th>活跃</th>
		      		<th>总活跃</th>
		      		<th>安装数</th>
					<th>活跃安装比</th>
					<th>广告收益</th>
					<th>单个活跃收益</th>
					<th>第三方收益</th>
					<th>第三方单个活跃收益</th>

					<th>广告单个销量(排重前)收益</th>
					<th>广告单个销量(排重后)收益</th>
					<th>第三方单个销量(排重前)收益</th>
					<th>第三方单个销量(排重后)收益</th>

					<th>总收益</th>
					<th>单个销量收益</th>
		      		<th>下沉数</th>
		      		<th>引导数</th>
   				    <th>aipu</th>
		      		<th>下沉率</th>
		      		<th>引导率</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>  
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="projectUserCount"/></td>
				<td><s:property value="activeCount"/></td>
				<td><s:property value="totalActiveCount"/></td>
				<td><s:property value="pushInstallTimes+installTimes+silenceInstallTimes"/></td>
				<td><s:property value="activeInstallPercent"/>%</td>
				<td><s:property value="adIncome"/> </td>
				<td><s:property value="eachActiveIncome"/></td>
				<td><s:property value="thirdIncome"/> </td>
				<td><s:property value="eachThridActiveIncome"/></td>

				<td><s:property value="beforeadIncome"/> </td>
				<td><s:property value="afteradIncome"/> </td>
				<td><s:property value="beforethirdIncome"/> </td>
				<td><s:property value="afterthirdIncome"/> </td>

				<td><s:property value="totalIncome"/> </td>
				<td><s:property value="eachUserIncome"/></td>
				<td><s:property value="sinkInstallTimes"/></td>
      			<td><s:property value="leadInstallTimes"/></td>
      			<td><s:text name="global.format.number"><s:param value="pushAipu+aipu"/></s:text></td>
				<td><s:text name="global.format.number"><s:param value="sinkAipu*100.0"/></s:text>%</td>
				<td><s:text name="global.format.number"><s:param value="leadAipu*100.0"/></s:text>%</td>
				</tr>
			</s:iterator>
		</table>
		</div>
</s:else>