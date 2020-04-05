$(function(){
	$('#tg').treegrid({
	    url:'articleMenuQuery',
		method: 'get',
	    idField:'menuId',    
	    treeField:'name', 
	    rownumbers: true,
	    singleSelect:true,
	    columns:[[
            {title:'节点ID',field:'menuId'},    
            {title:'父节点ID',field:'parentId'},
	        {title:'顺序',field:'order'}, 
	        {title:'名称',field:'name'},    
	        {title:'描述',field:'desc'},
	        {title:'点击量',field:'clickCount'}
	    ]],
	    onLoadSuccess:function(){	initLinkButton();	},
	    onClickRow:function(){	initLinkButton();	}
	});
	
	// LinkButton-初始化
	function initLinkButton(){
		var selectList = $('#tg').datagrid('getSelections');
		
		if (selectList.length > 0 ) {
			$("#cut").linkbutton("enable");
		} else {
			$("#cut").linkbutton("disable");
		}
		
		if (selectList.length == 1 ) {
			$("#edit").linkbutton("enable");
		} else {
			$("#edit").linkbutton("disable");
		}
	}
	
	// add
	$("#add").click(function(){
		// 校验有效性
		if ($(this).linkbutton('options').disabled == true)	return;
		
		$('#addWindow').window('open');
		$("#addCancel").click();
		
		var item = $('#tg').datagrid('getSelected');
		var parentId = 0;
		if (item != null) {
			parentId = item.menuId;
		}
		$("#addWindow .parentId").numberbox('setValue', parentId);
	});
	$("#addCancel").click(function(){
		$('#addForm').form('reset');
	});
	$("#addOk").click(function(){
		if (!$("#addForm").form('validate')) {
			return;
		}
		
		$.ajax({
	    	url : 'articleMenuAdd',
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
		    	if (data.code == "S") {
					$.messager.alert('系统提示', '菜单添加成功', 'info');
					$('#addWindow').window('close')
					$('#tg').treegrid('reload');
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
		
		var item = $('#tg').datagrid('getSelected');
		if (item == null) {
			$.messager.alert('系统提示', '未选中待删除菜单', 'warning');
			return;
		}
		
		$.messager.confirm('确认对话框', '您确定要删除选中菜单吗？', function(r){
			if (r){
				$.ajax({
			    	url : 'articleMenuDel',
				    type : 'post',
				    async : true,
				    data : {
				    	"menuId" : item.menuId
				    },
				    dataType:'json',
				    beforeSend : function() {
						$.messager.progress({title:'请稍后'});
					},
					complete:function(){
						$.messager.progress('close');
					},
				    success: function(data){
				    	if (data.code == "S") {
							$.messager.alert('系统提示', '菜单删除成功', 'info');
							$('#tg').treegrid('reload');
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
		
		var item = $('#tg').datagrid('getSelected');
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
	    	url : 'articleMenuUpdate',
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
		    	if (data.code == "S") {
					$.messager.alert('系统提示', '菜单更新成功', 'info');
					$('#editWindow').window('close')
					$('#tg').treegrid('reload');
				} else {
					$.messager.alert('系统提示', data.msg, 'warning');
				}
	        },
	        error: function(){
	        	$.messager.alert('系统提示', '网络异常', 'error');
	        }
	    });
	});
	
});
