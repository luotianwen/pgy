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
        <s:if test="operatorVO.id==0">新建运营商</s:if>
        <s:if test="operatorVO.id>0">修改运营商</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">运营商CODE码</td>
                <td>
                    <input id="code" type="text" size="20" value="<s:property value="operatorVO.code"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">运营商名</td>
                <td>
                    <input id="operatorName" type="text" size="100" value="<s:property value="operatorVO.operatorName"/>"/>
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
<script type="text/javascript">
    function updateInfo() {
        var path = '<%=path%>/record/Operator!dosave.action';
        var id = '<s:property value="operatorVO.id"/>';
        var code = $("#code").val().trim();
        var operatorName = $("#operatorName").val().trim();
        if (code == "") {
            showInfoToastMiddle("请填写CODE码！");
            return false;
        }
        if (operatorName == "") {
            showInfoToastMiddle("请填写运营商名！");
            return false;
        }

        $("#createBtn").attr("disabled", "disabled");
        jQuery.post(path, {
                    'operatorVO.operatorId': id,
                    'operatorVO.code': code,
                    'operatorVO.operatorName': operatorName
                }
                , function (response) {
                    if (response == "-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display": "none"});
                    } else {
                        $("#load").css({"display": "none"});
                        if (status==3201 && originalStatus==3200 && id>0) {
                            response += "<br/>注意！必须要有一个外放域名噢，记得添加。";
                        }
                        showInfoToastMiddle(response);
                        jQuery.post('<%=path%>/ddl/DdlProject!domainList.action',{"refreshRow":1},function(response){
                            if(response==-1) {
                                showErrorToast("提示","系统出错，请重试或联系管理员");
                            } else {
                                $("#operatorTBody").html(jQuery.trim(response));
                            }
                        });
                    }
                });
    }
</script>