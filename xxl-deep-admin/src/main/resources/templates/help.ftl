<!DOCTYPE html>
<html>
<head>
	<#-- import macro -->
  	<#import "./common/common.macro.ftl" as netCommon>
	<#-- commonStyle -->
	<@netCommon.commonStyle />
</head>
<body class="hold-transition skin-blue sidebar-mini" >
<div class="wrapper">

	<!-- header -->
	<@netCommon.commonHeader />

	<!-- left -->
	<@netCommon.commonLeft "/help" />

	<!-- right start -->
	<div class="content-wrapper">

		<!-- content-header -->
		<section class="content-header">
			<h1>${I18n.admin_help}</h1>
		</section>

		<!-- content-main -->
		<section class="content">
			<div class="callout callout-info">
				<h4>${I18n.admin_name_full}</h4>
				<br>
				<p>
					<a target="_blank" href="https://github.com/xuxueli/xxl-deep">Github</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<iframe src="https://ghbtns.com/github-btn.html?user=xuxueli&repo=xxl-deep&type=star&count=true" frameborder="0" scrolling="0" width="170px" height="20px" style="margin-bottom:-5px;"></iframe>
					<br><br>
                    <a target="_blank" href="https://www.xuxueli.com/xxl-deep/">${I18n.admin_help_document}</a>
                    <br><br>

				</p>
				<p></p>
            </div>
		</section>

	</div>
	<!-- right end -->

	<!-- footer -->
	<@netCommon.commonFooter />
</div>
<@netCommon.commonScript />
</body>
</html>
