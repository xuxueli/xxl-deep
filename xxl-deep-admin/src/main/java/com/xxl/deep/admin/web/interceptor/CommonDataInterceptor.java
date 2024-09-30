package com.xxl.deep.admin.web.interceptor;

import com.xxl.deep.admin.model.entity.XxlDeepMenu;
import com.xxl.deep.admin.model.entity.XxlDeepUser;
import com.xxl.deep.admin.util.I18nUtil;
import com.xxl.deep.admin.service.impl.LoginService;
import com.xxl.tool.freemarker.FreemarkerTool;
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
			modelAndView.addObject("I18nUtil", FreemarkerTool.generateStaticModel(I18nUtil.class.getName()));

			// menu load
			XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
			if (loginUser != null) {

				// mock数据
				List<XxlDeepMenu> menuData = new ArrayList<>();
				menuData.add(new XxlDeepMenu("首页", "/index", 1, "fa-home", null));						// index

				menuData.add(new XxlDeepMenu("CMS管理（建设中）", "/cms/article", 1, "fa-leaf", null));						// user、content
				menuData.add(new XxlDeepMenu("CRM管理（建设中）", "/crm/custome", 1, "fa-leaf", null));						// custome，基础信息，联系方式
				menuData.add(new XxlDeepMenu("酒店管理（建设中）", "/hotel/room", 1, "fa-leaf", null));						// hotel

				// 组织管理 （组织人员/adminuser + RBAC）
				menuData.add(new XxlDeepMenu("组织管理", "/org", 2,"fa-users",
						Arrays.asList(
								new XxlDeepMenu("组织管理", "/org/org", 1, "", null),
								new XxlDeepMenu("用户管理", "/org/user", 2, "", null),
								new XxlDeepMenu("角色管理", "/org/role", 3, "", null),
								new XxlDeepMenu("资源管理", "/org/resource", 4, "", null))));

				// 系统管理
				menuData.add(new XxlDeepMenu("系统管理", "/sys", 3,"fa-cogs",
						Arrays.asList(
								new XxlDeepMenu("通知公告（建设中）", "/sys/notice", 1, "", null),
								new XxlDeepMenu("配置中心（建设中）", "/sys/data", 2, "", null),
								new XxlDeepMenu("审计日志（建设中）", "/sys/log", 3, "", null),
								new XxlDeepMenu("在线用户（建设中）", "/sys/online_user", 4, "", null),
								new XxlDeepMenu("机器监控（建设中）", "/sys/monitor", 5, "", null)
						)));

				// 系统工具
				menuData.add(new XxlDeepMenu("系统工具", "/tool", 4,"fa-wrench",
						Arrays.asList(
								new XxlDeepMenu("代码生成", "/tool/codegen", 3, "", null))));

				// 帮助
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
