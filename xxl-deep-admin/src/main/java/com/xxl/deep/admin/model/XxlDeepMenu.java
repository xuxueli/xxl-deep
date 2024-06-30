package com.xxl.deep.admin.model;

import java.util.List;

/**
 * @author xuxueli 2024-06-15
 */
public class XxlDeepMenu {
	
	private int id;
	private String name;
	private String path;
	private int order;
	private String icon;
	private List<XxlDeepMenu> menuList;

	public XxlDeepMenu() {
	}

	public XxlDeepMenu(String name, String path, int order, String icon, List<XxlDeepMenu> menuList) {
		this.name = name;
		this.path = path;
		this.order = order;
		this.icon = icon;
		this.menuList = menuList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<XxlDeepMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<XxlDeepMenu> menuList) {
		this.menuList = menuList;
	}

}
