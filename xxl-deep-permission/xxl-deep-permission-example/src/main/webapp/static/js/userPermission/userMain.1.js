$(function() {
	
	// datagrid 属性
	$('#dg').datagrid({ 
		url: 'userQuery' ,
	    //method:'get', 
		pagination:true, 
		pageSize: 10, 				//读取分页条数，即向后台读取数据时传过去的值
		pageList: [10, 20, 30, 50], //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据
		toolbar:'#tb', 				// 设置表格顶部的工具栏，以数组形式设置
	    rownumbers:true,	
		checkOnSelect:true, 
		singleSelect:false,
		fitColumns: true, 			// 设置为true将自动使列适应表格宽度以防止出现水平滚动,false则自动匹配大小
	    //sortName: "user_name", 		// 默认排序字段
		//sortOrder: "asc", 			// 正序
	    columns:[[    
	        {field:'ck',checkbox:true},
			{field:'id', title:'用户ID', width:80,sortable:true},
			{field:'userName', title:'用户名', width:80},
			{field:'password', title:'密码', width:80,align:'right'},
			{field:'userToken', title:'TOKEN', width:80,align:'right'},
			{field:'realName', title:'真实命名', width:80}
	    ]],
	    onLoadSuccess:function(){	initLinkButton();	},
	    onSelect:function(){	initLinkButton();	},
	    onUnselect:function(){	initLinkButton();	},
	    onSelectAll:function(){	initLinkButton();	},
	    onUnselectAll:function(){	initLinkButton();	}
	});
	initLinkButton();
	
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
			$("#editRole").linkbutton("enable");
		} else {
			$("#edit").linkbutton("disable");
			$("#editRole").linkbutton("disable");
		}
	}
	
	// query
	$("#search").click(function(){
		if ($("#queryForm").form('validate')) {
			var url = 'userQuery';
			$("#dg").datagrid("load", url + "?" + $("#queryForm").serialize());	//将searchForm表单内的元素序
		}
	});
	// redo
	$("#redo").click(function(){
		$('#queryForm').form('reset');
	});
	
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
	    	url : 'userAdd',
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
					$.messager.alert('系统提示', '用户添加成功', 'info');
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
		var userIds = new Array();
		var rows = $('#dg').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			userIds.push(row.id);
		}
		
		if (userIds.length < 1) {
			$.messager.alert('系统提示', '未选中待删除用户', 'warning');
			return;
		}
		
		$.messager.confirm('确认对话框', '您确定要删除选中用户吗？', function(r){
			if (r){
				$.ajax({
			    	url : 'userDel',
				    type : 'post',
				    async : true,
				    data : {
				    	"userIds" : userIds
				    },
				    dataType:'json',
				    beforeSend : function() {
						$.messager.progress({title:'请稍后'});
					},
					complete:function(){
						$.messager.progress('close');
					},
				    success: function(data){
				    	if (data.code == 200) {
							$.messager.alert('系统提示', '用户删除成功', 'info');
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
	    	url : 'userUpdate',
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
	
	
	// edit role
	$("#editRole").click(function(){
		// 校验有效性
		if ($(this).linkbutton('options').disabled == true)	return;
		
		// 用户ID
		var item = $('#dg').datagrid('getSelected');
		var userId = item.id;
		
		$("#editRoleWindow").window("open");
		$("#roles").tree({
			url:'userRoleQuery?userId=' + userId,
			method:'get',
			idField:'id',
			checkbox:true,
			cascadeCheck:false,
			lines :true
		});

	});
	$("#editRoleCancel").click(function(){
		$('#roles').combobox('clear');
	});
	$("#editRoleOk").click(function(){
		// 用户ID
		var item = $('#dg').datagrid('getSelected');
		var userId = item.id;

		// 选中角色ID
		var nodes = $('#roles').tree('getChecked');
		var roleIds = new Array();
		for(var i=0; i<nodes.length; i++){
			roleIds.push(nodes[i].id);
		}

		$.post("userRoleUpdate", "userId=" + userId + "&roleIds[]=" + roleIds
		,function(data) {
			if (data.code == 200) {
				$.messager.alert('系统提示', '用户角色更新成功', 'info');
				$("#editRoleWindow").window("close");
			} else {
				$.messager.alert('系统提示', data.msg, 'warning');
			}
		}, "json");
	});
	
});