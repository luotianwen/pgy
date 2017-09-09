<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="linkAdverVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-primary" type="button" onclick="createlinkadver('<%=path %>/sdkinfo')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal  fade"  data-width="1000"  tabindex="-1"></div>
            	<label class="control-label inline" for="pname">ID</label>
					<input id="qid" type="text" class="input-medium inline"   style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<label class="control-label inline" for="pname">名称</label>
   			    	<input id="qname" type="text" class="input-medium inline"    style="width: 100px">

					<label class="control-label inline" for="pname">状态</label>
					<s:select list="#{'-1':'全部','1':'是','0':'否'}" listKey="key" listValue="value" id="pstatus"  />

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
					<thead >
						<tr >
						<th >ID</th>
						<th >名称</th>
						<th >接入人员</th>
						<th style="text-align: center">跳转url</th>
						<th>广告主</th>
						<th>推广类型</th>
						<th>上限</th>
						<th>cpm</th>
						<th>说明</th>
						<%--<th>推广国家</th>--%>
						<th >创建时间</th>
						<th >状态</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="linkAdverVOList">
		    		<tr >
						<td><s:property value="id"/></td>
						<td><s:property value="name"/></td>
						<td><s:iterator value="#Option.advLinkmanList">
							<s:if test="advLinkmanId==id">
								<s:property value="name"/>
							</s:if>
						</s:iterator></td>
						<td><s:property value="redirectUrl"/></td>
						<td>
						<s:iterator value="#Option.adverList">
							<s:if test="adverId==id">
								<s:property value="name"/>
							</s:if>
						</s:iterator>
						</td>
						<td>
						<s:iterator value="#Option.pushType">
							<s:if test="clickType==key">
								<s:property value="value"/>
							</s:if>
						</s:iterator>
						</td>
						<td><s:property value="cap"/></td>
						<td><s:property value="cpm"/></td>
						<td><s:property value="notes"/></td>
						<%--<td><s:property value="extensionContry"/></td>--%>
						<td><s:property value="createTime"/></td>
						<td>
							<s:if test="status==0">关闭</s:if>
							<s:if test="status==1">打开</s:if>
						</td>
		    		<td>
	    				<%--<a class="btn btn-info" href="javascript: detaillinkadver('<%=path%>/adver/LinkAdver!detail.action','<s:property value="id"/>')" title="详情">
						详情
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;--%>
						<a class="btn btn-warning" href="javascript: modifylinkadver('<%=path%>/sdkinfo','<s:property value="id"/>')" title="修改">
						修改
						</a>
							&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="btn btn-danger" href="javascript: removelinkadver('<%=path%>/sdkinfo','<s:property value="id"/>')" title="删除">
							删除
						</a>
					</td>
		    		</tr>
				</s:iterator>
				<tr>
					<td colspan="8">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="linkAdverVO.page" operation="/sdkinfo/LinkAdver!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				 	<s:if test="linkAdverVO.isPage==0">
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
		argus += "linkAdverVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&linkAdverVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();
		var pstatus = $("#pstatus").val();
		var plinkAdType = $("#plinkAdType").val();
		var pclickType = $("#pclickType").val();

        argus += "&linkAdverVO.id=";
        argus += id;
        argus += "&linkAdverVO.name=";
        argus += name;
		argus += "&linkAdverVO.status=";
		argus += pstatus;
        argus += "&linkAdverVO.isPage=1";
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

		url = "<%=request.getContextPath()%>/sdkinfo/LinkAdver!list.action";
		jQuery.post(url,{
			"linkAdverVO.id":id,"linkAdverVO.name":name,
			"linkAdverVO.status":pstatus,
			"linkAdverVO.isPage":1
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
	function modifylinkadver(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/LinkAdver!modify.action', {"linkAdverVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createlinkadver(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/LinkAdver!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function detaillinkadver(url,id) {
		$("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/adver/LinkAdver!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
		var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
		$("#dataContent").html('').append(loading_img);
		$.post(url,{"linkadverVO.id":id},function(response){
			if(response==-1) {
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				$("#dataContent").html(jQuery.trim(response));
			}
		});
	}

	function removelinkadver(path,id){
		var url = path +'/sdkinfo/LinkAdver!remove.action';
		jQuery.post(url, {"linkAdverVO.id":id },
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
	function updatelinkadver(path) {
		var id = $("#cid").val();

		var url = path +'/sdkinfo/LinkAdver!update.action';
		if(id==0) {
			url = path+'/sdkinfo/LinkAdver!save.action';
		}

		var name=$("#name").val();
		var adverId=$("#adverId").val();
		var linkType=$("#linkType").val();
		var redirectUrl=$("#redirectUrl").val();
		var createTime=$("#createTime").val();
		var status=$("#status").val();

		var clickType=$("#clickType").val();
		var cap=$("#cap").val();
		var cpm=$("#cpm").val();
		var notes=$("#notes").val();
		var iconUrl=$("#iconUrl").val();

		var asiaChecked=$("input[name^='asiaCheckBox']:checked").val([]);
		var southChecked=$("input[name^='southCheckBox']:checked").val([]);
		var northChecked=$("input[name^='northCheckBox']:checked").val([]);
		var europeChecked=$("input[name^='europeCheckBox']:checked").val([]);
		var oceaniaChecked=$("input[name^='oceaniaCheckBox']:checked").val([]);
		var africaChecked=$("input[name^='africaCheckBox']:checked").val([]);

		var asiaCheckValues="";
		for(var i=0;i<asiaChecked.length;i++){
			asiaCheckValues += asiaChecked[i].value +",";
		}
		for(var i=0;i<southChecked.length;i++){
			asiaCheckValues += southChecked[i].value +",";
		}
		for(var i=0;i<northChecked.length;i++){
			asiaCheckValues += northChecked[i].value +",";
		}
		for(var i=0;i<europeChecked.length;i++){
			asiaCheckValues += europeChecked[i].value +",";
		}
		for(var i=0;i<oceaniaChecked.length;i++){
			asiaCheckValues += oceaniaChecked[i].value +",";
		}
		for(var i=0;i<africaChecked.length;i++){
			asiaCheckValues += africaChecked[i].value +",";
		}

		var countryIds=asiaCheckValues;
		var advLinkmanId=$("#advLinkmanId").val();

		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {"linkAdverVO.id":id,
					"linkAdverVO.name":name,
					"linkAdverVO.adverId":adverId,
					"linkAdverVO.linkType":linkType,
					"linkAdverVO.redirectUrl":redirectUrl,
					"linkAdverVO.createTime":createTime,
					"linkAdverVO.status":status,
					"linkAdverVO.extensionContry":countryIds,
					"linkAdverVO.clickType":clickType,
					"linkAdverVO.cap":cap,
					"linkAdverVO.cpm":cpm,
					"linkAdverVO.iconUrl":iconUrl,
					"linkAdverVO.advLinkmanId":advLinkmanId,
					"linkAdverVO.notes":notes
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
