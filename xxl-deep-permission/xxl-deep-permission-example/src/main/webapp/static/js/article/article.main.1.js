$(function(){
	// 文章菜单初始化【引用同一Json会导致多个easyui combotree失效，需复制一份】
	var articleMenuJsonAdd = new Object(articleMenuJson);
	$("#add-menuId").combotree({data:articleMenuJsonAdd});
	var articleMenuJsonEdit = new Object(articleMenuJson);
	$("#edit-menuId").combotree({data:articleMenuJsonEdit});
	var articleMenuJsonQuery = new Object(articleMenuJson);
	$("#query-menuId").combotree({data:articleMenuJsonQuery});
	
	// datagrid 属性
	$('#dg').datagrid({ 
		//url:url,
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
			{field:'articleId', title:'文章ID'},
			{field:'userId', title:'作者ID'},
			{field:'menuId', title:'菜单ID'},
			{field:'state', title:'状态'},
			{field:'title', title:'标题'},
			{field:'content', title:'内容', hidden:true}, 
			{field:'createTime',title:'创建时间',
				formatter:function(value, row, index) {
					var unixTimestamp = new Date(value);
					//return unixTimestamp.toLocaleString();
					return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
				}
			},
			{field:'clickCount', title:'点击量'}
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
			$("#view").linkbutton("enable");
		} else {
			$("#edit").linkbutton("disable");
			$("#view").linkbutton("disable");
		}
	}
	
	// query
	$("#search").click(function(){
		if ($("#queryForm").form('validate')) {
			var url = 'articleQuery?' + encodeURI( $("#queryForm").serialize() );
			$("#dg").datagrid("load", url);
		}
	});
	// redo
	$("#redo").click(function(){
		$('#queryForm').form('reset');
	});
	
	// add
	var addUE;
	$("#add").click(function(){
		// 实例化编辑器
		addUE = UE.getEditor('add-content');
		addUE.ready(function() {
			// 初始窗口
			$('#addWindow').window('open');
			$("#addCancel").click();
		});
	});
	$("#addCancel").click(function(){
		$('#addForm').form('reset');
		// 清空编辑器
		addUE.setContent('');
	});
	$("#addOk").click(function(){
		if (!$("#addForm").form('validate')) {
			return;
		}
		
		$.ajax({
	    	url : 'articleAdd',
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
		var articleIds = new Array();
		var rows = $('#dg').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			articleIds.push(row.articleId);
		}
		
		if (articleIds.length < 1) {
			$.messager.alert('系统提示', '未选中待删除文章', 'warning');
			return;
		}
		
		$.messager.confirm('确认对话框', '您确定要删除选中文章吗？', function(r){
			if (r){
				$.ajax({
			    	url : 'articleDel',
				    type : 'post',
				    async : true,
				    data : {
				    	"articleIds" : articleIds
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
	var editUE;
	$("#edit").click(function(){
		// 校验有效性
		if ($(this).linkbutton('options').disabled == true)	return;
		
		// 渲染编辑器
		editUE = UE.getEditor('edit-content');
		editUE.ready(function() {
			// 初始窗口
			$('#editWindow').window('open');
			$("#editCancel").click();
			
			// 填充表单
			var item = $('#dg').datagrid('getSelected');
			$('#editForm').form('load',item);
			// 填充编辑器
			editUE.setContent(item.content);
			// 填充文章菜单
			$('#edit-menuId').combotree('setValue', item.menuId);
		});
	});
	$("#editCancel").click(function(){
		$('#editForm').form('reset');
		// 清空编辑器
		editUE.setContent(''); 
	});
	$("#editOk").click(function(){
		if (!$("#editForm").form('validate')) {
			return;
		}
		
		$.ajax({
	    	url : 'articleEdit',
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
	
	// view
	$("#view").click(function(){
		if ($(this).linkbutton('options').disabled == true)	return;
		// 加载内容
		var item = $('#dg').datagrid('getSelected');
		// 打开窗口
		$('#viewWindow').window('open');
		$("#viewWindow").html(item.content);
	});
	
});

//对Date的扩展，将 Date 转化为指定格式的String 
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) { //author: meizz 
	var o = {
		"M+" : this.getMonth() + 1, //月份 
		"d+" : this.getDate(), //日 
		"h+" : this.getHours(), //小时 
		"m+" : this.getMinutes(), //分 
		"s+" : this.getSeconds(), //秒 
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
		"S" : this.getMilliseconds()
	//毫秒 
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}