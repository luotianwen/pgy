<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:hidden id="cid" name="silentPluginInfoVO.id"/>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="silentPluginInfoVO.id==0">新建线下插件服务</s:if>
        <s:if test="silentPluginInfoVO.id>0">修改线下插件服务</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">apkId</td>
                <td>
                    <input type="text" class="input-medium" id="apkId" name="silentPluginInfoVO.apkId"
                           value="${silentPluginInfoVO.apkId}"/>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">名称</td>
                <td>
                    <input type="text" class="input-medium" id="title" name="silentPluginInfoVO.title"
                           value="${silentPluginInfoVO.title}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">版本</td>
                <td>
                    <input type="text" class="input-medium" id="versions" name="silentPluginInfoVO.versions"
                           value="${silentPluginInfoVO.versions}"
                           onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">包名</td>
                <td>
                    <input type="text" class="input-medium" id="pkgName" name="silentPluginInfoVO.pkgName"
                           value="${silentPluginInfoVO.pkgName}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">启动参数</td>
                <td>
                    <input type="text" class="input-medium" id="startArgu" name="silentPluginInfoVO.startArgu"
                           value="${silentPluginInfoVO.startArgu}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">启动服务名</td>
                <td>
                    <input type="text" class="input-medium" id="startServiceName"
                           name="silentPluginInfoVO.startServiceName" value="${silentPluginInfoVO.startServiceName}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="state"
                              name="silentPluginInfoVO.state"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">插件类型</td>
                <td>
                    <s:select list="#{'8200700':'独立插件','8200701':'互斥插件','8200702':'其他插件' }" listKey="key"
                              listValue="value" id="apkType" name="silentPluginInfoVO.apkType"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">下载地址</td>
                <td>
                    <input type="text" size="60" class="input-xxlarge" id="wwwurl" name="silentPluginInfoVO.wwwurl"
                           value="${silentPluginInfoVO.wwwurl}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">排序</td>
                <td>
                    <input type="text" class="input-medium" id="rank" name="silentPluginInfoVO.rank"
                           value="${silentPluginInfoVO.rank}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title" rowspan="6">推广国家</td>
                <td>

                    亚洲
                    <input id="inlineAsiaCheckbox" onclick="javascript:change(this.value);" name="allAsia"
                           type="checkbox"
                           value="1000001">
                </td>
                <td colspan="5">
                    <s:iterator value="#Option.asiaCountryList" status="asia">
                        <input id="inline_<s:property value="id"/>" name="asiaCheckBox_<s:property value="name"/>"
                               type="checkbox" class="asiaClass"
                               value="<s:property value="id"/>" onchange="javascript:changeDetail(1000001);">
                        <s:property value="name"/>
                        <s:if test="(#asia.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>

                    北美洲<input id="inlineNorthCheckbox" onclick="javascript:change(this.value);" name="allNorth"
                              type="checkbox"
                              value="1000002">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.northAmericaCountryList" status="north">
                        <input id="inline_<s:property value="id"/>" name="northCheckBox_<s:property value="name"/>"
                               type="checkbox" class="northClass"
                               value="<s:property value="id"/>" onchange="javascript:changeDetail(1000002);">
                        <s:property value="name"/>
                        <s:if test="(#north.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>

                    南美洲<input id="inlineSouthCheckbox" onclick="javascript:change(this.value);" name="allSouth"
                              type="checkbox"
                              value="1000003">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.southAmericaCountryList" status="south">
                        <input id="inline_<s:property value="id"/>" name="southCheckBox_<s:property value="name"/>"
                               type="checkbox" class="southClass"
                               value="<s:property value="id"/>" onchange="javascript:changeDetail(1000003);">
                        <s:property value="name"/>
                        <s:if test="(#south.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>

                    大洋洲
                    <input id="inlineOceaniaCheckbox" onclick="javascript:change(this.value);" name="allOceania"
                           type="checkbox"
                           value="1000004">
                </td>
                <td colspan="2">

                    <s:iterator value="#Option.oceaniaCountryList" status="oceania">
                        <input id="inline_<s:property value="id"/>" name="oceaniaCheckBox_<s:property value="name"/>"
                               type="checkbox" class="oceaniaClass"
                               value="<s:property value="id"/>" onchange="javascript:changeDetail(1000004);">
                        <s:property value="name"/>
                        <s:if test="(#oceania.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>
                    欧洲
                    <input id="inlineEuropeCheckbox" onclick="javascript:change(this.value);" name="allEurope"
                           type="checkbox"
                           value="1000005">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.europeCountryList" status="europe">
                        <input id="inline_<s:property value="id"/>" name="europeCheckBox_<s:property value="name"/>"
                               type="checkbox" class="europeClass"
                               value="<s:property value="id"/>" onchange="javascript:changeDetail(1000005);">
                        <s:property value="name"/>
                        <s:if test="(#europe.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>
                    非洲
                    <input id="inlineAfricaCheckbox" onclick="javascript:change(this.value);" name="allAfrica"
                           type="checkbox"
                           value="1000006">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.africaCountryList" status="africa">
                        <input id="inline_<s:property value="id"/>" name="africaCheckBox_<s:property value="name"/>"
                               type="checkbox" class="africaClass"
                               value="<s:property value="id"/>" onchange="javascript:changeDetail(1000006);">
                        <s:property value="name"/>
                        <s:if test="(#africa.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input id="createBtn" type="button" class="btn btn-primary" value="确定"
                           onclick="return updateInfo();">
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="modal-footer">
</div>
<script type="text/javascript">
    function updateInfo() {
        var path = '<%=path%>/sdkinfo/SilentPluginInfo!save.action';

        var asiaChecked = $("input[name^='asiaCheckBox']:checked").val([]);
        var southChecked = $("input[name^='southCheckBox']:checked").val([]);
        var northChecked = $("input[name^='northCheckBox']:checked").val([]);
        var europeChecked = $("input[name^='europeCheckBox']:checked").val([]);
        var oceaniaChecked = $("input[name^='oceaniaCheckBox']:checked").val([]);
        var africaChecked = $("input[name^='africaCheckBox']:checked").val([]);

        var asiaCheckValues = "";
        for (var i = 0; i < asiaChecked.length; i++) {
            asiaCheckValues += asiaChecked[i].value + ",";
        }
        for (var i = 0; i < southChecked.length; i++) {
            asiaCheckValues += southChecked[i].value + ",";
        }
        for (var i = 0; i < northChecked.length; i++) {
            asiaCheckValues += northChecked[i].value + ",";
        }
        for (var i = 0; i < europeChecked.length; i++) {
            asiaCheckValues += europeChecked[i].value + ",";
        }
        for (var i = 0; i < oceaniaChecked.length; i++) {
            asiaCheckValues += oceaniaChecked[i].value + ",";
        }
        for (var i = 0; i < africaChecked.length; i++) {
            asiaCheckValues += africaChecked[i].value + ",";
        }

        var countryIds = asiaCheckValues;
        var id = $("#cid").val();
        var versions = $("#versions").val();
        var state = $("#state").val();
        var title = $("#title").val();
        var pkgName = $("#pkgName").val();
        var startArgu = $("#startArgu").val();
        var startServiceName = $("#startServiceName").val();
        var wwwurl = $("#wwwurl").val();
        var rank = $("#rank").val();
        var apkType = $("#apkType").val();
        var apkId = $("#apkId").val();
        if (wwwurl == "") {
            showInfoToastMiddle("请填写下载地址!");
            $("#wwwurl").focus();
            return false;
        }
        if (versions == "") {
            showInfoToastMiddle("请填写版本!");
            $("#versions").focus();
            return false;
        }
        $("#createBtn").attr("disabled", "disabled");

        jQuery.post(path, {
                    'silentPluginInfoVO.id': id,
                    'silentPluginInfoVO.versions': versions,
                    'silentPluginInfoVO.wwwurl': wwwurl,
                    'silentPluginInfoVO.pkgName': pkgName,
                    'silentPluginInfoVO.apkId': apkId,
                    'silentPluginInfoVO.apkType': apkType,
                    'silentPluginInfoVO.rank': rank,
                    'silentPluginInfoVO.startArgu': startArgu,
                    'silentPluginInfoVO.startServiceName': startServiceName,
                    'silentPluginInfoVO.startClass': '',
                    'silentPluginInfoVO.state': state,
                    'silentPluginInfoVO.version': 1,
                    'silentPluginInfoVO.title': title,
                    'silentPluginInfoVO.note': '',
                    'silentPluginInfoVO.creator': 1,
                    'silentPluginInfoVO.isOutDownload': 3200,
                    'silentPluginInfoVO.extensionContry': countryIds

                }
                , function (response) {
                    if (response == "-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display": "none"});
                    } else {
                        $("#load").css({"display": "none"});
                        showInfoToastMiddle(response);
                        doPageBottom('turn');
                    }
                });
    }

    var asiaSize = '<s:property value="#Option.asiaCountryList.size"/>';
    var northSize = '<s:property value="#Option.northAmericaCountryList.size"/>';
    var southSize = '<s:property value="#Option.southAmericaCountryList.size"/>';
    var oceaniaSize = '<s:property value="#Option.oceaniaCountryList.size"/>';
    var europeSize = '<s:property value="#Option.europeCountryList.size"/>';
    var africaSize = '<s:property value="#Option.africaCountryList.size"/>';

    function change(code) {
        if (code == 1000001) {
            if ($("#inlineAsiaCheckbox").prop("checked")) {
                $("input[name^='asiaCheckBox']").prop("checked", true);
            } else {
                $("input[name^='asiaCheckBox']").prop("checked", false);
            }
        } else if (code == 1000002) {
            if ($("#inlineNorthCheckbox").prop("checked")) {
                $("input[name^='northCheckBox']").prop("checked", true);
            } else {
                $("input[name^='northCheckBox']").prop("checked", false);
            }
        } else if (code == 1000003) {
            if ($("#inlineSouthCheckbox").prop("checked")) {
                $("input[name^='southCheckBox']").prop("checked", true);
            } else {
                $("input[name^='southCheckBox']").prop("checked", false);
            }
        } else if (code == 1000004) {
            if ($("#inlineOceaniaCheckbox").prop("checked")) {
                $("input[name^='oceaniaCheckBox']").prop("checked", true);
            } else {
                $("input[name^='oceaniaCheckBox']").prop("checked", false);
            }
        } else if (code == 1000005) {
            if ($("#inlineEuropeCheckbox").prop("checked")) {
                $("input[name^='europeCheckBox']").prop("checked", true);
            } else {
                $("input[name^='europeCheckBox']").prop("checked", false);
            }
        } else if (code == 1000006) {
            if ($("#inlineAfricaCheckbox").prop("checked")) {
                $("input[name^='africaCheckBox']").prop("checked", true);
            } else {
                $("input[name^='africaCheckBox']").prop("checked", false);
            }
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


    var country = '<s:property value="silentPluginInfoVO.extensionContry"/>';
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
</script>
