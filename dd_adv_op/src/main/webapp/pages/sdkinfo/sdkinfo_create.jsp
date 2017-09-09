<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:hidden id="cid" name="sdkInfoVO.id"/>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="sdkInfoVO.id==0">新建SDK</s:if>
        <s:if test="sdkInfoVO.id>0">修改SDK</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">SdkId</td>
                <td>
                    <input type="number" class="input-medium" id="sdkId" name="sdkInfoVO.sdkId" value="${sdkInfoVO.sdkId}"/>
                </td>
                <td class="table_td_title">名称</td>
                <td>
                    <input type="text" class="input-medium" id="name" name="sdkInfoVO.name" value="${sdkInfoVO.name}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">版本</td>
                <td>
                    <input type="text" class="input-medium" id="sdkVersion" name="sdkInfoVO.sdkVersion"
                           value="${sdkInfoVO.sdkVersion}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>

                </td>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#{'0':'关闭','1':'打开'}" listKey="key" listValue="value" id="status"
                              name="sdkInfoVO.status"></s:select>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">启动类</td>
                <td colspan="3">
                    <input type="text" size="55" class="input-xxlarge" id="startClass" value="${sdkInfoVO.startClass}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">md5</td>
                <td colspan="3">
                    <input type="text" size="55" class="input-xxlarge" id="md5" value="${sdkInfoVO.md5}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">启动参数</td>
                <td>
                    <input type="text" class="input-medium" id="startArg" value="${sdkInfoVO.startArg}"/>
                </td>
                <td class="table_td_title">类类型</td>
                <td>
                    <s:select list="#{'0':'接口','1':'静态类'}" listKey="key" listValue="value" id="classType"
                              name="sdkInfoVO.classType"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">排序等级</td>
                <td>
                    <input type="number" class="input-medium" id="rank" value="${sdkInfoVO.rank}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">备注</td>
                <td colspan="3">
                    <textarea rows="5" cols="55" id="descInfo"
                              name="sdkInfoVO.descInfo">${sdkInfoVO.descInfo}</textarea>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">SDK文件地址</td>
                <td colspan="3">
                    <input type="text" size="55" class="input-xxlarge" id="url" name="sdkInfoVO.url"
                           value="${sdkInfoVO.url}"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <input id="createBtn" type="button" class="btn btn-primary" value="确定"
                           onclick="return updateInfo();">
                </td>
            </tr>
        </table>
    </form>
</div>
<%--<div class="modal-footer">--%>
<%--</div>--%>
<script type="text/javascript">
    function updateInfo() {
        var path = '<%=path%>/sdkinfo/SdkInfo!save.action';
        var id = $("#cid").val();
        var sdkId = $("#sdkId").val();
        if (sdkId == "" || sdkId == 0) {
            showInfoToastMiddle("请填写 SdkId !");
            $("#sdkId").focus();
            return false;
        }
        var name = $("#name").val();
        var status = $("#status").val();
        if (name == "") {
            showInfoToastMiddle("请填写SDK名称!");
            $("#name").focus();
            return false;
        }
        var sdkVersion = $("#sdkVersion").val();
        var descInfo = $("#descInfo").val();
        var url = $("#url").val();
        if (sdkVersion == "") {
            showInfoToastMiddle("请填写版本!");
            $("#sdkVersion").focus();
            return false;
        }
        if (url == "") {
            showInfoToastMiddle("请填写下载地址!");
            $("#url").focus();
            return false;
        }
        var md5 = $("#md5").val();
        if(md5==""){
            showInfoToastMiddle("请填写md5");
            $("#md5").focus();
            return false;
        }

        var startClass = $("#startClass").val();
        var startArg = $("#startArg").val();
        if ("" == startClass) {
            showInfoToastMiddle("请填写启动类");
            $("#startClass").focus();
            return false;
        }
        if ("" == startArg) {
            showInfoToastMiddle("请填写启动参数");
            $("#startArg").focus();
            return false;
        }
        var rank = $("#rank").val();
        var classType = $("#classType").val();

        $("#createBtn").attr("disabled", "disabled");
        jQuery.post(path, {
                    'sdkInfoVO.id': id,
                    'sdkInfoVO.sdkId':sdkId,
                    'sdkInfoVO.sdkVersion': sdkVersion,
                    'sdkInfoVO.descInfo': descInfo,
                    'sdkInfoVO.status': status,
                    'sdkInfoVO.url': url,
                    'sdkInfoVO.name': name,
                    'sdkInfoVO.md5': md5,
                    'sdkInfoVO.startClass': startClass,
                    'sdkInfoVO.startArg': startArg,
                    'sdkInfoVO.classType': classType,
                    'sdkInfoVO.rank': rank
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

</script>