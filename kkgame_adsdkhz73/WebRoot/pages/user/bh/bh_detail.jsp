<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>代理商详情</h2>
</div>
<div class="modal-body">
<table class="table table-bordered">
<tr >	
	<td class="table_td_title">姓名</td>			 
	<td colspan="3"><s:property value="bhVO.name"/></td>
</tr>
<tr >	
	<td class="table_td_title">所属商务拓展人员</td>	
	<td colspan="3"><s:property value="bhVO.businessDeveloperName"/></td>
</tr>

<tr >	
	<td class="table_td_title">类型</td>	
	<td colspan="3">
	<s:if test='bhVO.ifOwn==true'>商务本人</s:if>
	<s:else>其他</s:else>
	</td>
</tr>
<%@ include file="../common/user_detail.jsp" %>
<tr >
	<td class="table_td_title">备注</td>			 
    <td colspan="3"><textarea id="description" name="bhVO.description" cols="80" rows="5" readonly="readonly"><s:property value="bhVO.description"/></textarea></td>
</tr>
</table>
</div>
<div class="modal-footer">
</div>
