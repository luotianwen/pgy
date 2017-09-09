<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>

<s:hidden id="cid" name="advSdkVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>上传文件</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<table class="table">
				<tr>
				<td class="table_td_title">文件</td>	
				<td>
					<s:select list="#{'1':'APK文件','2':'icon图片','3':'插屏图片' }" listKey="key" listValue="value" id="fileType" name="advSdkVO.fileType"></s:select>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">文件</td>	
				<td>
					<input class="btn" type="file" id="file" name="file" />
					<img src="<%=request.getContextPath()%>/img/ajax-loader.gif" id="loading" style="display: none;">
				</td>
				</tr>
				<tr>
				<td colspan="2">
					<input id="uploadBtn" type="button" class="btn btn-primary" value="上传" onclick="return ajaxFileUpload();">
				</td>
				</tr>
			</table>
	</form>
</div>
<div class="modal-footer">
</div> 
<script type="text/javascript">

	
function ajaxFileUpload()
{
	$("#load").ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
    });//文件上传完成将图片隐藏起来  
    var file = $("#file").val();
    var id = $("#cid").val();
    var fileType = $("#fileType").val();
    if(file=="") {
    	showInfoToastMiddle("请选择上传文件包!");
    	$("#file").focus();
    	return false;
    }
    jQuery("#uploadBtn").attr('disabled', 'disabled');	 
    $.ajaxFileUpload({
    	url:'<%=path%>/adver/AdvSdk!saveUpload.action',//用于文件上传的服务器端请求地址
        secureuri:false,//一般设置为false
        fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
        dataType: 'json',//返回值类型 一般设置为json
        data:{'advSdkVO.id':id,'advSdkVO.fileType':fileType},
        success: function (data, status) {
            //服务器成功响应处理函数 
            showInfoToastMiddle(data.msg);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
            jQuery("#uploadBtn").removeAttr('disabled');
        },
        error: function (data, status, e) {
            	//服务器响应失败处理函数
       		alert(e);
        }
    });
    return false;
}
</script>
