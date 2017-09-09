<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="offerSdkVO.id==0">新建订阅SDK</s:if>
        <s:if test="offerSdkVO.id>0">修改订阅SDK</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">广告名</td>
                <td>
                    <input id="adName" type="text" value="<s:property value="offerSdkVO.adName"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">标题</td>
                <td>
                    <input id="title" type="text" class="input-medium" value="<s:property value="offerSdkVO.title"/>"/>
                </td>
                <td class="table_td_title">图片名</td>
                <td>
                    <input id="imageName" type="text" value="<s:property value="offerSdkVO.imageName"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">推广链接</td>
                <td colspan="3">
                    <input id="promotionUrl" type="text" size="90" class="input-medium"
                           value="<s:property value="offerSdkVO.promotionUrl"/>"/>默认tid={tid}

                </td>
            </tr>
            <tr>
                <td class="table_td_title">备选推广链接</td>
                <td colspan="3">
                    <input id="_promotionUrl" type="text" size="90" class="input-medium"
                           value="<s:property value="offerSdkVO._promotionUrl"/>"/>默认tid={tid}

                </td>
            </tr>
            <tr>
                <td class="table_td_title">图片链接</td>
                <td colspan="3">
                    <input id="imageUrl" type="text" size="100" class="input-medium"
                           value="<s:property value="offerSdkVO.imageUrl"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">描述</td>
                <td colspan="3">
                    <input id="remark" type="text" size="100" class="input-medium"
                           value="<s:property value="offerSdkVO.remark"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">接入人员</td>
                <td>
                    <s:select id="advLinkmanId" list="#Option.advLinkmanList" listKey="id" listValue="name"
                              name="offerSdkVO.advLinkManId"
                              headerKey="0" headerValue="请选择接入人员" cssClass="selectWidth"></s:select>
                </td>
                <td class="table_td_title">广告主</td>
                <td>
                    <s:select id="adverId" list="#Option.adverList" listKey="id" listValue="name"
                              name="offerSdkVO.adverId"
                              cssClass="selectWidth"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">ECPM状态</td>
                <td>
                    <s:select list="#{'1':'开','0':'关'}" listKey="key" listValue="value"
                              id="ecpmStatus" name="offerSdkVO.ecpmStatus"></s:select>
                </td>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#{'1':'开','0':'关'}" listKey="key" listValue="value"
                              id="status" name="offerSdkVO.status"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">网络类型</td>
                <td colspan="3">
                    <s:select id="internet" list="#Option.netType" listKey="key" listValue="value"
                              name="subscribeVO.internet" cssClass="selectWidth"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">类型</td>
                <td>
                    <s:select id="adType" list="#Option.offerSdkList" listKey="key" listValue="value"
                              name="offerSdkVO.type" multiple="true" cssClass="selectWidth"></s:select>
                </td>

            </tr>
            <tr>
                <td class="table_td_title">价格</td>
                <td>
                    <input id="price" type="number" value="<s:property value="offerSdkVO.price"/>"/>
                </td>
                <td class="table_td_title">手动ecpm</td>
                <td>
                    <input id="manuEcpm" type="number" value="<s:property value="offerSdkVO.manuEcpm"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">国家</td>
                <td>
                    <s:select id="cou" list="#Option.countryList" listKey="id" listValue="name"
                              name="offerSdkVO.cou" cssClass="selectWidth" onchange="selectOperator()"> </s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">运营商</td>
                <td>
                    <s:select id="operator" list="#Option.operatorsList" listKey="key" listValue="value"
                              name="offerSdkVO.operator" multiple="true" cssClass="selectWidth" > </s:select>
                </td>
            </tr>
            <td colspan="2">
                <input id="createBtn" type="button" class="btn btn-primary" value="确定"
                       onclick="return updateInfo();">
            </td>
            </tr>
        </table>
    </form>
</div>
<%--临时加js--%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/kkpay.js"></script>

<script type="text/javascript">
    var adTypeSelect2 = $("#adType").select2();
    var deviceTypes = '';
    deviceTypes = "<s:property value="offerSdkVO.type"/>";
    var data = deviceTypes.split(",");
    adTypeSelect2.val(data).trigger("change");

    var operatorSelect2 = $("#operator").select2();
    var operators = '';
    operators = "<s:property value="offerSdkVO.operator"/>";
    data = operators.split(",");
    operatorSelect2.val(data).trigger("change");

    $("#cou").select2();
    function updateInfo() {

        var cou = $("#cou").val().trim();
        var id = '<s:property value="offerSdkVO.id"/>';
        var path = "";
        if (id == null || id == "") {
            path = '<%=path%>/offerinfo/OfferSdk!save.action';
        } else {
            path = '<%=path%>/offerinfo/OfferSdk!update.action';
        }
        var type = $("#adType").val().toString();
        if (type != null && type != "") {
            type = type.toString();
        } else {
            type = "";
        }
        var adName = $("#adName").val().trim();
        var imageName = $("#imageName").val().trim();
        var title = $("#title").val().trim();
        var promotionUrl = $("#promotionUrl").val().trim();
        var _promotionUrl = $("#_promotionUrl").val().trim();
        var imageUrl = $("#imageUrl").val().trim();
        var advLinkmanId = $("#advLinkmanId").val().trim();
        var adverId = $("#adverId").val().trim();
        var ecpmStatus = $("#ecpmStatus").val().trim();
        var manuEcpm = $("#manuEcpm").val().trim();
        var internet = $("#internet").val().trim();
        var operator = $("#operator").val();
        if (operator != null && operator != "") {
            operator = operator.toString();
        } else {
            operator = "";
        }
        var price = $("#price").val().trim();
        var remark = $("#remark").val();
        var status = $("#status").val();
//		if (packgeName == "") {
//			showInfoToastMiddle("请填写包名！");
//			return false;
//		}

        $("#createBtn").attr("disabled", "disabled");
        jQuery.post(path, {
                'offerSdkVO.id': id,
                'offerSdkVO.type': type,
                'offerSdkVO.title': title,
                'offerSdkVO.promotionUrl': promotionUrl,
                'offerSdkVO._promotionUrl': _promotionUrl,
                'offerSdkVO.imageUrl': imageUrl,
                'offerSdkVO.adName': adName,
                'offerSdkVO.imageName': imageName,
                'offerSdkVO.cou': cou,
                'offerSdkVO.internet': internet,
                'offerSdkVO.remark': remark,
                'offerSdkVO.adverId': adverId,
                'offerSdkVO.advLinkManId': advLinkmanId,
                'offerSdkVO.ecpmStatus': ecpmStatus,
                'offerSdkVO.operator': operator,
                'offerSdkVO.manuEcpm': manuEcpm,
                'offerSdkVO.price': price,
                'offerSdkVO.status': status
            }
            , function (response) {
                if (response == "-1") {
                    showInfoToastMiddle("系统出错，请重试或联系管理员");
                    $("#load").css({"display": "none"});
                } else {
                    $("#load").css({"display": "none"});
                    showInfoToastMiddle(response);
                    searchList();
                }
            });
    }
    var asiaSize = '<s:property value="#Option.asiaCountryList.size"/>';
    var northSize = '<s:property value="#Option.northAmericaCountryList.size"/>';
    var southSize = '<s:property value="#Option.southAmericaCountryList.size"/>';
    var oceaniaSize = '<s:property value="#Option.oceaniaCountryList.size"/>';
    var europeSize = '<s:property value="#Option.europeCountryList.size"/>';
    var africaSize = '<s:property value="#Option.africaCountryList.size"/>';


    function selectOperator(){
        var realpath = '<%=path %>';
        var countryId = $("#cou").val();
        if(countryId==0) {
            jQuery("#operator").html('');
        } else {
            url = realpath+"/record/GetOperatorsByCou.action";
            jQuery.post(url,{"operatorVO.cou":countryId},function(response){
                jQuery("#operator").html(jQuery.trim(response));
            });
        }
    }
    function changeDetail(code) {
        if (code == 1000001) {
            var aa = $("input.asiaClass:checked");
            // var aa = $("input[name='asiaCheckBox']:checked").val([]);

            var len = aa.length;
            if (len >= asiaSize) {
                $("#inlineAsiaCheckbox").prop("checked", true);
            } else {
                $("#inlineAsiaCheckbox").prop("checked", false);
            }
        } else if (code == 1000002) {
            var aa = $("input.northClass:checked");
            // var aa = $("input[name='northCheckBox']:checked").val([]);
            var len = aa.length;
            if (len >= northSize) {
                $("#inlineNorthCheckbox").prop("checked", true);
            } else {
                $("#inlineNorthCheckbox").prop("checked", false);
            }
        } else if (code == 1000003) {
            var aa = $("input.southClass:checked");
            //var aa = $("input[name='southCheckBox']:checked").val([]);
            var len = aa.length;
            if (len >= southSize) {
                $("#inlineSouthCheckbox").prop("checked", true);
            } else {
                $("#inlineSouthCheckbox").prop("checked", false);
            }
        } else if (code == 1000004) {
            var aa = $("input.oceaniaClass:checked");
            // var aa = $("input[name='oceaniaCheckBox']:checked").val([]);;
            var len = aa.length;
            if (len >= oceaniaSize) {
                $("#inlineOceaniaCheckbox").prop("checked", true);
            } else {
                $("#inlineOceaniaCheckbox").prop("checked", false);
            }
        } else if (code == 1000005) {
            var aa = $("input.europeClass:checked");
            //var aa = $("input[name='europeCheckBox']:checked").val([]);
            var len = aa.length;
            if (len >= europeSize) {
                $("#inlineEuropeCheckbox").prop("checked", true);
            } else {
                $("#inlineEuropeCheckbox").prop("checked", false);
            }
        } else if (code == 1000006) {
            var aa = $("input.africaClass:checked");
            //var aa = $("input[name='africaCheckBox']:checked").val([]);
            var len = aa.length;
            if (len >= africaSize) {
                $("#inlineAfricaCheckbox").prop("checked", true);
            } else {
                $("#inlineAfricaCheckbox").prop("checked", false);
            }
        }

    }


    var country = '<s:property value="offerSdkVO.cou"/>';
    var data = country.toString().split(",");
    for (var i = 0; i < data.length; i = i + 1) {
        $("#inline_" + data[i]).prop("checked", true);
    }

    changeDetail(1000001);
    changeDetail(1000002);
    changeDetail(1000003);
    changeDetail(1000004);
    changeDetail(1000005);
    changeDetail(1000006);

    function checkCountryName(obj) {
        var countryNames = obj.value;
        if (countryNames == "" || countryNames.lenght == 0) {
            return;
        }
        var data = countryNames.toString().split(",");
        for (var i = 0; i < data.length; i = i + 1) {
            $("input[name*='" + data[i] + "']").prop("checked", true);
        }
    }
</script>