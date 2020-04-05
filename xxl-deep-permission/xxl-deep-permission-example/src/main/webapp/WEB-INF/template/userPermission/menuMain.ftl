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
		</div>
	</div>
	
	<!-- treegrid -->
	<table id="tg" class="easyui-treegrid"></table>
	
	<!-- add -->
	<div id="addWindow" class="easyui-window" title="添加菜单" data-options="modal:true,closed:true,iconCls:'icon-save', resizable:false" style="width:270px;height:270px; text-align:center;">
		<form id="addForm" method="post" >
    	<table cellpadding="5">
    		<tr>
    			<td>父菜单:</td>
    			<td><input class="easyui-numberbox parentId" type="text" name="parentId" data-options="required:true, min:0,precision:0" /></td>
    		</tr>
    		<tr>
    			<td>顺序:</td>
    			<td><input class="easyui-numberbox" type="text" name="order" data-options="required:true, min:1,precision:0" /></td>
    		</tr>
    		<tr>
    			<td>名称:</td>
    			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true, validType:['length[2,10]']" /></td>
    		</tr>
    		<tr>
    			<td>URL:</td>
    			<td><input class="easyui-validatebox" type="text" name="permessionUrl" data-options="" /></td>
    		</tr>
    		<tr>
    			<td>权限ID:</td>
    			<td><input class="easyui-numberbox" type="text" name="permessionId" data-options="required:true, min:0,precision:0" /></td>
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
	
	<!-- edit -->
	<div id="editWindow" class="easyui-window" title="编辑菜单" data-options="modal:true,closed:true,iconCls:'icon-save', resizable:false" style="width:270px;height:270px; text-align:center;">
		<form id="editForm" method="post" >
		<input type="hidden" name="id" />
    	<table cellpadding="5">
    		<tr>
    			<td>父菜单:</td>
    			<td><input class="easyui-numberbox" type="text" name="parentId" data-options="required:true, min:0,precision:0,readonly:false" /></td>
    		</tr>
    		<tr>
    			<td>顺序:</td>
    			<td><input class="easyui-numberbox" type="text" name="order" data-options="required:true, min:1,precision:0" /></td>
    		</tr>
    		<tr>
    			<td>名称:</td>
    			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true, validType:['length[2,10]']" /></td>
    		</tr>
    		<tr>
    			<td>URL:</td>
    			<td><input class="easyui-validatebox" type="text" name="permessionUrl" data-options="" /></td>
    		</tr>
    		<tr>
    			<td>权限ID:</td>
    			<td><input class="easyui-numberbox" type="text" name="permessionId" data-options="required:true, min:0,precision:0" /></td>
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
	
</body>
<script type="text/javascript" src="${request.contextPath}/static/js/userPermission/menuMain.1.js"></script>
</html>