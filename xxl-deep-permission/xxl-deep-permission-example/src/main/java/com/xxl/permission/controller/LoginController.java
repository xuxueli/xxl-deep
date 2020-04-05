package com.xxl.permission.controller;

import com.xxl.permission.controller.annotation.PermessionType;
import com.xxl.permission.controller.core.LoginIdentity;
import com.xxl.permission.core.constant.CommonDic;
import com.xxl.permission.core.constant.CommonDic.HttpSessionKeyDic;
import com.xxl.permission.core.model.XxlPermissionRole;
import com.xxl.permission.core.result.ReturnT;
import com.xxl.permission.core.util.HttpSessionUtil;
import com.xxl.permission.dao.IXxlPermissionRoleDao;
import com.xxl.permission.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xuxueli
 */
@Controller
public class LoginController {
	
	@Autowired
	private ILoginService loginService;
	@Autowired
	private IXxlPermissionRoleDao xxlPermissionRoleDao;
	

	@RequestMapping("")
	@PermessionType(loginState = false)
	public String index(Model model, HttpSession session, RedirectAttributes redirect) {
		
		LoginIdentity identity = (LoginIdentity) HttpSessionUtil.get(session, HttpSessionKeyDic.LOGIN_IDENTITY);
		if (identity != null) {
			redirect.addAttribute("from", "sessionLogin");
			return "redirect:/home";
		}

		List<XxlPermissionRole> roleList = xxlPermissionRoleDao.getAllRoles();
		model.addAttribute("roleList", roleList);
		model.addAttribute("SUPER_ROLE_ID", CommonDic.SUPER_ROLE_ID);

		return "login";
	}
	
	@RequestMapping("/home")
	@PermessionType()
	public String home(HttpServletRequest request, HttpServletResponse response, ModelMap resultMap) {
		return "home";
	}

	@RequestMapping("/login")
	@ResponseBody
	@PermessionType(loginState = false, randCode=true)
	public ReturnT<String> login(HttpSession session, String username, String password,int role_id) {
		ReturnT<String> result = loginService.login(session, username, password, role_id);
		return result;
	}

	@RequestMapping("/logout")
	@ResponseBody
	@PermessionType()
	public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response, 
			ModelMap resultMap, HttpSession session) {
		
		ReturnT<String> result = loginService.logout(session);
		return result;
	}

	@RequestMapping("/modifyPwd")
	@ResponseBody
	@PermessionType()
	public ReturnT<String> modifyPwd(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, String password, String newPwd, String reNewPwd) {
		
		ReturnT<String> result =loginService.modifyPwd(session, password, newPwd, reNewPwd);
		return result;
	}
	
}
