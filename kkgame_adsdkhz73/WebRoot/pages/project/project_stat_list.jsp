<%--
  Created by IntelliJ IDEA.
  User: XJ
  Date: 2016/3/21
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
<script src="<%=request.getContextPath()%></>/js/bootbox.min.js"></script>
<table id="dataTable" class="table table-bordered table-striped table-hover">
    <s:set name="totalPackegaNum" value="0"/>
    <tbody>
    <s:iterator value="projectStatVOList" status="st">
        <tr>
            <s:if test="projectStatVO.isShowTime == 1">
                <td><s:property value="time"/></td>
            </s:if>
            <td><s:property value="bdName"/></td>
            <td>
                <s:if test="status==1">新建</s:if>
                <s:if test="status==2">待技术审核</s:if>
                <s:if test="status==3">待出包</s:if>
                <s:if test="status==4">已出包</s:if>
                <s:if test="status==5">测试通过</s:if>
                <s:if test="status==6">技术参数错误</s:if>
                <s:if test="status==7">审核未通过</s:if>
            </td>
            <td><s:property value="packegaNum"/></td>
        </tr>
        <s:set name="totalPackegaNum" value="#totalPackegaNum + packegaNum"/>
    </s:iterator>
    <tr>
        <td colspan="<s:if test="projectStatVO.isShowTime == 1">3</s:if><s:else>2</s:else>"><div align="right">总计</div></td>
        <td><s:property value="#totalPackegaNum"/></td>
    </tr>
    </tbody>
    <thead>
    <tr>
        <s:if test="projectStatVO.isShowTime == 1">
            <th>日期</th>
        </s:if>
        <th>商务</th>
        <th>状态</th>
        <th>出包个数</th>
    </tr>
    </thead>
</table>


