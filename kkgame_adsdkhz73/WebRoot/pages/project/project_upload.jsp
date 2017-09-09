<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
<s:hidden id="projectId" name="projectVO.id"></s:hidden>
<s:hidden id="status" name="projectVO.status"></s:hidden>
<s:hidden id="queryType" name="projectVO.queryType"></s:hidden>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<div class="container">
<table class="table">
		<tr>
			<td class="table_td_title">项目名称-ID<font color="red">*</font></td>			 
            <td>
            	<s:property value="projectVO.name"/>&nbsp;&nbsp;-&nbsp;&nbsp;P.[<font color="red"><s:property value="projectVO.id"/></font>]
            </td>
            <td class="table_td_title">渠道商名称<font color="red">*</font></td>			 
            <td><s:property value="projectVO.schemeName"/></td>
		</tr>	
		<role:equal type="SP,MG,BD">
		<tr >
			<td class="table_td_title">所属代理商<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.agentList">
					<s:if test="id==projectVO.agentId">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>	
		<tr ><td class="table_td_title">商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bdList">
					<s:if test="projectVO.bdId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		<tr ><td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bhList">
					<s:if test="projectVO.bhId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		</role:equal>
		<role:equal type="AG">
		<tr ><td class="table_td_title">商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bdList">
					<s:if test="projectVO.bdId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>
		<tr ><td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bhList">
					<s:if test="projectVO.bhId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		</role:equal>				
		<role:equal type="SP,MG,AG,BD">	
		<tr ><td class="table_td_title">客户<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.customerList">
					<s:if test="projectVO.customerId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>		
		</tr>
		</role:equal>
		<tr >
			<td class="table_td_title">合作模式<font color="red">*</font></td>			 
            <td >
            	<s:iterator value="#Policy.cooperateTypeList">
					<s:if test="projectVO.cooperateType==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">推广方式<font color="red">*</font></td>			 
            <td>
            	<s:iterator value="#Policy.populariseTypeList">
					<s:if test="projectVO.populariseType==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">平台<font color="red">*</font></td>			 
            <td >
            	<s:iterator value="#Policy.osList">
					<s:if test="projectVO.osId==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">分辨率<font color="red">*</font></td>			 
            <td>
            	<s:iterator value="#Policy.screenList">
					<s:if test="projectVO.screenId==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">产品<font color="red">*</font></td>			 
            <td colspan="3" id="inData">
            	
			</td>
		</tr>
		<tr >
			<td class="table_td_title">备注</td>			 
            <td colspan="3"><textarea cols="50" rows="5" ><s:property value="projectVO.intro"/></textarea></td>
		</tr>
	</table>
</div>	
<div>
<table class="table">

<tr>
<td class="table_td_title">包文件</td>	
<td>
	<input class="btn" type="file" id="file" name="file" />
	<img src="<%=request.getContextPath()%>/images/loading.gif" id="loading" style="display: none;">
</td>
</tr>
<tr >
	<td class="table_td_title">备注</td>			 
    <td ><textarea cols="50" rows="5" id="info"></textarea></td>
</tr>
<tr>
<td colspan="2">
	<input type="button" class="btn btn-primary" value="上传" onclick="return ajaxFileUpload();">
   	<input id="btn2" class="btn" type="button" value=" 返 回 " onclick="doBack('<s:property value="projectVO.queryType"/>');"/>&nbsp;&nbsp;
</td>
</tr>
</table>
</div>

<script type="text/javascript">
function ajaxFileUpload()
{
	$("#loading").ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
    });//文件上传完成将图片隐藏起来  
    var info = $("#info").val();
    var file = $("#file").val();
    if(file=="") {
    	showInfoToastMiddle("请选择上传文件包!");
    	$("#file").focus();
    	return false;
    }
    $.ajaxFileUpload({
    	url:'Project!uploadFile.action?projectVO.id='+<s:property value="projectVO.id"/>+'&projectVO.status='+<s:property value="projectVO.status"/>+'&projectFileVO.info='+info,//用于文件上传的服务器端请求地址
        secureuri:false,//一般设置为false
        fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
        dataType: 'json',//返回值类型 一般设置为json
        success: function (data, status) {
            //服务器成功响应处理函数 
            showInfoToastMiddle(data.msg);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
        },
        error: function (data, status, e) {
            	//服务器响应失败处理函数
       		alert(e);
        }
    });
    return false;
}
</script> 	