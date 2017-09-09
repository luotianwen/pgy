<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="adLinkDataVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>		
		<s:set name="totalUserCount" value="0"/>
		<s:set name="totalFeeUserCount" value="0"/>
		<s:set name="totalFeeConfirmUserCount" value="0"/>
		
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="adLinkDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAd==1"><th>广告</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowInternet==1"><th>网络类型</th></s:if>
		      		<th>定时PV</th>
   				    <th>桌面PV</th>
		      		<th>pushPV</th>
		      		<th>浏览器PV</th>
		      		<th>总PV</th>
					<th>UV</th>
		      		<th>转化数</th>
		      		<th>收益</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAd==1"><td><s:property value="adName"/>[<s:property value="adId"/>]</td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowInternet==1"><td>
					<s:iterator value="#Option.netType">
						<s:if test="internet==key">
							<s:property value="value"/>
						</s:if>
					</s:iterator>
				</td></s:if>
				<td><s:property value="noLimitClickTimes"/></td>
				<td><s:property value="iconClickTimes"/></td>
      			<td><s:property value="statusBarClickTimes"/></td>
      			<td><s:property value="browserClickTimes"/></td>
				<td><s:property value="totalClickTimes"/></td>
				<td><s:property value="uv"/></td>
				<td><s:property value="thirdClickTimes"/></td>
				<td><s:property value="linkIncome"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:else>