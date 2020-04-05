<#import "/common/common.macro.ftl" as common>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>控制中心</title>
	<@common.common_style />
	<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/home.1.css">
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">

	<!-- 脚本禁止提示 -->
	<noscript>抱歉，请开启脚本支持！</noscript>

	<!-- Loading动画 -->
	<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
		<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
	    	<img src="static/images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...
	    </div>
	</div>
	
	<!-- Nouth:顶部 -->
    <div region="north" split="true" border="false" style="overflow: hidden;height:30px;line-height:30px;background:#1a7bc9;color:white;font-size:12px;font-family:Verdana,微软雅黑,黑体;" >
		<span class="north_fun" style="padding-right:10px; float:right;margin-top:0px;" >
			<span id="bgclock"></span>：<b>${Session['login_identity'].realName?if_exists}</b>  
			<a href="#" id="modifyPwdOpen">修改密码</a>
			<a href="#" id="logout">安全退出</a>
		</span>
		<span style="padding-left:10px;float:left;margin-top:0px;">→</span>
		<ul class="north_menu" id="menuModule" style="padding-left:10px;float:left;margin-top:0px;">
			<!-- <li><a name="basic" href="javascript:;" title="基础数据">基础数据</a></li> -->
		</ul>
    </div>
    
    <!-- South:底部 
    <div region="south" split="true" title="by xuxueli"></div>
    -->
    
    <!-- West:菜单 -->
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
		<div id='wnav' class="easyui-accordion navlist" fit="true" border="false">
			<!--  导航内容 -->
		</div>
	</div>
    
    <!-- Center:选项卡区域 -->
	<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" closable="false">
				<h1>Welcome.</h1>
			</div>
		</div>
    </div>
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" maximizable="false" 
    		icon="icon-save"  style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            	<form id="modifyPwdForm" method="post">
                <table cellpadding=3>
                	<tr>
                        <td>旧密码：</td>
                        <td><input id="password" name="password" type="password" class="easyui-validatebox" data-options="required:true, validType:['length[6,16]']" /></td>
                    </tr>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="newPwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true, validType:['length[6,16]']" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="reNewPwd" name="reNewPwd" type="password" class="easyui-validatebox" data-options="required:true, validType:['length[6,16]']" /></td>
                    </tr>
                </table>
                </form>
            </div>
            <div region="south" border="false" style="text-align: center; height: 27px;">
                <a id="modifyPwd" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a>
                <a id="modifyPwdClose" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
    
	<!-- 鼠标右键菜单 -->
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="refresh">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>

</body>
<script>
	// 权限菜单
	var _menus = JSON.parse('${Session['login_identity'].menuModuleJson?if_exists}');
	var base_url = "${request.contextPath}/";
</script>
<script type="text/javascript" src="${request.contextPath}/static/js/home.1.js"></script>
</html>