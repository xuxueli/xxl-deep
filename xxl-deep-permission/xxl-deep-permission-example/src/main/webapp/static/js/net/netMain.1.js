$(function() {
	
	// 一面墙，数据爬虫
	$(".wallClaw").click(function(){
		$.ajax({
	    	url : base_url + '/net/wallClaw',
		    type : 'post',
		    async : true,
		    data : {
		    	runType : $(this).attr("runType")
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
					$.messager.alert('系统提示', '操作成功', 'info');
				} else {
					$.messager.alert('系统提示', data.msg, 'warning');
				}
	        },
	        error: function(){
	        	$.messager.alert('系统提示', '网络异常', 'error');
	        }
	    });
	});
	
	// 官网，静态化
	$(".netHtml").click(function(){
		$.ajax({
	    	url : base_url + '/net/netHtml',
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
		    	if (data.code == "S") {
					$.messager.alert('系统提示', '操作成功', 'info');
				} else {
					$.messager.alert('系统提示', data.msg, 'warning');
				}
	        },
	        error: function(){
	        	$.messager.alert('系统提示', '网络异常', 'error');
	        }
	    });
	});
	
	
	$(".editNetAddress").click(function(){
		$.ajax({
	    	url : base_url + '/net/netAddressLoad',
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
		    	if (data.code == "S") {
		    		if (data.returnContent.value) {
		    			$("#netAddress").val(data.returnContent.value);
		    		}
		    		$('#editNetAddressWindow').window('open');
				} else {
					$.messager.alert('系统提示', data.msg, 'warning');
				}
	        },
	        error: function(){
	        	$.messager.alert('系统提示', '网络异常', 'error');
	        }
	    });
	});
	
	$("#editNetAddressOk").click(function(){
		$.ajax({
	    	url : base_url + '/net/netAddressSave?netAddress=' + $("#netAddress").val(),
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
		    	if (data.code == "S") {
		    		$.messager.alert('系统提示', '操作成功', 'info');
		    		$('#editNetAddressWindow').window('close');
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