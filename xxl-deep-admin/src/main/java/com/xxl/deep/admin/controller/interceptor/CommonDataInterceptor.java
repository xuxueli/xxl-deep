package com.xxl.deep.admin.controller.interceptor;

import com.xxl.deep.admin.core.model.XxlDeepMenu;
import com.xxl.deep.admin.core.model.XxlDeepUser;
import com.xxl.deep.admin.core.util.FtlUtil;
import com.xxl.deep.admin.core.util.I18nUtil;
import com.xxl.deep.admin.service.impl.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * push cookies to model as cookieMap
 *
 * @author xuxueli 2015-12-12 18:09:04
 */
@Component
public class CommonDataInterceptor implements AsyncHandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {

			// i18n, static method
			modelAndView.addObject("I18nUtil", FtlUtil.generateStaticModel(I18nUtil.class.getName()));

			// menu load
			XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
			if (loginUser != null) {

				// mock数据
				List<XxlDeepMenu> menuData = new ArrayList<>();
				menuData.add(new XxlDeepMenu("首页", "/", 1, "fa-home", null));
				menuData.add(new XxlDeepMenu("用户权限", "", 2,"fa-users",
						Arrays.asList(
								new XxlDeepMenu("用户管理", "/user", 1, "", null),
								new XxlDeepMenu("角色管理", "/role", 2, "", null),
								new XxlDeepMenu("菜单管理", "/menu", 3, "", null))));
				menuData.add(new XxlDeepMenu("个人信息", "/myinfo", 3, "fa-user", null));
				menuData.add(new XxlDeepMenu("帮助中心", "/help", 4, "fa-book", null));


				// 管理员过滤
				if (loginUser.getRole() == 1) {
					// 权限过滤
				}

				modelAndView.addObject("menuData", menuData);
			}

		}

	}
	
}
