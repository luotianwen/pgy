<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8"
	language="java"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
<!--
.table_td_title {
	
}
-->
</style>
<div class="box box-primary">
	<div class="box-header">
		<h3 class="box-title">应用详情</h3>
	</div>
<div class="widget-box"><table class="table table-bordered table-striped">
	<tr>
		<td class="table_td_title">
			应用名称
		</td>
		<td>
			<font color="red"><s:property value="productVO.name" />
			</font>
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			APP ID
		</td>
		<td>
			
			<font color="red"><s:property value="productVO.id" />
			</font>
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			应用包名
		</td>
		<td>
			<s:property value="productVO.packageName" />
		</td>
	</tr>
	
	<tr>
		<td class="table_td_title">
			SDK
		</td>
		<td>
		<s:iterator value="productSdkVOList">
		<s:property value="sdkName" />-<s:property value="sdkId" />聚合自定义界面<s:if test="hasSelfConfirm==0">否</s:if><s:if test="hasSelfConfirm==1">是</s:if>
		</s:iterator>
			
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			备注
		</td>
		<td>
			<s:property value="productVO.info" />
		</td>
	</tr>
	
	<tr>
		<td class="table_td_title">
			创建时间
		</td>
		<td>
			<s:property value="productVO.createTime" />
		</td>
	</tr>
</table>
</div>
</div>