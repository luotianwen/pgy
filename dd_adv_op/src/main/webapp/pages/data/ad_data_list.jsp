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
					<s:if test="searchVO.rowFieldVO.isShowAd==1"><th>广告</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>推送人数</th>
		      		<th>次数</th>
		      		<th>成功数</th>
   				    <th>接收率</th>
		      		<th>展示人数</th>
		      		<th>展示数</th>
					<th>展示率</th>
		      		<th>点击数</th>
		      		<th>点击率</th>
		      		<th>下载数</th>
					<th>下载率</th>
		      		<th>安装数</th>
					<th>安装率</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAd==1"><td><s:property value="adName"/>[<s:property value="adId"/>]</td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
	      		<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>
				<td><s:property value="pushSendCount"/></td>
				<td><s:property value="pushSendTimes"/></td>
      			<td><s:property value="pushReceiveTimes"/></td>
      			<td><s:property value="receivePushPercent"/>%</td>
				<td><s:property value="pushShowCount"/></td>
				<td><s:property value="pushShowTimes"/></td>
				<td><s:property value="showPushPercent"/>%</td>
      			<td><s:property value="pushClickTimes"/></td>
      			<td><s:property value="clickPushPercent"/>%</td>
      			<td><s:property value="pushDownloadTimes"/></td>
   				<td><s:property value="downloadPushPercent"/>%</td>
      			<td><s:property value="pushInstallTimes"/></td>
   			   	<td><s:property value="installPushPercent"/>%</td>
				</tr>
			</s:iterator>
		</table>
		</div>
		
		<div class="box box-info" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="adDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAd==1"><th>广告</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>插屏人数</th>
		      		<th>次数</th>
		      		<th>成功数</th>
   				    <th>接收率</th>
		      		<th>展示人数</th>
		      		<th>展示数</th>
					<th>展示率</th>
		      		<th>点击数</th>
		      		<th>点击率</th>
		      		<th>下载数</th>
					<th>下载率</th>
		      		<th>安装数</th>
					<th>安装率</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAd==1"><td><s:property value="adName"/>[<s:property value="adId"/>]</td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
	      		<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>
   				<td><s:property value="sendCount"/></td>
   				<td><s:property value="sendTimes"/></td>
      			<td><s:property value="receiveTimes"/></td>
       			<td><s:property value="receivePercent"/>%</td>
   				<td><s:property value="showCount"/></td>
   				<td><s:property value="showTimes"/></td>
 				<td><s:property value="showPercent"/>%</td>
   				<td><s:property value="clickTimes"/></td>
   				<td><s:property value="clickPercent"/>%</td>
      			<td><s:property value="downloadTimes"/></td>
   				<td><s:property value="downloadPercent"/>%</td>
      			<td><s:property value="installTimes"/></td>
   			   	<td><s:property value="installPercent"/>%</td>
				</tr>
			</s:iterator>
		</table>
		</div>
		
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="adDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAd==1"><th>广告</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>线下人数</th>
		      		<th>次数</th>
		      		<th>成功数</th>
   				    <th>接收率</th>
		      		<th>展示人数</th>
		      		<th>展示数</th>
					<th>展示率</th>
		      		<th>点击数</th>
		      		<th>点击率</th>
		      		<th>下载数</th>
					<th>下载率</th>
		      		<th>安装数</th>
					<th>安装率</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAd==1"><td><s:property value="adName"/>[<s:property value="adId"/>]</td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
	      		<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>
				<td><s:property value="silenceSendCount"/></td>
				<td><s:property value="silenceSendTimes"/></td>
      			<td><s:property value="silenceReceiveTimes"/></td>
      			<td><s:property value="receiveSilencePercent"/>%</td>
				<td><s:property value="silenceShowCount"/></td>
				<td><s:property value="silenceShowTimes"/></td>
				<td><s:property value="showSilencePercent"/>%</td>
      			<td><s:property value="silenceClickTimes"/></td>
      			<td><s:property value="clickSilencePercent"/>%</td>
      			<td><s:property value="silenceDownloadTimes"/></td>
   				<td><s:property value="downloadSilencePercent"/>%</td>
      			<td><s:property value="silenceInstallTimes"/></td>
   			   	<td><s:property value="installSilencePercent"/>%</td>
				</tr>
			</s:iterator>
		</table>
		</div>
		
		</s:else>