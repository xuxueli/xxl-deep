package com.xxl.permission.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限菜单[三级:模块-组-子项]
 * @author xuxueli
 */
public class XxlPermissionMenu {

	private int id;					// 主键ID
	private int parentId;			// 父节点ID
	private int order;				// 序号
	private String name;			// 名称
	private String permessionUrl;	// 权限URL
	private int permessionId;		// 权限ID

	// 关联角色ID
	private boolean checked;
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	// 父节点：用于easyui展示树形结构
	@SuppressWarnings("unused")
	private int _parentId;
	public int get_parentId() {
		return this.parentId;
	}
	public void set_parentId(int _parentId) {
		this._parentId = _parentId;
	}
	
	// 子菜单：用于手动生成权限菜单
	private List<XxlPermissionMenu> subMenus = new ArrayList<XxlPermissionMenu>();
	public List<XxlPermissionMenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<XxlPermissionMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermessionUrl() {
		return permessionUrl;
	}

	public void setPermessionUrl(String permessionUrl) {
		this.permessionUrl = permessionUrl;
	}

	public int getPermessionId() {
		return permessionId;
	}

	public void setPermessionId(int permessionId) {
		this.permessionId = permessionId;
	}
}
