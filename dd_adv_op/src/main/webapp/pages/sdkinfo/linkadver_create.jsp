<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:hidden id="cid" name="linkAdverVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
		<s:if test="linkAdverVO.id==0">新建电商广告</s:if>
		<s:if test="linkAdverVO.id>0">修改电商广告</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
		<table class="table">
			<tr>
				<td class="table_td_title"  style="width: 80px; color:red">名称</td>
				<td>
					<input id="name" type="text"  class="input-medium" name="linkAdverVO.name"
						   value="${linkAdverVO.name}"/>

				</td>
				<td class="table_td_title">接入人员</td>
				<td>
					<s:select id="advLinkmanId" list="#Option.advLinkmanList" listKey="id" listValue="name" name="linkAdverVO.advLinkmanId"
							  headerKey="0" headerValue="请选择接入人员" cssClass="selectWidth"></s:select>
				</td>
			</tr>
			<tr>
				<td class="table_td_title"  style="color:red">跳转url</td>
				<td colspan="3">
					<input id="redirectUrl" type="text" size="100" class="input-medium" name="linkAdverVO.redirectUrl"
						   value="${linkAdverVO.redirectUrl}"/>
				</td>
			</tr>
			<tr>
				<td class="table_td_title">广告主</td>
				<td>
					<s:select id="adverId" list="#Option.adverList" listKey="id" listValue="name" name="linkAdverVO.adverId"
							  cssClass="selectWidth"></s:select>
				</td>
				<td class="table_td_title">状态</td>
				<td>
					<s:select id="status" list="#Option.selectList" listKey="key" listValue="value"
							  name="linkAdverVO.status" cssClass="selectWidth"></s:select>

				</td>
			</tr>
			<tr>
				<td class="table_td_title">推广类型</td>
				<td>
					<s:select id="clickType" headerValue="3200" list="#Option.pushType" listKey="key" listValue="value"
							  name="linkAdverVO.clickType" cssClass="selectWidth"></s:select>

				</td>

			</tr>

			<tr>
				<td class="table_td_title" style="color:red">上限</td>
				<td>
					<input id="cap" type="text" class="input-medium" name="linkAdverVO.cap" value="${linkAdverVO.cap}"/>

				</td>
				<td class="table_td_title" style="color:red">cpm</td>
				<td>
					<input id="cpm" type="text" class="input-medium" name="linkAdverVO.cpm"
						   value="${linkAdverVO.cpm}"/>

				</td>
			</tr>

			<tr>
				<td class="table_td_title">图标</td>
				<td colspan="3">
					<textarea rows="2" cols="100" id="iconUrl">${linkAdverVO.iconUrl}</textarea>
				</td>
			</tr>

			<tr>
				<td class="table_td_title">说明</td>
				<td colspan="3">
					<textarea rows="2" cols="100" id="notes">${linkAdverVO.notes}</textarea>
				</td>
			</tr>



			<tr>
				<td class="table_td_title" rowspan="6">推广国家</td>
				<td>

					亚洲
					<input id="inlineAsiaCheckbox" onclick="javascript:change(this.value);" name="allAsia"
						   type="checkbox"
						   value="1000001">
				</td>
				<td colspan="2">
					<s:iterator value="#Option.asiaCountryList" status="asia">
						<input id="inline_<s:property value="id"/>" name="asiaCheckBox_<s:property value="name"/>"
							   type="checkbox" class="asiaClass"
							   value="<s:property value="id"/>" onchange="javascript:changeDetail(1000001);">
						<s:property value="name"/>
						<s:if test="(#asia.index+1)%5==0"><br/></s:if>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>

					北美洲<input id="inlineNorthCheckbox" onclick="javascript:change(this.value);" name="allNorth"
							  type="checkbox"
							  value="1000002">
				</td>
				<td colspan="2">
					<s:iterator value="#Option.northAmericaCountryList" status="north">
						<input id="inline_<s:property value="id"/>" name="northCheckBox_<s:property value="name"/>"
							   type="checkbox" class="northClass"
							   value="<s:property value="id"/>" onchange="javascript:changeDetail(1000002);">
						<s:property value="name"/>
						<s:if test="(#north.index+1)%5==0"><br/></s:if>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>

					南美洲<input id="inlineSouthCheckbox" onclick="javascript:change(this.value);" name="allSouth"
							  type="checkbox"
							  value="1000003">
				</td>
				<td colspan="2">
					<s:iterator value="#Option.southAmericaCountryList" status="south">
						<input id="inline_<s:property value="id"/>" name="southCheckBox_<s:property value="name"/>"
							   type="checkbox" class="southClass"
							   value="<s:property value="id"/>" onchange="javascript:changeDetail(1000003);">
						<s:property value="name"/>
						<s:if test="(#south.index+1)%5==0"><br/></s:if>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>

					大洋洲
					<input id="inlineOceaniaCheckbox" onclick="javascript:change(this.value);" name="allOceania"
						   type="checkbox"
						   value="1000004">
				</td>
				<td colspan="2">

					<s:iterator value="#Option.oceaniaCountryList" status="oceania">
						<input id="inline_<s:property value="id"/>" name="oceaniaCheckBox_<s:property value="name"/>"
							   type="checkbox" class="oceaniaClass"
							   value="<s:property value="id"/>" onchange="javascript:changeDetail(1000004);">
						<s:property value="name"/>
						<s:if test="(#oceania.index+1)%5==0"><br/></s:if>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>
					欧洲
					<input id="inlineEuropeCheckbox" onclick="javascript:change(this.value);" name="allEurope"
						   type="checkbox"
						   value="1000005">
				</td>
				<td colspan="2">
					<s:iterator value="#Option.europeCountryList" status="europe">
						<input id="inline_<s:property value="id"/>" name="europeCheckBox_<s:property value="name"/>"
							   type="checkbox" class="europeClass"
							   value="<s:property value="id"/>" onchange="javascript:changeDetail(1000005);">
						<s:property value="name"/>
						<s:if test="(#europe.index+1)%5==0"><br/></s:if>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>
					非洲
					<input id="inlineAfricaCheckbox" onclick="javascript:change(this.value);" name="allAfrica"
						   type="checkbox"
						   value="1000006">
				</td>
				<td colspan="2">
					<s:iterator value="#Option.africaCountryList" status="africa">
						<input id="inline_<s:property value="id"/>" name="africaCheckBox_<s:property value="name"/>"
							   type="checkbox" class="africaClass"
							   value="<s:property value="id"/>" onchange="javascript:changeDetail(1000006);">
						<s:property value="name"/>
						<s:if test="(#africa.index+1)%5==0"><br/></s:if>
					</s:iterator>
				</td>
			</tr>
			<tr>


			<tr>
				<td colspan="4">
					<input id="createBtn" type="button" class="btn btn-primary" value="确 定"
						   onclick="javascript:updatelinkadver('<%=path %>');">
				</td>
			</tr>
		</table>
	</form>
</div>
<div class="modal-footer">
</div>
<script type="text/javascript">

	var asiaSize = '<s:property value="#Option.asiaCountryList.size"/>';
	var northSize = '<s:property value="#Option.northAmericaCountryList.size"/>';
	var southSize = '<s:property value="#Option.southAmericaCountryList.size"/>';
	var oceaniaSize = '<s:property value="#Option.oceaniaCountryList.size"/>';
	var europeSize = '<s:property value="#Option.europeCountryList.size"/>';
	var africaSize = '<s:property value="#Option.africaCountryList.size"/>';

	function change(code) {
		if (code == 1000001) {
			if ($("#inlineAsiaCheckbox").prop("checked")) {
				$("input[name^='asiaCheckBox']").prop("checked", true);
			} else {
				$("input[name^='asiaCheckBox']").prop("checked", false);
			}
		} else if (code == 1000002) {
			if ($("#inlineNorthCheckbox").prop("checked")) {
				$("input[name^='northCheckBox']").prop("checked", true);
			} else {
				$("input[name^='northCheckBox']").prop("checked", false);
			}
		} else if (code == 1000003) {
			if ($("#inlineSouthCheckbox").prop("checked")) {
				$("input[name^='southCheckBox']").prop("checked", true);
			} else {
				$("input[name^='southCheckBox']").prop("checked", false);
			}
		} else if (code == 1000004) {
			if ($("#inlineOceaniaCheckbox").prop("checked")) {
				$("input[name^='oceaniaCheckBox']").prop("checked", true);
			} else {
				$("input[name^='oceaniaCheckBox']").prop("checked", false);
			}
		} else if (code == 1000005) {
			if ($("#inlineEuropeCheckbox").prop("checked")) {
				$("input[name^='europeCheckBox']").prop("checked", true);
			} else {
				$("input[name^='europeCheckBox']").prop("checked", false);
			}
		} else if (code == 1000006) {
			if ($("#inlineAfricaCheckbox").prop("checked")) {
				$("input[name^='africaCheckBox']").prop("checked", true);
			} else {
				$("input[name^='africaCheckBox']").prop("checked", false);
			}
		}

	}
	function changeDetail(code) {
		if (code == 1000001) {
			var aa = $("input.asiaClass:checked");
			// var aa = $("input[name='asiaCheckBox']:checked").val([]);

			var len = aa.length;
			if (len >= asiaSize) {
				$("#inlineAsiaCheckbox").prop("checked", true);
			} else {
				$("#inlineAsiaCheckbox").prop("checked", false);
			}
		} else if (code == 1000002) {
			var aa = $("input.northClass:checked");
			// var aa = $("input[name='northCheckBox']:checked").val([]);
			var len = aa.length;
			if (len >= northSize) {
				$("#inlineNorthCheckbox").prop("checked", true);
			} else {
				$("#inlineNorthCheckbox").prop("checked", false);
			}
		} else if (code == 1000003) {
			var aa = $("input.southClass:checked");
			//var aa = $("input[name='southCheckBox']:checked").val([]);
			var len = aa.length;
			if (len >= southSize) {
				$("#inlineSouthCheckbox").prop("checked", true);
			} else {
				$("#inlineSouthCheckbox").prop("checked", false);
			}
		} else if (code == 1000004) {
			var aa = $("input.oceaniaClass:checked");
			// var aa = $("input[name='oceaniaCheckBox']:checked").val([]);;
			var len = aa.length;
			if (len >= oceaniaSize) {
				$("#inlineOceaniaCheckbox").prop("checked", true);
			} else {
				$("#inlineOceaniaCheckbox").prop("checked", false);
			}
		} else if (code == 1000005) {
			var aa = $("input.europeClass:checked");
			//var aa = $("input[name='europeCheckBox']:checked").val([]);
			var len = aa.length;
			if (len >= europeSize) {
				$("#inlineEuropeCheckbox").prop("checked", true);
			} else {
				$("#inlineEuropeCheckbox").prop("checked", false);
			}
		} else if (code == 1000006) {
			var aa = $("input.africaClass:checked");
			//var aa = $("input[name='africaCheckBox']:checked").val([]);
			var len = aa.length;
			if (len >= africaSize) {
				$("#inlineAfricaCheckbox").prop("checked", true);
			} else {
				$("#inlineAfricaCheckbox").prop("checked", false);
			}
		}

	}


	var country = '<s:property value="linkAdverVO.extensionContry"/>';
	var data = country.toString().split(",");
	for (var i = 0; i < data.length; i = i + 1) {
		$("#inline_" + data[i]).prop("checked", true);
	}

	changeDetail(1000001);
	changeDetail(1000002);
	changeDetail(1000003);
	changeDetail(1000004);
	changeDetail(1000005);
	changeDetail(1000006);

	function checkCountryName(obj) {
		var countryNames = obj.value;
		if (countryNames == "" || countryNames.lenght == 0) {
			return;
		}
		var data = countryNames.toString().split(",");
		for (var i = 0; i < data.length; i = i + 1) {
			$("input[name*='" + data[i] + "']").prop("checked", true);
		}
	}
</script>
