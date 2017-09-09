<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="pcustomerVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:99%"><tr><td>
		   		<input class="btn btn-primary" type="button" onclick="createpcustomer('<%=path %>/adver')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal  fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">链接ID</label>
   				<input id="qid" type="text" class="input-medium inline" name="pcustomerVO.id" value="<s:property value="pcustomerVO.id"/>" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		<label class="control-label inline" for="pname">渠道ID</label>
   				<input id="qname" type="text" class="input-medium inline" name="pcustomerVO.countFee" value="<s:property value="pcustomerVO.countFee"/>" style="width: 100px">

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
						<th >渠道ID</th>
						<th >链接ID</th>
						<th >推广链接</th>
						<th >跳转链接</th>

						<th >创建时间</th>
					    <th>说明</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="pcustomerVOList">  
		    		<tr >
						<td>
                            <s:iterator value="#Option.pcustomerList">
                                <s:if test="customerId==id">
                                    <s:property value="name"/>
                                </s:if>
                            </s:iterator>
                            [<s:property value="customerId"/>[</td>
						<td>
                            <s:iterator value="#Option.promotionList">
                                <s:if test="promotionId==id">
                                    <s:property value="name"/>
                                </s:if>
                            </s:iterator>
                            [<s:property value="promotionId"/>]</td>
						<td><s:property value="linkUrl"/></td>
						<td><s:property value="redirectUrl"/></td>
						<td><s:property value="createTime"/></td>
						<td><s:property value="notes"/></td>

		    		<td>
						<a class="btn btn-info"
						   href="http://new.google8abc.com/cacheUtil?act=get&key=promotion<s:property value="customerId"/>-<s:property value="promotionId"/>"
						   title="查看缓存" target="_blank">查看缓存</a>
						&nbsp;|&nbsp;

						<a class="btn btn-warning"
						   href="http://new.google8abc.com/cacheUtil?act=set&val=<s:property value="dredirectUrl"/>&key=promotion<s:property value="customerId"/>-<s:property value="promotionId"/>"
						   target="_blank" title="修改缓存">
							修改缓存</a>

						&nbsp;&nbsp;|&nbsp;&nbsp;
					<%--	<a class="btn btn-info"
                           href="http://192.168.199.172:8082/cacheUtil?act=get&key=promotion<s:property value="customerId"/>-<s:property value="promotionId"/>"
                           title="查看缓存" target="_blank">查看缓存</a>
                        &nbsp;|&nbsp;

                        <a class="btn btn-warning"
                           href="http://192.168.199.172:8082/cacheUtil?act=set&val=<s:property value="dredirectUrl"/>&key=promotion<s:property value="customerId"/>-<s:property value="promotionId"/>"
                           target="_blank" title="修改缓存">
                            修改缓存</a>

                        &nbsp;&nbsp;|&nbsp;&nbsp;
                        --%>
                    <a class="btn btn-warning" href="javascript: modifypcustomer('<%=path%>/adver','<s:property value="id"/>')" title="修改">
                    修改
                    </a> </td>
                </tr>
            </s:iterator>
            <tr>
                <td colspan="8">
                <div class="pagelist" id="pagelist1" align="right">
                    <page:paginationAjax formName="productForm" property="pcustomerVO.page" operation="/adver/Pcustomer!list.action"/>
                 </div>
            </td>
            </tr>
            </tbody>
                 <s:if test="pcustomerVO.isPage==0">
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
    argus += "pcustomerVO.page.pageNum=";
    argus += $("#pageNum").val();
    argus += "&pcustomerVO.page.pageSize=";
    argus += $("#pageSize").val();
    var id = $("#qid").val();
    var name = $("#qname").val();
    argus += "&pcustomerVO.promotionId=";
    argus += id;
    argus += "&pcustomerVO.customerId=";
    argus += name;
    argus += "&pcustomerVO.isPage=1";
    return argus;
}
function searchList() {
    $("#load").css({"display":""});
    jQuery("#dataTable").html("");
    var id = $("#qid").val();
    var name = $("#qname").val();
    url = "<%=request.getContextPath()%>/adver/Pcustomer!list.action";
    jQuery.post(url,{"pcustomerVO.promotionId":id,  "pcustomerVO.customerId":name,"pcustomerVO.isPage":1
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
function modifypcustomer(path,id) {
    $('body').modalmanager('loading');
      setTimeout(function(){
          $modal.load(path+'/Pcustomer!modify.action', {"pcustomerVO.id":id}, function(){
             $modal.modal();
        });
      }, 100);
}
function createpcustomer(path) {
    $('body').modalmanager('loading');
      setTimeout(function(){
          $modal.load(path+'/Pcustomer!create.action', {}, function(){
             $modal.modal();
        });
      }, 100);
}

function detailpcustomer(url,id) {
    $("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/adver/Pcustomer!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
    var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
    $("#dataContent").html('').append(loading_img);
    $.post(url,{"pcustomerVO.id":id},function(response){
        if(response==-1) {
            showErrorToast("提示","系统出错，请重试或联系管理员");
        } else {
            $("#dataContent").html(jQuery.trim(response));
        }
    });
}
//修改or新增
function updatepcustomer(path) {
    var id = $("#cid").val();

    var url = path +'/adver/Pcustomer!update.action';
    if(id==0) {
        url = path+'/adver/Pcustomer!save.action';
    }

     var promotionId=$("#promotionId").val();
     var redirectUrl=$("#redirectUrl").val();
     var createTime=$("#createTime").val();
     var status=$("#status").val();
     var notes=$("#notes").val();
     var customerId=$("#customerId").val();

    var linkUrl=$("#linkUrl").val();
     if(promotionId=="") {
         showInfoToastMiddle("请选择链接!");
         $("#promotionId").focus();
         return false;
     }
    if(customerId=="") {
        showInfoToastMiddle("请选择渠道!");
        $("#promotionId").focus();
        return false;
    }
    $("#surebtn").attr("disabled","disabled");
    jQuery.post(url, {
        "pcustomerVO.id":id,
        "pcustomerVO.promotionId":promotionId,
        "pcustomerVO.redirectUrl":redirectUrl,
        "pcustomerVO.createTime":createTime,
        "pcustomerVO.status":status,
        "pcustomerVO.notes":notes,
        "pcustomerVO.linkUrl":linkUrl,
        "pcustomerVO.customerId":customerId　
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
