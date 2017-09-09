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

//校验资源提交表单
function updateRes(path){
	var resId = $("#resId").val();
	var parentId = $("#parentId").val();
	var resName = $("#resName").val();
	var resDesc = $("#resDesc").val();
	var url = path+'/Res!save.action';
	if(resId>0) {
		url = path+'/Res!update.action';
	}
	if(VerifyUtil.verifyNull("resName")){
		showInfoToastMiddle("资源名称信息不能为空!");
		$("#resName").focus();
		return false;
	}
	if(VerifyUtil.verifyNull("resDesc")){
		showInfoToastMiddle("资源描述信息不能为空!");
		$("#resDesc").focus();
		return false;
	}
	jQuery.post(url, {"resVO.id":resId,"resVO.parentId":parentId,"resVO.resName":resName,"resVO.resDesc":resDesc}, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#surebtn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
	       		goBack(path,parentId);
	        }
   });	
}

function createRes(path,pid) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/Res!create.action', {"resVO.parentId":pid}, function(){
     		$modal.modal();
    	});
	  }, 100);
}

function modifyRes(path,id) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/Res!modify.action', {"resVO.id":id}, function(){
     		$modal.modal();
    	});
	  }, 100);
}

function goBack(path,pid) {
	var url = path+"/Res!list.action";
	jQuery.post(url,{"resVO.parentId":pid},function(data) {
		 $("#dataContent").empty().html(data);
	});	
}

//删除资源
function deleteRes(path, id,pid){
	bootbox.confirm("删除此资源将会影响所有资源的访问,确定是否要删除?", function(result) {
		if(result) {
			jQuery.post(path+"/Res!delete.action",{"resVO.id":id,"resVO.parentId":pid},function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
					$("#res_"+id).remove();
				}
			});
		}
	});
}