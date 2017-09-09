<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:hidden id="cid" name="advSdkVO.id"/>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="advSdkVO.id==0">新建广告</s:if>
        <s:if test="advSdkVO.id>0">修改广告</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title" style="color:red">名称</td>
                <td>
                    <input id="appName" type="text"  class="input-medium" name="advSdkVO.appName"
                           value="${advSdkVO.appName}"/>

                </td>
                <td class="table_td_title">接入人员</td>
                <td>
                    <s:select id="advLinkmanId" list="#Option.advLinkmanList" listKey="id" listValue="name" name="advSdkVO.advLinkmanId"
                              headerKey="0" headerValue="请选择接入人员" cssClass="selectWidth"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title"  style="color:red">包名</td>
                <td colspan="3">
                    <input id="apkPackageName" type="text" size="100" class="input-medium" name="advSdkVO.apkPackageName"
                           value="${advSdkVO.apkPackageName}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">广告主</td>
                <td>
                    <s:select id="cpname" list="#Option.adverList" listKey="id" listValue="name" name="advSdkVO.cpname"
                              cssClass="selectWidth"></s:select>
                </td>
                <td class="table_td_title">cpid</td>
                <td>
                    <input id="cpid" type="text" class="input-medium" name="advSdkVO.cpid" value="${advSdkVO.cpid}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否推送</td>
                <td>
                    <s:select id="pushStatus" headerValue="3200" list="#Option.enumStatus" listKey="key"
                              listValue="value" name="advSdkVO.pushStatus" cssClass="selectWidth"></s:select>

                </td>
                <td class="table_td_title">是否插屏</td>
                <td>
                    <s:select id="isTablePlaque" headerValue="3200" list="#Option.enumStatus" listKey="key"
                              listValue="value" name="advSdkVO.isTablePlaque" cssClass="selectWidth"></s:select>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否线下</td>
                <td>
                    <s:select id="isSlient" headerValue="3200" list="#Option.enumStatus" listKey="key" listValue="value"
                              name="advSdkVO.isSlient" cssClass="selectWidth"></s:select>

                </td>
                <s:hidden id="isGprsDownLoad" name="advSdkVO.isGprsDownLoad"/>
                <s:hidden id="isDel" name="advSdkVO.isDel"/>
                <s:hidden id="isNotification" name="advSdkVO.isNotification"/>
                <s:hidden id="isInterface" name="advSdkVO.isInterface"/>
                <%--<td class="table_td_title">是否预下载</td>
                <td>
                    <s:select id="isGprsDownLoad"   headerValue="3200"  list="#Option.enumStatus" listKey="key" listValue="value" name="advSdkVO.isGprsDownLoad"  cssClass="selectWidth"></s:select>

                </td>
                </tr>
                <tr>
                <td class="table_td_title">是否删除</td>
                <td>
                    <s:select id="isDel"   headerValue="3201"  list="#Option.enumStatus" listKey="key" listValue="value" name="advSdkVO.isDel"  cssClass="selectWidth"></s:select>

                </td>
                <td class="table_td_title">通知栏是否清除</td>
                <td>
                    <s:select id="isNotification"  headerValue="3201"   list="#Option.enumStatus" listKey="key" listValue="value" name="advSdkVO.isNotification"  cssClass="selectWidth"></s:select>
                </td>
                </tr>
                <tr>
                <td class="table_td_title">提示界面是否可清除</td>
                <td>
                    <s:select id="isInterface"   headerValue="3201"  list="#Option.enumStatus" listKey="key" listValue="value" name="advSdkVO.isInterface"  cssClass="selectWidth"></s:select>

                </td>   --%>
                <s:hidden id="apkStatus" name="advSdkVO.apkStatus"/>
                <%--<td class="table_td_title">apk状态</td>
                <td>
                    <s:select id="apkStatus"  list="#Option.apkStatus" listKey="key" listValue="value" name="advSdkVO.apkStatus"  cssClass="selectWidth"></s:select>
                </td>
                </tr>
                <tr>   --%>
                <td class="table_td_title">广告类型</td>
                <td>
                    <s:select id="adtype" list="#Option.adType" listKey="key" listValue="value" name="advSdkVO.adtype"
                              cssClass="selectWidth"></s:select>

                </td>
                <s:hidden id="atype" name="advSdkVO.atype"/>
                <%--<td class="table_td_title">应用类型</td>
                <td>
                    <s:select id="atype"  list="#Option.atype" listKey="key" listValue="value" name="advSdkVO.atype"  cssClass="selectWidth"></s:select>
                </td>--%>
            </tr>
            <tr>
                <td class="table_td_title">审核状态</td>
                <td>
                    <s:select id="status" list="#Option.enumStatus" listKey="key" listValue="value"
                              name="advSdkVO.status" cssClass="selectWidth"></s:select>

                </td>
                <td class="table_td_title">大小</td>
                <td>
                    <input id="sizes" type="text" class="input-medium" name="advSdkVO.sizes" value="${advSdkVO.sizes}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title"  style="color:red">推送下发上限</td>
                <td>
                    <input id="tsUp" type="text" class="input-medium" name="advSdkVO.tsUp" value="${advSdkVO.tsUp}"/>

                </td>
                <td class="table_td_title" style="color:red">插屏下发上限</td>
                <td>
                    <input id="cpUp" type="text" class="input-medium" name="advSdkVO.cpUp" value="${advSdkVO.cpUp}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title" style="color:red">线下下发上限</td>
                <td>
                    <input id="cap" type="text" class="input-medium" name="advSdkVO.cap" value="${advSdkVO.cap}"/>

                </td>
                <td class="table_td_title" >md5</td>
                <td>
                    <input id="passnote" type="text" class="input-medium" name="advSdkVO.passnote"
                           value="${advSdkVO.passnote}"/>
                </td>
            </tr>

            <tr>
                <td class="table_td_title">延迟时间</td>
                <td>
                    <input id="dalyTime" type="text" class="input-medium" name="advSdkVO.dalyTime"
                           value="${advSdkVO.dalyTime}"/>

                </td>
                <td class="table_td_title">单价</td>
                <td>
                    <input id="price" type="text" class="input-medium" name="advSdkVO.price" value="${advSdkVO.price}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title" style="color:red">cpm</td>
                <td>
                    <input id="orders" type="text" class="input-medium" name="advSdkVO.orders"
                           value="${advSdkVO.orders}"/>

                </td>
                <td class="table_td_title">激活率</td>
                <td>
                    <input id="jhl" type="text" class="input-medium" name="advSdkVO.jhl" value="${advSdkVO.jhl}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">存留时间</td>
                <td>
                    <input id="clsj" type="text" class="input-medium" name="advSdkVO.clsj" value="${advSdkVO.clsj}"/>

                </td>
                <td class="table_td_title">安装区域</td>
                <td>
                    <s:select id="dataOrSys" list="#{'8200800':'data','8200801':'sys' }" listKey="key" listValue="value"
                              name="advSdkVO.dataOrSys" cssClass="selectWidth"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">次日存留</td>
                <td>
                    <input id="retentionRate" type="text" class="input-medium" name="advSdkVO.retentionRate"
                           value="${advSdkVO.retentionRate}"/>

                </td>
                <td class="table_td_title">激活条件</td>
                <td>
                    <s:select id="actionStatus" list="#{'8200900':'暗屏','8200901':'亮屏','8200902':'暗亮屏','8200903':'不激活' }"
                              listKey="key" listValue="value" name="advSdkVO.actionStatus" headerValue="8200902"
                              cssClass="selectWidth"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">点击次数</td>
                <td>
                    <input id="tracinglinkc" type="text" class="input-medium" name="advSdkVO.tracinglinkc"
                           value="${advSdkVO.tracinglinkc}"/>
                </td>
                <td class="table_td_title">cp转化率</td>
                <td>
                    <input id="cpConversionRate" type="text" class="input-medium" name="advSdkVO.cpConversionRate"
                           value="${advSdkVO.cpConversionRate}"/>
                </td>
            </tr>
            <tr>
            <tr>
                <td class="table_td_title" style="color:red">线下cpm</td>
                <td>
                    <input id="silenceCpm" type="text" class="input-medium" name="advSdkVO.silenceCpm"
                           value="${advSdkVO.silenceCpm}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">tracing link</td>
                <td colspan="3">
                    <textarea rows="3" cols="100" id="tracinglink">${advSdkVO.tracinglink}</textarea>
                </td>
            </tr>
            <tr>
                <td class="table_td_title" style="color:red">广告语</td>
                <td colspan="3">
                    <textarea rows="2" cols="100" id="pushText">${advSdkVO.pushText}</textarea>
                </td>
            </tr>
            <tr>
                <td class="table_td_title" style="color:red">说明</td>
                <td colspan="3">
                    <textarea rows="2" cols="100" id="introduction">${advSdkVO.introduction}</textarea>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">APK下载</td>
                <td colspan="3">
                    <s:select id="isOutDownload" list="#Option.enumStatus" listKey="key" listValue="value"
                              name="advSdkVO.isOutDownload" cssClass="selectWidth"></s:select>

                </td>
            </tr>
            <tr>
                <td class="table_td_title" style="color:red">APK下载地址</td>
                <td colspan="3">
                    <input id="outwww" size="100" type="text" class="input-medium" name="advSdkVO.outwww"
                           value="${advSdkVO.outwww}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">ICON下载</td>
                <td colspan="3">
                    <s:select id="isouticon" list="#Option.enumStatus" listKey="key" listValue="value"
                              name="advSdkVO.isouticon" cssClass="selectWidth"></s:select>

                </td>
            </tr>
            <tr>
                <td class="table_td_title" style="color:red">ICON下载地址</td>
                <td colspan="3">
                    <input id="outiconwww" size="100" type="text" class="input-medium" name="advSdkVO.outiconwww"
                           value="${advSdkVO.outiconwww}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">插屏下载</td>
                <td colspan="3">
                    <s:select id="isoutcptp" list="#Option.enumStatus" listKey="key" listValue="value"
                              name="advSdkVO.isoutcptp" cssClass="selectWidth"></s:select>

                </td>
            </tr>
            <tr>
                <td class="table_td_title" style="color:red">插屏下载地址</td>
                <td colspan="3">
                    <input id="outcptpwww" size="100" type="text" class="input-medium" name="advSdkVO.outcptpwww"
                           value="${advSdkVO.outcptpwww}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">推广国家别名列表</td>
                <td colspan="3">
                    <input id="countryName" size="100" type="text" class="input-medium" name="advSdkVO.countryName"
                           value="${advSdkVO.countryName}" onblur="checkCountryName(this)"/>
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
                <td colspan="2">
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


            <tr>
                <td colspan="4">
                    <input id="createBtn" type="button" class="btn btn-primary" value="确 定"
                           onclick="return updateInfo();">
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="modal-footer">
</div>
<script type="text/javascript">

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


    var country = '<s:property value="advSdkVO.language"/>';
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
