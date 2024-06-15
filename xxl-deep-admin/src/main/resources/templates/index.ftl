<!DOCTYPE html>
<html>
<head>
    <#-- import macro -->
  	<#import "./common/common.macro.ftl" as netCommon>
    <#-- commonStyle -->
	<@netCommon.commonStyle />

    <!-- daterangepicker -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" >
    <div class="wrapper" >

        <!-- header -->
        <@netCommon.commonHeader />

        <!-- left -->
        <@netCommon.commonLeft "/" />

        <!-- right start -->
        <div class="content-wrapper">

            <!-- content-header -->
            <section class="content-header">
                <h1>工作台</h1>
                <!--
                <h1>运行报表<small>任务调度中心</small></h1>
                <ol class="breadcrumb">
                    <li><a><i class="fa fa-dashboard"></i>调度中心</a></li>
                    <li class="active">使用教程</li>
                </ol>
                -->
            </section>

            <!-- content-main -->
            <section class="content">

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
                                    <span style="color: #999;">登录时间：2024年05月01日 10:00:00</span>
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
                                <strong><i class="fa fa-book margin-r-5"></i> 用户管理</strong>
                                <p class="text-muted">
                                    新增和管理系统用户信息；同时进行人员角色、菜单权限、人员授权等操作管理。
                                </p>

                                <hr>
                                <strong><i class="fa fa-map-marker margin-r-5"></i> Location</strong>
                                <p class="text-muted">Malibu, California</p>

                                <hr>
                                <strong><i class="fa fa-pencil margin-r-5"></i> Skills</strong>
                                <p>
                                    <span class="label label-danger">UI Design</span>
                                    <span class="label label-success">Coding</span>
                                    <span class="label label-info">Javascript</span>
                                    <span class="label label-warning">PHP</span>
                                    <span class="label label-primary">Node.js</span>
                                </p>

                                <hr>
                                <strong><i class="fa fa-file-text-o margin-r-5"></i> Notes</strong>
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


            </section>

        </div>
        <!-- right end -->

        <!-- footer -->
        <@netCommon.commonFooter />
    </div>

    <#-- commonScript -->
    <@netCommon.commonScript />

    <!-- daterangepicker -->
    <script src="${request.contextPath}/static/adminlte/bower_components/moment/moment.min.js"></script>
    <script src="${request.contextPath}/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- echarts -->
    <script src="${request.contextPath}/static/plugins/echarts/echarts.common.min.js"></script>
    <!-- js file -->
    <script src="${request.contextPath}/static/js/index.js"></script>
</body>
</html>
