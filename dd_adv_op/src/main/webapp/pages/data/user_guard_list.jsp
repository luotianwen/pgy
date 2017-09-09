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
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<th>销量</th>
		      		<th>项目销量</th>
		      		<th>活跃</th>
		      		<th>被守护插件ID</th>
		      		<th>总数</th>
		      		<th>成功数</th>
   				    <th>成功率</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="guardList==null||guardList.size==0">
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      			<td><s:property value="userCount"/></td>
	      			<td><s:property value="projectUserCount"/></td>
					<td><s:property value="activeCount"/>
					</td>
					<td colspan="4"></td>
				</s:if>
				<s:else>
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><td rowspan="<s:property value="guardList.size"/>"><s:property value="statDate"/></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td rowspan="<s:property value="guardList.size"/>"><s:property value="agentName"/></td></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><td rowspan="<s:property value="guardList.size"/>"><s:property value="bdName"/></td></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td rowspan="<s:property value="guardList.size"/>"><s:property value="customerName"/></td></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td rowspan="<s:property value="guardList.size"/>"><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
		      		
	      			<td rowspan="<s:property value="guardList.size"/>"><s:property value="userCount"/></td>
	      			<td rowspan="<s:property value="guardList.size"/>"><s:property value="projectUserCount"/></td>
					<td rowspan="<s:property value="guardList.size"/>"><s:property value="activeCount"/>
					</td>
					<s:iterator value="guardList" status="guardIndex">
						<s:if test="#guardIndex.index>=1"><tr></s:if>
					<td>
						<s:property value="guardPluginId"/>
						<!-- 
						<s:iterator value="#Option.guardPluginIdList">
							<s:if test="apkId==guardPluginId">
								<s:property value="note"/>
							</s:if>
						</s:iterator> -->
					</td>
					<td><s:property value="allUserCount"/></td>
	      			<td><s:property value="succUserCount"/></td>
	      			<td><s:property value="guardPercent"/>%</td>
	      			<s:if test="#guardIndex.index>=1"></tr></s:if>
					</s:iterator>
				</s:else>
				</tr>
			</s:iterator>
		</table>
		</div>
</s:else>