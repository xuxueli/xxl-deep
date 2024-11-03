## 《快速开发平台 XXL-BOOT》

[![GitHub release](https://img.shields.io/github/release/xuxueli/xxl-boot.svg)](https://github.com/xuxueli/xxl-boot/releases)
[![License](https://img.shields.io/badge/license-GPLv3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
[![donate](https://img.shields.io/badge/%24-donate-ff69b4.svg?style=flat-square)](https://www.xuxueli.com/page/donate.html)

[TOCM]

[TOC]

## 一、简介

### 1.1 概述

XXL-BOOT 是一个快速开发平台，核心目标是 简化开发、灵活扩展、能力丰富、开箱即用。内置安全登录、权限管控、端到端代码生成、响应式布局、多语言、通告触达……等能力。整合前后端流行技术，致力为 中小企业、个人开发者 打造开箱即用的中后台解决方案。

### 1.2 特性
- 1、组织管理：针对部门组织架构进行管理，进行多层级组织架构的新增、管理、排序等操作。
- 2、用户管理：针对系统用户进行管理，进行用户新增、管理、角色授权等操作。
- 3、角色管理：针对系统权限角色进行动态管理，进行角色新增、管理、菜单分配等操作。
- 4、资源管理：针对系统资源进行细粒度管理，支持页面、按钮等多类型资源管理，进行新增、管理等操作。
- 5、集群登录：系统基于token实现登录、注销能力，系统支持集群部署及登录。
- 6、通知管理：针对系统用户推送通知消息，支持自定义推送范围（全员、部分圈选），支持触达率、打开率监控。
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

- [中文文档](https://www.xuxueli.com/xxl-boot/)

#### 源码仓库地址

源码仓库地址 | Release Download
--- | ---
[https://github.com/xuxueli/xxl-boot](https://github.com/xuxueli/xxl-boot) | [Download](https://github.com/xuxueli/xxl-boot/releases)
[https://gitee.com/xuxueli0323/xxl-boot](https://gitee.com/xuxueli0323/xxl-boot) | [Download](https://gitee.com/xuxueli0323/xxl-boot/releases)  


#### 技术交流
- [社区交流](https://www.xuxueli.com/page/community.html)

### 1.4 环境
- JDK：1.8+


## 二、快速入门

### 第一步：编译项目
项目目录结构如下：
```
- xxl-boot
    - xxl-boot-admin        : 中后台系统模块
    - xxx-boot-web          : 前台系统服务（预留）
```

### 第二步：部署运行
应用 “xxl-boot-admin” 是个spring boot应用，直接启动运行即可。


## 三、操作指南

### 3.1、代码生成

#### 第一步：准备SQL
代码生成，是以数据库表为维度进行生成。所以，需要准备好待生成代码的表SQL脚本。
然后进入代码生成平台，将建表SQL脚本填写到 "表结构信息" 输入框即可。
（默认已经提供了一个供参考Demo表SQL脚本，可操作体验下）

![输入图片说明](https://www.xuxueli.com/doc/static/xxl-boot/images/img_01.png "在这里输入图片标题")


#### 第二步：生成代码
点击右上角 "生成代码按钮"，即可完整多层代码的生成，非常方便；

![输入图片说明](https://www.xuxueli.com/doc/static/xxl-boot/images/img_02.png "在这里输入图片标题")


#### 第三步：Finish
代码生成后，可在界面查看和使用 "controller/service/dao/mybatis/model" 多层源代码。部分截图如下：

![输入图片说明](https://www.xuxueli.com/doc/static/xxl-boot/images/img_03.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-boot/images/img_04.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-boot/images/img_05.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-boot/images/img_06.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-boot/images/img_07.png "在这里输入图片标题")
![输入图片说明](https://www.xuxueli.com/doc/static/xxl-boot/images/img_08.png "在这里输入图片标题")


## 四、总体设计

### 4.1、系统架构

略

### 4.2、RBAC权限体系

项目进行安全的用户权限体系设计，基于RBAC（Role-Based Access Control，基于角色的访问控制）这种广泛采用的权限管理模型，通过将权限授予角色，然后将角色分配给用户，从而实现对系统资源的访问控制。
RBAC 的设计目标是简化对系统资源的访问管理，提高系统的安全性和可维护性。以下是项目 RBAC 权限体系相关实体表：
```
xxl_boot_user           : 用户表
xxl_boot_role           : 角色表
xxl_boot_resource       : 资源表，菜单Page、功能Btn等。
xxl_boot_user_role      : 用户-角色关系表
xxl_boot_role_res       : 角色-资源关系表
```

### 4.2、安全登录验证

项目进行安全的登录验证防护设计，针对需要登录验证、以及需要强权限校验的页面、操作等资源控制场景，抽象出如下权限注解：
```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

	/**
	 * permission value (need login)    // 权限标识，为空则不校验，非空则需要通过RBAC权限体系进行相关资源授权才可访问
	 */
	String value() default "";

	/**
	 * need login                       // 是否需要登录验证，默认全部需要，特殊情况可定制
	 */
	boolean login() default true;
	
}
```

示例：
```
// 1、需要登录态
@Permission						: need login, but not valid permission

// 2、需要登录态，同时需要进行RBAC权限授权相关资源
@Permission("xxx")				: need login, and valid permission

// 3、不需要登录态
@Permission(login = false)		: not need login, not valid anything
```

### 4.2、一站式代码生成

参考上文 “3.1、代码生成”。

### 4.2、通告触达

略

### 4.2、审计日志

略

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
### 版本 v1.0.0 Release Notes[2024-11-03]
- 1、【整合】项目更名 XXL-BOOT，整合xxl-permission、xxl-code-generator多个历史项目；定位为 快速开发平台，整合流行前后端技术能力，致力为中小企业与个人开发者打造开箱即用的快速开发解决方案。
- 2、【规范】研发规范：基于标准分层架构设计，统一数据响应结构体，规范化项目目录结构。
- 3、【规范】异常机制：严谨设计全局异常处理机制、ErrorPage异常处理机制，保障系统底限安全体验。
- 4、【新增】组织管理：针对组织、用户、角色及资源等进行管理，支持灵活的人员角色、菜单权限、人员授权等操作管理。
- 5、【新增】系统管理：提供通知触达、审计日志、系统监控……等相关能力，支持高校灵活进行系统监控及管理。
- 6、【新增】系统工具：提供Entity、业务代码、SQL、页面交互等……前后端一站式代码生成工具，辅助快速进行敏捷迭代开发。
- 7、【扩展】分布式扩展：系统设计预留丰富扩展能力，可低成本扩展接入RPC、MQ、JOB、CONF、KV、SSO…等分布式中间件能力。


### TODO LIST
- 1、代码生成：支持交互层代码生成，包括ui及js；层级目录支持；
- 2、其他：快速开发框架；多技术栈；


## 五、其他

### 5.1 项目贡献
欢迎参与项目贡献！比如提交PR修复一个bug，或者新建 [Issue](https://github.com/xuxueli/xxl-boot/issues/) 讨论新特性或者变更。

### 5.2 用户接入登记
更多接入的公司，欢迎在 [登记地址](https://github.com/xuxueli/xxl-boot/issues/1 ) 登记，登记仅仅为了产品推广。

### 5.3 开源协议和版权
产品开源免费，并且将持续提供免费的社区技术支持。个人或企业内部可自由的接入和使用。

- Licensed under the GNU General Public License (GPL) v3.
- Copyright (c) 2015-present, xuxueli.

---
### 捐赠
无论金额多少都足够表达您这份心意，非常感谢 ：）      [前往捐赠](https://www.xuxueli.com/page/donate.html )