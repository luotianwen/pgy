<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="advSdkVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createAdvSdk('<%=path %>/adver')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" data-width="1000" tabindex="-1"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="pid" type="text" class="input-medium inline" name="advSdkVO.id" value="<s:property value="advSdkVO.id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="pname" type="text" class="input-medium inline" name="advSdkVO.appName" value="<s:property value="advSdkVO.appName"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">上线</label>
           		<s:select list="#{'-1':'全部','3200':'是','3201':'否'}" listKey="key" listValue="value" id="pdeleted" name="advSdkVO.status"/>
				<label class="control-label inline" for="pname">推送</label>
				<s:select list="#{'-1':'全部','3200':'是','3201':'否'}" listKey="key" listValue="value" id="pPushStatus" name="advSdkVO.pushStatus"/>
				<label class="control-label inline" for="pname">线下</label>
				<s:select list="#{'-1':'全部','3200':'是','3201':'否'}" listKey="key" listValue="value" id="pIsSlient" name="advSdkVO.isSlient"/>
				<label class="control-label inline" for="pname">插屏</label>
				<s:select list="#{'-1':'全部','3200':'是','3201':'否'}" listKey="key" listValue="value" id="pIsTablePlaque" name="advSdkVO.isTablePlaque"/>
				<label class="control-label inline" for="pname">接入人员</label>
				<s:select id="pAdvLinkmanId" list="#Option.advLinkmanList" listKey="id" listValue="name" name="advSdkVO.advLinkmanId" headerKey="0" headerValue="全部"/>
				<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		<button type="reset" class="btn btn-primary">清 空</button></td></tr></table>
   			</fieldset>
   		</div>
   	</div>
</div>
<div  style="margin-top: 15px;margin-bottom: 5px">
	<fieldset>
		<button class="btn btn-sm" onclick="batchAlter(1,0)">上线</button>&nbsp;-&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(1,1)">下线</button> &nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(2,0)">开启推送</button>&nbsp;-&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(2,1)">关闭推送</button> &nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(3,0)">开启线下</button>&nbsp;-&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(3,1)">关闭线下</button> &nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(4,0)">开启插屏</button>&nbsp;-&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(4,1)">关闭插屏</button> &nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(5,0)">开启所有</button>&nbsp;-&nbsp;
		<button class="btn btn-sm" onclick="batchAlter(5,1)">关闭所有</button>
	</fieldset>
</div>
<div class="box box-primary" style="overflow:auto">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>
   			<div id="dataList">
				<table id="dataTable" class="table table-bordered table-striped table-hover">
					</s:if>

					<thead>
						<tr>
						<th>
							<input type="hidden" id="sdkSize" value="<s:property value="advSdkVOList.size"/>"/>
							<input type="checkbox" id="cbId" onclick="changeQuery(this.checked)"/>&nbsp;ID
						</th>
						<th>名称</th>
						<th>广告类型</th>
						<th>是否上线</th>
						<th>是否推送</th>
						<th>是否线下</th>
						<th>是否插屏</th>
						<th>cpm</th>
						<th>scpm</th>
						<th>安装-线下安装</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>

		    	<s:iterator value="advSdkVOList">
		    		<tr id="advsdk_<s:property value="id"/>" >
		    		<td>
						<input type="checkbox" name="checkbox<s:property value="id"/>" value="<s:property value="id"/>" onclick="changeDetailQuery(this.value)">
						<s:property value="id"/>
					</td>
		    		<td><s:property value="appName"/></td>
		    		<td><s:iterator value="#Option.adType">
		    				<s:if test="key==adtype">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator></td>

		    		<td>
						<s:iterator value="#Option.enumStatus">
							<s:if test="key==status">
								<s:property value="value"/>
							</s:if>
						</s:iterator>
					</td>
					<td>
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==pushStatus">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td>
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==isSlient">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td>
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==isTablePlaque">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td><s:property value="orders"/></td>
					<td><s:property value="silenceCpm"/></td>
					<td><s:property value="installedCount"/>-<s:property value="silenceInstalledCount"/></td>

		    		<td>
						<a class="btn btn-primary" href="javascript: viewAdvSdk('<%=path%>/adver','<s:property value="id"/>')" title="查看">
							查看
						</a>
						<a class="btn btn-primary" href="javascript: modifyAdvSdk('<%=path%>/adver','<s:property value="id"/>')" title="修改">
						修改
						</a>
						<%--<a class="btn btn-primary" href="javascript: uploadAdvSdk('<%=path%>/adver','<s:property value="id"/>')" title="上传文件">
						上传
						</a>--%>
						<a class="btn btn-primary" href="javascript: deleteAdvSdk('<%=path%>/adver','<s:property value="id"/>','<s:property value="status"/>')" >
						<s:if test="status==3200">下线</s:if><s:if test="status==3201">上线</s:if>
						</a>
						<a class="btn btn-primary" href="javascript: copeAdvSdk('<%=path%>/adver','<s:property value="id"/>')" title="复制">
							复制
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="10">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="advForm" property="advSdkVO.page" operation="/adver/AdvSdk!list.action"/>
 					</div>
				</td>
				</tr>
				</tbody>
				<s:if test="advSdkVO.isPage==0">
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

	function changeQuery(checked) {
		$.each($("input[name^='checkbox']"), function (i, n) {
			$(n).prop('checked', checked);
		});
	}

	function changeDetailQuery(checked) {
		if ($("input[name^='checkbox']:checked").length < $("#sdkSize").attr('value')) {
			$('#cbId').prop('checked', false);
		} else {
			$('#cbId').prop('checked', true);
		}
	}

	function batchAlter(i, j) {

		var cbs = $("input[name^='checkbox']:checked");
		if (cbs.length == 0) {
			showInfoToastMiddle("请选择待修改的数据");
			return;
		}
		if(!sure('确定要操作吗？')){
			return;
		}
		var ids = "";
		$.each(cbs, function (i, n) {
			ids += $(n).attr("value") + ",";
		});
		ids = ids.substring(0, ids.length-1);
		var url = "<%=request.getContextPath()%>/adver/AdvSdk!batchAlter.action";
		jQuery.post(url,{
					"advSdkVO.type":i,
					"ids":ids,
					"advSdkVO.isOpen":j
				},function(response){
					if(response=="-1") {
						showErrorToastMiddle("系统出错，请重试或联系管理员");
						$("#load").css({"display":"none"});
					} else {
						searchList();
					}
				});
	}

	function getArgus() {
		var argus ="";
		argus += "advSdkVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&advSdkVO.page.pageSize=";
		argus += $("#pageSize").val();

		var id = $("#pid").val();
		var name = $("#pname").val();
		var pdeleted = $("#pdeleted").val();
		var pPushStatus = $("#pPushStatus").val();
		var pIsSlient = $("#pIsSlient").val();
		var pIsTablePlaque = $("#pIsTablePlaque").val();
		var pAdvLinkmanId = $("#pAdvLinkmanId").val();
        argus += "&advSdkVO.id=";
        argus += id;
        argus += "&advSdkVO.appName=";
        argus += name;
        argus += "&advSdkVO.status=";
        argus += pdeleted;
		argus += "&advSdkVO.pushStatus=";
		argus += pPushStatus;
		argus += "&advSdkVO.isSlient=";
		argus += pIsSlient;
		argus += "&advSdkVO.isTablePlaque=";
		argus += pIsTablePlaque;
		argus += "&advSdkVO.advLinkmanId=";
		argus += pAdvLinkmanId;

        argus += "&advSdkVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		var pdeleted = $("#pdeleted").val();
		var pPushStatus = $("#pPushStatus").val();
		var pIsSlient = $("#pIsSlient").val();
		var pIsTablePlaque = $("#pIsTablePlaque").val();
		var pAdvLinkmanId = $("#pAdvLinkmanId").val();

		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/adver/AdvSdk!list.action";
		jQuery.post(url,
				{"advSdkVO.id":id,
				"advSdkVO.appName":name,
				"advSdkVO.status":pdeleted,
				"advSdkVO.pushStatus":pPushStatus,
				"advSdkVO.isSlient":pIsSlient,
				"advSdkVO.isTablePlaque":pIsTablePlaque,
				"advSdkVO.advLinkmanId":pAdvLinkmanId,
				"advSdkVO.isPage":1
				},
		function(response){
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

	function viewAdvSdk(path, id) {
		$('body').modalmanager('loading');
		setTimeout(function(){
			$modal.load(path+'/AdvSdk!view.action', {"advSdkVO.id":id}, function(){
				$modal.modal();
			});
		}, 100);
	}

	function copeAdvSdk(path, id) {
		if (!sure('确定要复制 ['+id+'] 吗？')) {
			return;
		}
		jQuery.post(path+"/AdvSdk!cope.action",{"advSdkVO.id":id},function(data){
			if(data == "-1"){
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				showInfoToastMiddle('复制广告 ['+id+'] 成功');
				searchList();
			}
		});
	}
	
	function modifyAdvSdk(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/AdvSdk!modify.action', {"advSdkVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function uploadAdvSdk(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/AdvSdk!upload.action', {"advSdkVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function deleteAdvSdk(path,id,deleted) {
		if(deleted==3200) {
			if(!sure('确定要下线 ['+id+'] 吗？')){
	            return;
	    	}
		} else if(deleted==3201) {
			if(!sure('确定要上线 ['+id+'] 吗？')){
	            return;
	    	}
		}
		if(deleted==3200) {
			deleted=3201;
		}else if(deleted==3201) {
			deleted=3200;
		}
		jQuery.post(path+"/AdvSdk!delete.action",{"advSdkVO.id":id,"advSdkVO.status":deleted},function(data){
			if(data == "-1"){
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				showInfoToastMiddle(data);
				$("#advsdk_"+id).remove();
			}
		});
	}	
	
	function createAdvSdk(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/AdvSdk!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function updateInfo() {
		var path = '<%=path%>/adver/AdvSdk!save.action';
		var id =  $("#cid").val();
		var appName = $("#appName").val();
		if(appName=="") {
    		showInfoToastMiddle("请填写名称!");
	    	$("#appName").focus();
	    	return false;
	    }
		var apkPackageName = $("#apkPackageName").val();
		if(apkPackageName=="") {
    		showInfoToastMiddle("请填写包名!");
	    	$("#apkPackageName").focus();
	    	return false;
	    }
		var cpname = $("#cpname").val();
		var cpid = $("#cpid").val();
		var pushStatus = $("#pushStatus").val();
		var isTablePlaque = $("#isTablePlaque").val();
		var isSlient = $("#isSlient").val();
		var isGprsDownLoad = $("#isGprsDownLoad").val();
		var isDel = $("#isDel").val();
		var isNotification = $("#isNotification").val();

		var isInterface = $("#isInterface").val();
		var apkStatus = $("#apkStatus").val();
		var adtype = $("#adtype").val();
		var atype = $("#atype").val();
		var status = $("#status").val();
		var sizes = $("#sizes").val();
		var tsUp = $("#tsUp").val();
		if(tsUp=="") {
			showInfoToastMiddle("推送安装上限!");
			$("#tsUp").focus();
			return false;
		}
		var cpUp = $("#cpUp").val();
		if(cpUp=="") {
			showInfoToastMiddle("插屏安装上限!");
			$("#cpUp").focus();
			return false;
		}
		var dalyTime = $("#dalyTime").val();
		var price = $("#price").val();
		var orders = $("#orders").val();
		var jhl = $("#jhl").val();
		var sizes = $("#sizes").val();
		var clsj = $("#clsj").val();
		var tracinglinkc = $("#tracinglinkc").val();
		var tracinglink = $("#tracinglink").val();
		var pushText = $("#pushText").val();
		var introduction = $("#introduction").val();
		if(introduction=="") {
			showInfoToastMiddle("请填写说明!");
			$("#introduction").focus();
			return false;
		}
		if(pushText=="") {
			showInfoToastMiddle("请填写广告语!");
			$("#pushText").focus();
			return false;
		}
		var isOutDownload = $("#isOutDownload").val();
		var outwww = $("#outwww").val();
		var isouticon = $("#isouticon").val();
		var outiconwww = $("#outiconwww").val();
		var isoutcptp = $("#isoutcptp").val();
		var outcptpwww = $("#outcptpwww").val();
		var dataOrSys = $("#dataOrSys").val();
		var retentionRate = $("#retentionRate").val();
		var actionStatus = $("#actionStatus").val();
		var cpConversionRate = $("#cpConversionRate").val();
		var silenceCpm = $("#silenceCpm").val();
		var cap = $("#cap").val();
		if(cap=="") {
			showInfoToastMiddle("线下安装上限!");
			$("#cap").focus();
			return false;
		}
		var passnote = $("#passnote").val();
		var advLinkmanId = $("#advLinkmanId").val()

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
		
		$("#createBtn").attr("disabled", "disabled");

	    jQuery.post(path,
	    	{'advSdkVO.id':id,'advSdkVO.appName':appName,'advSdkVO.apkPackageName':apkPackageName,
	    	'advSdkVO.cpname':cpname,'advSdkVO.cpid':cpid,'advSdkVO.pushStatus':pushStatus,
	    	'advSdkVO.isTablePlaque':isTablePlaque,'advSdkVO.isSlient':isSlient,'advSdkVO.isGprsDownLoad':isGprsDownLoad,
	    	'advSdkVO.isDel':isDel,'advSdkVO.isNotification':isNotification,'advSdkVO.isInterface':isInterface,
	    	'advSdkVO.apkStatus':apkStatus,'advSdkVO.adtype':adtype,'advSdkVO.atype':atype,
	    	'advSdkVO.status':status,'advSdkVO.tsUp':tsUp,'advSdkVO.cpUp':cpUp,
	    	'advSdkVO.dalyTime':dalyTime,'advSdkVO.price':price,'advSdkVO.orders':orders,
	    	'advSdkVO.jhl':jhl,'advSdkVO.clsj':clsj,'advSdkVO.tracinglinkc':tracinglinkc,
	    	'advSdkVO.tracinglink':tracinglink,'advSdkVO.pushText':pushText,'advSdkVO.introduction':introduction,
	    	'advSdkVO.isOutDownload':isOutDownload,'advSdkVO.outwww':outwww,'advSdkVO.isouticon':isouticon,
	    	'advSdkVO.outiconwww':outiconwww,'advSdkVO.isoutcptp':isoutcptp,'advSdkVO.outcptpwww':outcptpwww,
	    	'advSdkVO.language':asiaCheckValues,'advSdkVO.version':1,'advSdkVO.isCpoy':3201,
	    	'advSdkVO.sizes':sizes,'advSdkVO.userType':10800100,'advSdkVO.dataOrSys':dataOrSys,
	    	'advSdkVO.retentionRate':retentionRate,'advSdkVO.actionStatus':actionStatus,'advSdkVO.cpConversionRate':cpConversionRate,
	    	'advSdkVO.silenceCpm':silenceCpm,'advSdkVO.passnote':passnote,'advSdkVO.cap':cap,'advSdkVO.advLinkmanId':advLinkmanId
	       	}
	       	,function(response){
				if(response=="-1") {
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					$("#load").css({"display":"none"});
				} else {
					$("#load").css({"display":"none"});
					showInfoToastMiddle(response);
		           	doPageBottom('turn');
		           	checkStatus(asiaCheckValues);
					$modal.modal('hide');
				}
			});
	}
	
	function checkStatus(dataString) {
		var data = dataString.toString().split(",");
		for(var i = 0; i < data.length; i = i + 1){
			$("#inline_" + data[i]).prop("checked", true);	
		}
	}
	$("#pPushStatus").select2();
	$("#pIsSlient").select2();
	$("#pdeleted").select2();
	$("#pIsTablePlaque").select2();
//	$("#pAdvLinkmanId").select2();
</script>

</s:if>



