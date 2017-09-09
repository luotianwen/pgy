<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8"
	language="java"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
<!--
.table_td_title {
	
}
-->
</style>
<div class="alert alert-warning alert-dismissable" style="margin-top: 10px!important">
	如果信息有误，请联系对接人进行更改.
	<a href="#" data-dismiss="alert" class="close">×</a>
</div>
<div class="box box-primary">
	<div class="box-header">
		<h3 class="box-title">客户信息</h3>
	</div>
<div class="widget-box">
<table class="table table-bordered table-striped">
<tbody>
	<tr>
		<td class="table_td_title">
			客户名称-ID
		</td>
		<td>
			<s:property value="customerVO.name" />
			&nbsp;&nbsp;-&nbsp;&nbsp;C.[
			<font color="red"><s:property value="customerVO.id" />
			</font>]
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			代理商
		</td>
		<td>
			<s:property value="customerVO.agentName" />
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			客户联系人
		</td>
		<td>
			<s:property value="customerVO.contact" />
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			手机号码
		</td>
		<td>
			<s:property value="customerVO.mobile" />
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			QQ
		</td>
		<td>
			<s:property value="customerVO.qq" />
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			Mail
		</td>
		<td>
			<s:property value="customerVO.mail" />
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			地址
		</td>
		<td>
			<s:property value="customerVO.address" />			
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			结算方式
		</td>
		<td>
			<s:property value="customerVO.balanceType" />			
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			结算比例
		</td>
		<td>
			<s:property value="customerVO.balancePercent" />			
		</td>
	</tr>
	<tr>
		<td class="table_td_title">
			创建时间
		</td>
		<td>
			<s:property value="customerVO.createTime" />
		</td>
	</tr>
	</tbody>
</table>
</div>
</div>