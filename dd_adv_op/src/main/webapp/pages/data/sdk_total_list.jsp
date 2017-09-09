<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
	UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="sdkDataVOList.size==0">
	<font color="red">暂无数据</font>
</s:if>
<s:else>
	<s:set name="desktopClickTimes" value="0"/>
	<s:set name="suspensionClickTimes" value="0"/>
	<s:set name="pushClickTimes" value="0"/>
	<s:set name="appClickTimes" value="0"/>
	<s:set name="otherappClickTimes" value="0"/>
	<s:set name="TotalClickTimes" value="0"/>

	<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="sdkDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
						<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
						<s:if test="searchVO.rowFieldVO.isShowAd==1"><th>广告</th></s:if>
						<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
						<th>桌面点击PV</th>
						<th>悬浮PV</th>
						<th>pushPV</th>
						<th>app拦截PV</th>
						<th>其它类型PV</th>
						<th>总PV</th>
					</tr>
					</thead>
				</s:if>
				<tr>
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAd==1"><td><s:property value="adName"/>[<s:property value="adId"/>]</td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="couName"/>[<s:property value="cou"/>]</td></s:if>
					<td><s:property value="desktopClickTimes"/></td>
					<td><s:property value="suspensionClickTimes"/></td>
					<td><s:property value="pushClickTimes"/></td>
					<td><s:property value="appClickTimes"/></td>
					<td><s:property value="otherappClickTimes"/></td>
					<td><s:property value="TotalClickTimes"/></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</s:else>