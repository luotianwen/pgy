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
				<td><s:property value="linkads2VO.name" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">ADVER_ID</td>	
				<td><s:property value="linkads2VO.adverId" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">LINK_TYPE</td>	
				<td><s:property value="linkads2VO.linkType" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">LINK_URL</td>	
				<td><s:property value="linkads2VO.linkUrl" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">REDIRECT_URL</td>	
				<td><s:property value="linkads2VO.redirectUrl" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">DESK_ICON_NAME</td>	
				<td><s:property value="linkads2VO.deskIconName" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">DESK_ICON_URL</td>	
				<td><s:property value="linkads2VO.deskIconUrl" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">STATUS_BAR_TITLE</td>	
				<td><s:property value="linkads2VO.statusBarTitle" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">STATUS_BAR_CONTENT</td>	
				<td><s:property value="linkads2VO.statusBarContent" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">CREATE_TIME</td>	
				<td><s:property value="linkads2VO.createTime" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">STATUS</td>	
				<td><s:property value="linkads2VO.status" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">ORDERS</td>	
				<td><s:property value="linkads2VO.orders" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">OPERATOR</td>	
				<td><s:property value="linkads2VO.operator" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">COUNTRY_IDS</td>	
				<td><s:property value="linkads2VO.countryIds" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">IS_RSS</td>	
				<td><s:property value="linkads2VO.isRss" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">LINK_AD_TYPE</td>	
				<td><s:property value="linkads2VO.linkAdType" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">CLICK_TYPE</td>	
				<td><s:property value="linkads2VO.clickType" /></td>
			</tr>
	
	 
	 
</table>
</div>
</div>