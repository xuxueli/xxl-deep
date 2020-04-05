package com.xxl.permission.service;

import com.xxl.permission.core.model.XxlPermissionMenu;
import com.xxl.permission.core.result.ReturnT;

import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * 用户-角色-权限
 * @author xuxueli
 */
public interface IUserPermissionService {
	
	// ---------------------- user ----------------------
	public Map<String, Object> userQuery(int page,int rows, String userName);

	public ReturnT<Integer> userAdd(String userName, String password);
	public ReturnT<Integer> userDel(HttpSession session, int[] userIds);
	public ReturnT<Integer> userUpdate(int userId, String userName,	String password);

	// ---------------------- user-role ----------------------
	public String userRoleQueryJson(int userId);
	public ReturnT<Integer> userRoleUpdate(HttpSession session, int userId, int[] roleIds);

	// ---------------------- role ----------------------
	public Map<String, Object> roleQuery();
	public ReturnT<Integer> roleAdd(String name, int order);
	public ReturnT<Integer> roleDel(int[] roleIds);
	public ReturnT<Integer> roleUpdate(int roleId, String name, int order);

	// ---------------------- role-menu ----------------------
	public String roleMenuQueryJson(int roleId);
	public ReturnT<String> roleMenuUpdate(int roleId, int[] menuIds);

	// ---------------------- menu ----------------------
	public Map<String, Object> menuQuery();
	public ReturnT<Integer> menuAdd(XxlPermissionMenu menu);
	public ReturnT<Integer> menuDel(int menuId);
	public ReturnT<Integer> menuUpdate(XxlPermissionMenu menu);
}
