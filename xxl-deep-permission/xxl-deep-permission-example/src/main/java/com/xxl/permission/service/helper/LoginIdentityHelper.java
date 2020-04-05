package com.xxl.permission.service.helper;

import com.xxl.permission.controller.core.LoginIdentity;
import com.xxl.permission.core.constant.CommonDic.HttpSessionKeyDic;
import com.xxl.permission.core.model.XxlPermissionMenu;
import com.xxl.permission.core.model.XxlPermissionUser;
import com.xxl.permission.core.util.HttpSessionUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户登陆信息，操作相关
 * @author Administrator
 *
 */
public class LoginIdentityHelper {
	
	/**
	 * 填充“用户登录信息”
	 * @param identity
	 * @param user
	 */
	private static void fillin(LoginIdentity identity, XxlPermissionUser user, List<XxlPermissionMenu> menus){
		//BeanUtils.copyProperties(identity, user);
		
		identity.setUserId(user.getId());
		identity.setUserName(user.getUserName());
		identity.setPassword(user.getPassword());
		identity.setUserToken(user.getUserToken());
		identity.setRealName(user.getRealName());
		
		// 权限菜单
		identity.setMenePermissionNums(MenuModuleHelper.initMenePermissionNums(menus));
		identity.setMenuModuleJson(MenuModuleHelper.initMenuModuleJson(menus));
	}

	/**
	 * “用户登陆信息”-初始化
	 * @param user
	 */
	public static void login(HttpSession session, XxlPermissionUser user, List<XxlPermissionMenu> menus) {
		// 初始化用户登陆信息
		LoginIdentity identity = new LoginIdentity();
		LoginIdentityHelper.fillin(identity, user, menus);
		HttpSessionUtil.set(session, HttpSessionKeyDic.LOGIN_IDENTITY, identity);
	}

	/**
	 * “用户登陆信息”-注销
	 * @param session
	 */
	public static void logout(HttpSession session) {
		// SESSION移除 “用户登陆信息”
		HttpSessionUtil.remove(session, HttpSessionKeyDic.LOGIN_IDENTITY);
	}
}
