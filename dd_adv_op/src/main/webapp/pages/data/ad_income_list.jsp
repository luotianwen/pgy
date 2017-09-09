<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="adIncomeVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>		
		<s:set name="totalUserCount" value="0"/>
		<s:set name="totalFeeUserCount" value="0"/>
		<s:set name="totalFeeConfirmUserCount" value="0"/>
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="adIncomeVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAd==1"><th>广告</th></s:if>
					<s:if test="searchVO.adId>0||searchVO.rowFieldVO.isShowAd==1"><th>单价</th></s:if>
					<th>总安装数</th>
					<th>总收入</th>
					<th>SDK展示数</th>
					<th>SDK安装数</th>
					<th>线下展示数</th>
					<th>线下安装数</th>
		      		<th>转化数</th>
		      		<th>转化率</th>
		      		<th>预计转化率</th>
		      		<th>预计cpm</th>
		      		<th>预计scpm</th>
		      		<th>cpm</th>
		      		<th>scpm</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAd==1"><td><s:property value="adName"/>[<s:property value="adId"/>]</td></s:if>
				<s:if test="searchVO.adId>0||searchVO.rowFieldVO.isShowAd==1"><td><s:property value="price"/></td></s:if>
				<td><s:property value="installedCount+silenceInstalledCount"/></td>
      			<td><s:property value="income"/></td>
				<td><s:property value="showTotal"/></td>
				<td><s:property value="installedCount"/></td>
				<td><s:property value="silenceShowTotal"/></td>
				<td><s:property value="silenceInstalledCount"/></td>
      			<td><s:property value="realTotal+inputTotal"/></td>
     			<td><s:property value="percent"/></td>
     			<td><s:property value="cpConversionRate"/></td>
      			<td><s:property value="expectCpm"/></td>
      			<td><s:property value="expectScpm"/></td>
      			<td><s:property value="cpm"/></td>
      			<td><s:property value="scpm"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:else>