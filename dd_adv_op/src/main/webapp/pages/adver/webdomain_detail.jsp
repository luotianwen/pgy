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
		<h5>链接广告详情</h5>
	</div>
<div class="widget-content nopadding" style="padding-top: 10px!important">
<table class="table table-bordered table-striped">
			<tr>	
				<td class="table_td_title">域名</td>
				<td><s:property value="webdomainVO.download" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">创建时间</td>
				<td><s:property value="webdomainVO.cdate" /></td>
			</tr>
	
	 
	 
</table>
</div>
</div>