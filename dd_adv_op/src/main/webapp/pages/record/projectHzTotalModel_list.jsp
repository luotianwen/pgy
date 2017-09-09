<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:if test="projectHzTotalModelVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px;overflow:auto">
    <div class="span12">
        <div class="form-inline">
            <fieldset>
                <table style="width:99%">
                    <tr>
                        <td>
                            <input class="btn btn-primary" type="button"
                                   onclick="createprojectHzTotalModel('<%=path %>/record')" value="导入项目数据">
                            <input class="btn btn-primary" type="button"
                                   onclick="download('<%=path %>/record')" value="下载导入模板">
                        </td>
                        <td align="right">
                            <div id="ajax-modal" class="modal  fade" tabindex="-1"></div>
                            项目第三方收益录入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <label class="control-label inline" for="pname">项目</label>
                            <input id="qid" type="text" class="input-medium inline" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <label class="control-label inline" for="pname"> 时间</label>
                            <input type="text" name="qname" id="qname" onfocus="javascript:WdatePicker()" readonly="readonly"
                                 value='<s:property value="projectHzTotalModelVO.sdate"/>' style="width:150px;">
                            <button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询
                            </button>
                            <button type="reset" class="btn">清 空</button>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </div>
</div>
<div class="box box-primary" style="margin-top: 15px;overflow:auto">
    <div class="span12">
        <form class="form-horizontal" id="dataForm">
            <fieldset>
                <div id="dataList">
                    <table id="dataTable" class="table table-bordered table-striped table-hover">
                        </s:if>
                        <thead>
                        <tr>

                            <th>时间</th>
                            <th>项目</th>
                            <th>sdk类型</th>
                            <th>销量</th>
                            <th>活跃数</th>
                            <th>第三方收益</th>

                            <!-- <th>操作</th>-->
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="projectHzTotalModelVOList">
                            <tr>
                                <td><s:property value="sdate"/></td>
                                <td><s:property value="projectId"/></td>
                                <td>
                                    <s:iterator value="#Option.typeList">
                                        <s:if test="sdktype==key">
                                            <s:property value="value"/>
                                        </s:if>
                                    </s:iterator>
                                    </td>
                                <td><s:property value="newusers"/></td>
                                <td><s:property value="actusers"/></td>
                                <td><s:property value="thirdincome"/></td>
                                <!--
		    		<td>
	    				<a class="btn btn-info" href="javascript: detailprojectHzTotalModel('<%=path%>/record/ProjectHzTotalModel!detail.action','<s:property value="projectId"/>','<s:property value="sdate"/>','<s:property value="sdktype"/>')" title="详情">
						详情
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="btn btn-warning" href="javascript: modifyprojectHzTotalModel('<%=path%>/record','<s:property value="projectId"/>','<s:property value="sdate"/>')" title="修改">
						修改
						</a> </td>-->
                            </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="6">
                                <div class="pagelist" id="pagelist1" align="right">
                                    <page:paginationAjax formName="productForm" property="projectHzTotalModelVO.page"
                                                         operation="/record/ProjectHzTotalModel!list.action"/>
                                </div>
                            </td>
                        </tr>
                        </tbody>
<s:if test="projectHzTotalModelVO.isPage==0">
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
        var argus = "";
        argus += "projectHzTotalModelVO.page.pageNum=";
        argus += $("#pageNum").val();
        argus += "&projectHzTotalModelVO.page.pageSize=";
        argus += $("#pageSize").val();
        var id = $("#qid").val();
        var name = $("#qname").val();
        argus += "&projectHzTotalModelVO.projectId=";
        argus += id;
        argus += "&projectHzTotalModelVO.sdate=";
        argus += name;
        argus += "&projectHzTotalModelVO.isPage=1";
        return argus;
    }
    function searchList() {
        $("#load").css({"display": ""});
        jQuery("#dataTable").html("");
        var id = $("#qid").val();
        var name = $("#qname").val();
        url = "<%=request.getContextPath()%>/record/ProjectHzTotalModel!list.action";
        jQuery.post(url, {
            "projectHzTotalModelVO.projectId": id,
            "projectHzTotalModelVO.sdate": name,
            "projectHzTotalModelVO.isPage": 1
        }, function (response) {
            if (response == "-1") {
                showErrorToastMiddle("系统出错，请重试或联系管理员");
                $("#load").css({"display": "none"});
            } else {
                jQuery("#dataTable").html(jQuery.trim(response));
                $("#load").css({"display": "none"});
            }
        });
    }

    var $modal = $('#ajax-modal');
    $modal.on('click', '.update', function () {
        $modal.modal('loading');
        setTimeout(function () {
            $modal.modal('loading').find('.modal-body')
                    .prepend('<div class="alert alert-info fade in">' +
                            'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                            '</div>');
        }, 100);
    });
    function modifyprojectHzTotalModel(path, id, sdate) {
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load(path + '/ProjectHzTotalModel!modify.action', {
                "projectHzTotalModelVO.projectId": id,
                "projectHzTotalModelVO.sdate": sdate
            }, function () {
                $modal.modal();
            });
        }, 100);
    }
    function createprojectHzTotalModel(path) {
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load(path + '/ProjectHzTotalModel!create.action', {}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function download(path) {
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load(path + '/ProjectHzTotalModel!download.action', {}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function detailprojectHzTotalModel(url, projectId, sdate, sdktype) {
        //$("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/record/ProjectHzTotalModel!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
        var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
        $("#dataContent").html('').append(loading_img);
        $.post(url, {
            "projectHzTotalModelVO.sdate": sdate,
            "projectHzTotalModelVO.projectId": projectId,
            "projectHzTotalModelVO.sdktype": sdktype
        }, function (response) {
            if (response == -1) {
                showErrorToast("提示", "系统出错，请重试或联系管理员");
            } else {
                $("#dataContent").html(jQuery.trim(response));
            }
        });
    }
    //修改or新增
    function updateprojectHzTotalModel(path) {
        var id = $("#cid").val();

        var url = path + '/record/ProjectHzTotalModel!update.action';
        if (id == 0) {
            url = path + '/record/ProjectHzTotalModel!save.action';
        }
        var sdate = $("#sdate").val();
        var projectId = $("#projectId").val();
        var thirdincome = $("#thirdincome").val();
        $("#surebtn").attr("disabled", "disabled");
        jQuery.post(url, {
                    "projectHzTotalModelVO.sdate": sdate,
                    "projectHzTotalModelVO.projectId": projectId,
                    "projectHzTotalModelVO.thirdincome": thirdincome
                },
                function (response) {
                    if (jQuery.trim(response) == "-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        jQuery("#surebtn").attr('disabled', 'disabled');
                    } else {
                        showInfoToastMiddle(response);
                        $("#qid").val(projectId);
                        $("#qname").val(sdate);
                        doPageBottom('turn');
                    }
                });
    }


</script>
</s:if>
