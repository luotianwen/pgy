<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:hidden id="cid" name="desktopInfo.id"/>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="desktopInfo.id==0">新建桌面快捷方式</s:if>
        <s:if test="desktopInfo.id>0">修改桌面快捷方式</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title" style="width: 80px; color:red">名称</td>
                <td>
                    <input id="deskName" type="text" class="input-medium" name="desktopInfo.deskName"
                           value="${desktopInfo.deskName}"/>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">图标</td>
                <td>
                    <input id="deskIconUrl" type="text" size="80" class="input-medium" name="desktopInfo.deskIconUrl"
                           value="${desktopInfo.deskIconUrl}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title" style="color:red">点击链接</td>
                <td colspan="3">
                    <input id="homePage" type="text" size="80" class="input-medium" name="desktopInfo.homePage"
                           value="${desktopInfo.homePage}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select id="status" list="#Option.selectList" listKey="key" listValue="value"
                              name="desktopInfo.status" cssClass="selectWidth"></s:select>

                </td>
            </tr>

            <tr>

            <tr>
                <td colspan="4">
                    <input id="createBtn" type="button" class="btn btn-primary" value="确 定"
                           onclick="javascript:updatedesktopinfo('<%=path %>');">
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="modal-footer">
</div>