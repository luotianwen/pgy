<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="ddlDataVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>		
		<div class="box box-primary" style="overflow:auto">
		<s:set name="totalClickCount" value="0"/>
		<s:set name="totalValidClickCount" value="0"/>

		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="ddlDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowChannel==1"><th>渠道</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowType==1"><th>UA类型</th></s:if>		      		
		      		<th>点击数</th>
		      		<th>有效点击数</th>
		      		<th>有效比</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/>-<s:property value="hour"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowChannel==1"><td><s:property value="channelName"/>[<s:property value="channelId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowType==1">
	      			
       			<td><s:if test="uaType==200201">android</s:if>
       			<s:if test="uaType==200202">iphone</s:if>
	      		<s:if test="uaType==200203">ipad</s:if>
	      		<s:if test="uaType==200204">web</s:if>
	      		</td></s:if>
      			<td><s:property value="clickCount"/></td>
      			<td><s:property value="validClickCount"/></td>
      			<td><s:property value="percent"/></td>
      			<s:set name="totalClickCount" value="#totalClickCount + clickCount"/>
       			<s:set name="totalValidClickCount" value="#totalValidClickCount + validClickCount"/>
				</tr>
			</s:iterator>
			<s:set name="row" value="searchVO.rowFieldLen-1"/>
			<tr >
				<td >总计</td>
				<s:if test="#row >0">
				<td  colspan="<s:property value="#row"/>"></td>
				</s:if>
				<td ><s:property value="#totalClickCount"/></td>	
				<td ><s:property value="#totalValidClickCount"/></td>	
				<td ><s:text name="global.format.number"><s:param value="#totalValidClickCount*100.0/#totalClickCount"/></s:text>
				</td>	
			</tr>
		</table>
		</div>
		</s:else>