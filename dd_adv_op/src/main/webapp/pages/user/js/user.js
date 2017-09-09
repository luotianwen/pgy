function resetForm(){
	$("#name").val("");
	$("#type").val("-1");
}
function checkPasswd(val){
	if(val != ""){
		$.post("user/User!passwd.action", {passwd:val}, function(data){
			if(data == "0"){
				$("#oldpwd").focus();
				$("#surebtn").attr("disabled", "disabled");
				showInfoToastMiddle("请输入您当前的密码!");				
			}else{
				$("#passwdTips").hide();
				$("#surebtn").removeAttr('disabled');
			}
		});
	}
}
function passwd(){
	var oldpwd = $("#oldpwd").val();
	var newpwd = $("#newpwd").val();
	var cmfpwd = $("#cmfpwd").val();
	if(oldpwd == ""){
		showInfoToastMiddle("请输入您当前的密码!");
		$("#oldpwd").focus();
		return;
	}
	if(newpwd == ""){
		showInfoToastMiddle("请输入新密码!");
		$("#newpwd").focus();
		return;
	}
	if(newpwd.length < 6 || newpwd.length > 15){
		showInfoToastMiddle("密码长度在6-15个字符之间!");
		$("#newpwd").focus();
		return;
	}
	if(newpwd != cmfpwd){
		showInfoToastMiddle("你输入的确认密码不一致!");
		$("#cmfpwd").focus();
		return;
	}
	
	$.post("user/User!updatePasswd.action", {newpass:newpwd}, function(data){
		if(data == "0"){
			showErrorToastMiddle("修改密码失败,请重新尝试!");
			return;
		}else{
			showInfoToastMiddle("修改密码成功!");
			$("#oldpwd").val("");
			$("#newpwd").val("");
			$("#cmfpwd").val("");
			return;
		}
	});
}

//授权角色
function setPermission(path, id, chkbox){
	var ret = 0;
	if($(chkbox).prop("checked")==true){
		ret = 1;
	}
	var puid = $("#puid").val();
	$.post(path+"/user/User!setPermission.action", {puid:puid, ret:ret, roleid:id}, function(data){
		if(data == "0"){
			showErrorToast("授权角色发生错误,请联系管理员!");
			return;
		}
	});
}

//操作
function oper(path, id, status){
	bootbox.confirm("你确定要执行此项操作吗?", function(result) {
		if(result) {
			jQuery.post(path+"/User!updateStatus.action",{"userVO.id":id,"userVO.status":status},function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
			});
		}
	});
}
//删除
function deleteUser(path, id, name){
	bootbox.confirm("确定要删除 ["+name+"] 吗?", function(result) {
		if(result) {
			jQuery.post(path+"/User!delete.action",{"userVO.id":id},function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
			});
		}
	});
}
//校验用户表单提交
function updateUser(path){
	var loginId = $("#loginId").val();
	var passwd = $("#passwd").val();
	var realName = $("#realName").val();
	var id = $("#id").val();

	var url = path +'/user/User!update.action';
	if(id==0) {
		url = path+'/user/User!save.action';
	}
	if(VerifyUtil.verifyNull("realName")){
		showInfoToastMiddle("用户姓名不能为空!");
		$("#realName").focus();
		return false;
	}
	if(VerifyUtil.verifyNull("loginId")){
		showInfoToastMiddle("用户账号信息不能为空!");
		$("#loginId").focus();
		return false;
	}
	/**if($("#loginId").val().length < 6 || $("#loginId").val().length > 15){
		alert("用户账号长度在6-15个字符之间!");
		$("#loginId").focus();
		return false;
	}**/
	if(VerifyUtil.verifyNull("passwd")){
		showInfoToastMiddle("密码信息不能为空!");
		$("#passwd").focus();
		return false;
	}
	if($("#passwd").val().length < 6 || $("#passwd").val().length > 15){
		showInfoToastMiddle("密码长度在6-15个字符之间!");
		$("#passwd").focus();
		return false;
	}
	jQuery.post(url, {"userVO.id":id,"userVO.loginId":loginId,"userVO.passwd":passwd,"userVO.realName":realName}, 
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

function createUser(path) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/User!create.action', {}, function(){
     		$modal.modal();
    	});
	  }, 100);
}

function modifyUser(path,id) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/User!modify.action', {"userVO.id":id}, function(){
     		$modal.modal();
    	});
	  }, 100);
}

function grantRole(path,id) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/User!grantRole.action', {"userVO.id":id}, function(){
     		$modal.modal();
    	});
	  }, 100);
}

//检测用户是否存在
function checkExisitPU(val){
	if (VerifyUtil.verifyNull("loginId")){
		showInfoToastMiddle("<font color=red>请填写登陆账号!</font>");
		$("#loginId").focus();
		$("#surebtn").attr("disabled","disabled");
		return;
	} else{
		$("#surebtn").removeAttr('disabled');
	}
	$.post("user/User!exist.action",{loginId:val},function(data){
		if (data == "0"){
			showInfoToastMiddle("<font color=red>登录账号已存在</font>");
			$("#loginId").focus();
			$("#surebtn").attr("disabled","disabled");
		}else{
			$("#surebtn").removeAttr('disabled');
		}
	})
};


function getArgus() {		
	var argus ="";
	argus += "userVO.page.pageNum=";
	argus += $("#pageNum").val();
	argus += "&userVO.page.pageSize=";
	argus += $("#pageSize").val();
	var name = $("#pname").val();		
    argus += "&userVO.loginId=";
    argus += name;
    argus += "&userVO.isPage=1";
	return argus;
}
function searchList(path) {		
	$("#load").css({"display":""});
	$("#dataTable").html("");  		
	var pname = $("#pname").val();
	url = path+"/user/User!list.action";
	$.post(url,{"userVO.loginId":pname,"userVO.isPage":1},function(response){
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