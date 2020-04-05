package com.xxl.permission.dao.impl;

import com.xxl.permission.core.model.XxlPermissionRole;
import com.xxl.permission.dao.IXxlPermissionRoleDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xuxueli
 */
@Repository
public class XxlPermissionRoleDaoImpl implements IXxlPermissionRoleDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int add(XxlPermissionRole xxlPermissionRole) {
		return sqlSessionTemplate.insert("XxlPermissionRoleMapper.add", xxlPermissionRole);
	}

	@Override
	public int delete(List<Integer> roleIds) {
		return sqlSessionTemplate.delete("XxlPermissionRoleMapper.delete", roleIds);
	}

	@Override
	public int update(XxlPermissionRole xxlPermissionRole) {
		return sqlSessionTemplate.update("XxlPermissionRoleMapper.update", xxlPermissionRole);
	}

	@Override
	public XxlPermissionRole loadRole(int id) {
		return sqlSessionTemplate.selectOne("XxlPermissionRoleMapper.loadRole", id);
	}

	@Override
	public List<XxlPermissionRole> getAllRoles() {
		return sqlSessionTemplate.selectList("XxlPermissionRoleMapper.getAllRoles");
	}

	@Override
	public int findUserRoleCount(int userId, int roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		return sqlSessionTemplate.selectOne("XxlPermissionRoleMapper.findUserRoleCount", params);
	}

	@Override
	public int findUserCountByRole(int roleId) {
		return sqlSessionTemplate.selectOne("XxlPermissionRoleMapper.findUserCountByRole", roleId);
	}

	@Override
	public List<XxlPermissionRole> findRoleByUserId(int userId) {
		return sqlSessionTemplate.selectList("XxlPermissionRoleMapper.findRoleByUserId", userId);
	}

	@Override
	public int bindRoleMenu(int roleId, Set<Integer> menudIds) {
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("menudIds", menudIds);

		return sqlSessionTemplate.insert("XxlPermissionRoleMapper.bindRoleMenu", params);
	}

	@Override
	public int unBindRoleMenu(int[] roleIds) {
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("roleIds", roleIds);
		return sqlSessionTemplate.delete("XxlPermissionRoleMapper.unBindRoleMenu", params);
	}

	@Override
	public int unBindRoleMenus(int roleId, Set<Integer> delMenudIds) {
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("delMenudIds", delMenudIds);
		
		return sqlSessionTemplate.insert("XxlPermissionRoleMapper.unBindRoleMenus", params);
	}

}
