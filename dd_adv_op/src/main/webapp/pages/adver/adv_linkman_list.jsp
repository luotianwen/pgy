<%--
  User: XJ
  Date: 2016/4/15
  Time: 10:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%
    String path = request.getContextPath() + "/adver";
%>
<s:if test="advLinkmanVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
    <div class="span12">
        <div class="form-inline">
            <fieldset>
                <table style="width: 100%">
                    <tr>
                        <td>
                            <button type="button" onclick="create()" class="btn btn-success">新增</button>
                            <div id="ajax-modal" class="modal fade" tabindex="-1" data-width="600px"/>
                        </td>
                        <td align="right">
                            <label class="control-label inline" for="sName">姓名</label>
                            <input type="text" class="input-medium" id="sName"/>
                            <label class="control-label inline" for="sDeleted">状态</label>
                            <s:select list="#{'-1':'全部','0':'是','1':'否'}" id="sDeleted" value="0"  />
                            <button type="button" class="btn btn-primary" onclick="searchList()">查询</button>
                            <button type="reset" class="btn btn-primary">清空</button>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </div>
</div>
<div class="box box-primary" style="margin-top: 15px; overflow: auto;">
    <div class="span12">
        <table id="dataTable" class="table table-bordered table-striped table-hover">
</s:if>
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>QQ</th>
                    <th>地址</th>
                    <th>tcbl</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <s:iterator value="advLinkmanVOList">
            <tr>
                <td><s:property value="name"/></td>
                <td><s:property value="phone"/></td>
                <td><s:property value="qq"/></td>
                <td><s:property value="address"/></td>
                <td><s:property value="tcbl"/></td>
                <td>
                    <s:if test="deleted==0">是</s:if>
                    <s:elseif test="deleted==1">否</s:elseif>
                    <s:else>未知</s:else>
                </td>
                <td>
                    <button type="button" onclick="modify('<s:property value="id"/>')" class="btn btn-primary">修改</button>
                </td>
            </tr>
            </s:iterator>
            <tr>
                <td colspan="7">
                    <div class="pagelist" id="pagelist1" align="right">
                        <page:paginationAjax formName="advLinkmanForm" property="advLinkmanVO.page" operation="/adver/AdvLinkman!list.action"/>
                    </div>
                </td>
            </tr>
<s:if test="advLinkmanVO.isPage==0">
        </table>
    </div>
</div>
<script type="text/javascript">
    function getArgus() {
        var argus ="";
        argus += "advLinkmanVO.page.pageNum=" + $("#pageNum").val();
        argus += "&advLinkmanVO.page.pageSize=" + $("#pageSize").val();
        argus += "&advLinkmanVO.name=" + $("#sName").val();
        argus += "&advLinkmanVO.deleted=" + $("#sDeleted").val();
        argus += "&advLinkmanVO.isPage=1";
        return argus;
    }
    function  searchList() {
        var name = $("#sName").val();
        var deleted = $("#sDeleted").val();

        $("#load").css({"display":""});
        jQuery("#dataTable").html("");
        var url = "<%=path%>/AdvLinkman!list.action";
        $.post(url, {
            'advLinkmanVO.name':name,
            'advLinkmanVO.deleted':deleted,
            'advLinkmanVO.isPage':1
        }, function(response){
            $("#load").css('display','none');
            if ("-1" == response) {
                showErrorToastMiddle("系统出错，请重试或联系管理员");
            } else {
                $("#dataTable").html($.trim(response));
            }
        });
    }

    var $modal = $("#ajax-modal");
    $modal.on('click', '.update', function(){
        $modal.modal('loading');
//        setTimeout(function(){
//            $modal.modal('loading').find('.modal-body')
//                    .prepend('<div class="alert alert-info fade in">' +
//                            'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
//                            '</div>');
//        }, 100);
    });

    function create() {
        $('body').modalmanager('loading');
        setTimeout(function(){
            $modal.load('<%=path%>/AdvLinkman!create.action', {}, function(){
                $modal.modal();
            });
        }, 100);
    }
    function modify(id) {
        $('body').modalmanager('loading');
        setTimeout(function(){
            $modal.load('<%=path%>/AdvLinkman!modify.action', {"advLinkmanVO.id":id}, function(){
                $modal.modal();
            });
        }, 100);
    }
</script>
</s:if>