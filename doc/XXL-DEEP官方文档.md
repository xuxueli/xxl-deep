## 《快速开发平台XXL-DEEP》

[![GitHub release](https://img.shields.io/github/release/xuxueli/xxl-deep.svg)](https://github.com/xuxueli/xxl-deep/releases)
[![License](https://img.shields.io/badge/license-GPLv3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
[![donate](https://img.shields.io/badge/%24-donate-ff69b4.svg?style=flat-square)](https://www.xuxueli.com/page/donate.html)

[TOCM]

[TOC]

## 一、简介

### 1.1 概述
XXL-DEEP 是一个快速开发平台，核心目标是开发迅速、学习简单、能力丰富、开箱即用。支持 完善的权限管控、响应式页面布局、端到端代码生成、多语言开发、丰富基础模块……等等。
整合前后端流行技术，致力为 中小企业、个人开发者 打造开箱即用的快速开发解决方案。

### 1.2 特性
- 1、组织管理：针对部门组织架构进行管理，进行多层级组织架构的新增、管理、排序等操作。
- 2、用户管理：针对系统用户进行管理，进行用户新增、管理、角色授权等操作。
- 3、角色管理：针对系统权限角色进行动态管理，进行角色新增、管理、菜单分配等操作。
- 4、资源管理：针对系统资源进行细粒度管理，支持页面、按钮等多类型资源管理，进行新增、管理等操作。
- 5、集群登录：系统基于token实现登录、注销能力，系统支持集群部署及登录。
- 6、通知公告：针对系统用户推送通知公告，支持自定义推送范围（全员、部分圈选），支持触达率、打开率监控。
- 7、配置中心： 针对常用业务数据进行动态配置，如业务参数、数据字典等，进行新增、管理等操作。
- 8、审计日志：记录系统操作及活动的日志，用于系统的监控、审计和安全分析，可快速了解系统运行情况、发现异常行为、追溯问题源头，以及评估系统的安全性。
- 9、在线用户：实时查看分析当前在线用户，支持一键踢出异常用户登录态。
- 10、机器监控：针对服务器硬件资源监控，如CPU使用率、JVM状态、磁盘利用率……等；支持一键GC等系统主动优化能力。
- 11、代码生成：自带轻量级代码生成器，支持前后端、全流程代码生成，覆盖“controller、servie、mybatis、model……template、js” 等多层。
- 12、异常机制：严谨设计全局异常处理机制、ErrorPage异常处理机制，保障系统底限安全体验。
- 13、研发规范：基于标准分层架构设计，统一数据响应结构体，规范化项目目录结构。
- 14、分布式扩展：系统设计预留丰富扩展能力，可低成本扩展接入RPC、MQ、JOB、CONF、KV、SSO…等分布式中间件能力。

### 1.3 下载

#### 文档地址

- [中文文档](https://www.xuxueli.com/xxl-deep/)

#### 源码仓库地址

源码仓库地址 | Release Download
--- | ---
[https://github.com/xuxueli/xxl-deep](https://github.com/xuxueli/xxl-deep) | [Download](https://github.com/xuxueli/xxl-deep/releases)
[https://gitee.com/xuxueli0323/xxl-deep](https://gitee.com/xuxueli0323/xxl-deep) | [Download](https://gitee.com/xuxueli0323/xxl-deep/releases)  


#### 技术交流
- [社区交流](https://www.xuxueli.com/page/community.html)

### 1.4 环境
- JDK：1.8+


## 二、快速入门

### 第一步：编译项目
项目目录结构如下：
```
- xxl-deep
    - xxl-deep-admin        : 基础管理平台
    - xxl-deep-xx-service   : XX业务-服务模块
    - xxx-deep-xx-api       : XX业务-API模块
    - xxx-deep-xx-web       : XX业务-Web服务
```

### 第二步：部署运行
应用 “xxl-deep-admin” 是个spring boot应用，直接启动运行即可。


## 三、操作指南

### 3.1、代码生成

#### 第一步：准备SQL
代码生成，是以数据库表为维度进行生成。所以，需要准备好待生成代码的表SQL脚本。
然后进入代码生成平台，将建表SQL脚本填写到 "表结构信息" 输入框即可。
（默认已经提供了一个供参考Demo表SQL脚本，可操作体验下）

![输入图片说明](https://www.xuxueli.com/doc/static/xxl-deep/images/img_01.png "在这里输入图片标题")


#### 第二步：生成代码
点击右上角 "生成代码按钮"，即可完整多层代码的生成，非常方便；

![输入图片说明](https://www.xuxueli.com/doc/static/xxl-deep/images/img_02.png "在这里输入图片标题")


#### 第三步：Finish
代码生成后，可在界面查看和使用 "controller/service/dao/mybatis/model" 多层源代码。部分截图如下：

![输入图片说明](https://www.xuxueli.com/doc/static/xxl-deep/images/img_03.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-deep/images/img_04.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-deep/images/img_05.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-deep/images/img_06.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-deep/images/img_07.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-deep/images/img_08.png "在这里输入图片标题")


## 四、总体设计

### 4.1、设计思想
略

### 4.2、代码生成    
#### 公共响应结果：ReturnT
统一接口返回数据类型，有利于接口对接与效率提升；因此生成的多层代码交互时，采用统一ReturnT，源码如下:
```
import java.io.Serializable;

/**
 * common return
 * @author xuxueli 2015-12-4 16:32:31
 */
public class ReturnT<T> implements Serializable {
	public static final long serialVersionUID = 42L;

	public static final int SUCCESS_CODE = 200;
	public static final int FAIL_CODE = 500;
	public static final ReturnT<String> SUCCESS = new ReturnT<String>(null);
	public static final ReturnT<String> FAIL = new ReturnT<String>(FAIL_CODE, null);
	
	private int code;
	private String msg;
	private T data;
	
	public ReturnT(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public ReturnT(T data) {
		this.code = SUCCESS_CODE;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

}
```

#### 代码生成特性
代码生成拥有如下特性：
- 1、简洁：界面操作，简洁直观，可快速上手；
- 2、轻量级：仅需提供建表SQL，即可自动完成代码生成，简洁高效；
- 3、多层代码生成：自动生成  "controller/service/dao/mybatis/model" 多层代码，参与到开发全流程；
- 4、高效：从SQL到API接口，全部代码均支持自动生成，极大提高生产力和效率； 
- 5、在线预览：代码生成后，支持实时在线预览，直接复制使用；


## 四、版本更新日志
### 版本 v0.1.0 Release Notes[2018-05-03]
- 1、简洁：界面操作，简洁直观，可快速上手；
- 2、轻量级：仅需提供建表SQL，即可自动完成代码生成，简洁高效；
- 3、多层代码生成：自动生成  "controller/service/dao/mybatis/model" 多层代码，参与到开发全流程；
- 4、高效：从SQL到API接口，全部代码均支持自动生成，极大提高生产力和效率； 
- 5、在线预览：代码生成后，支持实时在线预览，直接复制使用；

### 版本 v0.2.0 Release Notes[2019-10-03]
- 1、表字段comment不支持逗号问题修复；
- 2、Docker基础镜像切换，精简镜像；
- 3、修复注释为空页面渲染报错问题；
- 4、数据库类型为char，解析成object问题修复；
- 5、建表语句包含unique key，key里的属性，重复生成问题修复；
- 6、项目依赖升级，并清理POM冗余依赖；
- 
### 版本 v1.0.0 Release Notes[迭代中]
- 1、【整合】项目更名 XXL-DEEP，整合xxl-permission、xxl-code-generator多个历史项目；定位为 快速开发平台，整合流行前后端技术能力，致力为中小企业与个人开发者打造开箱即用的快速开发解决方案。
- 2、【新增】代码生成功能集成，支持多层代码自动生成 "controller/service/dao/mybatis/model…"，助力全流程开发提效；
- 3、【新增】代码生成支持 单元测试、前端代码 生成；

### TODO LIST
- 1、代码生成：
    - 多模板支持：当前模板为 "SpringMVC + Mybatis + Mysql" 技术栈；计划新增不同代码生成模板；
    - 扩展多层：目前支持生成 "controller/service/dao/mybatis/model" 层代码；计划新增前端代码，如 "jquery、vuejs、react" 等；
- 2、其他：
    定位：快速开发框架；多技术栈；
    仓库格式：
```
/admin              ：快速版本（Bootstrap + SpringBoot）【管理系统开发框架】
    /doc
        /db/
        /XX.md
    /deep-admin（APP）
    /deep-auth
    /deep-codegen
    /deep-xxljob
    /deep-cms
/admin_vue          ：前后端分离版本（Vue + SpringBoot）【管理系统开发框架】
    /doc
    /deep-admin（APP）
    /deep-auth
    /deep-codegen
    /deep-ui
/admin_cloud        ：微服务版本（Vue + SpringCloud）
    /doc
    /deep-gateway（GW）
    /deep-auth（Node）
    /deep-codegen（Node）
    /deep-ui
README.md
``` 
    功能：
      - 多套技术栈：
        - 前端：模板引擎[freemarker] or 前后端分离[vue]
        - 后端：单体（admin + web） + 分布式:RPC/CONF/JOB/MQ(admin + web + service/api)
        - 业务：开发框架（系统管理/技术能力） + 业务系统（CMS + 工作流 + …）；
      - 系统管理：
        - 组织管理：部门 + 岗位
        - 人员管理：用户
        - 资源管理：菜单 + 按钮 + 资源
        - 权限管理：授权
      - 技术能力：代码生成 + 工作流 + 操作日志 + 系统监控 + 通知公告 + 字典管理；
      - 业务能力：
        - 端：管理端 + 用户端
        - 业务：内容发布（发布-CRUD，查询-RPC，定时发布-MQ，发布黑名单-CONF）、内容排行（排行生成-JOB、排行查询-Cache）；

## 五、其他

### 5.1 项目贡献
欢迎参与项目贡献！比如提交PR修复一个bug，或者新建 [Issue](https://github.com/xuxueli/xxl-deep/issues/) 讨论新特性或者变更。

### 5.2 用户接入登记
更多接入的公司，欢迎在 [登记地址](https://github.com/xuxueli/xxl-deep/issues/1 ) 登记，登记仅仅为了产品推广。

### 5.3 开源协议和版权
产品开源免费，并且将持续提供免费的社区技术支持。个人或企业内部可自由的接入和使用。

- Licensed under the GNU General Public License (GPL) v3.
- Copyright (c) 2015-present, xuxueli.

---
### 捐赠
无论金额多少都足够表达您这份心意，非常感谢 ：）      [前往捐赠](https://www.xuxueli.com/page/donate.html )