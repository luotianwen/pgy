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
        <s:if test="offlinejarVO.id==0">新建线下JAR</s:if>
        <s:if test="offlinejarVO.id>0">修改线下JAR</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">启动类</td>
                <td>
                    <input id="startClass" type="text" size="60" value="<s:property value="offlinejarVO.startClass"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">启动参数</td>
                <td>
                    <input id="startArgu" type="text" size="60" value="<s:property value="offlinejarVO.startArgu"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">下载地址</td>
                <td>
                    <input id="url" type="text" size="60" value="<s:property value="offlinejarVO.url"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">MD5</td>
                <td>
                    <input id="md5" type="text" size="60" value="<s:property value="offlinejarVO.md5"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">插件ID</td>
                <td>
                    <input id="apkId" type="number"  value="<s:property value="offlinejarVO.apkId"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">版本</td>
                <td>
                    <input id="version" type="number"  value="<s:property value="offlinejarVO.version"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#{'1':'开','0':'关'}" listKey="key" listValue="value"
                              id="status" name="offlinejarVO.status"></s:select>
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
        var path = '<%=path%>/offline/Offjar!save.action';
        var id = '<s:property value="offlinejarVO.id"/>';
        var apkId = $("#apkId").val();
        var startArgu = $("#startArgu").val().trim();
        var url = $("#url").val().trim();
        var startClass = $("#startClass").val().trim();
        var md5 = $("#md5").val().trim();

        var version = $("#version").val();
        var status = $("#status").val();
        if (apkId == "" || apkId <=0) {
            showInfoToastMiddle("请填写插件id！并且大于0");
            return false;
        }
        if (url == "") {
            showInfoToastMiddle("请填写下载地址！");
            return false;
        }
        if (startArgu == "") {
            showInfoToastMiddle("请填写启动参数！");
            return false;
        }
        if (startClass == "") {
            showInfoToastMiddle("请填写启动类！");
            return false;
        }
        if (version == "" || version <=0) {
            showInfoToastMiddle("请填写版本号！并且大于0");
            return false;
        }

        $("#createBtn").attr("disabled", "disabled");
        jQuery.post(path, {
                    'offlinejarVO.id': id,
                    'offlinejarVO.apkId': apkId,
                    'offlinejarVO.startClass': startClass,
                    'offlinejarVO.startArgu': startArgu,
                    'offlinejarVO.url': url,
                    'offlinejarVO.md5': md5,
                    'offlinejarVO.version': version,
                    'offlinejarVO.status': status
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