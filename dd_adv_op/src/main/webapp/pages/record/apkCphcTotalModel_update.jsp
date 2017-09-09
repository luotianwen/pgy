<%--
  User: XJ
  Date: 2016/4/20
  Time: 15:07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>修改广告收益单价</h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">时间</td>
                <td>
                    <input type="text" onfocus="javascript:WdatePicker()" value="<s:property value="apkCphcTotalModelVO.sdate"/>" disabled="disabled">
                </td>
            </tr>
            <tr>
                <td class="table_td_title">单价</td>
                <td>
                    <input id="uPrice" type="number" value="<s:property value="apkCphcTotalModelVO.price"/>">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input id="uPriceBtn" type="button" class="btn btn-primary" value="确定" onclick="return updatePrice()">
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function updatePrice() {
        var id = '<s:property value="apkCphcTotalModelVO.apkid"/>';
        var createTime = '<s:property value="apkCphcTotalModelVO.sdate"/> ';

        var uPrice = $("#uPrice").val();
        if ("" == uPrice) {
            showInfoToastMiddle("请输入价格");
            return false;
        }
        $("#uPriceBtn").attr('disabled','disabled');
        var url = '<%=request.getContextPath()%>/record/ApkCphcTotalModel!updatePrice.action';
        $.post(url, {
            'apkCphcTotalModelVO.apkid':id,
            'apkCphcTotalModelVO.sdate':createTime,
            'apkCphcTotalModelVO.price':uPrice
        },function(response){
            if ("-1" == response) {
                showErrorToastMiddle("系统出错，请重试或联系管理员");
            } else {
                showInfoToastMiddle(response.trim());
                doPageBottom('turn');
            }
        });
    }
</script>
