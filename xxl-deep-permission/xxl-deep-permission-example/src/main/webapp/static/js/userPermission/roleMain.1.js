$(function() {
	
	var url = 'roleQuery';

	// datagrid 属性
	$('#dg').datagrid({ 
		url:url,
	    method:'get', 
		toolbar:'#tb', 				// 设置表格顶部的工具栏，以数组形式设置
	    rownumbers:true,	
		checkOnSelect:true, 
		singleSelect:false,
		fitColumns: true, 			// 设置为true将自动使列适应表格宽度以防止出现水平滚动,false则自动匹配大小
	    columns:[[    
	        {field:'ck',checkbox:true},
			{field:'id', title:'角色ID'},
			{field:'name', title:'角色名'},
			{field:'order', title:'顺序'}
	    ]],
	    onLoadSuccess:function(){	initLinkButton();	},
	    onSelect:function(){	initLinkButton();	},
	    onUnselect:function(){	initLinkButton();	},
	    onSelectAll:function(){	initLinkButton();	},
	    onUnselectAll:function(){	initLinkButton();	}
	});
	
	// LinkButton-初始化
	function initLinkButton(){
		var selectList = $('#dg').datagrid('getSelections');
		
		if (selectList.length > 0 ) {
			$("#cut").linkbutton("enable");
		} else {
			$("#cut").linkbutton("disable");
		}
		
		if (selectList.length == 1 ) {
			$("#edit").linkbutton("enable");
			$("#editMenu").linkbutton("enable");
		} else {
			$("#edit").linkbutton("disable");
			$("#editMenu").linkbutton("disable");
		}
	}
	
	// add
	$("#add").click(function(){
		// 初始窗口
		$('#addWindow').window('open');
		$("#addCancel").click();
	});
	$("#addCancel").click(function(){
		$('#addForm').form('reset');
	});
	$("#addOk").click(function(){
		if (!$("#addForm").form('validate')) {
			return;
		}
		
		$.ajax({
	    	url : 'roleAdd',
		    type : 'post',
		    async : true,
		    data : $("#addForm").serialize(),
		    dataType:'json',
		    beforeSend : function() {
				$.messager.progress({title:'请稍后'});
			},
			complete:function(){
				$.messager.progress('close');
			},
		    success: function(data){
		    	if (data.code == 200) {
					$.messager.alert('系统提示', '角色添加成功', 'info');
					$('#addWindow').window('close')
					$('#dg').datagrid('reload');
				} else {
					$.messager.alert('系统提示', data.msg, 'warning');
				}
	        },
	        error: function(){
	        	$.messager.alert('系统提示', '网络异常', 'error');
	        }
	    });
	});
	
	// cut
	$("#cut").click(function(){
		// 校验有效性
		if ($(this).linkbutton('options').disabled == true)	return;
		
		// 获取选中项userIds数组
		var roleIds = new Array();
		var rows = $('#dg').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			roleIds.push(row.id);
		}
		
		if (roleIds.length < 1) {
			$.messager.alert('系统提示', '未选中待删除用户', 'warning');
			return;
		}
		
		$.messager.confirm('确认对话框', '您确定要删除选中角色吗？', function(r){
			if (r){
				$.ajax({
			    	url : 'roleDel?roleIds[]=' + roleIds,
				    type : 'post',
				    async : true,
				    dataType:'json',
				    beforeSend : function() {
						$.messager.progress({title:'请稍后'});
					},
					complete:function(){
						$.messager.progress('close');
					},
				    success: function(data){
				    	if (data.code == 200) {
							$.messager.alert('系统提示', '角色删除成功', 'info');
							$('#dg').datagrid('reload');	// 重新载入当前页面数据  
						} else {
							$.messager.alert('系统提示', data.msg, 'warning');
						}
			        },
			        error: function(){
			        	$.messager.alert('系统提示', '网络异常', 'error');
			        }
			    });
			}
		});
	
	});
	
	// edit
	$("#edit").click(function(){
		// 校验有效性
		if ($(this).linkbutton('options').disabled == true)	return;
		
		// 初始窗口
		$('#editWindow').window('open');
		$("#editCancel").click();
		
		var item = $('#dg').datagrid('getSelected');
		$('#editForm').form('load',item);
	});
	$("#editCancel").click(function(){
		$('#editForm').form('reset');
	});
	$("#editOk").click(function(){
		if (!$("#editForm").form('validate')) {
			return;
		}
		
		$.ajax({
	    	url : 'roleUpdate',
		    type : 'post',
		    async : true,
		    data : $("#editForm").serialize(),
		    dataType:'json',
		    beforeSend : function() {
				$.messager.progress({title:'请稍后'});
			},
			complete:function(){
				$.messager.progress('close');
			},
		    success: function(data){
		    	if (data.code == 200) {
					$.messager.alert('系统提示', '用户更新成功', 'info');
					$('#editWindow').window('close')
					$('#dg').datagrid('reload');
				} else {
					$.messager.alert('系统提示', data.msg, 'warning');
				}
	        },
	        error: function(){
	        	$.messager.alert('系统提示', '网络异常', 'error');
	        }
	    });
	});
	
	
	// edit menu
	$("#editMenu").click(function(){
		if ($(this).linkbutton('options').disabled == true)	return;
		$('#editMenuWindow').window('open');
		$("#editMenuCancel").click();
		
		var item = $('#dg').datagrid('getSelected');
		var roleId = item.id;
		
		$('#tt').tree({    
			url: 'roleMenuQuery?roleId=' + roleId,
			method:'get',
			checkbox:true,
			cascadeCheck:false,
			lines :true
		});  
		
	});
	$("#editMenuCancel").click(function(){
		$('#tt').tree('reload');
	});
	$("#editMenuOk").click(function(){
		if (!$("#editMenuForm").form('validate')) {
			return;
		}
		// 获取选中项roleId
		var item = $('#dg').datagrid('getSelected');
		var roleId = item.id;
		
		// 获取选中菜单节点
		var nodes = $('#tt').tree('getChecked');
		var menuIds = new Array();
		for(var i=0; i<nodes.length; i++){
			menuIds.push(nodes[i].id);
		}

		$.post("roleMenuUpdate", "roleId=" + roleId + "&menuIds[]=" + menuIds
		,function(data) {
			if (data.code == 200) {
				$.messager.alert('系统提示', '角色菜单更新成功', 'info');
				$('#editMenuWindow').window('close');
			} else {
				$.messager.alert('系统提示', data.msg, 'warning');
			}
		}, "json");
		
	});
	
});