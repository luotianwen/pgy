<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:if test="sdkDetailVO.isPage==0">
	<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
		<div class="span12">
			<div class="form-inline">
				<fieldset>
					<table style="width:100%">
						<tr>
							<td>
							<td align="right">
								<div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
								<label class="control-label inline" for="imei">imei</label>
								<input id="imei" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <label class="control-label inline" for="pname">类型</label>
                            <s:select list="#{'-1':'全部','1':'定时','2':'悬浮','3':'push','4':'电商拦截','5':'其他拦截'}" listKey="key" listValue="value" id="type"  />
                            <label class="control-label inline" for="pkgid">pkgid</label>
								<input id="pkgid" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label class="control-label inline" for="ctime"> 时间</label>
								<input type="text" name="ctime" id="ctime" onfocus="javascript:WdatePicker()"
									   readonly="readonly" value="${sdkDetailVO.cdate}"
									   style="width:150px;">
								<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询
								</button>
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
		</div>
	</div>
	<div class="box box-primary" style="margin-top: 15px">
	<div class="span12">
	<form class="form-horizontal" id="dataForm">
	<fieldset>

	<div id="dataList">
	<table id="dataTable" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
<tr>
	<th>imei</th>
	<th>广告类型</th>
	<th>adId</th>
	<th>创建日期</th>
	<th>国家</th>
	<th>包id</th>

</tr>
</thead>
<tbody>
<s:iterator value="sdkDetailVOList">
	<tr>
		<td><s:property value="imei"/></td>
		<td>
			<s:iterator value="#Option.promotionType">
				<s:if test="key==type">
					<s:property value="value"/>
				</s:if>
			</s:iterator>
		</td>
		<td><s:property value="adId"/></td>
		<td><s:property value="cdate"/></td>
		<td>
			<s:iterator value="#Option.countryList">
				<s:if test="id==cou">
					<s:property value="name"/>
				</s:if>
			</s:iterator>
		</td>
		<td><s:property value="pkgid"/></td>
	</tr>
</s:iterator>

</tbody>
<s:if test="sdkDetailVO.isPage==0">
	</table>
	</div>
	<div style='text-align:center;'>
		<img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
	</div>
	</fieldset>
	</form>
	</div>
	</div>
	<script type="text/javascript">

		function searchList() {

			var imei = $("#imei").val();
			var pkgid = $("#pkgid").val();
			var ctime = $("#ctime").val();
            var type = $("#type").val();
			$("#load").css({"display": ""});
			jQuery("#dataTable").html("");
			url = "<%=request.getContextPath()%>/detail/SdkDetail!list.action";
			jQuery.post(url, {
				"sdkDetailVO.isPage": 1,
				"sdkDetailVO.pkgid": pkgid,
				"sdkDetailVO.imei": imei,
				"sdkDetailVO.cdate": ctime,
				"sdkDetailVO.type": type

			}, function (response) {
				if (response == "-1") {
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					$("#load").css({"display": "none"});
				} else {
					jQuery("#dataTable").html(jQuery.trim(response));
					$("#load").css({"display": "none"});
				}
			});
		}


	</script>
</s:if>
