package com.xxl.permission.core.model;

/**
 * 角色表
 * @author xuxueli
 */
public class XxlPermissionRole {
	
	private int id;
	private String name;
	private int order;

	// 角色是否拥有
	private boolean checked;
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	// tree test
	private String text;
	public String getText() {
		return name;
	}
	public void setText(String text) {
		this.text = text;
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
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
