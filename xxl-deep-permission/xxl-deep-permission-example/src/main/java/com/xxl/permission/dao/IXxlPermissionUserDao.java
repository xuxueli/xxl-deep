package com.xxl.permission.dao;

import com.xxl.permission.core.model.XxlPermissionUser;

import java.util.List;
import java.util.Set;

public interface IXxlPermissionUserDao {

	public int add(XxlPermissionUser xxlPermissionUser);
	public int delete(List<Integer> userIds);
	public int update(XxlPermissionUser xxlPermissionUser);

	public XxlPermissionUser loadUser(int userId);
	public XxlPermissionUser findUserByUserName (String username);

	public List<XxlPermissionUser> queryUser(int offset, int pagesize, String userName);
	public int queryUserCount(int offset, int pagesize, String userName);

	public int bindUserRoles(int userId, Set<Integer> addRoldIds);
	public int unBindUserRoles(int userId, Set<Integer> delRoldIds);
	public int unBindUserRoleAll(int[] userIds);

}
