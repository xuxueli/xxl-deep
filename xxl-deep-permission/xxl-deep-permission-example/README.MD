1、底层框架：spring3.2.9(mvc) + mybatis3.2.7 + mysql5.5
---------------------------------------
2、权限设计：RBAC：用户-角色-权限【权限模块-权限组-权限项】

-----------------------------------------------
---通用---
后台权限管理
后台用户管理:1000100:userPermission/userMain
后台角色管理:1000200:userPermission/roleMain
后台菜单管理:1000300:userPermission/menuMain
---官网---
文章管理
文章菜单管理:1000400:article/articleMenuMain
文章内容管理:1000500:article/articleMain
一面墙管理
一面墙内容管理:1000600:wall/wallMain
---内嵌---

-----------------------------------------------
1、文章菜单：模块 -> 组 -> 文章
2、一面墙：jsonp爬虫

-----------------------------------------------
线上线下,差异配置文件：
	1、jdbc.properties					：数据库配置
	2、freemarker.variables.properties	：跟地址配置
	3、config.json 						：ueditor文件上传地址配置
	4、ueditor.properties				：ueditor文件上传参数配置
	5、memcached.properties				：缓存配置

-----------------------------------------------
JS插件引用：
	001：jquery-easyui-1.4：未做修改
	002：ueditor1_4_3 (上传本项目目录下):
		01：引入ueditor的JAR：ueditor-1.1.1.jar
		02：修改：config.json：常规配置,文件上传/管理路径：修改*UrlPrefix("/5i-admin"或项目域名)和*PathFormat("/uploadfiles/im..."); 
		03：修复bug：controller.jsp：添加部分代码,解决插件自身获取绝对路径bug;
		04：修复bug：ueditor.all.min.js：解决弹框层级冲突：zIndex:999改为zIndex:99999,解决与easyui的遮罩9000冲突;	
	003：七牛云存储 (整合ueditor) :
		01：引入七牛的JAR：qiniu-java-sdk.Jar;
		02：修改：修改ueditor的JAR/加新JAR配置文件：ueditor-1.1.1-qiniu-xxl.jar/ueditor.properties (保存文件时,优先判断保存七牛云)
		03：修改：config.json：修改*UrlPrefix地址：http://7xir7k.com1.z0.glb.clouddn.com/@;



		

