<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:hidden id="cid" name="silentInfoVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
	<s:if test="silentInfoVO.id==0">新建线下配置</s:if>
	<s:if test="silentInfoVO.id>0">修改线下配置</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<table class="table">
				<tr>
				<td class="table_td_title">线下频率</td>
				<td>
					<input type="text" class="input-medium" id="frequency" name="silentInfoVO.frequency" value="${silentInfoVO.frequency}"  onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">线下次数</td>
				<td>
					<input type="text" class="input-medium" id="times" name="silentInfoVO.times" value="${silentInfoVO.times}"  onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">线下下发广告数</td>
				<td>
					<input type="text" class="input-medium" id="days" name="silentInfoVO.sdkVersion" value="${silentInfoVO.days}"  onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">版本号</td>	
				<td>
					<input type="text" class="input-medium" id="versions" name="silentInfoVO.versions" value="${silentInfoVO.versions}"  onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">状态</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="status" name="silentInfoVO.status"></s:select>
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">备注</td>	
				<td>
					<textarea rows="5" cols="50" id="note" name="silentInfoVO.note">${silentInfoVO.note}</textarea>
				</td>
				</tr>
				<tr>
				<td colspan="2">
					<input id="createBtn" type="button" class="btn btn-primary" value="确 定" onclick="return updateInfo();">
				</td>
				</tr>
			</table>
	</form>
</div>
<div class="modal-footer">
</div> 
<script type="text/javascript">
function updateInfo() {
	var path = '<%=path%>/sdkinfo/SilentInfo!save.action';
	var id =  $("#cid").val();
    var frequency =  $("#frequency").val();
    var days =  $("#days").val();
    var times =  $("#times").val();
    var versions =  $("#versions").val();
    var status =  $("#status").val();
    var note =  $("#note").val();

	$("#createBtn").attr("disabled", "disabled");

    jQuery.post(path,{'silentInfoVO.id':id,
       	'silentInfoVO.frequency':frequency,
       	'silentInfoVO.days':days,
       	'silentInfoVO.times':times,
       	'silentInfoVO.status':status,
       	'silentInfoVO.versions':versions,
       	'silentInfoVO.version':1,
       	'silentInfoVO.creator':1,
       	'silentInfoVO.note':note,
       	'silentInfoVO.yl1':'',
       	'silentInfoVO.yl2':'',
       	'silentInfoVO.idDel':3201
    	}
       	,function(response){
			if(response=="-1") {
				showErrorToastMiddle("系统出错，请重试或联系管理员");
				$("#load").css({"display":"none"});
			} else {
				$("#load").css({"display":"none"});
				showInfoToastMiddle(response);
	           	doPageBottom('turn');
			}
		});
}
</script>
