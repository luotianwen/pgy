$(function(){
});
function mainLoadData(url) {
	$("#load").css({"display":""});
	$("#containerData").html("");	
	$.post(url,{},function(response){
		if(response==-1) {
			showErrorToast("提示","系统出错，请重试或联系管理员");
		} else {
			$("#containerData").html(jQuery.trim(response));
		}
	});
}

function selBd(agentId) {
	var agentId = $("#agentId").val();
	if(agentId != 0) {
		jQuery("#customerId").html('<option value="0">--所有客户--</option>');
		jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		url = "getAllBd.action";
		jQuery.post(url,{"bdVO.agentId":agentId},function(response){
			jQuery("#bdId").html(jQuery.trim(response));
		});
	} else {
		jQuery("#bdId").html('<option value="0">--所有商务--</option>');
		jQuery("#customerId").html('<option value="0">--所有客户--</option>');
		jQuery("#projectId").html('<option value="0">--所有项目--</option>');
	}
}
	
function selCustomer() {
	var bdId = $("#bdId").val();
	if(bdId != 0) {			
		jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		url = "getAllCustomer.action";
		jQuery.post(url,{"customerVO.businessDeveloperId":bdId},function(response){
			jQuery("#customerId").html(jQuery.trim(response));
		});
	} else {
		jQuery("#customerId").html('<option value="0">--所有客户--</option>');
		jQuery("#projectId").html('<option value="0">--所有项目--</option>');
	}
}

function selProject() {		
	var customerId = $("#customerId").val();		
	if(customerId != 0) {
		url = "getAllProject.action";
		jQuery.post(url,{"projectVO.customerId":customerId},function(response){
			jQuery("#projectId").html(jQuery.trim(response));
		});
	} else {
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
	}
}	
function changeProject(){
	var customerId = $('#customerId').val(); 
	if(customerId != 0) {
		url = "getAllProject.action";
		jQuery.post(url,{"projectVO.customerId":customerId},function(response){
			jQuery("#projectId").html(jQuery.trim(response));
		});
	} else {
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
	}
}
function showErrorToast(title,message) {
	toastr.options = {"debug": false,"positionClass": "toast-top-right","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.error(title, message);
}
function showErrorToastMiddle(title,message) {
	toastr.options = {"debug": false,"positionClass": "toast-middle-middle","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.error(title, message);
}
function showInfoToastMiddle(message) {
	toastr.options = {"debug": false,"positionClass": "toast-top-right","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.info(message);
}
//中间显示提示信息
function showInfoToastMiddle(message) {
	toastr.options = {"debug": false,"positionClass": "toast-middle-middle","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.info(message);
}
/**
 * 弹出提示框
 * @param {} info
 * @return {}
 */
function sure(info){
    return confirm(info)?true:false;
}

function showInfoToastSure(message) {
	toastr.options = {"debug": false,"positionClass": "toast-middle-middle","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.info("<div>"+message+"</div><div><button type=\"button\" id=\"okBtn\" class=\"btn btn-primary\">确 定</button><button type=\"button\" id=\"cancelBtn\" class=\"btn\" style=\"margin: 0 8px 0 8px\">取 消</button></div>")
}

function doBack(queryType) {
	var url = "Project!query.action";
	jQuery.post(url,{"projectVO.queryType":queryType},function(data) {
		 $("#containerData").empty().html(data);
	});	
}
function goFirstPage() {
	var url = "Login!firstPage.action";
	jQuery.post(url,{},function(data) {
		 $("#containerData").empty().html(data);
	});	
}