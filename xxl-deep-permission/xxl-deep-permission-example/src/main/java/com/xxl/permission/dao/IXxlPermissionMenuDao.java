package com.xxl.permission.dao;

import com.xxl.permission.core.model.XxlPermissionMenu;

import java.util.List;

/**
 * @author xuxueli
 */
public interface IXxlPermissionMenuDao {

	public int add(XxlPermissionMenu xxlPermissionMenu);
	public int delete(int id);
	public int update(XxlPermissionMenu xxlPermissionMenu);

	public XxlPermissionMenu load(int id);
	public List<XxlPermissionMenu> getAllMenus();
	public List<XxlPermissionMenu> getMenusByRoleId(int roleId);
	public List<XxlPermissionMenu> getMenusByParentId(int parentId);

	public int findBindRoleCount(int menuId);
}
