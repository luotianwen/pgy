<%--
  User: XJ
  Date: 2016/3/28
  Time: 9:49
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        查看广告
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">名称</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.appName}" />
                </td>
                <td class="table_td_title">接入人员</td>
                <td>
                    <s:select id="advLinkmanId" list="#Option.advLinkmanList" listKey="id" listValue="name" name="advSdkVO.advLinkmanId"
                              headerKey="0" disabled="true" headerValue="请选择接入人员" cssClass="selectWidth"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">包名</td>
                <td colspan="3">
                    <input type="text" size="100" readonly="readonly" class="input-medium" value="${advSdkVO.apkPackageName}" />
                </td>
            </tr>
            <tr>
                <td class="table_td_title">广告主</td>
                <td>
                    <s:select disabled="true" name="advSdkVO.cpname" list="#Option.adverList" listKey="id" listValue="name" cssClass="selectWidth"/>
                </td>
                <td class="table_td_title">cpid</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.cpid}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否推送</td>
                <td>
                    <s:select name="advSdkVO.pushStatus" disabled="true" headerValue="3200" list="#Option.enumStatus" listKey="key" listValue="value" cssClass="selectWidth"/>
                </td>
                <td class="table_td_title">是否插屏</td>
                <td>
                    <s:select name="advSdkVO.isTablePlaque" disabled="true" headerValue="3200"  list="#Option.enumStatus" listKey="key" listValue="value" cssClass="selectWidth"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否线下</td>
                <td>
                    <s:select name="advSdkVO.isSlient" disabled="true" headerValue="3200"  list="#Option.enumStatus" listKey="key" listValue="value" cssClass="selectWidth"/>
                </td>
                <td class="table_td_title">广告类型</td>
                <td>
                    <s:select name="advSdkVO.adtype" list="#Option.adType" disabled="true" listKey="key" listValue="value" cssClass="selectWidth"/>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">审核状态</td>
                <td>
                    <s:select name="advSdkVO.status" list="#Option.enumStatus" disabled="true" listKey="key" listValue="value" cssClass="selectWidth"/>
                </td>
                <td class="table_td_title">大小</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.sizes}" />
                </td>
            </tr>
            <tr>
                <td class="table_td_title">推送下发上限</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.tsUp}" />
                </td>
                <td class="table_td_title">插屏下发上限</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.cpUp}" />
                </td>
            </tr>
            <tr>
                <td class="table_td_title">线下下发上限</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.cap}" />
                </td>
                <td class="table_td_title">md5</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.passnote}" />
                </td>
            </tr>

            <tr>
                <td class="table_td_title">延迟时间</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.dalyTime}" />
                </td>
                <td class="table_td_title">单价</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.price}" />
                </td>
            </tr>
            <tr>
                <td class="table_td_title">cpm</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.orders}" />

                </td>
                <td class="table_td_title">激活率</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.jhl}" />
                </td>
            </tr>
            <tr>
                <td class="table_td_title">存留时间</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.clsj}" />

                </td>
                <td class="table_td_title">安装区域</td>
                <td>
                    <s:select name="advSdkVO.dataOrSys" list="#{'8200800':'data','8200801':'sys' }" listKey="key" listValue="value" cssClass="selectWidth" disabled="true"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">次日存留</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.retentionRate}" />
                </td>
                <td class="table_td_title">激活条件</td>
                <td>
                    <s:select name="advSdkVO.actionStatus" disabled="true" list="#{'8200900':'暗屏','8200901':'亮屏','8200902':'暗亮屏','8200903':'不激活' }" listKey="key" listValue="value" headerValue="8200902" cssClass="selectWidth"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">点击次数</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.tracinglinkc}" />
                </td>
                <td class="table_td_title">cp转化率</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.cpConversionRate}" />
                </td>
            </tr>
            <tr>
            <tr>
                <td class="table_td_title">线下cpm</td>
                <td>
                    <input type="text" readonly="readonly" class="input-medium" value="${advSdkVO.silenceCpm}" />
                </td>
            </tr>
            <tr>
                <td class="table_td_title">tracing link</td>
                <td colspan="3">
                    <textarea readonly="readonly" rows="3" cols="100">${advSdkVO.tracinglink}</textarea>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">广告语</td>
                <td colspan="3">
                    <textarea readonly="readonly" rows="2" cols="100">${advSdkVO.pushText}</textarea>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">说明</td>
                <td colspan="3">
                    <textarea readonly="readonly" rows="2" cols="100">${advSdkVO.introduction}</textarea>
                </td>
            </tr>
            <%--<tr>
                <td class="table_td_title">APK下载</td>
                <td  colspan="3">
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" name="advSdkVO.isOutDownload"  cssClass="selectWidth"/>
                </td>
            </tr>--%>
            <tr>
                <td class="table_td_title">APK下载地址</td>
                <td colspan="3">
                    <input readonly="readonly" size="100" type="text" class="input-medium" value="${advSdkVO.outwww}" />
                </td>
            </tr>
            <%--<tr>
                <td class="table_td_title">ICON下载</td>
                <td  colspan="3">
                    <s:select name="advSdkVO.isouticon" list="#Option.enumStatus" listKey="key" listValue="value" cssClass="selectWidth"/>
                </td>
            </tr>--%>
            <tr>
                <td class="table_td_title">ICON下载地址</td>
                <td  colspan="4">
                    <%--<input size="100" readonly="readonly" type="text" class="input-medium" value="${advSdkVO.outiconwww}" />--%>
                    <s:if test='advSdkVO.outiconwww==null||advSdkVO.outiconwww==""'>
                        没有图片
                    </s:if>
                    <s:else>
                        <img src="${advSdkVO.outiconwww}" title="${advSdkVO.outiconwww}" alt="${advSdkVO.outiconwww}"/>
                    </s:else>
                </td>
            </tr>
            <%--<tr>
                <td class="table_td_title">插屏下载</td>
                <td  colspan="3">
                    <s:select name="advSdkVO.isoutcptp" list="#Option.enumStatus" listKey="key" listValue="value" cssClass="selectWidth"/>
                </td>
            </tr>--%>
            <tr>
                <td class="table_td_title">插屏下载地址</td>
                <td colspan="3">
                <%--<input size="100" readonly="readonly" type="text" class="input-medium" value="${advSdkVO.outcptpwww}" />--%>
                    <s:if test='advSdkVO.outcptpwww==null||advSdkVO.outcptpwww==""'>
                        没有图片
                    </s:if>
                    <s:else>
                        <img src="${advSdkVO.outcptpwww}" title="${advSdkVO.outcptpwww}" alt="${advSdkVO.outcptpwww}"/>
                    </s:else>
                </td>
            </tr>
            <%--<tr>
                <td class="table_td_title">推广国家别名列表</td>
                <td colspan="3">
                    <input size="100" type="text" class="input-medium" value="${advSdkVO.language}" onload="checkCountryName(this)"/>
                </td>
            </tr>--%>
            <tr>
                <td class="table_td_title" rowspan="6">推广国家</td>
                <td>
                    亚洲<input id="inlineAsiaCheckbox" type="checkbox" disabled="disabled">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.asiaCountryList" status="asia">
                        <input type="checkbox" id="inline_<s:property value="id"/>" disabled="disabled" class="asiaClass">
                        <s:property value="name"/>
                        <s:if test="(#asia.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>
                    北美洲<input id="inlineNorthCheckbox" type="checkbox" disabled="disabled">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.northAmericaCountryList" status="north">
                        <input type="checkbox" id="inline_<s:property value="id"/>" class="northClass" disabled="disabled">
                        <s:property value="name"/>
                        <s:if test="(#north.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>
                    南美洲<input id="inlineSouthCheckbox" type="checkbox" disabled="disabled">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.southAmericaCountryList" status="south">
                        <input type="checkbox" id="inline_<s:property value="id"/>" class="southClass" disabled="disabled">
                        <s:property value="name"/>
                        <s:if test="(#south.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>
                    大洋洲<input id="inlineOceaniaCheckbox" type="checkbox" disabled="disabled">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.oceaniaCountryList" status="oceania">
                        <input type="checkbox" id="inline_<s:property value="id"/>" class="oceaniaClass" disabled="disabled">
                        <s:property value="name"/>
                        <s:if test="(#oceania.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>
                    欧洲<input id="inlineEuropeCheckbox" type="checkbox" disabled="disabled">
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.europeCountryList" status="europe">
                        <input type="checkbox" id="inline_<s:property value="id"/>" class="europeClass" disabled="disabled">
                        <s:property value="name"/>
                        <s:if test="(#europe.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
                <td>
                    非洲<input id="inlineAfricaCheckbox" type="checkbox" disabled="disabled"/>
                </td>
                <td colspan="2">
                    <s:iterator value="#Option.africaCountryList" status="africa">
                        <input type="checkbox" id="inline_<s:property value="id"/>" class="africaClass" disabled="disabled"/>
                        <s:property value="name"/>
                        <s:if test="(#africa.index+1)%5==0"><br/></s:if>
                    </s:iterator>
                </td>
            </tr>
            <tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    var asiaSize = '<s:property value="#Option.asiaCountryList.size"/>';
    var northSize = '<s:property value="#Option.northAmericaCountryList.size"/>';
    var southSize = '<s:property value="#Option.southAmericaCountryList.size"/>';
    var oceaniaSize = '<s:property value="#Option.oceaniaCountryList.size"/>';
    var europeSize = '<s:property value="#Option.europeCountryList.size"/>';
    var africaSize = '<s:property value="#Option.africaCountryList.size"/>';

    function changeDetail(code) {
        if(code==1000001)  {
            var aa = $("input.asiaClass:checked");
            var len = aa.length;
            if(len >= asiaSize){
                $("#inlineAsiaCheckbox").prop("checked",true);
            } else {
                $("#inlineAsiaCheckbox").prop("checked",false);
            }
        } else if(code==1000002)  {
            var aa = $("input.northClass:checked");
            var len = aa.length;
            if(len >= northSize){
                $("#inlineNorthCheckbox").prop("checked",true);
            } else {
                $("#inlineNorthCheckbox").prop("checked",false);
            }
        } else if(code==1000003)  {
            var aa = $("input.southClass:checked");
            var len = aa.length;
            if(len >= southSize){
                $("#inlineSouthCheckbox").prop("checked",true);
            } else {
                $("#inlineSouthCheckbox").prop("checked",false);
            }
        } else if(code==1000004)  {
            var aa = $("input.oceaniaClass:checked");
            var len = aa.length;
            if(len >= oceaniaSize){
                $("#inlineOceaniaCheckbox").prop("checked",true);
            } else {
                $("#inlineOceaniaCheckbox").prop("checked",false);
            }
        } else if(code==1000005)  {
            var aa = $("input.europeClass:checked");
            var len = aa.length;
            if(len >= europeSize){
                $("#inlineEuropeCheckbox").prop("checked",true);
            } else {
                $("#inlineEuropeCheckbox").prop("checked",false);
            }
        } else if(code==1000006)  {
            var aa = $("input.africaClass:checked");
            var len = aa.length;
            if(len >= africaSize){
                $("#inlineAfricaCheckbox").prop("checked",true);
            } else {
                $("#inlineAfricaCheckbox").prop("checked",false);
            }
        }
    }

    var country = '<s:property value="advSdkVO.language"/>';
    var data = country.toString().split(",");
    for(var i = 0; i < data.length; i = i + 1){
        $("#inline_" + data[i]).prop("checked", true);
    }

    changeDetail(1000001);
    changeDetail(1000002);
    changeDetail(1000003);
    changeDetail(1000004);
    changeDetail(1000005);
    changeDetail(1000006);
</script>

