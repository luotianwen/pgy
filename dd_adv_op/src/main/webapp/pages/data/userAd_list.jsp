<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="userAdVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="userAdVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>总用户数</th>
		      		<th>无广告用户</th>
		      		<th>比例</th>
		      		<th>安装完用户</th>
		      		<th>比例</th>
		      		<th>其他用户</th>
		      		<th>比例</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:if test="type==1">push</s:if>
       			<s:if test="type==2">插屏</s:if>
       			<s:if test="type==3">线下</s:if>
         			</td></s:if>  
      			<td><s:property value="totalUserCount"/></td>
      			<td><s:property value="noUserCount"/></td>
      			<td><s:property value="noPercent"/></td>
				<td><s:property value="allUserCount"/></td>
				<td><s:property value="allPercent"/></td>
				<td><s:property value="yesUserCount"/></td>
				<td><s:property value="yesPercent"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
</s:else>