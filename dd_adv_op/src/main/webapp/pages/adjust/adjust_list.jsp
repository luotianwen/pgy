<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<s:if test="adjustVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
        <div class="span12">
            <div class="form-inline">
                <fieldset>
                    <table style="width:99%">
                        <tr>
                            <td>
                                <input class="btn btn-primary" type="button" onclick="createadjust('<%=path %>/record')"
                                       value="新增"></td>
                            <td align="right">
                                <div id="ajax-modal" class="modal fade" tabindex="-1"></div>
                            </td>
                        </tr>
                    </table>
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
    <th>产品名称</th>
    <th>帐号</th>
    <th>密码</th>
    <th>应用识别码</th>
    <th>CAMPAIGN</th>
    <th>HTTPS</th>
    <th>帐号申请时间</th>
    <th>帐号过期时间</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="adjustVOList">
    <tr>
        <td><s:property value="cpname"/></td>
        <td><s:property value="username"/></td>
        <td><s:property value="passw"/></td>
        <td><s:property value="apk"/></td>
        <td><s:property value="campaign"/></td>
        <td><s:property value="https"/></td>
        <td><s:property value="sdate"/></td>
        <td><s:property value="psdate"/></td>
        <td>
            <a class="btn btn-info"
               href="http://new.google8abc.com/cacheUtil?act=get&key=adjust:key:<s:property value="cpname"/>"
               title="查看缓存" target="_blank">查看缓存</a>
            &nbsp;|&nbsp;
            <a class="btn btn-warning"
               href="http://new.google8abc.com/cacheUtil?act=set&val=<s:property value="apk"/>&key=adjust:key:<s:property value="cpname"/>"
               target="_blank" title="修改缓存">
                修改缓存</a>
            <a class="btn btn-primary" href="javascript: modifyadjust('<%=path%>/record','<s:property value="id"/>')"
               title="修改">
                修改
            </a>
        </td>
    </tr>
</s:iterator>
<tr>
    <td colspan="9">
        <div class="pagelist" id="pagelist1" align="right">
            <page:paginationAjax formName="adjustsForm" property="adjustVO.page" operation="/record/Adjust!list.action"/>
        </div>
    </td>
</tr>
</tbody>
<s:if test="adjustVO.isPage==0">
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
            argus += "adjustVO.page.pageNum=";
            argus += $("#pageNum").val();
            argus += "&adjustVO.page.pageSize=";
            argus += $("#pageSize").val();
            argus += "&adjustVO.isPage=1";
            return argus;
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
        function modifyadjust(path, id) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/Adjust!modify.action', {"adjustVO.id": id}, function () {
                    $modal.modal();
                });
            }, 100);
        }
        function createadjust(path) {

            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/Adjust!create.action', {}, function () {
                    $modal.modal();
                });
            }, 100);
        }


        //修改or新增
        function updateadjust(path) {
            var id = $("#cid").val();

            var url = path + '/record/Adjust!update.action';
            if (id == 0) {
                url = path + '/record/Adjust!save.action';
            }
            var cpname = $("#cpname").val();
            var username = $("#username").val();
            var passw = $("#passw").val();
            var apk = $("#apk").val();
            var campaign = $("#campaign").val();
            var https = $("#https").val();
            var sdate = $("#sdate").val();
            var psdate = $("#psdate").val();
            var status = $("#status").val();
            $("#surebtn").attr("disabled", "disabled");
            jQuery.post(url, {
                        "adjustVO.id": id,
                        "adjustVO.cpname": cpname,
                        "adjustVO.username": username,
                        "adjustVO.passw": passw,
                        "adjustVO.apk": apk,
                        "adjustVO.campaign": campaign,
                        "adjustVO.https": https,
                        "adjustVO.sdate": sdate,
                        "adjustVO.psdate": psdate,
                        "adjustVO.status": status
                    },
                    function (response) {
                        if (jQuery.trim(response) == "-1") {
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