<#macro hostUrl >
<script type="text/javascript">
	var base_url = '${request.contextPath}/';
</script>
</#macro>

<#macro common_style>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/plugin/jquery-easyui-1.4/themes/metro/easyui-blue.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/plugin/jquery-easyui-1.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/plugin/jquery-easyui-1.4/themes/color.css">

<script type="text/javascript" src="${request.contextPath}/static/plugin/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/plugin/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/plugin/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
</#macro>

<#macro common_style_ueditor>
<script type="text/javascript" charset="utf-8" src="${request.contextPath}/static/plugin/ueditor1_4_3/ueditor.config.js" ></script>
<script type="text/javascript" charset="utf-8" src="${request.contextPath}/static/plugin/ueditor1_4_3/ueditor.all.min.js" > </script>
<script type="text/javascript" charset="utf-8" src="${request.contextPath}/static/plugin/ueditor1_4_3/lang/zh-cn/zh-cn.js" ></script>
</#macro>