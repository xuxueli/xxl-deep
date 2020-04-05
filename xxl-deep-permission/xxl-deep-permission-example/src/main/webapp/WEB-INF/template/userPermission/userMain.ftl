<#import "/common/common.macro.ftl" as common>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>后台用户管理</title>
	<@common.common_style />
</head>
<body class="easyui-layout" fit="true">  
	<!-- toolbar -->
	<div id="tb" style="padding:5px;height:auto">
		<!-- search -->
		<div style="padding:2px;border:1px solid #ddd;">
			<form id="queryForm">
				用户名:<input type="text" class="easyui-validatebox" data-options="validType:'length[0,20]'" style="width:100px" name="userName">
			</form>
		</div>
		<!-- operate -->
		<div style="padding:2px;border:1px solid #ddd;">
			<a href="#" id="add" class="easyui-linkbutton" data-options="iconCls:'icon-add'" >添加</a>
			<a href="#" id="cut" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" >删除</a>
			<a href="#" id="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >编辑</a>
			<a href="#" id="editRole" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >分配角色</a>
			&nbsp;|&nbsp;
			<a href="#" id="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			<a href="#" id="redo" class="easyui-linkbutton" iconCls="icon-redo">重置</a>
		</div>
	</div>
	<!-- DataGrid -->
	<table id="dg" title="后台用户" style="height:100%;width:100%;" ></table>
	
	<!-- add -->
	<div id="addWindow" class="easyui-window" title="添加用户" data-options="modal:true,closed:true,iconCls:'icon-save', resizable:false" style="width:250px;height:180px; text-align:center;">
		<form id="addForm" method="post" style="align:center">
    	<table >
    		<tr>
    			<td>账号:</td>
    			<td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true, validType:['length[6,16]']" /></td>
    		</tr>
    		<tr>
    			<td>密码:</td>
    			<td><input class="easyui-validatebox" type="text" name="password" data-options="required:true, validType:['length[6,16]']" /></td>
    		</tr>
    		<tr>
    			<td colspan="2" >
    				<a class="easyui-linkbutton" icon="icon-ok" id="addOk">保存</a>
    				<a class="easyui-linkbutton" icon="icon-cancel" id="addCancel">重置</a>
				</td>
    		</tr>
    	</table>
		</form>
	</div>
	
	<!-- update -->
	<div id="editWindow" class="easyui-window" title="添加用户" data-options="modal:true,closed:true,iconCls:'icon-save', resizable:false" style="width:250px;height:180px; text-align:center;">
		<form id="editForm" method="post" style="align:center">
		<td><input type="hidden" name="id" /></td>
    	<table >
    		<tr>
    			<td>账号:</td>
    			<td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true, validType:['length[6,16]']" /></td>
    		</tr>
    		<tr>
    			<td>密码:</td>
    			<td><input class="easyui-validatebox" type="text" name="password" data-options="required:true, validType:['length[6,16]']" /></td>
    		</tr>
    		<tr>
    			<td colspan="2" >
    				<a class="easyui-linkbutton" icon="icon-ok" id="editOk">保存</a>
    				<a class="easyui-linkbutton" icon="icon-cancel" id="editCancel">重置</a>
				</td>
    		</tr>
    	</table>
		</form>
	</div>
	
	<!-- edit role form -->
	<div id="editRoleWindow" class="easyui-window" title="分配角色" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false" style="width:300px;margin-top:50px,text-align:left;">
		<form class="editRoleForm" method="post" style="align:center">
		<td><input type="hidden" name="userId" /></td>
    	<table >
    		<tr>
    			<td>角色:</td>
    			<td>
                    <ul id="roles" class="easyui-tree" ></ul>
				</td>
    		</tr>
    		<tr>
    			<td colspan="2" >
    				<a class="easyui-linkbutton" id="editRoleOk" icon="icon-ok" >保存</a>
    				<a class="easyui-linkbutton" id="editRoleCancel" icon="icon-cancel" >重置</a>
				</td>
    		</tr>
    	</table>
		</form>
	</div>

</body>
<script type="text/javascript" src="${request.contextPath}/static/js/userPermission/userMain.1.js"></script>
</html>