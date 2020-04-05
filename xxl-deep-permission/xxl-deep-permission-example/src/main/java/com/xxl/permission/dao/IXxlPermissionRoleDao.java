package com.xxl.permission.dao;

import com.xxl.permission.core.model.XxlPermissionRole;

import java.util.List;
import java.util.Set;


/**
 * @author xuxueli
 */
public interface IXxlPermissionRoleDao {

	public int add(XxlPermissionRole xxlPermissionRole);
	public int delete(List<Integer> roleIds);
	public int update(XxlPermissionRole xxlPermissionRole);

	public XxlPermissionRole loadRole(int id);
	public List<XxlPermissionRole> getAllRoles();
	public int findUserRoleCount(int userId, int roleId);

	public int findUserCountByRole(int roleId);
	public List<XxlPermissionRole> findRoleByUserId(int userId);

	public int bindRoleMenu(int roleId, Set<Integer> menudIds);
	public int unBindRoleMenu(int[] roleIds);
	public int unBindRoleMenus(int roleId, Set<Integer> delMenudIds);

}
