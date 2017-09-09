<%@ page import="com.kkgame.feeop.util.CalendarFormat" %>
<%--
  User: XJ
  Date: 2016/4/13
  Time: 15:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>下载导入模版</h2>
</div>
<div class="modal-body">
    <form id="downloadForm">
        <table class="table">
            <tr>
                <td>时间</td>
                <td>
                    <input type="text" id="exportDate" name="exportDate" onfocus="javascript:WdatePicker()" readonly="readonly"
                           value='<%=CalendarFormat.getCurrentYmdDate()%>' class="selectwidth">
                </td>
            </tr>
            <tr>
                <td>天数</td>
                <td>
                    <input type="number" id="logDateNum" name="logDateNum" value="2" class="selectwidth"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" id="sureBtn" class="btn btn-primary" value="确定" onclick="downloadImportTemplate()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function downloadImportTemplate() {
        var exportDate = $("#exportDate").val();
        if ("" == exportDate) {
            showInfoToastMiddle("请选择待录入数据的时间");
            return;
        }
        var logDateNum = $("#logDateNum").val();
        if ("" == logDateNum) {
            showInfoToastMiddle("请选择查看日志天数");
            return;
        }
        $("#sureBtn").attr("disabled" ,'disabled');
        var exportForm = document.getElementById("downloadForm");
        exportForm.action = '<%=request.getContextPath()%>' + '/record/ProjectHzTotalModel!downloadImportTemplate.action';
        exportForm.submit();
    }
</script>
