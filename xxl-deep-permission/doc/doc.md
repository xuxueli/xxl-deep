### RBAC：基于角色的访问控制
基于角色的访问控制（Role-Based Access Control）：

系统元素可分为：
- 权限
- 角色
- 用户


需满足：
- 权限与角色多对多关联，可对角色赋予和回收权限，权限也分配给多个角色；
- 角色和用户多对多，用户可分属不同角色，角色也可以分配给多人；

### RBAC角色访问控制，建表SQL脚本
Tips-五张核心表 ：权限表、角色表、用户表、角色权限关联表、用户角色关联表；
```
CREATE TABLE `admin_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `order` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `url` varchar(50) DEFAULT NULL,
  `permession_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `admin_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `order` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `admin_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

CREATE TABLE `admin_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_token` varchar(50) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_unique` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5014 DEFAULT CHARSET=utf8;

CREATE TABLE `admin_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

```

### Annotation + 拦截器(Spring MVC)，实现权限过滤
1、用户根据角色进行登陆，登陆系统设计可参考“SKILL-用户登陆系统”；
2、根据用户登陆的角色，获取“权限列表”，存放在用户登陆信息缓存LoginIdentity中；
3、用户操作进行拦截器拦截，获取对应功能的权限ID，校验登陆用户是否拥有该权限；

##### 权限注解
```
package com.xxl.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限类型
 * @author xuxueli
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermessionType {
	
	/**
	 * 登陆拦截 (默认拦截)
	 */
	boolean loginState() default true;
	
	/**
	 * 权限编号 (默认不拦截)
	 */
	int permessionNum() default 0;
	
	/**
	 * 随机验证码拦截 (默认不拦截)
	 */
	boolean randCode() default false;
}
```

##### 为Controller方法，加权限注解
```
@RequestMapping("/articleMenuQuery")
@ResponseBody
@PermessionType(permessionNum = 1000400)
public List<ArticleMenu> articleMenuQuery() {
	return articleService.articleMenuQuery();
}
```

##### 在springmvc-context.xml配置中，配置权限拦截器
```
<mvc:interceptors>
	<mvc:interceptor>
		<mvc:mapping path="/**"/>
		<bean class="com.xxl.controller.interceptor.PermissionInterceptor"/>
	</mvc:interceptor>
</mvc:interceptors>
```

##### 权限拦截器，源码
```
package com.xxl.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.code.kaptcha.Constants;
import com.xxl.controller.annotation.PermessionType;
import com.xxl.controller.core.LoginIdentity;
import com.xxl.core.constant.CommonDic.HttpSessionKeyDic;
import com.xxl.core.constant.CommonDic.ReturnCodeEnum;
import com.xxl.core.exception.WebException;
import com.xxl.core.util.HttpSessionUtil;

/**
 * “登陆+权限”拦截器
 * @author xuxueli
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	//private static transient Logger logger = LoggerFactory.getLogger(LoginPermissionInterceptor.class);
	
	/*
	 * Controller“执行时异步”“执行
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterConcurrentHandlingStarted(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	/*
	 * Controller“执行后”执行
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	/*
	 * Controller“执行前”执行
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		// 请求非法
		if (!(handler instanceof HandlerMethod)) {
			return super.preHandle(request, response, handler);
		}
		
		// 获取“权限注解”
		HandlerMethod method = (HandlerMethod)handler;
		PermessionType permission = method.getMethodAnnotation(PermessionType.class);
		if (permission == null) {
			throw new WebException(ReturnCodeEnum.FAIL.code(), "权限受限");
		}
		boolean loginState = permission.loginState();
		int permessionNum = permission.permessionNum();
		boolean randCode = permission.randCode();
		
		LoginIdentity identity = (LoginIdentity) HttpSessionUtil.get(session, HttpSessionKeyDic.LOGIN_IDENTITY);
		// 01：登陆拦截
		if (loginState) {
			if (identity == null) {
				//response.sendRedirect(request.getContextPath() + "/");
				//return false;
				throw new WebException(ReturnCodeEnum.FAIL.code(), "登录失效,请重新登录");
			}
		}
		// 02：权限拦截器
		if (permessionNum > 0) {
			if (identity == null || identity.getMenePermissionNums() == null || 
					!identity.getMenePermissionNums().contains(String.valueOf(permessionNum))) {
				throw new WebException(ReturnCodeEnum.FAIL.code(), "权限受限");
			}
		}
		
		// 03：随机验证码拦截
		if (randCode) {
			String randCodeCache = (String) HttpSessionUtil.get(session, Constants.KAPTCHA_SESSION_KEY);
			String randCodeParam = request.getParameter("randCode");
			if (StringUtils.isBlank(randCodeCache) || !StringUtils.equals(randCodeCache, randCodeParam)) {
				throw new WebException(ReturnCodeEnum.FAIL.code(), "验证码错误");
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
```

