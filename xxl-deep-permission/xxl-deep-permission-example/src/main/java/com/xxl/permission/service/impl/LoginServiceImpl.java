package com.xxl.permission.service.impl;

import com.xxl.permission.controller.core.LoginIdentity;
import com.xxl.permission.core.constant.CommonDic;
import com.xxl.permission.core.model.XxlPermissionMenu;
import com.xxl.permission.core.model.XxlPermissionUser;
import com.xxl.permission.core.result.ReturnT;
import com.xxl.permission.core.util.HttpSessionUtil;
import com.xxl.permission.dao.IXxlPermissionMenuDao;
import com.xxl.permission.dao.IXxlPermissionRoleDao;
import com.xxl.permission.dao.IXxlPermissionUserDao;
import com.xxl.permission.service.ILoginService;
import com.xxl.permission.service.helper.LoginIdentityHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台用户
 * @author xuxueli
 */
@Service
public class LoginServiceImpl implements ILoginService {
	private static transient Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private IXxlPermissionUserDao xxlPermissionUserDao;
	@Autowired
	private IXxlPermissionRoleDao xxlPermissionRoleDao;
	@Autowired
	private IXxlPermissionMenuDao xxlPermissionMenuDao;
	
	/*
	 * 登陆
	 * @see com.xxl.service.IAdminUserService#loginDo(javax.servlet.http.HttpSession, java.lang.String, java.lang.String)
	 */
	@Override
	public ReturnT<String> login(HttpSession session, String username, String password, int roleId) {

		// 参数校验
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "用户名或密码为空");
		}
		
		// 用户校验--账号密码校验
		XxlPermissionUser user = xxlPermissionUserDao.findUserByUserName(username);
		if (user == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "用户名不存在");
		}
		if (!password.equals(user.getPassword())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "密码错误");
		}

		// 角色校验
		int  userRoleCount = xxlPermissionRoleDao.findUserRoleCount(user.getId(), roleId);
		if (userRoleCount < 1) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "对不起,角色权限不足");
		}

		// 菜单校验
		List<XxlPermissionMenu> menus = null;
		if (CommonDic.SUPER_ROLE_ID == roleId) {
			menus = xxlPermissionMenuDao.getAllMenus();
		} else {
			menus = xxlPermissionMenuDao.getMenusByRoleId(roleId);
		}

		if (CollectionUtils.isEmpty(menus)) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "对不起,菜单权限不足");
		}
		
		// “用户登陆信息” + “用户权限信息” -- 初始化
		LoginIdentityHelper.login(session, user, menus);
				
		logger.info("后台登录成功：username:{}", new Object[]{username});
		return ReturnT.SUCCESS;
	}
	
	/*
	 * 注销登陆
	 * @see com.xxl.service.IAdminUserService#logout(javax.servlet.http.HttpSession)
	 */
	@Override
	public ReturnT<String> logout(HttpSession session) {

		// “用户登陆信息” + “用户权限信息” -- 注销
		LoginIdentityHelper.logout(session);
		
		return ReturnT.SUCCESS;
	}

	/*
	 * 修改密码
	 * @see com.xxl.service.IAdminUserService#modifyPwd(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ReturnT<String> modifyPwd(HttpSession session, String password, String newPwd, String reNewPwd) {

		// 旧密码校验
		if (StringUtils.isBlank(password) || password.trim().length() < 6 || password.trim().length() > 16) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请正确输入旧密码");
		}
		
		LoginIdentity identity = (LoginIdentity) HttpSessionUtil.get(session, CommonDic.HttpSessionKeyDic.LOGIN_IDENTITY);
		if (!password.equals(identity.getPassword())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "您输入的旧密码错误");
		}
		
		// 新密码校验
		if (StringUtils.isBlank(newPwd) || newPwd.trim().length() < 6 || newPwd.trim().length() > 16) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "新密码格式不正确[6-16位]");
		}
		
		if (!newPwd.equals(reNewPwd)) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "新密码和确认密码不一致");
		}
		
		if (password.equals(newPwd)) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "新密码不允许和旧密码相同");
		}
		
		// 修改密码
		XxlPermissionUser user = xxlPermissionUserDao.findUserByUserName(identity.getUserName());
		if (user == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "系统登录异常");
		}
		user.setPassword(newPwd);
		int count = xxlPermissionUserDao.update(user);
		if (count < 1) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "密码修改失败，请稍后重试");
		}
		
		// “用户登陆信息”-注销
		LoginIdentityHelper.logout(session);
		
		logger.info("后台修改密码成功：username:{}", new Object[]{identity.getUserName()});
		return ReturnT.SUCCESS;
	}

}
