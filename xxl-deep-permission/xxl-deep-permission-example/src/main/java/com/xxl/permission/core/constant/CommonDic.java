package com.xxl.permission.core.constant;


/**
 * 通用字典类
 * @author xuxueli
 */
public class CommonDic {

	public static final int SUPER_ROLE_ID = -1;	// 超级管理员-角色ID
	public static final int BIZ_MENU_ID = 0;	// 业务-菜单ID [最多支持三级 : 0-业务 >>>> 组 >>>> 项]

	/**
	 * HttpSession上下文
	 */
	public class HttpSessionKeyDic {
		public static final String LOGIN_IDENTITY = "login_identity";	// 用户登陆信息
	}

}
