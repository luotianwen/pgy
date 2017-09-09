<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="apkCphcTotalModelVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px;overflow:auto">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>
				<table style="width:99%">
					<tr>
						<td>
							<input class="btn btn-primary" type="button"
								   onclick="createapkCphcTotalModel('<%=path %>/record')" value="导入广告数据">
							<a class="btn btn-primary" href="/document/model_ad_income.xlsx">下载导入模版</a>
						</td>
						<td align="right">
							<div id="ajax-modal" class="modal fade" tabindex="-1"></div>
							广告收益录入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="control-label inline" for="pname">apkid</label>
							<input id="qid" type="text" class="input-medium inline" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="control-label inline" for="pname"> 时间</label>
							<input type="text" name="qname" id="qname" value='<s:property value="apkCphcTotalModelVO.sdate"/>'
								   onfocus="javascript:WdatePicker()" readonly="readonly" style="width:150px;">
							<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
						</td>
					</tr>
				</table>
   			</fieldset>
   		</div>
   	</div>
</div>
<div class="box box-primary" style="margin-top: 15px;overflow:auto">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>
   			<div id="dataList">
				<table id="dataTable" class="table table-bordered table-striped table-hover">
					 	</s:if>
					<thead>
						<tr>
						<th >日期</th>
						<th >apkid</th>
						<th >安装数</th>
						<th >展示数</th>
						<th >转化数</th>
						<th >转化率</th>
						<th >单价</th>
						<th >收入</th>
						<th >CPM</th>
						<!-- 
						<th>操作</th> -->
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="apkCphcTotalModelVOList">  
		    		<tr >
						<td><s:property value="sdate"/></td>
						<td><s:property value="apkid"/></td>
						<td><s:property value="installtotal"/></td>
						<td><s:property value="showtotal"/></td>
						<td><s:property value="inputtotal+realtotal"/></td>
						<td><s:property value="inputPercent+realPercent"/></td>
						<td><s:property value="price"/></td>
						<td><s:property value="income"/></td>
						<td><s:property value="cpm"/></td>
						<!-- 
		    		<td>
	    				<a class="btn btn-info" href="javascript: detailapkCphcTotalModel('<%=path%>/record/ApkCphcTotalModel!detail.action','<s:property value="apkid"/>','<s:property value="sdate"/>')" title="详情">
						详情
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="btn btn-warning" href="javascript: modifyapkCphcTotalModel('<%=path%>/record','<s:property value="apkid"/>','<s:property value="sdate"/>')" title="修改">
						修改
						</a> </td> -->
						<td>
							<a class="btn btn-primary" href="javascript: modifyPrice('<%=path%>/record','<s:property value="apkid"/>'
							,'<s:property value="sdate"/>','<s:property value="price"/>')" title="修改">修改</a>
						</td>
		    		</tr>
				</s:iterator>
				<tr>
					<td colspan="8">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="apkCphcTotalModelVO.page" operation="/record/ApkCphcTotalModel!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				 	<s:if test="apkCphcTotalModelVO.isPage==0">
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
		argus += "apkCphcTotalModelVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&apkCphcTotalModelVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();		
        argus += "&apkCphcTotalModelVO.apkid=";
        argus += id;
        argus += "&apkCphcTotalModelVO.sdate=";
        argus += name;
        argus += "&apkCphcTotalModelVO.isPage=1";
		return argus;
	}
	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();		
		url = "<%=request.getContextPath()%>/record/ApkCphcTotalModel!list.action";
		jQuery.post(url,{"apkCphcTotalModelVO.apkid":id,"apkCphcTotalModelVO.sdate":name,"apkCphcTotalModelVO.isPage":1
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
	function modifyapkCphcTotalModel(path,id,sdate) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/ApkCphcTotalModel!modify.action', {"apkCphcTotalModelVO.apkid":id,"apkCphcTotalModelVO.sdate":sdate}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function modifyPrice(path,id,sdate,price) {
		$('body').modalmanager('loading');
		setTimeout(function(){
			$modal.load(path+'/ApkCphcTotalModel!modifyPrice.action', {
				"apkCphcTotalModelVO.apkid":id,
				"apkCphcTotalModelVO.sdate":sdate,
				"apkCphcTotalModelVO.price":price
			}, function(){
				$modal.modal();
			});
		}, 100);
	}
	function createapkCphcTotalModel(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/ApkCphcTotalModel!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function detailapkCphcTotalModel(url,id,sdate) {
		//$("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/record/ApkCphcTotalModel!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
		var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
		$("#dataContent").html('').append(loading_img);
		$.post(url,{"apkCphcTotalModelVO.apkid":id,"apkCphcTotalModelVO.sdate":sdate},function(response){
			if(response==-1) {
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				$("#dataContent").html(jQuery.trim(response));
			}
		});
	}
	//修改or新增
	function updateapkCphcTotalModel(path) {
		var id = $("#cid").val();
		 
		var url = path +'/record/ApkCphcTotalModel!update.action';
		if(id==0) {
			url = path+'/record/ApkCphcTotalModel!save.action';
		}
		 var sdate=$("#sdate").val();
		 var apkid=$("#apkid").val();
		 var inputtotal=$("#inputtotal").val();
		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, { 
			"apkCphcTotalModelVO.sdate":sdate,
			"apkCphcTotalModelVO.apkid":apkid,
			"apkCphcTotalModelVO.inputtotal":inputtotal
		 }, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#surebtn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
	       		$("#qid").val(apkid);
				$("#qname").val(sdate);	
	           	doPageBottom('turn');
	        }
	   });
	}
	 
	 
</script>
</s:if>
