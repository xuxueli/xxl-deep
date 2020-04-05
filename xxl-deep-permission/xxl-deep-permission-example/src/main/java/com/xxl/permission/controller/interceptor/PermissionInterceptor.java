package com.xxl.permission.controller.interceptor;

import com.google.code.kaptcha.Constants;
import com.xxl.permission.controller.annotation.PermessionType;
import com.xxl.permission.controller.core.LoginIdentity;
import com.xxl.permission.core.constant.CommonDic.HttpSessionKeyDic;
import com.xxl.permission.core.exception.WebException;
import com.xxl.permission.core.util.HttpSessionUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * “登陆+权限”拦截器
 * @author xuxueli
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	private static transient Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
	
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
			throw new WebException("权限受限");
		}
		boolean loginState = permission.loginState();
		int permessionNum = permission.permessionId();
		boolean randCode = permission.randCode();
		
		LoginIdentity identity = (LoginIdentity) HttpSessionUtil.get(session, HttpSessionKeyDic.LOGIN_IDENTITY);
		// 01：登陆拦截
		if (loginState) {
			if (identity == null) {
				//response.sendRedirect(request.getContextPath() + "/");
				//return false;
				throw new WebException("登录失效,请重新登录");
			}
		}
		// 02：权限拦截器
		if (permessionNum > 0) {
			if (identity == null || identity.getMenePermissionNums() == null || 
					!identity.getMenePermissionNums().contains(String.valueOf(permessionNum))) {
				throw new WebException("权限受限");
			}
		}
		
		// 03：随机验证码拦截
		if (randCode) {
			String randCodeCache = (String) HttpSessionUtil.get(session, Constants.KAPTCHA_SESSION_KEY);
			String randCodeParam = request.getParameter("randCode");
			if (StringUtils.isBlank(randCodeCache) || !StringUtils.equals(randCodeCache, randCodeParam)) {
				throw new WebException("验证码错误");
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
