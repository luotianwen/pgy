<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="appNameVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:99%"><tr><td>
		   		<input class="btn btn-primary" type="button" onclick="createappname('<%=path %>/sdkinfo')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal  fade"  data-width="1000"  tabindex="-1"></div>
            	<label class="control-label inline" for="pname">ID</label>
					<input id="qid" type="text" class="input-medium inline"   style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<label class="control-label inline" for="pname">名称</label>
   			    	<input id="qname" type="text" class="input-medium inline"    style="width: 100px">

					<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		<button type="reset" class="btn">清 空</button></td></tr></table>
   			</fieldset>
   		</div>
   	</div>
</div>
<div class="box box-primary" style="margin-top: 15px">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>
   			<div id="dataList">
				<table id="dataTable" style="width:95%" class="table table-bordered table-striped table-hover">
					 	</s:if>
					<thead>
						<tr>
						<th >ID</th>
						<th >APP名称</th>
						<th >APP包名</th>
						<th >创建时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="appNameVOList">
		    		<tr >
						<td><s:property value="id"/></td>
						<td><s:property value="appName"/></td>
						<td><s:property value="packageName"/></td>
						<td><s:property value="createDate"/></td>
		    		<td>
	    				<%--<a class="btn btn-info" href="javascript: detaillinkads2('<%=path%>/adver/Linkads2!detail.action','<s:property value="id"/>')" title="详情">
						详情
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;--%>
						<a class="btn btn-warning" href="javascript: modifyappname('<%=path%>/sdkinfo','<s:property value="id"/>')" title="修改">
						修改
						</a>
							&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="btn btn-danger" href="javascript: removeappname('<%=path%>/sdkinfo','<s:property value="id"/>')" title="删除">
							删除
						</a>
					</td>


		    		</tr>
				</s:iterator>
				<tr>
					<td colspan="8">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="appNameVO.page" operation="/sdkinfo/AppName!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				 	<s:if test="appNameVO.isPage==0">
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
	function getArgus() {		
		var argus ="";
		argus += "appNameVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&appNameVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();
		var pstatus = $("#pstatus").val();
		var plinkAdType = $("#plinkAdType").val();
		var pclickType = $("#pclickType").val();

        argus += "&appNameVO.id=";
        argus += id;
        argus += "&appNameVO.name=";
        argus += name;
		argus += "&appNameVO.status=";
		argus += pstatus;
        argus += "&appNameVO.isPage=1";
		return argus;
	}

	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();
		var pstatus = $("#pstatus").val();
		var plinkAdType = $("#plinkAdType").val();
		var pclickType = $("#pclickType").val();

		url = "<%=request.getContextPath()%>/sdkinfo/AppName!list.action";
		jQuery.post(url,{
			"appNameVO.id":id,"appNameVO.appName":name,
			"appNameVO.status":pstatus,
			"appNameVO.isPage":1
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
	function modifyappname(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/AppName!modify.action', {"appNameVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createappname(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/AppName!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function detaillinkads2(url,id) {
		$("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/adver/Linkads2!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
		var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
		$("#dataContent").html('').append(loading_img);
		$.post(url,{"appNameVO.id":id},function(response){
			if(response==-1) {
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				$("#dataContent").html(jQuery.trim(response));
			}
		});
	}

	function removeappname(path,id){
		var url = path +'/sdkinfo/AppName!remove.action';
		jQuery.post(url, {"appNameVO.id":id },
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
	function updateappname(path) {
		var id = $("#cid").val();

		var url = path +'/sdkinfo/AppName!update.action';
		if(id==0) {
			url = path+'/sdkinfo/AppName!save.action';
		}

		var packageName=$("#packageName").val();
		var appName=$("#appName").val();

		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {"appNameVO.id":id,
					"appNameVO.packageName":packageName,
					"appNameVO.appName":appName
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
