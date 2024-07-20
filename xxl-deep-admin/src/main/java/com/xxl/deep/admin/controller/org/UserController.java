package com.xxl.deep.admin.controller.org;

import com.xxl.deep.admin.core.annotation.Permission;
import com.xxl.deep.admin.model.XxlDeepUser;
import com.xxl.deep.admin.service.UserService;
import com.xxl.deep.admin.service.impl.LoginService;
import com.xxl.deep.admin.util.I18nUtil;
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

/**
 * @author xuxueli 2019-05-04 16:39:50
 */
@Controller
@RequestMapping("/org/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping
    @Permission(adminuser = true)
    public String index(Model model) {
        //model.addAttribute("groupList", null);
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

    @RequestMapping("/remove")
    @ResponseBody
    @Permission(adminuser = true)
    public Response<String> remove(HttpServletRequest request, int id) {
        // avoid opt login seft
        XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        if (loginUser.getId() == id) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("user_update_loginuser_limit") ).build();
        }

        userService.delete(id);
        return new ResponseBuilder<String>().success().build();
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public Response<String> updatePwd(HttpServletRequest request, String password){
        XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        return userService.updatePwd(loginUser, password);
    }

}
