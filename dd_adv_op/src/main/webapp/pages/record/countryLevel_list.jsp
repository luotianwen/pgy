<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="countryLevelVO.isPage==0">
	<div class="row-fluid" style="min-height: 40px;padding-top: 20px;overflow:auto">
		<div class="span12">
			<div class="form-inline">
				<fieldset>
					<table style="width:99%"><tr><td>
						<input class="btn btn-primary" type="button" onclick="createcountryLevel('<%=path %>/record')" value="新增"></td>
						<td align="right"><div id="ajax-modal" class="modal  fade" tabindex="-1"></div>
							<label class="control-label inline" for="pname">产品 </label>
							<s:select list="#Option.productList" listKey="id" listValue="name" id="productId2"   cssClass="selectWidth" headerKey="-100" headerValue="--所有产品--"></s:select>

							<label class="control-label inline" for="pname">国家</label>
							<s:select list="#Option.countryList" listKey="id" listValue="name"   id="countryId2"    cssClass="selectWidth" headerKey="-100" headerValue="--所有国家--"  ></s:select>
							<label class="control-label inline" for="pname">等级</label>
							<s:select list="#Option.countryLevel" listKey="key" listValue="value" id="level2"   cssClass="selectWidth" headerKey="-100" headerValue="--所有等级--"></s:select>

							<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
						</td></tr></table>
				</fieldset>
			</div>
		</div>
	</div>
	<div class="box box-primary" style="margin-top: 15px;overflow:auto">
	<div class="span12">
	<form class="form-horizontal" id="dataForm">
	<fieldset>
	<div id="dataList">
	<table id="dataTable" style="width:95%" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
<tr>
	<th >产品</th>
	<th >国家名称</th>
	<th >类型</th>
	<th>操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="countryLevelVOList">
	<tr >
		<td><s:property value="productname"/></td>
		<td><s:property value="countryname"/></td>
		<td>
			<s:if test="level==1">高</s:if>
			<s:if test="level==2">中</s:if>
			<s:if test="level==3">低</s:if>
			<s:if test="level==4">无效</s:if>
		</td>
		<td>

			<a class="btn btn-warning" href="javascript: modifycountryLevel('<%=path%>/record','<s:property value="id"/>','<s:property value="productId"/>')" title="修改">
				修改
			</a> |
			<a class="btn btn-warning" href="javascript: deletecountryLevel('<%=path%>/record','<s:property value="id"/>','<s:property value="productId"/>')" title="删除">
				删除
			</a>
		</td>
	</tr>
</s:iterator>
<tr>
	<td colspan="8">
		<div class="pagelist" id="pagelist1" align="right">
			<page:paginationAjax formName="productForm" property="countryLevelVO.page" operation="/record/CountryLevel!list.action"/>
		</div>
	</td>
</tr>
</tbody>
<s:if test="countryLevelVO.isPage==0">
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
		$("#countryId2").select2();
		$("#productId2").select2();
		$("#level2").select2();
		function getArgus() {
			var argus ="";
			argus += "countryLevelVO.page.pageNum=";
			argus += $("#pageNum").val();
			argus += "&countryLevelVO.page.pageSize=";
			argus += $("#pageSize").val();

			var level2 = $("#level2").val();
			var productId2 = $("#productId2").val();
			var countryId2 = $("#countryId2").val();
			argus += "&countryLevelVO.level=";
			argus += level2;

			argus += "&countryLevelVO.productId=";
			argus += productId2;
			argus += "&countryLevelVO.countryId=";
			argus += countryId2;
			argus += "&countryLevelVO.isPage=1";
			return argus;
		}
		function searchList() {
			$("#load").css({"display":""});
			jQuery("#dataTable").html("");
			var level2 = $("#level2").val();
			var productId2 = $("#productId2").val();
			var countryId2 = $("#countryId2").val();
			url = "<%=request.getContextPath()%>/record/CountryLevel!list.action";
			jQuery.post(url,{ "countryLevelVO.level":level2,"countryLevelVO.isPage":1,"countryLevelVO.productId":productId2,"countryLevelVO.countryId":countryId2
			},function(response){
				if(response=="-1") {
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					$("#load").css({"display":"none"});
				} else {
					jQuery("#dataTable").html(jQuery.trim(response));
					$("#load").css({"display":"none"});
				}
			});
		}

		var $modal = $('#ajax-modal');
		$modal.on('click', '.update', function(){
			$modal.modal('loading');
			setTimeout(function(){
				$modal.modal('loading').find('.modal-body')
						.prepend('<div class="alert alert-info fade in">' +
								'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
								'</div>');
			}, 100);
		});
		function modifycountryLevel(path,id,productId) {
			$('body').modalmanager('loading');
			setTimeout(function(){
				$modal.load(path+'/CountryLevel!modify.action', {"countryLevelVO.id":id,"countryLevelVO.productId":productId}, function(){
					$modal.modal();
				});
			}, 100);
		}
		function createcountryLevel(path) {
			$('body').modalmanager('loading');
			setTimeout(function(){
				$modal.load(path+'/CountryLevel!create.action', {}, function(){
					$modal.modal();
				});
			}, 100);
		}

		function detailcountryLevel(url,id,productId) {
			//$("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/record/CountryLevel!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
			var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
			$("#dataContent").html('').append(loading_img);
			$.post(url,{"countryLevelVO.id":id,"countryLevelVO.productId":productId},function(response){
				if(response==-1) {
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					$("#dataContent").html(jQuery.trim(response));
				}
			});
		}
		//修改or新增
		function updatecountryLevel(path) {
			var id = $("#cid").val();
			var url = path +'/record/CountryLevel!update.action';
			if(id==0) {
				url = path+'/record/CountryLevel!save.action';
			}
			var countrycode=$("#countryId").val();
			var level=$("#level").val();
			var productId=$("#productId").val();
			$("#surebtn").attr("disabled","disabled");
			jQuery.post(url, {
						"countryLevelVO.productId":productId,
						"countryLevelVO.countryId":countrycode,
						"countryLevelVO.level":level
					},
					function(response){
						if(jQuery.trim(response) == "-1"){
							showErrorToastMiddle("系统出错，请重试或联系管理员");
							jQuery("#surebtn").attr('disabled', 'disabled');
						} else {
							showInfoToastMiddle(response);
							doPageBottom('turn');

						}
					});
		}

		//修改or新增
		function deletecountryLevel(path,countryId,productId) {
			var url = path +'/record/CountryLevel!delete.action';

			jQuery.post(url, {
						"countryLevelVO.productId":productId,
						"countryLevelVO.countryId":countryId
					},
					function(response){
						if(jQuery.trim(response) == "-1"){
							showErrorToastMiddle("系统出错，请重试或联系管理员");
							jQuery("#surebtn").attr('disabled', 'disabled');
						} else {
							showInfoToastMiddle(response);
							doPageBottom('turn');

						}
					});
		}


	</script>
</s:if>
