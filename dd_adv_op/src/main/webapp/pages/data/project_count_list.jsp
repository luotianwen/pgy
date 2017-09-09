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
		      		<th>项目</th>
		      		<th title="SDK销量">sdkUser</th>
		      		<th title="引导销量">gUser</th>
		      		<th title="下沉销量">dUser</th>
		      		<th title="线下销量">sUser</th>
		      		<th title="SDK安装数">sdkAds</th>
		      		<th title="引导安装数">gAds</th>
		      		<th title="下沉安装数">dAds</th>
		      		<th title="线下安装数">sAds</th>
		      		<th title="广告总收益">adInc</th>
		      		<th title="第三方收益">3rdInc</th>
		      		<th title="链接收益">lInc</th>
		      		<th title="渠道转化数">con</th>
		      		<th title="渠道转化销量/SDK销量">cv</th>
		      		<th title="渠道成本">cost</th>
		      		<th title="利润">profit</th>
		      		<th title="每销量利润=利润/SDK销量">ppu</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<td><s:property value="projectName"/>[<s:property value="projectId"/>]</td>
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="leadUserCount"/></td>
      			<td><s:property value="apkUserCount"/></td>
      			<td><s:property value="silenceUserCount"/></td>
				<td><s:property value="installTimes+pushInstallTimes"/></td>
				<td><s:property value="leadTypeInstallTimes+leadPushInstallTimes"/></td>
				<td><s:property value="apkInstallTimes+apkPushInstallTimes"/></td>
				<td><s:property value="silenceSilenceInstallTimes"/></td>
     			<td><s:property value="income"/></td>
     			<td><s:property value="thirdIncome"/></td>
     			<td><s:property value="linkIncome"/></td>
      			<td><s:property value="totalUsers"/></td>
      			<td><s:property value="conversionPercent"/></td>
      			<td><s:property value="outcome"/></td>
     			<td style="background-color: <s:if test="profit<0">#ffff00</s:if>">
     			<s:property value="profit"/></td>
     			<td><s:property value="profitRegister"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
</s:else>