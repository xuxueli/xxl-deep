package com.xxl.permission.dao.impl;

import com.xxl.permission.core.model.XxlPermissionUser;
import com.xxl.permission.dao.IXxlPermissionUserDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class XxlPermissionUserDaoImpl implements IXxlPermissionUserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int add(XxlPermissionUser xxlPermissionUser) {
		return sqlSessionTemplate.insert("XxlPermissionUserMapper.add", xxlPermissionUser);
	}

	@Override
	public int delete(List<Integer> userIds) {
		return sqlSessionTemplate.delete("XxlPermissionUserMapper.delete", userIds);
	}

	@Override
	public int update(XxlPermissionUser xxlPermissionUser) {
		return sqlSessionTemplate.update("XxlPermissionUserMapper.update", xxlPermissionUser);
	}

	@Override
	public XxlPermissionUser loadUser(int id) {
		return sqlSessionTemplate.selectOne("XxlPermissionUserMapper.loadUser", id);
	}

	@Override
	public XxlPermissionUser findUserByUserName (String username) {
		return sqlSessionTemplate.selectOne("XxlPermissionUserMapper.findUserByUserName", username);
	}


	@Override
	public List<XxlPermissionUser> queryUser(int offset, int pagesize, String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("userName", userName);
		
		return sqlSessionTemplate.selectList("XxlPermissionUserMapper.queryUser", params);
	}

	@Override
	public int queryUserCount(int offset, int pagesize, String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("userName", userName);
		
		return sqlSessionTemplate.selectOne("XxlPermissionUserMapper.queryUserCount", params);
	}

	@Override
	public int bindUserRoles(int userId, Set<Integer> addRoldIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("addRoldIds", addRoldIds);
		return sqlSessionTemplate.delete("XxlPermissionUserMapper.bindUserRoles", params);
	}

	@Override
	public int unBindUserRoles(int userId, Set<Integer> delRoldIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("delRoldIds", delRoldIds);
		return sqlSessionTemplate.delete("XxlPermissionUserMapper.unBindUserRoles", params);
	}

	@Override
	public int unBindUserRoleAll(int[] userIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIds", userIds);
		return sqlSessionTemplate.delete("XxlPermissionUserMapper.unBindUserRoleAll", params);
	}

}
