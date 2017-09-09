<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
    String path = request.getContextPath()+"/sdkinfo";
%>
<s:hidden id="uSdkId" name="projectSdkVO.sdkId"/>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="projectSdkVO.sdkId==0">新建项目SDK</s:if>
        <s:if test="projectSdkVO.sdkId>0">修改项目SDK</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">项目Id</td>
                <td>
                    <select id="uProjectId" name="searchVO.projectId" class="selectWidth"
                           >
                        <s:action name="getAllProject" namespace="/customer" executeResult="true" >
                        </s:action>
                    </select>
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


    function update() {

        var projectId = $("#uProjectId").val();
        if('undefined'==typeof(projectId)) {
            projectId=0;
        }
        var sdkId = $("#uSdkId").val();

        if (0 >= projectId ) {
            showInfoToastMiddle("请选择项目Id！");
            return false;
        }


        $("#createBtn").attr("disabled", "disabled");
        var path = '<%=path%>/ThreeSdk!insideSave.action';
        jQuery.post(path, {
                    'projectSdkVO.projectId': projectId,
                    'projectSdkVO.sdkId': sdkId,
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