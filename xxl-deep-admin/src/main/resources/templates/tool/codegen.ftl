<!DOCTYPE html>
<html>
<head>
	<#-- import macro -->
	<#import "../common/common.macro.ftl" as netCommon>
	<#-- commonStyle -->
	<@netCommon.commonStyle />
	<link rel="stylesheet" href="${request.contextPath}/static/plugins/codemirror/lib/codemirror.css">
	<link rel="stylesheet" href="${request.contextPath}/static/plugins/codemirror/addon/hint/show-hint.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" >
<div class="wrapper">

	<!-- header -->
	<@netCommon.commonHeader />

	<!-- left -->
	<@netCommon.commonLeft "/tool/codegen" />

	<!-- right start -->
	<div class="content-wrapper">

		<!-- content-header -->
		<section class="content-header">
			<h1>${I18n.codegen_name}</h1>
		</section>

		<!-- content-main -->
		<section class="content">
			<#-- biz start  -->

			<#-- 表结构 -->
			<div class="box box-default">
				<div class="box-header with-border">
					<h4 class="pull-left">${I18n.codegen_tablesql}</h4>
					<button type="button" class="btn btn-primary btn-xs pull-right" id="codeGenerate" >生成代码</button>
				</div>
				<div class="box-body">
					<ul class="chart-legend clearfix">
						<li>
							<small class="text-muted" >
									<textarea id="tableSql" placeholder="${I18n.system_please_input}${I18n.codegen_tablesql}..." >
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
									</textarea>
							</small>
						</li>
					</ul>
				</div>
			</div>

			<#-- 生成代码 -->
			<div class="nav-tabs-custom" >
				<!-- Tabs within a box -->
				<ul class="nav nav-tabs pull-right" >
					<h4 class="pull-left" style="padding-left: 10px;">${I18n.codegen_result}</h4>

					<li><a href="#model" data-toggle="tab">Model</a></li>
					<li><a href="#mybatis" data-toggle="tab">Mybatis</a></li>
					<li><a href="#dao" data-toggle="tab">Dao</a></li>
					<li><a href="#service_impl" data-toggle="tab">ServiceImpl</a></li>
					<li><a href="#service" data-toggle="tab">Service</a></li>
					<li class="active" id="controller_nav" ><a href="#controller" data-toggle="tab" >Controller</a></li>

				</ul>
				<div class="tab-content no-padding">
					<div class="chart tab-pane active" id="controller">
						<div class="box-body">
							Controller：<textarea id="controller_ide" ></textarea>
						</div>
					</div>
					<div class="chart tab-pane active" id="service">
						<div class="box-body">
							Service：<textarea id="service_ide" ></textarea>
						</div>
					</div>
					<div class="chart tab-pane active" id="service_impl">
						<div class="box-body">
							ServiceImpl：<textarea id="service_impl_ide" ></textarea>
						</div>
					</div>
					<div class="chart tab-pane active" id="dao">
						<div class="box-body">
							Dao：<textarea id="dao_ide" ></textarea>
						</div>
					</div>
					<div class="chart tab-pane active" id="mybatis">
						<div class="box-body">
							Mybatis：<textarea id="mybatis_ide" ></textarea>
						</div>
					</div>
					<div class="chart tab-pane active" id="model" >
						<div class="box-body ">
							Model：<textarea id="model_ide" ></textarea>
						</div>
					</div>
				</div>
			</div>

			<#-- biz end  -->
		</section>

	</div>
	<!-- right end -->

	<!-- footer -->
	<@netCommon.commonFooter />
</div>
<@netCommon.commonScript />
<script src="${request.contextPath}/static/plugins/codemirror/lib/codemirror.js"></script>
<script src="${request.contextPath}/static/plugins/codemirror/addon/hint/show-hint.js"></script>
<script src="${request.contextPath}/static/plugins/codemirror/addon/hint/anyword-hint.js"></script>
<script src="${request.contextPath}/static/plugins/codemirror/addon/display/placeholder.js"></script>
<script src="${request.contextPath}/static/plugins/codemirror/mode/clike/clike.js"></script>
<script src="${request.contextPath}/static/plugins/codemirror/mode/sql/sql.js"></script>
<script src="${request.contextPath}/static/plugins/codemirror/mode/xml/xml.js"></script>
<script src="${request.contextPath}/static/js/tool/codegen.js"></script>
</body>
</html>