<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>代理商详情</h2>
</div>
<div class="modal-body">
<table class="table table-bordered">
<tr>
<td class="table_td_title">代理商名称</td>
<td><s:property value="agentVO.name" /></td>
<td class="table_td_title">代理商类型</td>
<td nowrap="nowrap"><s:if test="agentVO.type==1">企业代理 </s:if><s:elseif test="agentVO.type == 2">个人代理</s:elseif>
</td>
</tr>
<tr>
<td class="table_td_title">合作模式描述</td>
<td>
<s:property value="agentVO.cooperateMode" />
<td class="table_td_title">代理商级别</td>
<td><s:property value="agentVO.level" /></td>
</tr>
<%@ include file="../common/user_detail.jsp"%>
<tr>
<td class="table_td_title">备注</td>
<td colspan="3"><textarea id="description" cols="80" rows="5" readonly="readonly"><s:property value="agentVO.description" /></textarea>
</td>
</tr>
</table>
</div>
<div class="modal-footer">
</div>
		
			
