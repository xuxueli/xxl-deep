package com.xxl.permission.service;

import com.xxl.permission.core.result.ReturnT;

import javax.servlet.http.HttpSession;

public interface ILoginService {

	public ReturnT<String> login (HttpSession session, String username, String password, int roleId);

	public ReturnT<String> logout(HttpSession session);

	public ReturnT<String> modifyPwd(HttpSession session, String password, String newPwd, String reNewPwd);

}
