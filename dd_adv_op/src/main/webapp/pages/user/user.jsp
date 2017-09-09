<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="./js/user.js"></script>

<%String toolbarRootPath = request.getContextPath() + "/pages/contentmanage/images/"; %>

<style type="text/css">
.form {
	background-color: #EAF0FB;
}

.radioContainer {
	margin-bottom: 2px;
}

.comboContainer1 {
	margin-bottom: 1px;
}

.comboContainer2 {
	margin-botton: 2px;
}

.labelCls {
	background-color: #EAF0FB;
}

.radioCls {
	color: red;
	width: 20px;
	margin-left: 6px;
}

.gameName {
	background-color: #EAF0FB;
	margin-right: 3px;
}

.float {
	float: none;
}

.upload_note_label {
	float: none;
}

.cell_left {
	width: 20%;
	height: 30px;
	text-align: center;
	margin-bottom: 0px;
}

.cell_right {
	width: 80%;
	height: 24px;
	margin-bottom: 0px;
}

.red {
	color: red;
}

.upload_combo {
	height: 20px;
	margin-bottom: 1px;
}

.background1 {
	background-color: black;
}

.background2 {
	background-color: red;
}
</style>
<script type="text/javascript">
	var servlet_context_path = '<%=request.getContextPath()%>';
	var action_user_path = servlet_context_path + '/user/User!';
	var action_role_path = servlet_context_path + '/user/Role!';</script>