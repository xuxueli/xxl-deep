package com.xxl.deep.admin.web.interceptor;

import com.xxl.deep.admin.model.dto.XxlDeepResourceDTO;
import com.xxl.deep.admin.model.entity.XxlDeepUser;
import com.xxl.deep.admin.util.I18nUtil;
import com.xxl.deep.admin.service.impl.LoginService;
import com.xxl.tool.freemarker.FreemarkerTool;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * push cookies to model as cookieMap
 *
 * @author xuxueli 2015-12-12 18:09:04
 */
@Component
public class CommonDataInterceptor implements AsyncHandlerInterceptor {

	@Resource
	private LoginService loginService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			// i18n, static method
			modelAndView.addObject("I18nUtil", FreemarkerTool.generateStaticModel(I18nUtil.class.getName()));

			// resource load
			XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
			if (loginUser != null) {

				// filter Authentication （by authorization）
				List<XxlDeepResourceDTO> resourceList = loginService.queryAuthentication(loginUser);
				modelAndView.addObject("resourceList", resourceList);
			}
		}

	}
	
}
