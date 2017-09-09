<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
%>

<s:if test="operatorVO.isPage==0">
    <input class="btn btn-success" type="button" onclick="createOperator('<%=path %>/record')" value="新增">
    <div id="ajax-modal" class="modal fade" tabindex="-1" data-width="1000"></div>
    <div class="box box-primary" style="margin-top: 15px;overflow:auto">
    <div class="span12">
    <form class="form-horizontal" id="dataForm">
    <fieldset>
    <div id="dataList">
    <table id="dataTable" class="table table-bordered table-striped table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>运营商CODE码</th>
        <th>运营商名</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="operatorTBody">
</s:if>
<s:iterator value="operatorVOList">
    <tr>
        <td><s:property value="operatorId"/></td>
        <td><s:property value="code"/></td>
        <td><s:property value="operatorName"/></td>
        <td>
            <a class="btn btn-primary"
               href="javascript: modifyOperator('<%=path%>/record','<s:property value="id"/>')"
               title="修改">
                修改
            </a>
        </td>
    </tr>
</s:iterator>
<tr>
    <td colspan="8">
        <div class="pagelist" id="pagelist1" align="right">
            <page:paginationAjax formName="productForm" property="operatorVO.page" operation="/record/Operator!list.action"/>
        </div>
    </td>
</tr>
</tbody>
</table>
</div>
</fieldset>
</form>
</div>
</div>
<script type="text/javascript">

    function createOperator(path) {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load(path + '/Operator!create.action', {}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function modifyOperator(path, id) {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load(path + '/Operator!modify.action', {"operatorVO.operatorId": id}, function () {
                $modal.modal();
            });
        }, 100);
    }
</script>
