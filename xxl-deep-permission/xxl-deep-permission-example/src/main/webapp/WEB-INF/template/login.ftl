<#import "/common/common.macro.ftl" as common>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登陆后台</title>
	<@common.common_style />
</head>
<body style="background:url(static/images/login_bg.jpg) repeat top center;" >

	<!-- 登陆框 -->
	<div class="easyui-dialog" title="登陆" style="width:400px;" data-options="closable:false, draggable:false" >
		<div style="padding:10px 60px 20px 60px">
		    <form id="loginForm" method="post">
		    	<table cellpadding="5">
		    		<tr>
		    			<td>账号:</td>
		    			<td><input class="easyui-validatebox" type="text" name="username" data-options="required:true, validType:['length[6,16]']" value="xuxueli" ></input></td>
		    		</tr>
		    		<tr>
		    			<td>密码:</td>
		    			<td><input class="easyui-validatebox" type="password" name="password" data-options="required:true, validType:['length[6,16]']" value="123456" ></input></td>
		    		</tr>
		    		<tr>
		    			<td>角色:</td>
		    			<td>
							<select class="easyui-combobox" data-options="editable:false" panelheight="100%" name="role_id" >
								<#if roleList?exists && roleList?size gt 0>
								    <#list roleList as role>
								        <option value="${role.id}" >${role.name}</option>
								    </#list>
								</#if>
							</select>
						</td>
		    		</tr>
		    		<tr>
		    			<td>验证码:</td>
		    			<td>
		    				<input class="easyui-validatebox" type="text" name="randCode" data-options="required:true,validType:['length[1,4]']" style="width:70px;" value="8888" ></input>
		    				<img id="randCodeImg" style="width:70px;height:21px;margin-bottom: -5px;" alt="点击更换" title="点击更换" src=""/>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="text-align:center;padding:5px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登陆</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
		    </div>
    	</div>
	</div>
	
</body>
<@common.hostUrl />
<script type="text/javascript" src="${request.contextPath}/static/js/login.1.js"></script>
</html>