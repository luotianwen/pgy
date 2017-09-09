<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
    String path = request.getContextPath()+"/sdkinfo";
%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="projectSdkVO.projectId==0">新建项目SDK</s:if>
        <s:if test="projectSdkVO.projectId>0">修改项目SDK</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">项目Id</td>
                <td>
                    <select id="uProjectId" name="searchVO.projectId" class="selectWidth"
                            <s:if test="projectSdkVO.projectId>0">disabled="disabled"</s:if>>
                        <s:action name="getAllProject" namespace="/customer" executeResult="true">
                        </s:action>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">sdkId</td>
                <td>
                    <select id="uSdkId" name="searchVO.sdkId" class="selectWidth"
                            <s:if test="projectSdkVO.projectId>0">disabled="disabled"</s:if>>
                        <s:action name="getAllThreeSdk" namespace="/sdkinfo" executeResult="true">
                        </s:action>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">sdkKey</td>
                <td>
                    <input type="text" class="input-medium" id="sdkKey" name="projectSdkVO.sdkKey" value="${projectSdkVO.sdkKey}" />
                </td>
            </tr>
            <tr>
                <td class="table_td_title">说明</td>
                <td>
                    <input type="text" class="input-medium" id="note" name="projectSdkVO.note" value="${projectSdkVO.note}" />
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input id="createBtn" type="button" class="btn btn-primary" value="确定"
                           onclick="return update();">
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    $("#uProjectId").select2();
    $("#uSdkId").select2();

    function update() {
        var oldProjectId = '<s:property value="projectSdkVO.projectId"/>';
        var oldSdkId = '<s:property value="projectSdkVO.sdkId"/>';

        var projectId = $("#uProjectId").val();
        if('undefined'==typeof(projectId)) {
            projectId=0;
        }
        var sdkId = $("#uSdkId").val();
        if('undefined'==typeof(sdkId)) {
            sdkId=0;
        }
        if (0 >= projectId || 0 >= sdkId) {
            showInfoToastMiddle("请选择项目Id或sdkId！");
            return false;
        }
        var sdkKey = $("#sdkKey").val();
        var note = $("#note").val();

        $("#createBtn").attr("disabled", "disabled");
        var path = '<%=path%>/ThreeSdk!projectSdkSave.action';
        jQuery.post(path, {
                    'projectSdkVO.oldProjectId': oldProjectId,
                    'projectSdkVO.oldSdkId': oldSdkId,
                    'projectSdkVO.projectId': projectId,
                    'projectSdkVO.sdkId': sdkId,
                    'projectSdkVO.sdkKey': sdkKey,
                    'projectSdkVO.note': note,
                    'projectSdkVO.isPage':1
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