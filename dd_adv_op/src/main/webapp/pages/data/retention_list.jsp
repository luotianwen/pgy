<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="retentionVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>		
		<div class="box box-primary" style="overflow:auto">

		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="retentionVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<th>时间</th>
		      		<th>项目</th>
		      		<th>国家</th>
		      		<th>类型</th>
		      		<th>销量</th>
		      		<th>活跃</th>
		      		<th>总活跃</th>
		      		<th>第一天</th>
		      		<th>第二天</th>
		      		<th>第三天</th>
		      		<th>第四天</th>
   				    <th>第五天</th>
		      		<th>第六天</th>
		      		<th>第七天</th>
					<th>第十五天</th>
		      		<th>第三十天</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<td><s:property value="statDate"/></td>
				<td><s:property value="projectName"/>[<s:property value="projectId"/>]</td>
	      		<td><s:property value="countryName"/></td>
       			<td>
       			<s:if test="type==0">所有类型</s:if>
	      		<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator>
       			</td>
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="activeCount"/></td>
      			<td><s:property value="totalActiveCount"/></td>
				<td><s:if test="firstPercent==0">-</s:if><s:else ><s:property value="firstPercent"/>%</s:else></td>
				<td><s:if test="secondPercent==0">-</s:if><s:else ><s:property value="secondPercent"/>%</s:else></td>
				<td><s:if test="thirdPercent==0">-</s:if><s:else ><s:property value="thirdPercent"/>%</s:else></td>
      			<td><s:if test="fourthPercent==0">-</s:if><s:else ><s:property value="fourthPercent"/>%</s:else></td>
      			<td><s:if test="fifthPercent==0">-</s:if><s:else ><s:property value="fifthPercent"/>%</s:else></td>
				<td><s:if test="sixthPercent==0">-</s:if><s:else ><s:property value="sixthPercent"/>%</s:else></td>
				<td><s:if test="seventhPercent==0">-</s:if><s:else ><s:property value="seventhPercent"/>%</s:else></td>
				<td><s:if test="fiftyPercent==0">-</s:if><s:else ><s:property value="fiftyPercent"/>%</s:else></td>
      			<td><s:if test="thirtyPercent==0">-</s:if><s:else ><s:property value="thirtyPercent"/>%</s:else></td>
      			
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:else>