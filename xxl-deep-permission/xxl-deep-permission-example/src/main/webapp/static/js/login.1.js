$(function(){
	
	// 刷新验证码
	$("#randCodeImg").click(function(){
		$(this).attr("src", base_url + "kaptcha?v=" + Math.random() );
	});
	$("#randCodeImg").click();
	
});

// 重置
function clearForm(){
	$('#loginForm').form('reset');
}

// 登陆
function submitForm(){
	
	// 校验参数
	if (!$('#loginForm').form('validate')) {
		return;
	}
	// 登陆
	$.ajax({
    	url : base_url + 'login',
	    type : 'post',
	    async : true,
	    data : $('#loginForm').serialize(),
	    dataType:'json',
	    beforeSend : function() {
			$.messager.progress({title:'请稍后', msg:'请求中...'});
		},
		complete:function(){
			$.messager.progress('close');
		},
	    success: function(data){
	    	if(data.code == 200){
				window.location.href = base_url + "./home";
	    	} else{
	    		$.messager.alert('系统提示', data.msg, 'warning');
		    }
        },
        error: function(){
        	alert("网络异常");
        }
    });
	
}
