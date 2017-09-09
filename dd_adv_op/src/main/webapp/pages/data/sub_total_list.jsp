<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
	UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="subDataVOList.size==0">
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
		<thead>
		<tr >
			<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>日期</th></s:if>
			<s:if test="searchVO.rowFieldVO.isShowAd==1"><th>广告</th> </s:if>
			<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th> </s:if>
			<s:if test="searchVO.rowFieldVO.isShowType==1"><th>广告类型</th> </s:if>
			<s:if test="searchVO.rowFieldVO.isShowPlatform==1"><th>平台</th> </s:if>
			<s:if test="searchVO.rowFieldVO.isShowOperator==1"><th>运营商</th> </s:if>
			<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>渠道</th> </s:if>
			<th>激活量</th>
			<th>点击量</th>
			<s:if test="searchVO.rowFieldVO.isShowAd==1"><th>总ECPM值</th></s:if>
			<th>收入</th>
		</tr>
		</thead>

			<s:iterator value="subDataVOList" status="stat">
				<tr>
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="date"/></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAd==1"><td><s:property value="adName"/>[<s:property value="adId"/>]</td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="cou"/></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowType==1"><td> <s:iterator value="#Option.subType">
						<s:if test="adType==key">
							<s:property value="value"/>
						</s:if>
					</s:iterator></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowPlatform==1"><td><s:property value="platform"/></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowOperator==1"><td><s:property value="operator"/>[<s:property value="operatorId"/>]</td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowProject==1"><td>
					<s:iterator value="#Option.pcustomerList">
						<s:if test="project==id">
							<s:property value="name"/>
						</s:if>
					</s:iterator>
						[<s:property value="project"/>]</td></s:if>
					<td><s:property value="activeTimes"/></td>
					<td><s:property value="totalClickTimes"/></td>
					<s:if test="searchVO.rowFieldVO.isShowAd==1"><td><s:property value="ecpm"/></td></s:if>
					<td><s:property value="income"/></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</s:else>