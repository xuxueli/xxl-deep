$(function() {
	$('#loading-mask').fadeOut();
	
	// Nouth:顶部-“菜单模块”初始化
	$.each(_menus, function(index, module) {
		$("#menuModule").append('<li><a index="' + index + '" href="javascript:;" title="">' + $(module).attr('name') + '</a></li>');
	});
	
	// Nouth:顶部-“菜单模块”选择
	$('#menuModule a').click(function() {
		$('#menuModule a').removeClass('active');
		$(this).addClass('active');
		
		var moduleContent = $(_menus[$(this).attr('index')]).attr('subMenus');
		initMenuModule(moduleContent);
	});
	// 默认选中第一个
	$('#menuModule a:first').click();
	
	// Tab-绑定事件
	tabBind();
	// Tab-Contextmenu-初始化
	tabContextmenu();
	
	// 注销登录-初始化
	initLogout();
	// 修改密码-初始化
	initModifyPwd();
	// 本地时钟-初始化
	initClock();
   
});

// West:“菜单模块”-初始化
function initMenuModule(moduleContent){
	// 属性配置
	$("#wnav").accordion( {
		animate : false
	});
	
	// 清空模块
	var pp = $('#wnav').accordion('panels');
	$.each(pp, function(i, n) {
		if (n) {
			var t = n.panel('options').title;
			$('#wnav').accordion('remove', t);
		}
	});
	pp = $('#wnav').accordion('getSelected');
	if (pp) {
		var title = pp.panel('options').title;
		$('#wnav').accordion('remove', title);
	}
	
	// 添加模块
	$.each(moduleContent, function(index, group) {
		var menulist = '<ul>';
		$.each(group.subMenus, function(index, item) {
			menulist += '<li menuId="' + item.menuId + '" href="#" url="'	+ item.permessionUrl + '" >'
				+ '<span class="icon icon-nav'	+ '" >&nbsp;</span>' 
				+ '<span class="name">' + item.name	+ '</span></li> ';
		});
		menulist += '</ul>';

		$('#wnav').accordion('add', {
			title : group.name,
			content : menulist,
			iconCls : 'icon icon-sys'
		});
	});
	
	// 默认选中第一个
	var ppNew = $('#wnav').accordion('panels');
	if (ppNew[0]) {
		var t = ppNew[0].panel('options').title;
		$('#wnav').accordion('select', t);
	}
	
	// 模块初始化
	$('#wnav li').bind('click', function() {
		$('#wnav li').removeClass("selected");
		$(this).addClass("selected");
		
		// Tab-新增
		var url = $(this).attr("url");
		var menuid = $(this).attr("menuid");
		var tabTitle = $(this).children('.name').text();
		var icon = $(this).children('.icon').attr('class');
		
		addTab(tabTitle, url, icon);
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
	
}

// Tab-新增
function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm #refresh').click();
	}
	// Tab-绑定事件
	tabBind();
}

// Tab-远程加载内容
function createFrame(url) {
	return '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
}

// Tab-绑定事件
function tabBind() {
	$(".tabs-inner").bind('contextmenu', function(e) {
		// Tab绑定 “右键菜单栏”
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	}).bind('dblclick', function(e) {
		// Tab绑定 “双击关闭”
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
}

// 不允许关闭的标签的标题
var onlyOpenTitle = "欢迎使用";
// Tab-Contextmenu-初始化
function tabContextmenu() {
	$('#mm').menu({
		onClick : function(item) {
			var action = item.id;

			var alltabs = $('#tabs').tabs('tabs');
			var currentTab = $('#tabs').tabs('getSelected');
			var allTabtitle = [];
			$.each(alltabs, function(i, n) {
				allTabtitle.push($(n).panel('options').title);
			})

			switch (action) {
			case "refresh":
				var iframe = $(currentTab.panel('options').content);
				var src = iframe.attr('src');
				$('#tabs').tabs('update', {
					tab : currentTab,
					options : {
						content : createFrame(src)
					}
				})
				break;
			case "close":
				var currtab_title = currentTab.panel('options').title;
				$('#tabs').tabs('close', currtab_title);
				break;
			case "closeall":
				$.each(allTabtitle, function(i, n) {
					if (n != onlyOpenTitle) {
						$('#tabs').tabs('close', n);
					}
				});
				break;
			case "closeother":
				var currtab_title = currentTab.panel('options').title;
				$.each(allTabtitle, function(i, n) {
					if (n != currtab_title && n != onlyOpenTitle) {
						$('#tabs').tabs('close', n);
					}
				});
				break;
			case "closeright":
				var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

				if (tabIndex == alltabs.length - 1) {
					alert('亲，后边没有啦 ^@^!!');
					return false;
				}
				$.each(allTabtitle, function(i, n) {
					if (i > tabIndex) {
						if (n != onlyOpenTitle) {
							$('#tabs').tabs('close', n);
						}
					}
				});

				break;
			case "closeleft":
				var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
				if (tabIndex == 1) {
					alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
					return false;
				}
				$.each(allTabtitle, function(i, n) {
					if (i < tabIndex) {
						if (n != onlyOpenTitle) {
							$('#tabs').tabs('close', n);
						}
					}
				});

				break;
			case "exit":
				$('#closeMenu').menu('hide');
				break;
			}

		}
	});
	return false;
}

// 注销登录-初始化
function initLogout(){
	$('#logout').click(function() {
		$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
			if (r) {
				// 退出登录
				$.ajax({
					url : 'logout',
					type : 'post',
					async : true,
					dataType : 'json',
					beforeSend : function() {
						$('#loading-mask').fadeOut();
					},
					complete : function() {
						$('#loading-mask').fadeIn();
					},
					success : function(data) {
						if (data.code == 200) {
							window.location.href = base_url;
						} else {
							alert(data.msg);
						}
					},
					error : function() {
						alert("网络异常");
					}
				});
			}
		});
	});
};

// 修改密码-初始化
function initModifyPwd(){
	// 修改密码弹框:init
	$('#w').window({
        title: '修改密码',
        width: 300,
        height: 200,
        modal: true,
        shadow: true,
        closed: true,
        resizable:false
    });    
	// 修改密码弹框:open
	$('#modifyPwdOpen').click(function() {
		$('#w').window('open');
    });
	// 修改密码弹框:close
	$('#modifyPwdClose').click(function(){
		$('#w').window('close');
	});
	// 修改密码:请求
	$('#modifyPwd').click(function() {
		if (!$("#modifyPwdForm").form('validate')) {
			return;
		}
		var $newPwd = $('#newPwd');
		var $reNewPwd = $('#reNewPwd');
		if ($newPwd.val() != $reNewPwd.val()) {
			$.messager.alert('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}
		$.messager.confirm('确认','密码修改后将注销登录，请确认？',function(r){    
		    if (r){    
		    	$.post("./modifyPwd", $('#modifyPwdForm').serialize(), function(data){
					if (data.code == 200) {
						window.location.href = base_url;
					} else {
						$.messager.alert('系统提示', data.msg, 'warning');
					}
				},"JSON");
		    }    
		});  
	});
};

//本地时钟-初始化
function initClock() {
	var now = new Date();
	var year = now.getFullYear(); // getFullYear getYear
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	var hour = now.getHours();
	var minu = now.getMinutes();
	var sec = now.getSeconds();
	var week;
	month = month + 1;
	if (month < 10)
		month = "0" + month;
	if (date < 10)
		date = "0" + date;
	if (hour < 10)
		hour = "0" + hour;
	if (minu < 10)
		minu = "0" + minu;
	if (sec < 10)
		sec = "0" + sec;
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu
			+ ":" + sec + " " + week;

	$("#bgclock").html(time);

	var timer = setTimeout("initClock()", 200);
};
