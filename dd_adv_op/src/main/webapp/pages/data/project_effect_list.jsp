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
		      		<th>SDK销量</th>
		      		<th>项目销量</th>
		      		<th>总活跃</th>
  				    <th>收入</th>
		      		<th>渠道成本</th>
		      		<th>IO</th>
		      		<th>留存</th>
		      		<th>活跃安装率</th>
		      		<th>渠道转化数</th>
		      		<th>转化率</th>
		      		<th>项目转化率</th>
		      		<th>排重率</th>
		      		<th>质量预警</th>
		      		<th>排重预警</th>
		      		<th>收入预警</th>
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
      			<td><s:property value="newProjectUsers"/></td>
      			<td><s:property value="totalActUsers"/></td>
				<td><s:property value="totalIncome"/></td>
 				<td><s:property value="outcome"/></td>
	      		<td><s:property value="io"/></td>
	      		<td><s:property value="firstPercent"/></td>
 				<td><s:property value="activeInstallPercent"/>%</td>
      			<td><s:property value="highUsers+midUsers+lowUsers"/></td>
 				<td><s:property value="conversionPercent"/>%</td>
 				<td><s:property value="projectConversionPercent"/>%</td>
				<td><s:property value="distinctPercent"/></td>
				<td 
					style="background-color: 
						<s:if test="qualityWarn>=1.5">
						#006400
						</s:if>
						<s:elseif test="qualityWarn>=1.1&&qualityWarn<1.5">
							
						</s:elseif>
						<s:elseif test="qualityWarn>=0.9&&qualityWarn<1.1">
							
						</s:elseif>
						<s:elseif test="qualityWarn>=0.5&&qualityWarn<0.9">
							#FF0000
						</s:elseif>
						<s:elseif test="qualityWarn<0.5">
							#8B0000
						</s:elseif>
					"
				>
					<s:if test="qualityWarn>=1.5">
						<font color="#DEEAF6">优</font> 
					</s:if>
					<s:elseif test="qualityWarn>=1.1&&qualityWarn<1.5">
						<font color="#00B050">良</font>
					</s:elseif>
					<s:elseif test="qualityWarn>=0.9&&qualityWarn<1.1">
						<font color="#000">一般</font>
					</s:elseif>
					<s:elseif test="qualityWarn>=0.5&&qualityWarn<0.9">
						<font color="#FFE599">差</font>
					</s:elseif>
					<s:elseif test="qualityWarn<0.5">
						<font color="#FFD966">极差</font>
					</s:elseif>
					<s:property value="qualityWarn"/>
				</td>
				<td
					style="background-color: 
						<s:if test="distinctPercent>=0.5">
							#8B0000
						</s:if>
						<s:elseif test="distinctPercent>=0.3&&distinctPercent<0.5">
							#FF0000
						</s:elseif>
						<s:elseif test="distinctPercent>=0.15&&distinctPercent<0.3">
							
						</s:elseif>
						<s:elseif test="distinctPercent>=0.05&&distinctPercent<0.15">
							
						</s:elseif>
						<s:elseif test="distinctPercent<0.05">
							#006400
						</s:elseif>
					"
				>
					<s:if test="distinctPercent>=0.5">
						<font color="#FFD966">严重</font>
					</s:if>
					<s:elseif test="distinctPercent>=0.3&&distinctPercent<0.5">
						<font color="#FFE599">高</font>
					</s:elseif>
					<s:elseif test="distinctPercent>=0.15&&distinctPercent<0.3">
						<font color="#000">中</font>
					</s:elseif>
					<s:elseif test="distinctPercent>=0.05&&distinctPercent<0.15">
						<font color="#00B050">良</font>
					</s:elseif>
					<s:elseif test="distinctPercent<0.05">
						<font color="#DEEAF6">优</font>
					</s:elseif>
				</td>
				<td
					style="background-color: 
						<s:if test="io>=1.3">
						#006400
					</s:if>
					<s:elseif test="io>=1.1&&io<1.3">
					</s:elseif>
					<s:elseif test="io>=0.9&&io<1.1">
					</s:elseif>
					<s:elseif test="io>=0.7&&io<0.9">
						#FF0000
					</s:elseif>
					<s:elseif test="io<0.7">
						#8B0000
					</s:elseif>"
				>
					<s:if test="io>=1.3">
						<font color="#DEEAF6">优</font>
					</s:if>
					<s:elseif test="io>=1.1&&io<1.3">
						<font color="#00B050">良</font>
					</s:elseif>
					<s:elseif test="io>=0.9&&io<1.1">
						<font color="#000">一般</font>
					</s:elseif>
					<s:elseif test="io>=0.7&&io<0.9">
						<font color="#FFE599">差</font>
					</s:elseif>
					<s:elseif test="io<0.7">
						<font color="#FFD966">极差</font>
					</s:elseif>
				</td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:else>