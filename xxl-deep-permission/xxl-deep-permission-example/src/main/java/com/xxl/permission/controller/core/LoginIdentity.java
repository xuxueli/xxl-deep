package com.xxl.permission.controller.core;

import java.io.Serializable;

/**
 * 登陆身份
 * @author 
 */
@SuppressWarnings("serial")
public class LoginIdentity implements Serializable {
	
	// XxlPermissionUser.后台用户信息
	private int userId;
	private String userName;
	private String password;
	private String userToken;
	private String realName;
	private int vipType;
	
	// 权限编号ID
	private String menePermissionNums;
	// 权限JSON tree
	private String menuModuleJson;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getVipType() {
		return vipType;
	}
	public void setVipType(int vipType) {
		this.vipType = vipType;
	}
	public String getMenePermissionNums() {
		return menePermissionNums;
	}
	public void setMenePermissionNums(String menePermissionNums) {
		this.menePermissionNums = menePermissionNums;
	}
	public String getMenuModuleJson() {
		return menuModuleJson;
	}
	public void setMenuModuleJson(String menuModuleJson) {
		this.menuModuleJson = menuModuleJson;
	}
	
}
