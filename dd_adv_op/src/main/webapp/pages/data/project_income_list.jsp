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
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>销量</th>
		      		<th>活跃</th>
		      		<th>广告展示次数</th>
		      		<th>安装次数</th>
		      		<th>广告收益($)</th>
		      		<th>第三方收益($)</th>
		      		<th>链接收益($)</th>
		      		<th>销量:高</th>
		      		<th>销量:中</th>
		      		<th>销量:低</th>
		      		<th>结算:高</th>
		      		<th>结算:中</th>
		      		<th>结算:低</th>
		      		<th title="结算销量/有效销量">渠道转化率</th>
		      		<th>成本($)</th>
		      		<th>利润率</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="country"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>  
      			<td><s:property value="newUsers"/></td>
				<td><s:property value="actUsers"/></td>
				<td><s:property value="showTotal"/></td>
				<td><s:property value="installTotal"/></td>
      			<td><s:property value="income"/></td>
				<td><s:property value="thirdIncome"/></td>
				<td><s:property value="linkIncome"/></td>
				<td><s:property value="installHigh"/></td>
      			<td><s:property value="installMid"/></td>
      			<td><s:property value="installLow"/></td>
				<td><s:property value="highUsers"/></td>
      			<td><s:property value="midUsers"/></td>
      			<td><s:property value="lowUsers"/></td>
      			<td><s:property value="percent"/></td>
      			<td><s:property value="outcome"/></td>
   				<td><s:if test="rateOut<0"><font color="red"><s:property value="rateOut"/></font></s:if><s:else><s:property value="rateOut"/></s:else></td>
				</tr>
			</s:iterator>
			
		</table>
		</div>
		</s:else>