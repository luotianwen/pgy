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
		      		<th>项目</th>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
	      			<!-- <th title="SDK销量">sdkUser</th>
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
		      		<th title="每销量利润=利润/SDK销量">ppu</th> -->
		      		<th title="SDK销量">SDK销量</th>
		      		<th title="引导销量">引导销量</th>
		      		<th title="下沉销量">下沉销量</th>
		      		<th title="线下销量">线下销量</th>
		      		<th title="SDK安装数">SDK安装数</th>
		      		<th title="引导安装数">引导安装数</th>
		      		<th title="下沉安装数">下沉安装数</th>
		      		<th title="线下安装数">线下安装数</th>
  				    <th title="广告总收益">广告总收益</th>
		      		<th title="第三方收益">第三方收益</th>
		      		<th title="链接收益">链接收益</th>
		      		<th title="渠道转化数">渠道转化数</th>
		      		<th title="渠道转化销量/SDK销量">渠道转化销量/SDK销量</th>
		      		<th title="渠道成本">渠道成本</th>
		      		<th title="利润">利润t</th>
		      		<th title="每销量利润=利润/SDK销量">利润/SDK销量</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<td><s:property value="projectName"/>[<s:property value="projectId"/>]</td>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="country"/></td></s:if>
       			
      			<td><s:property value="newUsers"/></td>
      			<td><s:property value="leadNewUsers"/></td>
      			<td><s:property value="sinkNewUsers"/></td>
      			<td><s:property value="silenceNewUsers"/></td>
				<td><s:property value="installTotal"/></td>
				<td><s:property value="leadInstallTotal"/></td>
				<td><s:property value="sinkInstallTotal"/></td>
				<td><s:property value="silenceInstallTotal"/></td>
				<td><s:property value="income"/></td>
				<td><s:property value="thirdIncome"/></td>
				<td><s:property value="linkIncome"/></td>
				<td><s:property value="highUsers+midUsers+lowUsers"/></td>
				<td><s:property value="conversionPercent"/></td>
				<td><s:property value="outcome"/></td>
				<td><s:property value="profit"/></td>
				<td><s:property value="profitRegister"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:else>