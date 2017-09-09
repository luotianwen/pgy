$(function(){
});
function mainLoadData(url,flag) {
	switch (flag) {
		case 110:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li class=\"active\">首页</li>");break;
		case 111:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>明细数据查询</li><li class=\"active\">销量数据</li>");break;
		case 112:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>明细数据查询</li><li class=\"active\">活跃数据</li>");break;
		case 113:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>明细数据查询</li><li class=\"active\">下发数据</li>");break;
		case 118:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>明细数据查询</li><li class=\"active\">DDL数据</li>");break;
		case 119:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>明细数据查询</li><li class=\"active\">链接数据</li>");break;
		case 210:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据录入</li><li class=\"active\">产品列表</li>");break;
		case 211:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据录入</li><li class=\"active\">广告收益录入</li>");break;
		case 212:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据录入</li><li class=\"active\">项目第三方收益录入</li>");break;
		case 213:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据录入</li><li class=\"active\">项目转化率设置</li>");break;
		case 214:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据录入</li><li class=\"active\">国家设置</li>");break;
		case 215:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据录入</li><li class=\"active\">运营商设置</li>");break;
		case 308:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">累计数据查询</li>");break;
		case 309:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">总览数据查询</li>");break;
		case 310:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">项目数据总览</li>");break;
		case 311:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">项目数据查询</li>");break;
		case 312:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">广告数据查询</li>");break;
		case 313:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">项目收益查询</li>");break;
		case 314:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">广告收益查询</li>");break;
		case 315:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">留存数据</li>");break;
		case 316:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">同步数据</li>");break;
		case 317:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">DDL点击数据</li>");break;
		case 318:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">DDL销量数据</li>");break;
		case 319:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">版本数据</li>");break;
		case 320:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">项目效果分析</li>");break;
		case 321:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">广告效果分析</li>"); break;
		case 322:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">链接广告数据</li>");break;
		case 323:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">广告填充率</li>");break;
		case 324:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">项目数据</li>");break;
		case 325:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">项目排重数据</li>");break;
		case 326:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">守护数据</li>");break;
		case 327:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>数据统计</li><li class=\"active\">三方SDK项目统计</li>");break;
		case 411:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>系统管理</li><li class=\"active\">用户管理</li>");break;
		case 413:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>系统管理</li><li class=\"active\">修改密码</li>");break;
		case 421:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>系统管理</li><li class=\"active\">角色管理</li>");break;
		case 431:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>系统管理</li><li class=\"active\">资源管理</li>");break;
		case 510:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">SDK管理</li>");break;
		case 511:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">多插件管理</li>");break;
		case 512:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">引导配置</li>");break;
		case 513:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">静默配置</li>");break;
		case 514:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">项目配置</li>");break;
		case 515:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">项目广告配置</li>");break;
		case 516:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">静默插件配置</li>");break;
		case 517:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">三方SDK管理</li>");break;
		case 518:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>SDK管理</li><li class=\"active\">三方SDK项目管理</li>");break;
		case 610:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>广告管理</li><li class=\"active\">广告主管理</li>");break;
		case 611:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>广告管理</li><li class=\"active\">链接广告管理</li>");break;
		case 612:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>广告管理</li><li class=\"active\">广告管理</li>");break;
		case 613:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>广告管理</li><li class=\"active\">接入人员管理</li>");break;
		case 614:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>链接广告管理</li><li class=\"active\">配置管理</li>");break;
		case 615:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>链接广告管理</li><li class=\"active\">广告管理</li>");break;
		case 616:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>链接广告管理</li><li class=\"active\">链接域名管理</li>");break;
		case 617:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>广告管理</li><li class=\"active\">视频管理</li>");break;


		case 700:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>DDL管理</li><li class=\"active\">渠道管理</li>");break;
		case 701:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>DDL管理</li><li class=\"active\">产品管理</li>");break;
		case 702:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>DDL管理</li><li class=\"active\">项目管理</li>");break;
		case 703:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>DDL管理</li><li class=\"active\">域名管理</li>");break;
		case 704:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>DDL管理</li><li class=\"active\">DDL重复点击</li>");break;

		case 800:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>订阅管理</li><li class=\"active\">订阅信息</li>");break;

		case 900:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>线下管理</li><li class=\"active\">线下JAR</li>");break;
		case 901:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>线下管理</li><li class=\"active\">线下SDK</li>");break;
		case 902:
			$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>线下管理</li><li class=\"active\">线下APK</li>");break;

		case 1001:
            $("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>IFRAME</li><li class=\"active\">IFRAME下发数据明细</li>");break;
        case 1002:
            $("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>IFRAME</li><li class=\"active\">IFRAME激活数据明细</li>");break;
		// case 800:
		// 	$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>电商数据录入</li><li class=\"active\">桌面配置</li>");break;
		// case 801:
		// 	$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>电商数据录入</li><li class=\"active\">链接配置</li>");break;
		// case 802:
		// 	$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>电商数据录入</li><li class=\"active\">sdk配置</li>");break;
	}

	var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
	$("#dataContent").html('').append(loading_img);
	$.post(url,{},function(response){
		if(response==-1) {
			showErrorToast("提示","系统出错，请重试或联系管理员");
		} else {
			$("#dataContent").html(jQuery.trim(response));
		}
	});
}
function loading(id) {
	var loading_img = $("<div class='container-fluid' style='text-align:center;margin-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
	$("#"+id).html(loading_img);
}
function showErrorToast(title,message) {
	toastr.options = {"debug": false,"positionClass": "toast-top-right","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.error(title, message);
}
function showErrorToastMiddle(title,message) {
	toastr.options = {"debug": false,"positionClass": "toast-middle-middle","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.error(title, message);
}
function showInfoToast(title,message) {
	toastr.options = {"debug": false,"positionClass": "toast-top-right","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.info(message);
}
//中间显示提示信息
function showInfoToastMiddle(title,message) {
	toastr.options = {"debug": false,"positionClass": "toast-middle-middle","onclick": null,"fadeIn": 300,"fadeOut": 1000,"timeOut": 5000,"extendedTimeOut": 1000}
	toastr.info(message,title);
}
/**
 * 弹出提示框
 * @param {} info
 * @return {}
 */
function sure(info){
    return confirm(info)?true:false;
}

function doBack(path) {
	jQuery.post(path,{},function(data) {
		 $("#containerData").empty().html(data);
	});	
}