function setBg(tr){
	$(tr).addClass("mybg");
}
function clearBg(tr){
	$(tr).removeClass("mybg");
}

var VerifyUtil = {
	verifyNull:function(id){
		return $("#"+id).val() == "";
	},
	verifySelectIsNull:function(id){
		return $("#"+id).val()== -1 || $("#"+id).val()=="";
	},
	verifyRadio:function(name){
		var flag = false;
		$("input[name='"+name+"'][type='radio']").each(function(i,o){
			if($(this).attr("checked") == true){
				flag = true;
				return;
			}
		});
		return flag;
	},
	verifyCheckBox:function(name){
		var flag = false;
		$("input[name='"+name+"'][type='checkbox']").each(function(i,o){
			if($(this).attr("checked")==true){
				flag = true;
				return;
			}
		});
		return flag;
	},
	verifyNumber:function(id){
		var val = $("#"+id).val();
		if ((val == null) || (val == "")) 
			return true;
		return (/^\d{1,}$/.test(val))==true;
	}
};
/**
 * 获取单选按钮的值
 * @param name
 * @return
 */
function GetRadioValue(name){
	var ret = "";
	$("input[type='radio'][name='"+name+"']").each(function(i, o){
		if($(this).attr("checked") == true){
			ret = $(this).val();
			return false;
		}
	});
	return ret;
}
function GetCheckBoxValue(name){
	var ret = "";
	$("input[type='checkbox'][name='"+name+"']").each(function(i, o){
		if($(this).attr("checked") == true){
			ret += $(this).val() + ",";
		}
	});
	if(ret.length > 0){
		ret = ret.substring(0, ret.length-1);
	}
	return ret;
}
function checkAll(checkbox, checkboxname){
	if($(checkbox).attr("checked") == true){
		$("input[type='checkbox'][name='" + checkboxname + "']").attr("checked", true);
	}else{
		$("input[type='checkbox'][name='" + checkboxname + "']").attr("checked", false);
	}
}
/**
 * 提交表单
 * @param action
 * @return
 */
function submitForm(action){
	if (action != ""){
		document.forms[0].action = action;
	}
	document.forms[0].submit();
}
/**
 * 分页跳转
 * @param currentPage
 * @return
 */
function gotoPage(currentPage){
	$("#cp").val(currentPage);
	submitForm("");
}

/**
 * 根据代理商返回拓展人员列表
 * @param val		agentId
 * @param nextid	selectId
 * @return
 */
function GetBeByAgent(val, nextid){
	if(val != -1){
		$.post("be_queryByAgentId.action", {agnetId:val}, function(data){
			var html = "<option value='-1'>请选择...</option>";
			$.each(data, function(i, o){
				html += "<option value=" + o.id + ">" + o.name + "</option>";
			}); 
			$("#"+nextid).empty().append(html);
		}, "json");
	}
}
/**
 * 根据拓展人员查询客户列表
 * @param val
 * @param nextid
 * @return
 */
function GetCustomerByBe(val, nextid){
	if(val != "" && val != -1){
		$.post("cm_getCustomerByBe.action", {beid: val}, function(data){
			var html = "";
			$.each(data, function(i, o){
				html += "<option value=" + o.id + ">" + o.name + "</option>";
			});
			$("#"+nextid).empty().append(html);
		}, "json");
	}
}
