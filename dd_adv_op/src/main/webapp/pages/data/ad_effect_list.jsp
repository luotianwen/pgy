<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="adDataVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>		
		<s:set name="totalUserCount" value="0"/>
		<s:set name="totalFeeUserCount" value="0"/>
		<s:set name="totalFeeConfirmUserCount" value="0"/>
		
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="adDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<th>广告</th>
					<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>下载(普)</th>
		      		<th>下载(静)</th>
		      		<th>安装(普)</th>
   				    <th>安装(静)</th>
		      		<th>总安装</th>
		      		<th>总转化</th>
					<th>到达率</th>
		      		<th>转化率</th>
		      		<th>上架指数</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<td><s:property value="adName"/>[<s:property value="adId"/>]</td>
				<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
	      		<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>
				<td><s:property value="downloadCount"/></td>
				<td><s:property value="silenceDownloadCount"/></td>
      			<td><s:property value="installCount"/></td>
      			<td><s:property value="silenceInstallCount"/></td>
				<td><s:property value="installTotal"/></td>
				<td><s:property value="inputTotal"/></td>
				<td><s:property value="receivePercent"/></td>
      			<td><s:property value="changePercent"/></td>
      			<td><s:property value="upPercent"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:else>