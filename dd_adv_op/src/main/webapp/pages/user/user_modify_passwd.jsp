<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript"
	src="<%=path%>/pages/user/js/user.js?version=1.3"></script>
<div class="row-fluid">
	<div class="span12">
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"> <i class="icon-align-justify"></i> </span>
				<h5>
					�޸�����
				</h5>
			</div>
			<div class="widget-content nopadding">
				<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="oldPwd">
							ԭ����
							<font color="red">*</font>
						</label>
						<div class="controls">
							<input type="text" class="input-xlarge" name="userVO.loginId"
								id="oldpwd" onblur="javascript:checkPasswd(this.value)">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label line" for="newPwd">
							������
							<font color="red">*</font>
						</label>
						<div class="controls">
							<input type="text" class="input-xlarge" name="userVO.passwd"
								id="newpwd">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label line" for="newPwd">
							���ٴ�����������
							<font color="red">*</font>
						</label>
						<div class="controls">
							<input type="text" class="input-xlarge" name="userVO.passwd"
								id="cmfpwd">
						</div>
					</div>
					<div class="form-actions">
						<button type="button" id="surebtn" class="btn btn-primary"
							onclick="javascript:passwd();">
							�� ��
						</button>
						<button type="reset" class="btn">
							����
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
