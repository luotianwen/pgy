//授权资源
function setRes(path, id, chkbox, level){
	var ret = 0;
	if($(chkbox).prop("checked")==true){
		ret = 1;
	}
	var roleid = $("#roleid").val();
	if(level == 2){
		var myattr = $(chkbox).attr("myattr").toString();
		var pid = myattr.substr(3);
		if(ret == 1){
			$("#res_" + pid).prop("checked", true);
			$.post(path+"/user/Role!setRes.action", {roleid:roleid, ret:ret, resid:pid}, function(data){
				if(data == "0"){
					alert("授权角色发生错误,请联系管理员!");
					return;
				}
			});
		}else{
			//当子选项全部取消选择时，父选项也应该被取消选择
			var result = false;
			$("input[myattr='"+myattr+"']").each(function(i, o){
				if($(this).prop("checked") == true){
					result = true;
					return;
				}
			});
			if(result == false){
				$("#res_" + pid).prop("checked", false);
				$.post(path+"/user/Role!setRes.action", {roleid:roleid, ret:0, resid:pid}, function(data){
					if(data == "0"){
						alert("授权角色发生错误,请联系管理员!");
						return;
					}
				});
			}
		}
	}
	$.post(path+"/user/Role!setRes.action", {roleid:roleid, ret:ret, resid:id}, function(data){
		if(data == "0"){
			alert("授权角色发生错误,请联系管理员!");
			return;
		}
	});
}
//校验角色表单
function updateRole(path){
	var id=$("#roleId").val();
	var roleName = $("#roleName").val();
	var roleDesc = $("#roleDesc").val();
	if(VerifyUtil.verifyNull("roleName")){
		alert("角色名称信息不能为空!");
		$("#roleName").focus();
		return false;
	}
	if(VerifyUtil.verifyNull("roleDesc")){
		alert("角色描述信息不能为空!");
		$("#roleDesc").focus();
		return false;
	}
	var url = path +'/user/Role!update.action';
	if(id==0) {
		url = path+'/user/Role!save.action';
	}
	jQuery.post(url, {"roleVO.id":id,"roleVO.roleName":roleName,"roleVO.roleDesc":roleDesc}, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#surebtn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
	       		mainLoadData(path+'/user/Role!list.action')
	        }
   });
	return true;
}
//删除角色
function deleteRole(path, id){
	bootbox.confirm("删除角色将影响系统权限,你确定要执行此项操作吗?", function(result) {
		if(result) {
			jQuery.post(path+"/Role!delete.action",{"roleVO.id":id},function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
					$("#role_"+id).remove();
				}
			});
		}
	});
}
function createRole(path) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/Role!create.action', {}, function(){
     		$modal.modal();
    	});
	  }, 100);
}

function modifyRole(path,id) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/Role!modify.action', {"roleVO.id":id}, function(){
     		$modal.modal();
    	});
	  }, 100);
}

function grantRes(path,id) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/Role!grantRes.action', {"roleVO.id":id}, function(){
     		$modal.modal();
    	});
	  }, 100);
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

