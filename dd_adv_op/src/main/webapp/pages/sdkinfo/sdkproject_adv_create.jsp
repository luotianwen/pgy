<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:hidden id="cid" name="sdkProjectVO.id"/>
<s:hidden id="capk" name="sdkProjectVO.apk"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
	<s:if test="sdkProjectVO.id==0">新建项目广告配置</s:if>
	<s:if test="sdkProjectVO.id>0">修改项目广告配置</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<table class="table">
				<tr>
				<td class="table_td_title">coo_id</td>	
				<td>
					<input type="text" disabled="disabled" class="input-medium" id="coo_id" name="sdkProjectVO.coo_id" value="${sdkProjectVO.coo_id}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
					
				</td>
				<td class="table_td_title">项目名称</td>	
				<td>
					<input type="text" disabled="disabled" class="input-medium" id="apkName" name="sdkProjectVO.apkName" value="${sdkProjectVO.apkName}"  />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">开启推送</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="status" name="sdkProjectVO.status"></s:select>
				</td>
				<td class="table_td_title">开启插屏</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isTablePlaque" name="sdkProjectVO.isTablePlaque"></s:select>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">推送广告上限</td>	
				<td>
					<input type="text"  size="20"  class="input-medium" id="lower" name="sdkProjectVO.lower" value="${sdkProjectVO.lower}"  />
				</td>
				<td class="table_td_title">推送广告频率</td>	
				<td>
					<input type="text"  size="20"  class="input-medium" id="timss" name="sdkProjectVO.timss" value="${sdkProjectVO.timss}"  />
				</td>
				</tr>
				
				<tr>
				<td class="table_td_title">插屏广告上限</td>	
				<td>
					<input type="text"  size="20"  class="input-medium" id="tablePlaqueLower" name="sdkProjectVO.tablePlaqueLower" value="${sdkProjectVO.tablePlaqueLower}"  />
				</td>
				<td class="table_td_title">插屏广告频率</td>	
				<td>
					<input type="text"  size="20"  class="input-medium" id="tablePlaqueTimss" name="sdkProjectVO.tablePlaqueTimss" value="${sdkProjectVO.tablePlaqueTimss}"  />
				</td>
				</tr>
				
				<tr>
				<td class="table_td_title">是否插件下沉</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isTablePlaqueDown" name="sdkProjectVO.isTablePlaqueDown"></s:select>
				</td>
				<td class="table_td_title">是否打开插件升级</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isOpen" name="sdkProjectVO.isOpen"></s:select>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">是否可清除</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isNotice" name="sdkProjectVO.isNotice"></s:select>
				</td>
				<td class="table_td_title">是否弹出提示界面</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isPops" name="sdkProjectVO.isPops"></s:select>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">插屏广告效果</td>	
				<td>
					<s:select list="#Option.advEffect" listKey="key" listValue="value" id="isReturnDebug" name="sdkProjectVO.isReturnDebug"></s:select>
				</td>
				<td class="table_td_title">插屏效果方式</td>	
				<td>
					<s:select list="#Option.effectType" listKey="key" listValue="value" id="isCjTablePlaque" name="sdkProjectVO.isCjTablePlaque"></s:select>
				</td>
				</tr>
				
				<tr>
				<td class="table_td_title">插屏展示广告数</td>	
				<td colspan="3">
					<input type="text"  size="50"  class="input-medium" id="isGame" name="sdkProjectVO.isGame" value="${sdkProjectVO.isGame}"  onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">是否预下载</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="ydownload" name="sdkProjectVO.ydownload"></s:select>
				</td>
				<td class="table_td_title">审核</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="pass" name="sdkProjectVO.pass"></s:select>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">开启线下安装</td>
				<td colspan="3">
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isCjPush" name="sdkProjectVO.isCjPush"></s:select>
				</td>
				</tr>
				<tr>
					<td class="table_td_title">开启链接广告</td>
					<td colspan="3">
						<s:select list="#{1:'是',0:'否'}" listKey="key" listValue="value" id="isLink" name="sdkProjectVO.isLink"></s:select>
					</td>
				</tr>

			<tr>
				<td class="table_td_title">非浏览器每天弹出次数</td>
				<td>
					<input type="text"   class="input-medium" id="noBrowserTimes" name="sdkProjectVO.noBrowserTimes" value="${sdkProjectVO.noBrowserTimes}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>

				</td>
				<td class="table_td_title">非浏览器弹出间隔时间(分钟)</td>
				<td>
					<input type="text"   class="input-medium" id="noBrowserInterval" name="sdkProjectVO.noBrowserInterval" value="${sdkProjectVO.noBrowserInterval}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" />

				</td>
			</tr>

		<tr>
			<td class="table_td_title">状态栏次数</td>
			<td>
				<input type="text"   class="input-medium" id="statusBarTimes" name="sdkProjectVO.statusBarTimes" value="${sdkProjectVO.statusBarTimes}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>

			</td>
			<td class="table_td_title">状态栏频率(分钟)</td>
			<td>
				<input type="text"   class="input-medium" id="statusBarInterval" name="sdkProjectVO.statusBarInterval" value="${sdkProjectVO.statusBarInterval}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" />

			</td>
		</tr>
		<tr>
			<td class="table_td_title">桌面创建图标个数</td>
			<td>
				<input type="text"   class="input-medium" id="desktopTimes" name="sdkProjectVO.desktopTimes" value="${sdkProjectVO.desktopTimes}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>

			</td>
			<td class="table_td_title">桌面创建频率(分钟)</td>
			<td>
				<input type="text"   class="input-medium" id="desktopInterval" name="sdkProjectVO.desktopInterval" value="${sdkProjectVO.desktopInterval}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" />

			</td>
		</tr>


				<tr>
				<td colspan="4">
					<input id="createBtn" type="button" class="btn btn-primary" value="确定" onclick="return updateInfo();">
				</td>
				</tr>
			</table>
	</form>
</div>
<div class="modal-footer">
</div> 
<script type="text/javascript">
function updateInfo() {
	var path = '<%=path%>/sdkinfo/SdkProject!advSave.action';
	var id =  $("#cid").val();
    var apk =  $("#capk").val();
    var status =  $("#status").val();
    var isTablePlaque =  $("#isTablePlaque").val();
    var lower =  $("#lower").val();
    var timss =  $("#timss").val();
    var tablePlaqueLower =  $("#tablePlaqueLower").val();
    var tablePlaqueTimss =  $("#tablePlaqueTimss").val();
    var isTablePlaqueDown =  $("#isTablePlaqueDown").val();
    var isOpen =  $("#isOpen").val();
    var isNotice =  $("#isNotice").val();
    var isReturnDebug =  $("#isReturnDebug").val();
    var isCjTablePlaque =  $("#isCjTablePlaque").val();
    var isGame =  $("#isGame").val();
    var ydownload =  $("#ydownload").val();
    var pass =  $("#pass").val();
    var isCjPush =  $("#isCjPush").val();
    var isPops =  $("#isPops").val();

	var noBrowserTimes =  $("#noBrowserTimes").val();
	var noBrowserInterval =  $("#noBrowserInterval").val();
	var statusBarTimes =  $("#statusBarTimes").val();
	var statusBarInterval =  $("#statusBarInterval").val();
	var desktopTimes =  $("#desktopTimes").val();
	var desktopInterval =  $("#desktopInterval").val();
	var isLink =  $("#isLink").val();

	$("#createBtn").attr("disabled", "disabled");

    jQuery.post(path,{'sdkProjectVO.id':id,
		'sdkProjectVO.noBrowserTimes':noBrowserTimes,
	    'sdkProjectVO.noBrowserInterval':noBrowserInterval,
		'sdkProjectVO.statusBarTimes':statusBarTimes,
		'sdkProjectVO.statusBarInterval':statusBarInterval,
       	'sdkProjectVO.desktopTimes':desktopTimes,
		'sdkProjectVO.desktopInterval':desktopInterval,
		'sdkProjectVO.isLink':isLink,
		'sdkProjectVO.apk':apk,
       	'sdkProjectVO.status':status,
       	'sdkProjectVO.isTablePlaque':isTablePlaque,
       	'sdkProjectVO.lower':lower,
       	'sdkProjectVO.timss':timss,
       	'sdkProjectVO.tablePlaqueLower':tablePlaqueLower,
       	'sdkProjectVO.tablePlaqueTimss':tablePlaqueTimss,
       	'sdkProjectVO.isTablePlaqueDown':isTablePlaqueDown,
       	'sdkProjectVO.isOpen':isOpen,
       	'sdkProjectVO.isPops':isPops,
       	'sdkProjectVO.isNotice':isNotice,
       	'sdkProjectVO.isReturnDebug':isReturnDebug,
       	'sdkProjectVO.isCjTablePlaque':isCjTablePlaque,
       	'sdkProjectVO.isGame':isGame,
       	'sdkProjectVO.ydownload':ydownload,
       	'sdkProjectVO.pass':pass,
       	'sdkProjectVO.isCjPush':isCjPush,
       	'sdkProjectVO.creator':1,
       	'sdkProjectVO.member':1,
       	'sdkProjectVO.type':600400,
       	'sdkProjectVO.advType':100200,
       	'sdkProjectVO.version':1,
       	'sdkProjectVO.country':''
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
