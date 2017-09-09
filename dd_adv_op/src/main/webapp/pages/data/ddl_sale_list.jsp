<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="ddlDataVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>		
		<div class="box box-primary" style="overflow:auto">
		<s:set name="totalClickCount" value="0"/>
		<s:set name="totalSaleCount" value="0"/>
		<s:set name="totalShowSaleCount" value="0"/>
		<s:set name="totalValidClickCount" value="0"/>
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="ddlDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>日期</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowHour==1"><th>小时</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowChannel==1"><th>渠道</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>		      		
					<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>		      		
		      		<th>点击数</th>
		      		<th>有效点击数</th>
		      		<th>无效点击数</th>
		      		<th>点击有效率</th>
		      		<th>销量</th>
		      		<th>点击安装率</th>
		      		<th>转化销量</th>
		      		<th>渠道转化率</th>
		      		<th>CR</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowHour==1"><td><s:property value="hour"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowChannel==1"><td><s:property value="channelName"/>[<s:property value="channelId"/>]</td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><a href="javascript: modifyDdl('<%=path%>/customer','<s:property value="projectId"/>')"><s:property value="projectName"/>[<s:property value="projectId"/>]</a></td></s:if>
 	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
      			<td><s:property value="clickCount"/></td>
      			<td><s:property value="validClickCount"/></td>
      			<td><s:property value="clickCount-validClickCount"/></td>
      			<td><s:property value="percent"/></td>
      			<td><s:property value="saleCount"/></td>
      			<td><s:property value="salePercent"/></td>
      			<td><s:property value="showSaleCount"/></td>
      			<td><s:property value="changePercent"/></td>
      			<td><s:property value="cv"/>%</td>
      			<s:set name="totalClickCount" value="#totalClickCount + clickCount"/>
      			<s:set name="totalSaleCount" value="#totalSaleCount + saleCount"/>
      			<s:set name="totalShowSaleCount" value="#totalShowSaleCount + showSaleCount"/>
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
				<td ><s:property value="#totalClickCount-#totalValidClickCount"/></td>	
				<td >
				<s:text name="global.format.number"><s:param value="#totalValidClickCount*100.0/#totalClickCount"/></s:text>
				</td>	
				<td ><s:property value="#totalSaleCount"/></td>	
				<td >
				<s:text name="global.format.number"><s:param value="#totalSaleCount*100.0/#totalClickCount"/></s:text>
				</td>	
				<td ><s:property value="#totalShowSaleCount"/></td>	
				<td >
				<s:text name="global.format.number"><s:param value="#totalShowSaleCount*100.0/#totalSaleCount"/></s:text>
				</td>
				<td >
				<s:text name="global.format.number"><s:param value="#totalShowSaleCount*100.0/#totalClickCount"/></s:text>
				</td>
			</tr>
		</table>
		</div>
		</s:else>