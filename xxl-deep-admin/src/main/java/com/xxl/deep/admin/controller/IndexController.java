package com.xxl.deep.admin.controller;

import com.xxl.deep.admin.core.annotation.Permission;
import com.xxl.deep.admin.service.impl.LoginService;
import com.xxl.tool.core.StringTool;
import com.xxl.tool.response.Response;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * index controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
public class IndexController {


	@Resource
	private LoginService loginService;


	@RequestMapping("/")
	public String defaultpage(Model model) {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		//Map<String, Object> dashboardMap = xxlJobService.dashboardInfo();

		int jobInfoCount = 0;
		int jobLogCount = 0;
		int jobLogSuccessCount = 0;
		jobLogCount = 0;
		jobLogSuccessCount = 0;
		int executorCount = 10;

		Map<String, Object> dashboardMap = new HashMap<String, Object>();
		dashboardMap.put("jobInfoCount", jobInfoCount);
		dashboardMap.put("jobLogCount", jobLogCount);
		dashboardMap.put("jobLogSuccessCount", jobLogSuccessCount);
		dashboardMap.put("executorCount", executorCount);

		model.addAllAttributes(dashboardMap);

		return "index";
	}

    /*@RequestMapping("/chartInfo")
	@ResponseBody
	public Response<Map<String, Object>> chartInfo(Date startDate, Date endDate) {
		//Response<Map<String, Object>> chartInfo = xxlJobService.chartInfo(startDate, endDate);


		List<String> triggerDayList = new ArrayList<String>();
		List<Integer> triggerDayCountRunningList = new ArrayList<Integer>();
		List<Integer> triggerDayCountSucList = new ArrayList<Integer>();
		List<Integer> triggerDayCountFailList = new ArrayList<Integer>();
		int triggerCountRunningTotal = 0;
		int triggerCountSucTotal = 0;
		int triggerCountFailTotal = 0;


		Map<String, Object> result = new HashMap<String, Object>();
		result.put("triggerDayList", triggerDayList);
		result.put("triggerDayCountRunningList", triggerDayCountRunningList);
		result.put("triggerDayCountSucList", triggerDayCountSucList);
		result.put("triggerDayCountFailList", triggerDayCountFailList);

		result.put("triggerCountRunningTotal", triggerCountRunningTotal);
		result.put("triggerCountSucTotal", triggerCountSucTotal);
		result.put("triggerCountFailTotal", triggerCountFailTotal);


		return new ResponseBuilder<Map<String, Object>>().success(result).build();
    }*/
	
	@RequestMapping("/toLogin")
	@Permission(limit=false)
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView) {
		if (loginService.ifLogin(request, response) != null) {
			modelAndView.setView(new RedirectView("/",true,false));
			return modelAndView;
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	@Permission(limit=false)
	public Response<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember){
		boolean ifRem = StringTool.isNotBlank(ifRemember) && "on".equals(ifRemember);
		return loginService.login(response, userName, password, ifRem);
	}
	
	@RequestMapping(value="logout", method=RequestMethod.POST)
	@ResponseBody
	@Permission(limit=false)
	public Response<String> logout(HttpServletRequest request, HttpServletResponse response){
		return loginService.logout(request, response);
	}
	
	@RequestMapping("/help")
	public String help() {

		/*if (!PermissionInterceptor.ifLogin(request)) {
			return "redirect:/toLogin";
		}*/

		return "help";
	}

	@RequestMapping(value = "/errorpage")
	@Permission(limit=false)
	public ModelAndView errorPage(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {

		String exceptionMsg = "HTTP Status Code: "+response.getStatus();

		mv.addObject("exceptionMsg", exceptionMsg);
		mv.setViewName("common/common.errorpage");
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
