<%--
  User: XJ
  Date: 2016/3/31
  Time: 11:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="offlinesdkVO.id==0">新建线下SDK</s:if>
        <s:if test="offlinesdkVO.id>0">修改线下SDK</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">时间间隔</td>
                <td>
                    <input id="timeStep" type="number"  value="<s:property value="offlinesdkVO.timeStep"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">版本</td>
                <td>
                    <input id="version" type="number"  value="<s:property value="offlinesdkVO.version"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#{'1':'开','0':'关'}" listKey="key" listValue="value"
                              id="status" name="offlinesdkVO.status"></s:select>
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
 <%--临时加js--%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/kkpay.js"></script>
<script type="text/javascript">
    function updateInfo() {
        var path = '<%=path%>/offline/Offsdk!save.action';
        var id = '<s:property value="offlinesdkVO.id"/>';
        var timeStep = $("#timeStep").val();
        var version = $("#version").val();
        var status = $("#status").val();
        if (timeStep == "" || timeStep<=0) {
            showInfoToastMiddle("请填写时间间隔！并且大于0");
            return false;
        }
        if (version == "" || version<=0) {
            showInfoToastMiddle("请填写版本号！并且大于0");
            return false;
        }

        $("#createBtn").attr("disabled", "disabled");
        jQuery.post(path, {
                    'offlinesdkVO.id': id,
                    'offlinesdkVO.timeStep': timeStep,
                    'offlinesdkVO.version': version,
                    'offlinesdkVO.status': status
                }
                , function (response) {
                    if (response == "-1") {
                        showInfoToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display": "none"});
                    } else {
                        $("#load").css({"display": "none"});
                        showInfoToastMiddle(response);
                    }
                });
    }
</script>