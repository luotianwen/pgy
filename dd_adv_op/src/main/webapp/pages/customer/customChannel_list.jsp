<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserVO userVO = (UserVO) request.getSession().getAttribute(
			"SESSION_USER");
%>
<s:if test="customChannelVO.isPage==0">
	<s:hidden id="qproductId" name="customChannelVO.productId"></s:hidden>
	<div class="row-fluid" style="min-height: 40px; padding-top: 20px">
		<div class="span12">
			<div class="form-inline">
				<fieldset>
					<table style="width: 100%">
						<tr>
							<td>
								<button type="button" class="btn btn-primary"
									onclick="javascript: mainLoadData('<%=request.getContextPath()%>/customer/Product!list.action',113)">
									返 回
								</button>
							</td>
							<td align="right">
								<label class="control-label inline" for="pname">
									渠道ID
								</label>
								<input id="qid" type="text" class="input-medium inline"
									name="customChannelVO.customChannelId"
									value="<s:property value="customChannelVO.customChannelId"/>"
									style="width: 100px">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-primary"
									onclick="searchList('<%=path%>')">
									查 询
								</button>
								<button type="reset" class="btn btn-primary"
									onclick="clearInfo()">
									清 空
								</button>
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
		</div>
	</div>
	<div class="box box-primary" style="margin-top: 20px">
		<div class="span12">
			<form class="form-horizontal" id="dataForm">
				<fieldset>
					<div id="dataList">
						<table id="dataTable"
							class="table table-bordered table-striped table-hover">
							</s:if>
							<thead>
								<tr>
									<th>
										APP ID
									</th>
									<th>
										应用名称
									</th>
									<th>
										渠道ID
									</th>
									<th>
										显示比率
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="customChannelVOList">
									<tr>
										<td>
											<s:property value="productId" />
										</td>
										<td>
											<s:property value="productName" />
										</td>
										<td>
											<s:property value="customChannelId" />
										</td>
										<td name="config"  width="180px"  id="config_<s:property value="customChannelId" />"><s:property value="percent" /></td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="8">
										<div class="pagelist" id="pagelist1" align="right">
											<page:paginationAjax formName="productForm"
												property="productVO.page"
												operation="/customer/CustomChannel!list.action" />
										</div>
									</td>
								</tr>
							</tbody>
							<s:if test="customChannelVO.isPage==0">
						</table>
					</div>
					<div style='text-align: center;'>
						<img id="load"
							src="<%=request.getContextPath()%>/img/ajax-loader.gif"
							style="display: none" />
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<script type="text/javascript">
function getArgus() {
	var argus = "";
	argus += "customChannelVO.page.pageNum=";
	argus += $("#pageNum").val();
	argus += "&customChannelVO.page.pageSize=";
	argus += $("#pageSize").val();
	var id = $("#qid").val();
	var productId = $("#qproductId").val();
	argus += "&customChannelVO.customChannelId=";
	argus += id;
	argus += "&customChannelVO.productId=";
	argus += productId;
	argus += "&customChannelVO.isPage=1";
	return argus;
}
function searchList() {
	$("#load").css( {
		"display" : ""
	});
	jQuery("#dataTable").html("");
	var id = $("#qid").val();
	var productId = $("#qproductId").val();
	url = "<%=request.getContextPath()%>/customer/CustomChannel!list.action";
	jQuery.post(url, {
		"customChannelVO.customChannelId" : id,
		"customChannelVO.productId" : productId,
		"customChannelVO.isPage" : 1
	}, function(response) {
		if (response == "-1") {
			showErrorToastMiddle("系统出错，请重试或联系管理员");
			$("#load").css( {
				"display" : "none"
			});
		} else {
			jQuery("#dataTable").html(jQuery.trim(response));
			$("#load").css( {
				"display" : "none"
			});
		}
	});
}

function clearInfo() {
	$("#qid").val("")
}
$(document).ready(function() {
	var tds = $("td[name='config']");
	tds.dblclick(tdClick);
});
function tdClick() {
	//保存当前的td节点
	var td = $(this);
	var id = $(this).attr('id');
	//取出当前td节点的文本内容并保存起来
	var text = td.text();
	//清空td里面的内容
	td.html("");//也可以是td.empty();
	//建立一个文本框
	var input = $("<input id=\'" + id + "\' value=>\'" + text + "\'");
	//设置文本框的值是刚保存起来的
	input.attr("value", text);
	//将文本框加入到td中
	td.append(input);
	//让文本框里的文字高亮选中
	var inputDom = input.get(0);
	inputDom.select();
	//取消td的点击事件
	td.unbind("dblclick");
	//让文本框响应键盘事件
	var path = "<%=request.getContextPath()%>/customer/CustomChannel!update.action";
	
	input.blur(function(event) {
		var inputNode = $(this);
		var inputText = inputNode.val();
		var tdNode = inputNode.parent();
		var inputId = inputNode.attr('id');
		var customChannelId = inputId.substr(7);
		var productId = $("#qproductId").val();
		bootbox.confirm("确定要修改么?", function(result) {
			if (result) {
				jQuery.post(path, {
					"customChannelVO.productId" : productId,
					"customChannelVO.customChannelId" : customChannelId,
					"customChannelVO.percent" : inputText
				}, function(data) {
					if (data == "-1") {
						showErrorToast("提示", "系统出错，请重试或联系管理员");
						tdNode.click(tdClick);
						tdNode.html(text);
						tdNode.click(tdClick);
					} else {
						tdNode.html(inputText);
						showInfoToastMiddle(data);
						tdNode.click(tdClick);
					}
				});
			} else {
				tdNode.html(text);
				tdNode.click(tdClick);
			}
		});
	});
}
</script>
</s:if>
