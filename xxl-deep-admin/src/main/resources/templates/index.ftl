<!DOCTYPE html>
<html>
<head>
    <#-- import macro -->
  	<#import "./common/common.macro.ftl" as netCommon>
    <#-- commonStyle -->
	<@netCommon.commonStyle />

    <#-- biz start（1/5 style） -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.css">
    <#-- biz end（1/5 end） -->

</head>
<body class="hold-transition skin-blue sidebar-mini" >
<div class="wrapper" >

    <!-- header -->
    <@netCommon.commonHeader />

    <!-- left -->
    <#-- biz start（2/5 left） -->
    <@netCommon.commonLeft "/index" />
    <#-- biz end（2/5 left） -->

    <!-- right start -->
    <div class="content-wrapper">

        <!-- content-header -->
        <section class="content-header">
            <#-- biz start（3/5 name） -->
            <h1>${I18n.index_name}</h1>
            <#-- biz end（3/5 name） -->
        </section>

        <!-- content-main -->
        <section class="content">

            <#-- biz start（4/5 content） -->

            <!-- 顶部公告 start -->
            <div class="box box-default">
                <div class="box-body">
                    <div class="row">
                        <!-- left -->
                        <div class="col-md-6">
                            <div class="pull-left image">
                                <img src="https://www.xuxueli.com/doc/static/xxl-job/images/xxl-logo.png" style="height: 80px;" >
                            </div>
                            <div class="pull-left info" style="padding-left: 10px;padding-top: 5px;" >
                                <h4>早安，吴彦祖，祝你开心每一天！</h4>
                                <span style="color: #999;">登录时间：${.now?string('yyyy年MM月dd日 HH:mm:ss')}</span>
                            </div>
                        </div>
                        <!-- right -->
                        <div class="col-md-6">
                            <div class="pull-right info" style="margin-top: 10px;margin-right: 30px;">
                                <span style="color: #999;">用户浏览</span><br><span style="font-size: 30px;font-weight: 300;">900000</span>
                            </div>
                            <div class="pull-right info" style="margin-top: 10px;margin-right: 15px;padding-right: 15px;border-right: 1px solid #E5E5E5;">
                                <span style="color: #999;">注册用户</span><br><span style="font-size: 30px;font-weight: 300;">5000</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 顶部公告 end --->

            <!-- 个人信息区域 start -->
            <div class="row">
                <div class="col-md-8">
                    <!-- 常用功能 -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">常用功能</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <strong><i class="fa fa-users margin-r-5"></i>组织管理</strong>
                            <p class="text-muted">
                                新增和管理组织信息、人员信息；同时进行人员角色、菜单权限、人员授权等操作管理。
                            </p>

                            <hr>
                            <strong><i class="fa fa-cogs margin-r-5"></i>系统管理</strong>
                            <p class="text-muted">
                                新增管理通知公告；维护管理系统字典；查看系统日志、机器监控与在线用户等信息。
                            </p>

                            <hr>
                            <strong><i class="fa fa-wrench margin-r-5"></i>系统工具</strong>
                            <p class="text-muted">
                                系统默认提供系统工具，辅助快速进行敏捷迭代开发；支持前后端代码生成；
                            </p>

                            <hr>
                            <strong><i class="fa fa-book margin-r-5"></i> 帮助中心</strong>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam fermentum enim neque.</p>

                        </div>
                        <!-- /.box-body -->
                    </div>

                </div>
                <div class="col-md-4">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">动态</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <ul class="products-list product-list-in-box">
                                <li class="item">
                                    <div class="product-img">
                                        <img src="https://www.xuxueli.com/doc/static/xxl-job/images/xxl-logo.png" alt="Product Image">
                                    </div>
                                    <div class="product-info">
                                        <a href="javascript:void(0)" class="product-title">Samsung TV
                                            <span class="label label-warning pull-right">$1800</span></a>
                                        <span class="product-description">Samsung 32" 1080p 60Hz LED Smart HDTV.</span>
                                    </div>
                                </li>
                                <!-- /.item -->
                                <li class="item">
                                    <div class="product-img">
                                        <img src="https://www.xuxueli.com/doc/static/xxl-job/images/xxl-logo.png" alt="Product Image">
                                    </div>
                                    <div class="product-info">
                                        <a href="javascript:void(0)" class="product-title">Bicycle
                                            <span class="label label-info pull-right">$700</span></a>
                                        <span class="product-description">
                          26" Mongoose Dolomite Men's 7-speed, Navy Blue.
                        </span>
                                    </div>
                                </li>
                                <!-- /.item -->
                                <li class="item">
                                    <div class="product-img">
                                        <img src="https://www.xuxueli.com/doc/static/xxl-job/images/xxl-logo.png" alt="Product Image">
                                    </div>
                                    <div class="product-info">
                                        <a href="javascript:void(0)" class="product-title">Xbox One <span class="label label-danger pull-right">$350</span></a>
                                        <span class="product-description">
                          Xbox One Console Bundle with Halo Master Chief Collection.
                        </span>
                                    </div>
                                </li>
                                <!-- /.item -->
                                <li class="item">
                                    <div class="product-img">
                                        <img src="https://www.xuxueli.com/doc/static/xxl-job/images/xxl-logo.png" alt="Product Image">
                                    </div>
                                    <div class="product-info">
                                        <a href="javascript:void(0)" class="product-title">PlayStation 4
                                            <span class="label label-success pull-right">$399</span></a>
                                        <span class="product-description">
                          PlayStation 4 500GB Console (PS4)
                        </span>
                                    </div>
                                </li>
                                <!-- /.item -->
                            </ul>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer text-center">
                            <a href="javascript:void(0)" class="uppercase">View All Products</a>
                        </div>
                        <!-- /.box-footer -->
                    </div>
                </div>
            </div>
            <!-- 个人信息区域 end -->

            <#-- biz end（4/5 content） -->

        </section>

    </div>
    <!-- right end -->

    <!-- footer -->
    <@netCommon.commonFooter />
</div>

<#-- commonScript -->
<@netCommon.commonScript />

<#-- biz start（5/5 script） -->
<!-- daterangepicker -->
<script src="${request.contextPath}/static/adminlte/bower_components/moment/moment.min.js"></script>
<script src="${request.contextPath}/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- echarts -->
<script src="${request.contextPath}/static/plugins/echarts/echarts.common.min.js"></script>
<!-- js file -->
<script src="${request.contextPath}/static/js/index.js"></script>
<#-- biz end（5/5 script） -->

</body>
</html>
