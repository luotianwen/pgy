<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:if test="subscribeVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
        <div class="span12">
            <div class="form-inline">
                <fieldset>
                    <table style="width:99%">
                        <tr>
                            <td>
                                <input class="btn btn-primary" type="button"
                                       onclick="createsubscribe('<%=path %>/adver')" value="新增"></td>
                            <td align="right">
                                <div id="ajax-modal" class="modal  fade" data-width="1000" tabindex="-1"></div>
                                <label class="control-label inline" for="pname">ID</label>
                                <input id="qid" type="text" class="input-medium inline" name="subscribeVO.id"
                                       value="<s:property value="subscribeVO.id"/>" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label inline" for="pname">名称</label>
                                <input id="qname" type="text" class="input-medium inline" name="subscribeVO.name"
                                       value="<s:property value="subscribeVO.countFee"/>" style="width: 100px">
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
    <th>链接ID</th>
    <th>名称</th>
    <th>运营商</th>
    <th>网络类型</th>
    <th>推广链接</th>
    <%--<th>国家</th>--%>
    <td>实时ECPM</td>
    <td>手动ECPM</td>
    <td>单价</td>
    <td>ECPM状态</td>
    <td>ctr</td>
    <th>状态</th>
    <th>创建时间</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="subscribeVOList">
    <tr>
        <td><s:property value="id"/></td>
        <td><s:property value="name"/></td>
        <td>
            <s:iterator value="#Option.globalOperatorList">

                <s:if test="xoperator == code">
                    <s:property value="operatorName"></s:property>
                </s:if>
            </s:iterator>
        </td>
        <td>
            <s:if test="internet == 1">WIFI</s:if>
            <s:if test="internet == 0">GPRS</s:if>
            <s:if test="internet == 2">不限制</s:if>
        </td>
        <td><s:property value="redirectUrl"/></td>
        <%--<td><s:iterator value="#Option.countryList">--%>
                <%--<s:if test="cou == id">--%>
                     <%--<s:property value="name"></s:property>--%>
                <%--</s:if>--%>
            <%--</s:iterator>--%>
        <%--</td>--%>
        <td><s:property value="autoECPM"></s:property></td>
        <td><s:property value="manualECPM"></s:property></td>
        <td><s:property value="price"></s:property></td>
        <td>
            <s:if test="ecpmStatus == 1">开</s:if>
            <s:if test="ecpmStatus == 0">关</s:if>
        </td>
        <td><s:property value="ctr"></s:property> </td>
        <td>
            <s:if test="status == 1">开</s:if>
            <s:if test="status == 0">关</s:if>
        </td>
        <td><s:property value="createTime"/></td>


        <td>
            <a class="btn btn-warning" href="javascript: modifysubscribe('<%=path%>/adver','<s:property value="id"/>')"
               title="修改">
                修改
            </a>
            <a class="btn btn-info" href="javascript: copeSub('<%=path%>/adver','<s:property value="id"/>')"
               title="复制">
                复制
            </a>
            <a class="btn btn-info" href="javascript: copeSubcous('<%=path%>/adver','<s:property value="id"/>')"
               title="复制全部(国家)">
                复制全部(国家)
            </a>
        </td>
    </tr>
</s:iterator>
<tr>
    <td colspan="8">
        <div class="pagelist" id="pagelist1" align="right">
            <page:paginationAjax formName="productForm" property="subscribeVO.page"
                                 operation="/adver/Subscribe!list.action"/>
        </div>
    </td>
</tr>
</tbody>
<s:if test="subscribeVO.isPage==0">
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
            argus += "subscribeVO.page.pageNum=";
            argus += $("#pageNum").val();
            argus += "&subscribeVO.page.pageSize=";
            argus += $("#pageSize").val();
            var id = $("#qid").val();
            var name = $("#qname").val();
            argus += "&subscribeVO.id=";
            argus += id;
            argus += "&subscribeVO.name=";
            argus += name;
            argus += "&subscribeVO.isPage=1";
            return argus;
        }
        function searchList() {
            var id = $("#qid").val();
            var name = $("#qname").val();
            url = "<%=request.getContextPath()%>/adver/Subscribe!list.action";
            jQuery.post(url, {
                "subscribeVO.isPage": 1,
                "subscribeVO.name": name,
                "subscribeVO.id": id
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

        function copeSub(path, id) {
            if (!sure('确定要复制 ['+id+'] 吗？')) {
                return;
            }
            jQuery.post(path+"/Subscribe!copy.action",{"subscribeVO.id":id},function(data){
                if(data == "-1"){
                    showErrorToast("提示","系统出错，请重试或联系管理员");
                } else {
                    showInfoToastMiddle('复制订阅广告 ['+id+'] 成功');
                    searchList();
                }
            });
        }

        function copeSubcous(path, id){
            if (!sure('确定要复制全部(国家) ['+id+'] 吗？')) {
                return;
            }
            jQuery.post(path+"/Subscribe!copycous.action",{"subscribeVO.id":id},function(data){
                if(data == "-1"){
                    showErrorToast("提示","系统出错，请重试或联系管理员");
                } else {
                    showInfoToastMiddle('全复制成功');
                    searchList();
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
        function modifysubscribe(path, id) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/Subscribe!modify.action', {"subscribeVO.id": id}, function () {
                    $modal.modal();
                });
            }, 100);
        }
        function createsubscribe(path) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/Subscribe!create.action', {}, function () {
                    $modal.modal();
                });
            }, 100);
        }

        function detailsubscribe(url, id) {
            $("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/adver/subscribe!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
            var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
            $("#dataContent").html('').append(loading_img);
            $.post(url, {"subscribeVO.id": id}, function (response) {
                if (response == -1) {
                    showErrorToast("提示", "系统出错，请重试或联系管理员");
                } else {
                    $("#dataContent").html(jQuery.trim(response));
                }
            });
        }
        //修改or新增
        function updatesubscribe(path) {
            var id = $("#cid").val();

            var url = path + '/adver/Subscribe!update.action';
            if (id == 0) {
                url = path + '/adver/Subscribe!save.action';
            }

            var asiaChecked=$("input[name^='asiaCheckBox']:checked").val([]);
            var southChecked=$("input[name^='southCheckBox']:checked").val([]);
            var northChecked=$("input[name^='northCheckBox']:checked").val([]);
            var europeChecked=$("input[name^='europeCheckBox']:checked").val([]);
            var oceaniaChecked=$("input[name^='oceaniaCheckBox']:checked").val([]);
            var africaChecked=$("input[name^='africaCheckBox']:checked").val([]);

            var asiaCheckValues="";
            for(var i=0;i<asiaChecked.length;i++){
                asiaCheckValues += asiaChecked[i].value +",";
            }
            for(var i=0;i<southChecked.length;i++){
                asiaCheckValues += southChecked[i].value +",";
            }
            for(var i=0;i<northChecked.length;i++){
                asiaCheckValues += northChecked[i].value +",";
            }
            for(var i=0;i<europeChecked.length;i++){
                asiaCheckValues += europeChecked[i].value +",";
            }
            for(var i=0;i<oceaniaChecked.length;i++){
                asiaCheckValues += oceaniaChecked[i].value +",";
            }
            for(var i=0;i<africaChecked.length;i++){
                asiaCheckValues += africaChecked[i].value +",";
            }

            var countryIds=asiaCheckValues;


            var name = $("#name").val();
            var adverId = $("#adverId").val();
            var redirectUrl = $("#redirectUrl").val();
            var createTime = $("#createTime").val();
            var status = $("#status").val();
            var notes = $("#notes").val();
            var netType = $("#netType").val();
            var subType = $("#subType").val();
            var manualECPM = $("#manualECPM").val();
            var ecpmStatus = $("#ecpmStatus").val();
            var price = $("#price").val();
            var param1 = $("#param1").val();
            var param2 = $("#param2").val();
            var advLinkmanId = $("#advLinkmanId").val();

            var operatorId = $("#operatorId").val();
            if(operatorId == "" || operatorId == null){
//                showInfoToastMiddle("请选择一个运营商");
//                return false;
                operatorId = "0";
            }else{
                operatorId = operatorId.toString();
            }
            if(adverId ==""||redirectUrl==""||subType==0){
                alert("有必填项未输入,请填写");
                return false;
            }
            $("#surebtn").attr("disabled", "disabled");
            jQuery.post(url, {
                        "subscribeVO.id": id,
                        "subscribeVO.name": name,
                        "subscribeVO.adverId": adverId,
                        "subscribeVO.redirectUrl": redirectUrl,
                        "subscribeVO.createTime": createTime,
                        "subscribeVO.status": status,
                        "subscribeVO.notes": notes,
                        "subscribeVO.internet": netType,
                        "subscribeVO.type": subType,
                        "subscribeVO.cou": countryIds,
                        "subscribeVO.manualECPM":manualECPM,
                        "subscribeVO.ecpmStatus":ecpmStatus,
                        "subscribeVO.param1": param1,
                        "subscribeVO.param2": param2,
                        "subscribeVO.price": price,
                        "subscribeVO.advLinkmanId": advLinkmanId,
                        "subscribeVO.operatorId": operatorId
                    },
                    function (response) {
                        if (jQuery.trim(response) == "-1") {
                            showErrorToastMiddle("系统出错，请重试或联系管理员");
                            jQuery("#surebtn").attr('disabled', 'disabled');
                        } else {
                            showInfoToastMiddle(response);
                            searchList();

                        }
                    });

        }


    </script>
</s:if>
