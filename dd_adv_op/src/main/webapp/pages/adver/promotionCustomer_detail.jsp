<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8"
	language="java"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
<!--
.table_td_title {
	
}
-->
</style>
<div class="widget-box">
	<div class="widget-title">
		<span class="icon">
			<i class="icon-th"></i>
		</span>
		<h5>资费组合配置详情</h5>
	</div>
<div class="widget-content nopadding" style="padding-top: 10px!important">
<table class="table table-bordered table-striped">
			<tr>	
				<td class="table_td_title">NAME</td>	
				<td><s:property value="promotionCustomerVO.name" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">CDATE</td>	
				<td><s:property value="promotionCustomerVO.cdate" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">NOTES</td>	
				<td><s:property value="promotionCustomerVO.notes" /></td>
			</tr>
	
	 
	 
</table>
</div>
</div>