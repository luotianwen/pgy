<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  User: XJ
  Date: 2016/4/15
  Time: 9:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="advLinkmanVO.id==0">新建接入人员</s:if>
        <s:else>更新接入人员</s:else>
    </h2>
</div>
<div class="modal-body">
    <table class="table">
        <tr>
            <td class="td_lable">姓名</td>
            <td>
                <input id="name" class="input-medium" value="<s:property value="advLinkmanVO.name"/>"/>
            </td>
            <td class="td_lable">电话</td>
            <td>
                <input id="phone" class="input-medium" value="<s:property value="advLinkmanVO.phone"/>"/>
            </td>
        </tr>
        <tr>
            <td class="td_lable">QQ</td>
            <td>
                <input id="qq" class="input-medium" value="<s:property value="advLinkmanVO.qq"/>"/>
            </td>
            <td class="td_lable">地址</td>
            <td>
                <input id="address" class="input-medium" value="<s:property value="advLinkmanVO.address"/>"/>
            </td>
        </tr>
        <tr>
            <td class="td_lable">TCNL</td>
            <td>
                <input type="number" id="tcbl" class="input-medium" value="<s:property value="advLinkmanVO.tcbl"/>"/>
            </td>
            <td class="td_lable">状态</td>
            <td>
                <s:select id="deleted" list="#{0:'是',1:'否'}" name="advLinkmanVO.deleted"/>
            </td>
        </tr>
    </table>
</div>
<div class="modal-footer">
    <button type="button" id="createBtn" onclick="return update()" class="btn btn-primary">确定</button>
</div>
<script type="text/javascript">
    function update() {
        var id = '<s:property value="advLinkmanVO.id"/>';
        var name = $("#name").val();
        if ("" == name) {
            showInfoToastMiddle("请填写姓名");
            $("#name").focus();
            return false;
        }
        var phone = $("#phone").val();
        if ("" == phone) {
            showInfoToastMiddle("请填写联系方式");
            $("#phone").focus();
            return false;
        }
        var tcbl = $("#tcbl").val();
        if ("" == tcbl) {
            showInfoToastMiddle("请填写tcbl");
            $("#phone").focus();
            return false;
        }
        var qq = $("#qq").val();
        var address = $("#address").val();
        var deleted = $("#deleted").val();

        $("#createBtn").attr("disabled", "disabled");
        var url = '<%=request.getContextPath()%>'+"/adver/AdvLinkman!save.action";
        $.post(url, {
            'advLinkmanVO.id': id,
            'advLinkmanVO.name': name,
            'advLinkmanVO.phone': phone,
            'advLinkmanVO.qq': qq,
            'advLinkmanVO.address': address,
            'advLinkmanVO.tcbl': tcbl,
            'advLinkmanVO.deleted': deleted,
            'advLinkmanVO.isPage':1
        }, function (response) {
            $("#load").css({"display":"none"});
            if ("-1" == response) {
                showErrorToastMiddle("系统出错，请重试或联系管理员");
            } else {
                showInfoToastMiddle(response);
                doPageBottom('turn');
            }
        });
    }
</script>
