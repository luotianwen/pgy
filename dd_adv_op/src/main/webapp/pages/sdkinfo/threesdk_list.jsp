<%--
  User: XJ
  Date: 2016/4/1
  Time: 10:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>

<%
    String path = request.getContextPath() + "/sdkinfo";
%>

<s:if test="threeSdkVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
    <div class="span12">
        <div class="form-inline">
            <fieldset>
                <table style="width:100%">
                    <tr>
                        <td>
                            <input class="btn btn-success" type="button" onclick="create()" value="新增"/>
                        </td>
                        <td align="right">
                            <div id="ajax-modal" class="modal fade" tabindex="-1"></div>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            名称 <input id="name" type="text" class="input-medium inline" name="sdkInfoVO.name"
                                   value="<s:property value="threeSdkVO.name"/>" style="width: 150px">
                            <button type="button" class="btn btn-primary" onclick="searchList()">查 询</button>
                            <button type="reset" class="btn btn-primary">清 空</button>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </div>
</div>
<div class="box box-primary" style="margin-top: 15px;overflow:auto">
    <div class="span12">
        <form class="form-horizontal" id="dataForm">
            <fieldset>
                <div>
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <th>名称</th>
                                <th>说明</th>
                                <th>导出排序</th>
                                <th>备注</th>
                                <th>创建时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="dataTable">
</s:if>
                        <s:iterator value="threeSdkVOList">
                        <tr>
                            <td><s:property value="name"/></td>
                            <td><s:property value="note"/></td>
                            <td><s:property value="sort"/> </td>
                            <td><s:property value="remark"/></td>
                            <td><s:property value="createDate"/></td>
                            <td>
                                <s:if test="status==3200">是</s:if>
                                <s:else>否</s:else>
                            </td>
                            <td>
                                <a class="btn btn-primary"
                                   href="javascript: modify('<s:property value="id"/>')" title="修改">
                                    修改
                                </a>
                            </td>
                        </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="7">
                                <div class="pagelist" id="pagelist1" align="right">
                                    <page:paginationAjax formName="threeSdkForm" property="threeSdkVO.page" operation="/sdkinfo/ThreeSdk!list.action"/>
                                </div>
                            </td>
                        </tr>
<s:if test="threeSdkVO.isPage==0">
                        </tbody>
                    </table>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<script type="text/javascript">
    function getArgus() {
        var argus ="threeSdkVO.page.pageNum="+$("#pageNum").val()
                +"&threeSdkVO.page.pageSize="+$("#pageSize").val()
                +"&threeSdkVO.isPage=1";
        // 查询参数
        argus += "&threeSdkVO.name="+$("#name").val();
        return argus;
    }
    function searchList() {
        var name = $("#name").val();
        $("#load").css({"display":""});
        jQuery("#dataTable").html("");
        var url = "<%=path%>/ThreeSdk!list.action";
        jQuery.post(url,
                {
                    "threeSdkVO.name":name,
                    "threeSdkVO.isPage":1
                },
                function(response){
                    if(response=="-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display":"none"});
                    } else {
                        jQuery("#dataTable").html(jQuery.trim(response));
                        $("#load").css({"display":"none"});
                    }
                });
    }
    function create() {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load('<%=path%>/ThreeSdk!create.action', {}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function modify(id) {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load('<%=path%>/ThreeSdk!modify.action', {"threeSdkVO.id": id}, function () {
                $modal.modal();
            });
        }, 100);
    }
</script>
</s:if>