<%--
  User: XJ
  Date: 2016/3/31
  Time: 10:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
%>

<s:if test="refreshRow==0">
<input class="btn btn-success" type="button" onclick="createDomaiin('<%=path %>/ddl')" value="新增">
<div id="ajax-modal" class="modal fade" tabindex="-1" data-width="1000"></div>
<div class="box box-primary" style="margin-top: 15px;overflow:auto">
    <div class="span12">
        <form class="form-horizontal" id="dataForm">
            <fieldset>
                <div id="dataList">
                    <table id="dataTable" class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th>外放域名</th>
                            <th>下载域名</th>
                            <th>创建时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="domainListTBody">
</s:if>
                            <s:set name="totalStatus" value="0"/>
                                <s:iterator value="domainVOList">
                                    <tr>
                                        <td><s:property value="wwwDomain"/></td>
                                        <td><s:property value="downDomain"/></td>
                                        <td><s:property value="cdate"/></td>
                                        <td>
                                            <s:if test="status==3200">
                                                是
                                                <s:set name="totalStatus" value="#totalStatus+1"/>
                                            </s:if>
                                            <s:else>否</s:else>
                                        </td>
                                        <td>
                                            <a class="btn btn-primary"
                                               href="javascript: modifyDomain('<%=path%>/ddl','<s:property value="id"/>')"
                                               title="修改">
                                                修改
                                            </a>
                                        </td>
                                    </tr>
                            </s:iterator>
                            <input type="hidden" id="domainTotalStatus" value="<s:property value="#totalStatus"/>"/>
<s:if test="refreshRow==0">
                        </tbody>
                    </table>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<script type="text/javascript">
    function getDomainTotalStatus() {
        return $("#domainTotalStatus").val();
    }

    function createDomaiin(path) {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load(path + '/DdlProject!domainCreate.action', {}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function modifyDomain(path, id) {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load(path + '/DdlProject!domainModify.action', {"domainVO.id": id}, function () {
                $modal.modal();
            });
        }, 100);
    }
</script>
</s:if>