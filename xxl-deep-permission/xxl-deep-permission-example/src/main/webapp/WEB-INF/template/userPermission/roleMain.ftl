<#import "/common/common.macro.ftl" as common>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>后台角色管理</title>
	<@common.common_style />
		
</head>
<body class="easyui-layout" fit="true">  
	<!-- toolbar -->
	<div id="tb" style="padding:5px;height:auto">
		<!-- operate -->
		<div style="padding:2px;border:1px solid #ddd;">
			<a href="#" id="add" class="easyui-linkbutton" data-options="iconCls:'icon-add'" >添加</a>
			<a href="#" id="cut" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" >删除</a>
			<a href="#" id="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >编辑</a>
			<a href="#" id="editMenu" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >分配权限</a>
		</div>
	</div>
	<!-- DataGrid -->
	<table id="dg" title="后台用户" style="height:100%;width:100%;" ></table>
	
	<!-- add -->
	<div id="addWindow" class="easyui-window" title="添加角色" data-options="modal:true,closed:true,iconCls:'icon-save', resizable:false" style="width:250px;height:180px; text-align:center;">
		<form id="addForm" method="post" >
    	<table cellpadding="5">
    		<tr>
    			<td>名称:</td>
    			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true, validType:['length[2,10]']" /></td>
    		</tr>
    		<tr>
    			<td>顺序:</td>
    			<td>
    				<input class="easyui-numberbox" type="text" name="order" data-options="required:true, min:1,precision:0" />
    			</td>
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
	<div id="editWindow" class="easyui-window" title="编辑用户" data-options="modal:true,closed:true,iconCls:'icon-save', resizable:false" style="width:250px;height:180px; text-align:center;">
		<form id="editForm" method="post" style="align:center">
		<input type="hidden" name="id" />
    	<table cellpadding="5" >
    		<tr>
    			<td>名称:</td>
    			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true, validType:['length[2,10]']" /></td>
    		</tr>
    		<tr>
    			<td>顺序:</td>
    			<td>
    				<input class="easyui-numberbox" type="text" name="order" data-options="required:true, min:1,precision:0" />
    			</td>
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
	
	<!-- editMenu -->
	<div id="editMenuWindow" class="easyui-window" title="分配权限" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false" style="width:300px;margin-top:50px,text-align:left;" >
		<form id="editMenuForm" method="post" >
    	<table cellpadding="5" >
    		<tr><td colspan="2" >
    			<ul id="tt" class="easyui-tree" ></ul>
    		</td></tr>
    		<tr>
    			<td colspan="2" >
    				<a class="easyui-linkbutton" icon="icon-ok" id="editMenuOk">保存</a>
    				<a class="easyui-linkbutton" icon="icon-cancel" id="editMenuCancel">重置</a>
				</td>
    		</tr>
    	</table>
		</form>
	</div>

</body>

<script type="text/javascript" src="${request.contextPath}/static/js/userPermission/roleMain.1.js"></script>
</html>