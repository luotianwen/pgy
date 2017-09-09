<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
    String path = request.getContextPath()+"/sdkinfo";
%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="threeSdkVO.id==0">新建第三方SDK</s:if>
        <s:if test="threeSdkVO.id>0">修改第三方SDK</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">名称</td>
                <td>
                    <input type="text" class="input-medium" id="tname" value="${threeSdkVO.name}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">说明</td>
                <td>
                    <textarea rows="3" cols="50" id="note">${threeSdkVO.note}</textarea>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">导出排序</td>
                <td>
                    <input type="number" class="input-medium" id="sort" value="${threeSdkVO.sort}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="status"
                              name="threeSdkVO.status"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">备注</td>
                <td>
                    <textarea rows="4" cols="50" id="remark">${threeSdkVO.remark}</textarea>
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
    function update() {
        var id = '<s:property value="threeSdkVO.id"/>';

        var name = $("#tname").val();
        if (name == "") {
            showInfoToastMiddle("请填写名称!");
            $("#name").focus();
            return false;
        }
        var note = $("#note").val();
        var status = $("#status").val();
        var remark = $("#remark").val();
        var sort = $("#sort").val();
        $("#createBtn").attr("disabled", "disabled");

        var path = '<%=path%>/ThreeSdk!save.action';
        jQuery.post(path, {
                    'threeSdkVO.id': id,
                    'threeSdkVO.name': name,
                    'threeSdkVO.note': note,
                    'threeSdkVO.status': status,
                    'threeSdkVO.remark': remark,
                    'threeSdkVO.sort': sort,
                    'threeSdkVO.isPage':1
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