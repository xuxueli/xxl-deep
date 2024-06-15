package com.xxl.deep.admin.controller.interceptor;

import com.xxl.deep.admin.core.util.FtlUtil;
import com.xxl.deep.admin.core.util.I18nUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * push cookies to model as cookieMap
 *
 * @author xuxueli 2015-12-12 18:09:04
 */
@Component
public class I18nInterceptor implements AsyncHandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {


		// static method
		if (modelAndView != null) {
			modelAndView.addObject("I18nUtil", FtlUtil.generateStaticModel(I18nUtil.class.getName()));
		}

	}
	
}
