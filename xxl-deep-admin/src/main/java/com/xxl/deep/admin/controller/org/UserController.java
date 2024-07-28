package com.xxl.deep.admin.controller.org;

import com.xxl.deep.admin.annotation.Permission;
import com.xxl.deep.admin.constant.enums.UserStatuEnum;
import com.xxl.deep.admin.model.entity.XxlDeepUser;
import com.xxl.deep.admin.service.RoleService;
import com.xxl.deep.admin.service.UserService;
import com.xxl.deep.admin.service.impl.LoginService;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:39:50
 */
@Controller
@RequestMapping("/org/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @RequestMapping
    @Permission(adminuser = true)
    public String index(Model model) {

        /*PageModel<XxlDeepRole> pageModel = roleService.pageList(0, 100);*/
        model.addAttribute("userStatuEnum", UserStatuEnum.values());

        return "org/user";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    @Permission(adminuser = true)
    public Response<PageModel<XxlDeepUser>> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                                     @RequestParam(required = false, defaultValue = "10") int length,
                                                     String username,
                                                     @RequestParam(required = false, defaultValue = "-1") int status) {

        PageModel<XxlDeepUser> pageModel = userService.pageList(start, length, username, status);
        return new ResponseBuilder<PageModel<XxlDeepUser>>().success(pageModel).build();
    }

    @RequestMapping("/add")
    @ResponseBody
    @Permission(adminuser = true)
    public Response<String> add(XxlDeepUser xxlJobUser) {
        return userService.insert(xxlJobUser);
    }

    @RequestMapping("/update")
    @ResponseBody
    @Permission(adminuser = true)
    public Response<String> update(HttpServletRequest request, XxlDeepUser xxlJobUser) {
        XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        return userService.update(xxlJobUser, loginUser);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Permission(adminuser = true)
    public Response<String> delete(HttpServletRequest request,
                                   @RequestParam("ids[]") List<Integer> ids) {
        XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        return userService.deleteByIds(ids, loginUser);
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public Response<String> updatePwd(HttpServletRequest request, String password){
        XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        return userService.updatePwd(loginUser, password);
    }

}
