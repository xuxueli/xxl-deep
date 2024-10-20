package com.xxl.deep.admin.model.entity;

import com.xxl.deep.admin.model.dto.XxlDeepResourceDTO;
import com.xxl.deep.admin.model.dto.XxlDeepRoleDTO;

import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:43:12
 */
public class XxlDeepUserDTO {

	private int id;
	private String username;		// 账号
	private String password;		// 密码
	private String userToken;		// 登录token
	private int status;				// 状态：0-正常、1-停用
	private String realName;		// 真实姓名

	// permission
	private List<XxlDeepRoleDTO> roleList;
	private List<XxlDeepResourceDTO> resourceList;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public List<XxlDeepRoleDTO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<XxlDeepRoleDTO> roleList) {
		this.roleList = roleList;
	}

	public List<XxlDeepResourceDTO> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<XxlDeepResourceDTO> resourceList) {
		this.resourceList = resourceList;
	}

}
