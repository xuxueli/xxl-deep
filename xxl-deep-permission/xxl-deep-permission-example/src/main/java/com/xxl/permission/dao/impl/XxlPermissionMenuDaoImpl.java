package com.xxl.permission.dao.impl;

import com.xxl.permission.core.model.XxlPermissionMenu;
import com.xxl.permission.dao.IXxlPermissionMenuDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuxueli
 */
@Repository
public class XxlPermissionMenuDaoImpl implements IXxlPermissionMenuDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int add(XxlPermissionMenu xxlPermissionMenu) {
		return sqlSessionTemplate.insert("XxlPermissionMenuMapper.add", xxlPermissionMenu);
	}

	@Override
	public int delete(int id) {
		return sqlSessionTemplate.delete("XxlPermissionMenuMapper.delete", id);
	}

	@Override
	public int update(XxlPermissionMenu xxlPermissionMenu) {
		return sqlSessionTemplate.update("XxlPermissionMenuMapper.update", xxlPermissionMenu);
	}

	@Override
	public XxlPermissionMenu load(int id) {
		return sqlSessionTemplate.selectOne("XxlPermissionMenuMapper.load", id);
	}

	@Override
	public List<XxlPermissionMenu> getAllMenus() {
		return sqlSessionTemplate.selectList("XxlPermissionMenuMapper.getAllMenus");
	}

	@Override
	public List<XxlPermissionMenu> getMenusByRoleId(int roleId) {
		return sqlSessionTemplate.selectList("XxlPermissionMenuMapper.getMenusByRoleId", roleId);
	}

	@Override
	public List<XxlPermissionMenu> getMenusByParentId(int parentId) {
		return sqlSessionTemplate.selectList("XxlPermissionMenuMapper.getMenusByParentId", parentId);
	}

	@Override
	public int findBindRoleCount(int menuId) {
		return sqlSessionTemplate.selectOne("XxlPermissionMenuMapper.findBindRoleCount", menuId);
	}

}
