<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:if test="spromotionVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
        <div class="span12">
            <div class="form-inline">
                <fieldset>
                    <table style="width:99%">
                        <tr>
                            <td>
                                <input class="btn btn-primary" type="button"
                                       onclick="createspromotion('<%=path %>/adver')" value="新增"></td>
                            <td align="right">
                                <div id="ajax-modal" class="modal  fade" tabindex="-1"></div>
                                <label class="control-label inline" for="pname">渠道ID</label>
                                <input id="qname" type="text" class="input-medium inline" name="spromotionVO.countFee"
                                       value="<s:property value="spromotionVO.countFee"/>" style="width: 100px">

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
    <div class="box box-primary" style="margin-top: 15px">
    <div class="span12">
    <form class="form-horizontal" id="dataForm">
    <fieldset>
    <div id="dataList">
    <table id="dataTable" style="width:95%" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
<tr>
    <th>id</th>
    <th>渠道ID</th>
    <th>外放链接</th>
    <th>类型</th>
    <th>状态</th>
    <th>创建时间</th>
    <th>说明</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="spromotionVOList">
    <tr>
        <td><s:property value="id"></s:property></td>
        <td>
            <s:iterator value="#Option.pcustomerList">
                <s:if test="cooId==id">
                    <s:property value="name"/>
                </s:if>
            </s:iterator>
            [<s:property value="cooId"/>]
        </td>
        <td><s:property value="promotionLink"/></td>
        <td>
            <s:iterator value="#Option.subType">
                <s:if test="type==key">
                    <s:property value="value"/>
                </s:if>
            </s:iterator>
        </td>
        <td><s:if test="status==0">关</s:if>
            <s:if test="status==1">开</s:if>
        </td>
        <td><s:property value="createTime"/></td>
        <td><s:property value="notes"/></td>

         <td>
        <a class="btn btn-warning" href="javascript: modifyspromotion('<%=path%>/adver','<s:property value="id"/>')"
           title="修改">
            修改
        </a></td>
    </tr>
</s:iterator>
<tr>
    <td colspan="8">
        <div class="pagelist" id="pagelist1" align="right">
            <page:paginationAjax formName="productForm" property="spromotionVO.page"
                                 operation="/adver/Spromotion!list.action"/>
        </div>
    </td>
</tr>
</tbody>
<s:if test="spromotionVO.isPage==0">
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
            argus += "spromotionVO.page.pageNum=";
            argus += $("#pageNum").val();
            argus += "&spromotionVO.page.pageSize=";
            argus += $("#pageSize").val();
            var id = $("#qid").val();
            var name = $("#qname").val();
            argus += "&spromotionVO.promotionId=";
            argus += id;
            argus += "&spromotionVO.cooId=";
            argus += name;
            argus += "&spromotionVO.isPage=1";
            return argus;
        }
        function searchList() {
            $("#load").css({"display": ""});
            jQuery("#dataTable").html("");
            var id = $("#qid").val();
            var name = $("#qname").val();
            url = "<%=request.getContextPath()%>/adver/Spromotion!list.action";
            jQuery.post(url, {
                "spromotionVO.promotionId": id, "spromotionVO.customerId": name, "spromotionVO.isPage": 1
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
        function modifyspromotion(path, id) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/Spromotion!modify.action', {"spromotionVO.id": id}, function () {
                    $modal.modal();
                });
            }, 100);
        }
        function createspromotion(path) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/Spromotion!create.action', {}, function () {
                    $modal.modal();
                });
            }, 100);
        }

        function detailspromotion(url, id) {
            $("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/adver/Spromotion!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
            var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
            $("#dataContent").html('').append(loading_img);
            $.post(url, {"spromotionVO.id": id}, function (response) {
                if (response == -1) {
                    showErrorToast("提示", "系统出错，请重试或联系管理员");
                } else {
                    $("#dataContent").html(jQuery.trim(response));
                }
            });
        }
        //修改or新增
        function updatespromotion(path) {
            var id = $("#cid").val();

            var url = path + '/adver/Spromotion!update.action';
            if (id == 0) {
                url = path + '/adver/Spromotion!save.action';
            }


            var type = $("#type").val();
            var status = $("#status").val();
            var notes = $("#notes").val();
            var customerId = $("#customerId").val();

            if (type == "") {
                showInfoToastMiddle("请选择类型!");
                $("#type").focus();
                return false;
            }
            if (customerId == "") {
                showInfoToastMiddle("请选择渠道!");
                $("#promotionId").focus();
                return false;
            }
            $("#surebtn").attr("disabled", "disabled");
            jQuery.post(url, {
                        "spromotionVO.id": id,
                        "spromotionVO.type": type,
                        "spromotionVO.status": status,
                        "spromotionVO.notes": notes,

                        "spromotionVO.cooId": customerId
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
