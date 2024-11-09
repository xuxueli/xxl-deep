package com.xxl.boot.admin.controller;

import com.xxl.boot.admin.annotation.Permission;
import com.xxl.boot.admin.constant.enums.MessageStatusEnum;
import com.xxl.boot.admin.model.dto.LoginUserDTO;
import com.xxl.boot.admin.model.dto.XxlBootMessageDTO;
import com.xxl.boot.admin.service.MessageService;
import com.xxl.boot.admin.service.impl.LoginService;
import com.xxl.tool.core.CollectionTool;
import com.xxl.tool.core.StringTool;
import com.xxl.tool.response.PageModel;
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
import java.util.Date;
import java.util.List;

/**
 * index controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
public class IndexController {


	@Resource
	private LoginService loginService;
	@Resource
	private MessageService messageService;


	@RequestMapping("/")
	@Permission
	public String defaultpage(Model model) {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	@Permission
	public String index(HttpServletRequest request, Model model) {

		// message
		PageModel<XxlBootMessageDTO>  pageModel = messageService.pageList(MessageStatusEnum.NORMAL.getValue(), null, 0, 10);
		if (pageModel!=null && CollectionTool.isNotEmpty(pageModel.getPageData())) {
			List<XxlBootMessageDTO> messageList = pageModel.getPageData();
			model.addAttribute("messageList", messageList);
		}
		/*model.addAttribute("BasicJsonwriter", new BasicJsonwriter());*/

		return "index";
	}

	@RequestMapping("/help")
	@Permission
	public String help() {

		/*if (!PermissionInterceptor.ifLogin(request)) {
			return "redirect:/toLogin";
		}*/

		return "help";
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
	@Permission(login = false)
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView) {
		LoginUserDTO loginUserDTO = loginService.getLoginUser(request);
		if (loginUserDTO != null) {
			modelAndView.setView(new RedirectView("/",true,false));
			return modelAndView;
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	@Permission(login=false)
	public Response<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember){
		boolean ifRem = StringTool.isNotBlank(ifRemember) && "on".equals(ifRemember);
		return loginService.login(response, userName, password, ifRem);
	}
	
	@RequestMapping(value="logout", method=RequestMethod.POST)
	@ResponseBody
	@Permission(login=false)
	public Response<String> logout(HttpServletRequest request, HttpServletResponse response){
		return loginService.logout(request, response);
	}

	@RequestMapping(value = "/errorpage")
	@Permission(login = false)
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
