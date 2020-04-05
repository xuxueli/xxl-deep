package com.xxl.permission.controller;

import com.xxl.permission.controller.annotation.PermessionType;
import com.xxl.permission.core.model.XxlPermissionMenu;
import com.xxl.permission.core.result.ReturnT;
import com.xxl.permission.dao.IXxlPermissionRoleDao;
import com.xxl.permission.service.IUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 权限相关
 * @author xuxueli
 */
@Controller
@RequestMapping("/userPermission")
public class UserPermissionController {
	
	@Autowired
	private IUserPermissionService userPermissionService;
	@Autowired
	private IXxlPermissionRoleDao adminRoleDao;
	
	//-----------------------后台用户相关-----------------------
	@RequestMapping("/userMain")
	@PermessionType(permessionId = 1000100)
	public String userMain(ModelMap model) {
		return "userPermission/userMain";
	}
	
	@RequestMapping("/userQuery")
	@ResponseBody
	@PermessionType(permessionId = 1000100)
	public Map<String, Object> userQuery(@RequestParam(required=false, defaultValue="0")int page,@RequestParam(required=false, defaultValue="0")int rows, String userName) {
		Map<String, Object> resultMap = userPermissionService.userQuery(page, rows, userName);
		return resultMap;
	}
	
	@RequestMapping("/userAdd")
	@ResponseBody
	@PermessionType(permessionId = 1000100)
	public ReturnT<Integer> userAdd(String userName, String password) {
		return userPermissionService.userAdd(userName, password);
	}
	
	@RequestMapping("/userDel")
	@ResponseBody
	@PermessionType(permessionId = 1000100)
	public ReturnT<Integer> userDel(HttpSession session, @RequestParam("userIds[]") int[] userIds) {
		return userPermissionService.userDel(session, userIds);
	}
	
	@RequestMapping("/userUpdate")
	@ResponseBody
	@PermessionType(permessionId = 1000100)
	public ReturnT<Integer> userUpdate(int id, String userName, String password) {
		return userPermissionService.userUpdate(id, userName, password);
	}
	
	@RequestMapping("/userRoleQuery")
	@ResponseBody
	@PermessionType(permessionId = 1000100)
	public String userRoleQuery(int userId) {
		return userPermissionService.userRoleQueryJson(userId);
	}
	
	@RequestMapping("/userRoleUpdate")
	@ResponseBody
	@PermessionType(permessionId = 1000100)
	public ReturnT<Integer> userRoleUpdate(HttpSession session, int userId, @RequestParam("roleIds[]") int[] roleIds) {
		return userPermissionService.userRoleUpdate(session, userId, roleIds);
	}
	
	//-----------------------后台角色相关-----------------------
	@RequestMapping("/roleMain")
	@PermessionType(permessionId = 1000200)
	public String roleMain() {
		return "userPermission/roleMain";
	}
	
	@RequestMapping("/roleQuery")
	@ResponseBody
	@PermessionType(permessionId = 1000200)
	public Map<String, Object> roleQuery() {
		Map<String, Object> resultMap = userPermissionService.roleQuery();
		return resultMap;
	}
	
	@RequestMapping("/roleAdd")
	@ResponseBody
	@PermessionType(permessionId = 1000200)
	public ReturnT<Integer> roleAdd(HttpSession session, String name, int order) {
		return userPermissionService.roleAdd(name, order);
	}
	
	@RequestMapping("/roleDel")
	@ResponseBody
	@PermessionType(permessionId = 1000200)
	public ReturnT<Integer> roleDel(HttpSession session, @RequestParam("roleIds[]") int[] roleIds) {
		return userPermissionService.roleDel(roleIds);
	}
	
	@RequestMapping("/roleUpdate")
	@ResponseBody
	@PermessionType(permessionId = 1000200)
	public ReturnT<Integer> roleUpdate(HttpSession session, int id, String name, int	order) {
		return userPermissionService.roleUpdate(id, name, order);
	}
	
	@RequestMapping("/roleMenuQuery")
	@ResponseBody
	@PermessionType(permessionId = 1000200)
	public String roleMenuQuery(int roleId) {
		return userPermissionService.roleMenuQueryJson(roleId);
	}
	
	@RequestMapping("/roleMenuUpdate")
	@ResponseBody
	@PermessionType(permessionId = 1000200)
	public ReturnT<String> roleMenuUpdate(int roleId, @RequestParam(value="menuIds[]") int[] menuIds) {
		return userPermissionService.roleMenuUpdate(roleId, menuIds);
	}
	
	//-----------------------后台菜单相关-----------------------
	@RequestMapping("/menuMain")
	@PermessionType(permessionId = 1000300)
	public String menuMain() {
		return "userPermission/menuMain";
	}
	
	@RequestMapping("/menuQuery")
	@ResponseBody
	@PermessionType(permessionId = 1000300)
	public Map<String, Object> menuQuery() {
		Map<String, Object> resultMap = userPermissionService.menuQuery();
		return resultMap;
	}
	
	@RequestMapping("/menuAdd")
	@ResponseBody
	@PermessionType(permessionId = 1000300)
	public ReturnT<Integer> menuAdd(XxlPermissionMenu menu) {
		return userPermissionService.menuAdd(menu);
	}
	
	@RequestMapping("/menuDel")
	@ResponseBody
	@PermessionType(permessionId = 1000300)
	public ReturnT<Integer> menuDel(int menuId) {
		return userPermissionService.menuDel(menuId);
	}
	
	@RequestMapping("/menuUpdate")
	@ResponseBody
	@PermessionType(permessionId = 1000300)
	public ReturnT<Integer> menuUpdate(XxlPermissionMenu menu) {
		return userPermissionService.menuUpdate(menu);
	}
	
}
