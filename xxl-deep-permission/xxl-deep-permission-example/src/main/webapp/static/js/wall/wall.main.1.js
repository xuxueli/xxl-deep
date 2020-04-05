$(function(){
	
	// datagrid 属性
	$('#dg').datagrid({ 
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
			{field:'id', title:'ID'},
			{field:'status', title:'状态',
				styler: function(value,row,index){
					if (value == 0){
						return 'background-color:gray;color:red;';	// 原始状态
					} else if (value == 1){
						return 'background-color:red;color:black;';	// 审核未通过
					} else if (value == 2){
						return 'background-color:green;color:red;';	// 审核通过
					}
					
				}
			},
			{field:'source', title:'来源'},
			{field:'userId', title:'用户ID'},
			{field:'content', title:'内容', width: '80px'},
			{field:'image', title:'图片地址', width: '80px'}, 
			{field:'createTime',title:'发布时间',
				formatter:function(value, row, index) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
				}
			}
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
		} else {
			$("#edit").linkbutton("disable");
		}
	}
	
	// query
	$("#search").click(function(){
		if ($("#queryForm").form('validate')) {
			var url = 'wallQuery?' + encodeURI( $("#queryForm").serialize() );
			$("#dg").datagrid("load", url);
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
	    	url : 'wallAdd',
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
					$.messager.alert('系统提示', '保存成功', 'info');
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
		var ids = new Array();
		var rows = $('#dg').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			ids.push(row.id);
		}
		
		if (ids.length < 1) {
			$.messager.alert('系统提示', '未选中待删除项', 'warning');
			return;
		}
		
		$.messager.confirm('确认对话框', '您确定要删除选中项？', function(r){
			if (r){
				$.ajax({
			    	url : 'wallDel',
				    type : 'post',
				    async : true,
				    data : {
				    	"ids" : ids
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
							$.messager.alert('系统提示', '删除成功', 'info');
							$('#dg').datagrid('reload');
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
		
		// 填充表单
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
	    	url : 'wallUpdate',
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
					$.messager.alert('系统提示', '更新成功', 'info');
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