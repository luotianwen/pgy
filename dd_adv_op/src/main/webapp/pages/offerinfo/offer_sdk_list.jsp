<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<s:if test="offerSdkVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
        <div class="span12">
            <div class="form-inline">
                <fieldset>
                    <table style="width:100%">
                        <tr>
                            <td>
                                <input class="btn btn-success" type="button"
                                       onclick="createOfferSdk('<%=path %>/offerinfo')" value="新增"></td>
                            <td align="right">
                                <div id="ajax-modal" class="modal fade" data-width="1000" tabindex="-1"></div>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label inline" for="state">状态</label>
                                <select id="state" name="offerSdkVO.status">
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                                <label class="control-label inline" for="qname">广告名</label>
                                <input id="qname" style="width: 10%">
                                <label class="control-label inline" for="qman">介入人员</label>
                                <s:select id="qman" list="#Option.advLinkmanList" listKey="id" listValue="name"
                                          headerKey="0" headerValue="请选择接入人员" cssClass="selectWidth"
                                          cssStyle="width: 10%"/>
                                <label class="control-label inline" for="qoperator">运营商</label>
                                <s:select id="qoperator" list="#Option.operatorsList" listKey="key" listValue="value"
                                          cssClass="selectWidth" cssStyle="width: 10%" headerKey=""
                                          headerValue="请选择"> </s:select>
                                <label class="control-label inline" for="qadver">广告主</label>
                                <s:select id="qadver" list="#Option.adverList" listKey="id" listValue="name"
                                          name="offerSdkVO.adverId" cssClass="selectWidth" cssStyle="width: 10%"
                                          headerKey="0" headerValue="请选择"></s:select>
                                <label class="control-label inline" for="qcou">国家</label>
                                <s:select id="qcou" list="#Option.countryList" listKey="id" listValue="name"
                                          cssClass="selectWidth" cssStyle="width: 10%" headerKey="" headerValue="请选择"
                                          onchange="selectOperator()"> </s:select>
                                <button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询
                                </button>
                                <button type="reset" class="btn btn-primary">清 空</button>
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
<script>
    $("#qoperator").select2();
    $("#qadver").select2();
    $("#qcou").select2();

</script>
<tr>
    <th>id</th>
    <th>广告名</th>
    <th>标题</th>
    <th>图片名</th>
    <%--<th width="20">推广链接</th>--%>
    <%--<th width="20">备选推广链接</th>--%>
    <%--<th >图片链接</th>--%>
    <%--<th>描述</th>--%>
    <%--<th>是否审核</th>--%>
    <th>接入人员</th>
    <th>广告主</th>
    <th>网络类型</th>
    <th>状态</th>
    <th>ecpm状态</th>
    <th>手动ecpm</th>
    <th>自动ecpm</th>
    <th>ctr</th>
    <th>价格</th>
    <th>创建时间</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="offerSdkVOList">
    <tr>
        <td><s:property value="id"/></td>
        <td><s:property value="adName"/></td>
        <td><s:property value="title"/></td>
        <td><s:property value="imageName"></s:property></td>
        <%--<td><s:property value="promotionUrl"/></td>--%>
        <%--<td><s:property value="_promotionUrl"/></td>--%>
            <%--<td><s:property value="imageUrl"/></td>--%>
        <%--<td><s:property value="remark"/></td>--%>
            <%--<td><s:if test="review==1">是</s:if><s:if test="review==0">否</s:if></td>--%>
        <td><s:iterator value="#Option.advLinkmanList">
            <s:if test="advLinkManId==id">
                <s:property value="name"/>[<s:property value="id"/>]
            </s:if>
        </s:iterator></td>
        <td>
            <s:iterator value="#Option.adverList">
                <s:if test="adverId==id">
                    <s:property value="name"/>[<s:property value="id"/>]
                </s:if>
            </s:iterator>
        </td>
        <td><s:if test="internet==1">WIFI</s:if><s:if test="internet==0">GPRS</s:if><s:if test="internet==2">不限</s:if></td>

        <td><s:if test="status==1">开</s:if>
            <s:if test="status==0">关</s:if>
        </td>
        <td><s:if test="ecpmStatus==1">开</s:if>
            <s:if test="ecpmStatus==0">关</s:if>
        </td>
        <td><s:property value="manuEcpm"/></td>
        <td><s:property value="autoEcpm"/></td>
        <td><s:property value="ctr"></s:property></td>
        <td><s:property value="price"/></td>
        <td><s:property value="cdate"/></td>
        <td>
            <a class="btn btn-primary"
               href="javascript: modifyOfferSdk('<%=path%>/offerinfo','<s:property value="id"/>')" title="修改">
                修改
            </a>
            <a class="btn btn-danger"
               href="javascript: deleteOfferSdk('<%=path%>/offerinfo','<s:property value="id"/>')" title="删除">
                删除
            </a>
            <a class="btn btn-danger"
               href="javascript: deleteOfferSdk('<%=path%>/offerinfo','<s:property value="id"/>')" title="复制">
                复制
            </a>
        </td>
    </tr>
</s:iterator>
<tr>
    <td colspan="7">
        <div class="pagelist" id="pagelist1" align="right">
            <page:paginationAjax formName="OfferSdkForm" property="offerSdkVO.page"
                                 operation="/offerinfo/OfferSdk!list.action"/>
        </div>
    </td>
</tr>
</tbody>
<s:if test="offerSdkVO.isPage==0">
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
            argus += "offerSdkVO.page.pageNum=";
            argus += $("#pageNum").val();
            argus += "&offerSdkVO.page.pageSize=";
            argus += $("#pageSize").val();
            var id = $("#pid").val();
            var name = $("#pname").val();

            argus += "&offerSdkVO.apkId=";
            argus += id;
            argus += "&offerSdkVO.isPage=1";
            return argus;
        }

        function searchList() {
            var qname = $("#qname").val();
            var state = $("#state").val();
            var qcou = $("#qcou").val();
            var qman = $("#qman").val();
            var qoperator = $("#qoperator").val();
            var qadver = $("#qadver").val();
            $("#load").css({"display": ""});
            jQuery("#dataTable").html("");
            var url = "<%=request.getContextPath()%>/offerinfo/OfferSdk!list.action";
            jQuery.post(url,
                {
                    "offerSdkVO.operator": qoperator,
                    "offerSdkVO.isPage": 1,
                    "offerSdkVO.adName": qname,
                    "offerSdkVO.advLinkManId": qman,
                    "offerSdkVO.adverId": qadver,
                    "offerSdkVO.cou": qcou,
                    "offerSdkVO.status": state
                },
                function (response) {
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


        function modifyOfferSdk(path, id) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/OfferSdk!modify.action', {"offerSdkVO.id": id}, function () {
                    $modal.modal();
                });
            }, 100);
        }
        function createOfferSdk(path) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/OfferSdk!create.action', {}, function () {
                    $modal.modal();
                });
            }, 100);
        }

        function deleteOfferSdk(path, id) {
            if (!sure('确定要删除 [' + id + '] 吗？')) {
                return;
            }
            jQuery.post(path + "/OfferSdk!delete.action", {"offerSdkVO.id": id},
                function (data) {
                    if (data == "-1") {
                        showErrorToast("提示", "系统出错，请重试或联系管理员");
                    } else {
                        showInfoToastMiddle(data);
                        $("#apkinfo_" + id).remove();
                        searchList();
                    }
                });
        }

    </script>

</s:if>



