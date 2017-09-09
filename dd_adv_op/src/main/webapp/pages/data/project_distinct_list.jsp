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
		      		<th>项目</th>
		      		<th>排重项目</th>
		      		<th>销量</th>
		      		<th>比例</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<td><s:property value="projectName"/>[<s:property value="projectId"/>]</td>
				<td><s:property value="distinctProjectName"/>[<s:property value="distinctProjectId"/>]</td>
       			
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="showPercent"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
</s:else>