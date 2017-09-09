<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="linkads2VO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:99%"><tr><td>
		   		<input class="btn btn-primary" type="button" onclick="createlinkads2('<%=path %>/adver')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal  fade"  data-width="1000"  tabindex="-1"></div>
            	<label class="control-label inline" for="pname">ID</label>
					<input id="qid" type="text" class="input-medium inline"   style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<label class="control-label inline" for="pname">名称</label>
   			    	<input id="qname" type="text" class="input-medium inline"    style="width: 100px">

					<label class="control-label inline" for="pname">状态</label>
					<s:select list="#{'-1':'全部','1':'是','0':'否'}" listKey="key" listValue="value" id="pstatus"  />

					<label class="control-label inline" for="pname">网络类型</label>
					<s:select list="#{'-1':'全部','1':'wifi','0':'gprs','2':'不限'}" listKey="key" listValue="value" id="plinkAdType"  />

					<label class="control-label inline" for="pname">推广类型</label>
					<s:select list="#{'-1':'全部','1':'定时','2':'桌面','3':'push','4':'浏览器'}" listKey="key" listValue="value" id="pclickType"  />

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
						<th >名称</th>
						<th >广告主</th>
						<th >链接广告类型</th>
						<th >创建时间</th>
						<th >状态</th>
						<th >cpm</th>
						<th >网络类型</th>
						<th >推广类型</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="linkads2VOList">  
		    		<tr >
						<td><s:property value="id"/></td>
						<td><s:property value="name"/></td>
						<td>
						<s:iterator value="#Option.adverList">
							<s:if test="adverId==id">
								<s:property value="name"/>
							</s:if>
						</s:iterator>
						</td>
						<td>
						<s:iterator value="#Option.linkType">
							<s:if test="linkType==key">
								<s:property value="value"/>
							</s:if>
						</s:iterator>
						 </td>
						<td><s:property value="createTime"/></td>
						<td>
							<s:if test="status==0">关闭</s:if>
							<s:if test="status==1">打开</s:if>
						</td>
						<td><s:property value="cpm"/></td>

						<td>
						<s:iterator value="#Option.netType">
							<s:if test="linkAdType==key">
								<s:property value="value"/>
							</s:if>
						</s:iterator>
						<td>
						 <s:iterator value="#Option.clickType">
							<s:if test="clickType==key">
								<s:property value="value"/>
							</s:if>
						 </s:iterator>
						</td>
		    		<td>
	    				<%--<a class="btn btn-info" href="javascript: detaillinkads2('<%=path%>/adver/Linkads2!detail.action','<s:property value="id"/>')" title="详情">
						详情
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;--%>
						<a class="btn btn-warning" href="javascript: modifylinkads2('<%=path%>/adver','<s:property value="id"/>')" title="修改">
						修改
						</a>
							&nbsp;&nbsp;|&nbsp;&nbsp;
							<a class="btn btn-primary" href="javascript: copeLinkads2('<%=path%>/adver','<s:property value="id"/>')" title="复制">
								复制
							</a>
					</td>
		    		</tr>
				</s:iterator>
				<tr>
					<td colspan="8">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="linkads2VO.page" operation="/adver/Linkads2!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				 	<s:if test="linkads2VO.isPage==0">
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
		argus += "linkads2VO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&linkads2VO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();
		var pstatus = $("#pstatus").val();
		var plinkAdType = $("#plinkAdType").val();
		var pclickType = $("#pclickType").val();

        argus += "&linkads2VO.id=";
        argus += id;
        argus += "&linkads2VO.name=";
        argus += name;
		argus += "&linkads2VO.status=";
		argus += pstatus;
		argus += "&linkads2VO.linkAdType=";
		argus += plinkAdType;
		argus += "&linkads2VO.clickType=";
		argus += pclickType;
        argus += "&linkads2VO.isPage=1";
		return argus;
	}
	function copeLinkads2(path, id) {
		if (!sure('确定要复制 ['+id+'] 吗？')) {
			return;
		}
		jQuery.post(path+"/Linkads2!copy.action",{"linkads2VO.id":id},function(data){
			if(data == "-1"){
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				showInfoToastMiddle('复制广告 ['+id+'] 成功');
				searchList();
			}
		});
	}

	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();
		var pstatus = $("#pstatus").val();
		var plinkAdType = $("#plinkAdType").val();
		var pclickType = $("#pclickType").val();

		url = "<%=request.getContextPath()%>/adver/Linkads2!list.action";
		jQuery.post(url,{
			"linkads2VO.id":id,"linkads2VO.name":name,
			"linkads2VO.clickType":pclickType,"linkads2VO.linkAdType":plinkAdType,
			"linkads2VO.status":pstatus,
			"linkads2VO.isPage":1
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
	function modifylinkads2(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Linkads2!modify.action', {"linkads2VO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createlinkads2(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Linkads2!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function detaillinkads2(url,id) {
		$("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/adver/Linkads2!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
		var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
		$("#dataContent").html('').append(loading_img);
		$.post(url,{"linkads2VO.id":id},function(response){
			if(response==-1) {
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				$("#dataContent").html(jQuery.trim(response));
			}
		});
	}
	//修改or新增
	function updatelinkads2(path) {
		var id = $("#cid").val();

		var url = path +'/adver/Linkads2!update.action';
		if(id==0) {
			url = path+'/adver/Linkads2!save.action';
		}

		var name=$("#name").val();
		var adverId=$("#adverId").val();
		var linkType=$("#linkType").val();
		var redirectUrl=$("#redirectUrl").val();
		var createTime=$("#createTime").val();
		var status=$("#status").val();
		var operator=$("#operator").val();

		var linkAdType=$("#linkAdType").val();
		var clickType=$("#clickType").val();
		var cap=$("#cap").val();
		var cpm=$("#cpm").val();
		var notes=$("#notes").val();

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
		jQuery.post(url, {"linkads2VO.id":id,
					"linkads2VO.name":name,
					"linkads2VO.adverId":adverId,
					"linkads2VO.linkType":linkType,
					"linkads2VO.redirectUrl":redirectUrl,
					"linkads2VO.createTime":createTime,
					"linkads2VO.status":status,
					"linkads2VO.operator":operator,
					"linkads2VO.countryIds":countryIds,
					"linkads2VO.linkAdType":linkAdType,
					"linkads2VO.clickType":clickType,
					"linkads2VO.cap":cap,
					"linkads2VO.cpm":cpm,
					"linkads2VO.advLinkmanId":advLinkmanId,
					"linkads2VO.notes":notes
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
