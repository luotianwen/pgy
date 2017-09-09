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
		<h5>详情</h5>
	</div>
<div class="widget-content nopadding" style="padding-top: 10px!important">
<table class="table table-bordered table-striped">
			<tr>	
				<td class="table_td_title">日期</td>	
				<td><s:property value="apkCphcTotalModelVO.sdate" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">apkid</td>	
				<td><s:property value="apkCphcTotalModelVO.apkid" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">cpid</td>	
				<td><s:property value="apkCphcTotalModelVO.cpid" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">安装数</td>	
				<td><s:property value="apkCphcTotalModelVO.installtotal" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">展示数</td>	
				<td><s:property value="apkCphcTotalModelVO.showtotal" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">实时数</td>	
				<td><s:property value="apkCphcTotalModelVO.realtotal" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">对接数据</td>	
				<td><s:property value="apkCphcTotalModelVO.inputtotal" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">单价</td>	
				<td><s:property value="apkCphcTotalModelVO.price" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">收入</td>	
				<td><s:property value="apkCphcTotalModelVO.income" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">cpm</td>	
				<td><s:property value="apkCphcTotalModelVO.cpm" /></td>
			</tr>
			 
			<tr>	
				<td class="table_td_title">REAL_PERCENT</td>	
				<td><s:property value="apkCphcTotalModelVO.realPercent" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">INPUT_PERCENT</td>	
				<td><s:property value="apkCphcTotalModelVO.inputPercent" /></td>
			</tr>
	
	 
	 
</table>
</div>
</div>