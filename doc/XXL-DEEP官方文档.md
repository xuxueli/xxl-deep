## 《分布式企业开发平台XXL-DEEP》

[![GitHub release](https://img.shields.io/github/release/xuxueli/xxl-deep.svg)](https://github.com/xuxueli/xxl-deep/releases)
[![License](https://img.shields.io/badge/license-GPLv3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
[![donate](https://img.shields.io/badge/%24-donate-ff69b4.svg?style=flat-square)](https://www.xuxueli.com/page/donate.html)

[TOCM]

[TOC]

## 一、简介

### 1.1 概述
XXL-DEEP 是一个分布式企业开发平台。基于 SpringBoot+XXL技术栈 进行系统架构。
提供整套开箱即用的基础模块，包括：权限管理、用户管理、代码生成、配置中心、调度中心……等，努力为中小型企业打造开箱即用的分布式企业开发解决方案。

### 1.2 特性



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
- JDK：1.7+


## 二、快速入门

### 第一步：编译项目
项目目录结构如下：
```
- xxl-deep
    - xxl-deep-admin   ：分布式企业开发平台XXL-DEEP
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
### 版本 v0.0.1 新特性[2018-05-03]
- 1、简洁：界面操作，简洁直观，可快速上手；
- 2、轻量级：仅需提供建表SQL，即可自动完成代码生成，简洁高效；
- 3、多层代码生成：自动生成  "controller/service/dao/mybatis/model" 多层代码，参与到开发全流程；
- 4、高效：从SQL到API接口，全部代码均支持自动生成，极大提高生产力和效率； 
- 5、在线预览：代码生成后，支持实时在线预览，直接复制使用；

### 版本 v0.0.2[迭代中]
- 1、表字段comment不支持逗号问题修复；
- 2、Docker基础镜像切换，精简镜像；
- 3、修复注释为空页面渲染报错问题；
- 4、数据库类型为char，解析成object问题修复；
- 5、建表语句包含unique key，key里的属性，重复生成问题修复；
- 6、项目依赖升级，并清理POM冗余依赖；

### TODO LIST
- 1、代码生成：
    - 多模板支持：当前模板为 "SpringMVC + Mybatis + Mysql" 技术栈；计划新增不同代码生成模板；
    - 扩展多层：目前支持生成 "controller/service/dao/mybatis/model" 层代码；计划新增前端代码，如 "jquery、vuejs、react" 等；

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