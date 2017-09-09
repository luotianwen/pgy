<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		      		<th>操作</th>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>转化率</th>
		      		<th>SDK销量</th>
		      		<th>SDK项目销量</th>
   				    <th>高-中-低</th>
		      		<th>安装次数</th>
		      		<th>广告收益</th>
		      		<th>预计收益</th>
		      		<th>第三方收益</th>
		      		<th>链接收益</th>
		      		<th>结算：高-中-低</th>
		      		<th>结算额</th>
		      		<th>利润</th>
		      		<th>利润率</th>
		      		<th>预计利润率</th>
		      		<th>是否同步</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
		      	<td>
   					<a class="btn btn-info" href="javascript: generateProjectData('<%=path%>/data','<s:property value="statDate"/>','<s:property value="projectId"/>')" title="生成数据" >
						生成数据
						</a>  
   					<s:if test="status==0"><a class="btn btn-warning" href="javascript: postData('<%=path%>/data','<s:property value="statDate"/>','<s:property value="projectId"/>')" title="同步数据" >
						同步数据
						</a>   	
						</s:if>			
						<a class="btn btn-primary" href="javascript: modifyConfig('<%=path%>/data','<s:property value="statDate"/>','<s:property value="projectId"/>')" title="修改">
						修改配置
						</a>
   				</td>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="country"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>  
       			<td>
   					<s:if test="newUsers==0">
   						-
   					</s:if>
   					<s:else>
   						 <s:text name="global.format.number"><s:param value="(highUsers+midUsers+lowUsers)*100.0/newUsers"/></s:text>%
   					</s:else>
   				</td>
   				
      			<td><s:property value="newUsers"/></td>
      			<td><s:property value="newProjectUsers"/></td>
				<td><s:property value="installHigh"/>-<s:property value="installMid"/>-<s:property value="installLow"/></td>
				<td><s:property value="installTotal"/></td>
      			<td><s:property value="income"/></td>
      			<td><s:property value="expectIncome"/></td>
				<td><s:property value="thirdIncome"/></td>
				<td><s:property value="linkIncome"/></td>
				<td><s:property value="highUsers"/>-<s:property value="midUsers"/>-<s:property value="lowUsers"/></td>
      			<td><s:property value="outcome"/></td>
      			<td><s:property value="profit"/></td>
   				<td><s:if test="rateOut<0"><font color="red"><s:property value="rateOut"/></font></s:if><s:else><s:property value="rateOut"/></s:else></td>
   				<td><s:if test="expectRateOut<0"><font color="red"><s:property value="expectRateOut"/></font></s:if><s:else><s:property value="expectRateOut"/></s:else></td>
   				
   				<td><s:if test="status==0">否</s:if><s:else>是</s:else></td>
   				
				</tr>
			</s:iterator>
			
		</table>
		</div>
		</s:else>