//package com.xxl.deep.admin.controller;
//
//import com.xxl.deep.admin.core.exception.XxlJobException;
//import com.xxl.deep.admin.core.model.XxlJobUser;
//import com.xxl.deep.admin.core.util.I18nUtil;
//import com.xxl.deep.admin.service.impl.LoginService;
//import com.xxl.tool.response.Response;
//import com.xxl.tool.response.ResponseBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
///**
// * index controller
// * @author xuxueli 2015-12-19 16:13:16
// */
//@Controller
//@RequestMapping("/jobinfo")
//public class JobInfoController {
//	private static Logger logger = LoggerFactory.getLogger(JobInfoController.class);
//
//	/*@Resource
//	private XxlJobService xxlJobService;*/
//
//	@RequestMapping
//	public String index(HttpServletRequest request, Model model, @RequestParam(required = false, defaultValue = "-1") int jobGroup) {
//
//		// 枚举-字典
//		model.addAttribute("ExecutorRouteStrategyEnum", null);	    // 路由策略-列表
//		model.addAttribute("GlueTypeEnum", null);								// Glue类型-字典
//
//		// 执行器列表
//		List<XxlJobGroup> jobGroupList_all =  new ArrayList<>();
//
//		// filter group
//		List<XxlJobGroup> jobGroupList = filterJobGroupByRole(request, jobGroupList_all);
//		if (jobGroupList==null || jobGroupList.size()==0) {
//			throw new XxlJobException(I18nUtil.getString("jobgroup_empty"));
//		}
//
//		model.addAttribute("JobGroupList", jobGroupList);
//		model.addAttribute("jobGroup", jobGroup);
//
//		return "jobinfo/jobinfo.index";
//	}
//
//	public static List<XxlJobGroup> filterJobGroupByRole(HttpServletRequest request, List<XxlJobGroup> jobGroupList_all){
//		List<XxlJobGroup> jobGroupList = new ArrayList<>();
//		if (jobGroupList_all!=null && jobGroupList_all.size()>0) {
//			XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
//			if (loginUser.getRole() == 1) {
//				jobGroupList = jobGroupList_all;
//			} else {
//				List<String> groupIdStrs = new ArrayList<>();
//				if (loginUser.getPermission()!=null && loginUser.getPermission().trim().length()>0) {
//					groupIdStrs = Arrays.asList(loginUser.getPermission().trim().split(","));
//				}
//				for (XxlJobGroup groupItem:jobGroupList_all) {
//					if (groupIdStrs.contains(String.valueOf(groupItem.getId()))) {
//						jobGroupList.add(groupItem);
//					}
//				}
//			}
//		}
//		return jobGroupList;
//	}
//	public static void validPermission(HttpServletRequest request, int jobGroup) {
//		XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
//		if (!loginUser.validPermission(jobGroup)) {
//			throw new RuntimeException(I18nUtil.getString("system_permission_limit") + "[username="+ loginUser.getUsername() +"]");
//		}
//	}
//
//	@RequestMapping("/pageList")
//	@ResponseBody
//	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
//			@RequestParam(required = false, defaultValue = "10") int length,
//			int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author) {
//
//		//return xxlJobService.pageList(start, length, jobGroup, triggerStatus, jobDesc, executorHandler, author);
//
//		// page list
//		List<XxlJobInfo> list = new ArrayList<>();
//		int list_count = 0;
//
//		// package result
//		Map<String, Object> maps = new HashMap<String, Object>();
//		maps.put("recordsTotal", list_count);		// 总记录数
//		maps.put("recordsFiltered", list_count);	// 过滤后的总记录数
//		maps.put("data", list);  					// 分页列表
//
//		return maps;
//	}
//
//	@RequestMapping("/add")
//	@ResponseBody
//	public Response<String> add(XxlJobInfo jobInfo) {
//		return new ResponseBuilder<String>().success().build();
//	}
//
//	@RequestMapping("/update")
//	@ResponseBody
//	public Response<String> update(XxlJobInfo jobInfo) {
//		return new ResponseBuilder<String>().success().build();
//	}
//
//	@RequestMapping("/remove")
//	@ResponseBody
//	public Response<String> remove(int id) {
//		return new ResponseBuilder<String>().success().build();
//	}
//
//	@RequestMapping("/stop")
//	@ResponseBody
//	public Response<String> pause(int id) {
//		return new ResponseBuilder<String>().success().build();
//	}
//
//	@RequestMapping("/start")
//	@ResponseBody
//	public Response<String> start(int id) {
//		return new ResponseBuilder<String>().success().build();
//	}
//
//
//}
