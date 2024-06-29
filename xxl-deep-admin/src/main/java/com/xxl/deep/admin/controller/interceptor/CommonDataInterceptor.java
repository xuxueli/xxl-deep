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
				menuData.add(new XxlDeepMenu("首页", "/index", 1, "fa-home", null));						// index

				menuData.add(new XxlDeepMenu("CMS管理（建设中）", "/cms/article", 1, "fa-leaf", null));						// user、content
				menuData.add(new XxlDeepMenu("CRM管理（建设中）", "/crm/custome", 1, "fa-leaf", null));						// custome，基础信息，联系方式
				menuData.add(new XxlDeepMenu("酒店管理（建设中）", "/hotel/room", 1, "fa-leaf", null));						// hotel

				// 组织管理、权限管理/RBAC（admin_user）
				menuData.add(new XxlDeepMenu("组织管理", "/org", 2,"fa-users",
						Arrays.asList(
								new XxlDeepMenu("组织管理", "/org/org", 1, "", null),
								new XxlDeepMenu("人员管理", "/org/user", 1, "", null),
								new XxlDeepMenu("角色管理", "/org/role", 2, "", null),
								new XxlDeepMenu("菜单管理", "/org/menu", 3, "", null))));

				// 系统管理
				menuData.add(new XxlDeepMenu("系统管理", "/sys", 3,"fa-cogs",
						Arrays.asList(
								new XxlDeepMenu("通知公告", "/sys/notice", 1, "", null),
								new XxlDeepMenu("系统字典", "/sys/data", 1, "", null),
								new XxlDeepMenu("系统日志", "/sys/log", 2, "", null),
								new XxlDeepMenu("机器监控", "/sys/monitor", 2, "", null),
								new XxlDeepMenu("在线用户", "/sys/online_user", 3, "", null))));

				// 代码生成
				menuData.add(new XxlDeepMenu("系统工具", "/tool", 4,"fa-wrench",
						Arrays.asList(
								new XxlDeepMenu("代码生成", "/tool/codegen", 3, "", null))));

				//menuData.add(new XxlDeepMenu("我的信息", "/myinfo", 3, "fa-user", null));			// 基础信息、修改密码
				menuData.add(new XxlDeepMenu("帮助中心", "/help", 5, "fa-book", null));

				// 管理员过滤
				if (loginUser.getRole() == 1) {
					// 权限过滤
				}

				modelAndView.addObject("menuData", menuData);
			}

		}

	}
	
}
