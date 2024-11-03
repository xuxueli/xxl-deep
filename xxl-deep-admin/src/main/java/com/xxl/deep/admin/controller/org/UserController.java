package com.xxl.deep.admin.controller.org;

import com.xxl.deep.admin.annotation.Log;
import com.xxl.deep.admin.annotation.Permission;
import com.xxl.deep.admin.constant.enums.LogModuleEnum;
import com.xxl.deep.admin.constant.enums.LogTypeEnum;
import com.xxl.deep.admin.constant.enums.UserStatuEnum;
import com.xxl.deep.admin.model.dto.LoginUserDTO;
import com.xxl.deep.admin.model.dto.XxlDeepUserDTO;
import com.xxl.deep.admin.model.entity.XxlDeepRole;
import com.xxl.deep.admin.service.RoleService;
import com.xxl.deep.admin.service.UserService;
import com.xxl.deep.admin.service.impl.LoginService;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Resource
    private LoginService loginService;

    @RequestMapping
    @Permission("org:user")
    public String index(Model model) {

        PageModel<XxlDeepRole> pageModel = roleService.pageList(0, 999, null);

        model.addAttribute("roleList", pageModel.getPageData());
        model.addAttribute("userStatuEnum", UserStatuEnum.values());

        return "org/user";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    @Permission("org:user")
    public Response<PageModel<XxlDeepUserDTO>> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                                     @RequestParam(required = false, defaultValue = "10") int length,
                                                     String username,
                                                     @RequestParam(required = false, defaultValue = "-1") int status) {

        PageModel<XxlDeepUserDTO> pageModel = userService.pageList(start, length, username, status);
        return new ResponseBuilder<PageModel<XxlDeepUserDTO>>().success(pageModel).build();
    }

    @RequestMapping("/add")
    @ResponseBody
    @Permission("org:user")
    @Log(type= LogTypeEnum.OPT_LOG, module = LogModuleEnum.USER_MANAGE, title = "新增用户")
    public Response<String> add(XxlDeepUserDTO xxlJobUser) {
        return userService.insert(xxlJobUser);
    }

    @RequestMapping("/update")
    @ResponseBody
    @Permission("org:user")
    @Log(type= LogTypeEnum.OPT_LOG, module = LogModuleEnum.USER_MANAGE, title = "更新用户")
    public Response<String> update(HttpServletRequest request, XxlDeepUserDTO xxlJobUser) {
        LoginUserDTO loginUser = loginService.getLoginUser(request);
        return userService.update(xxlJobUser, loginUser);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Permission("org:user")
    @Log(type= LogTypeEnum.OPT_LOG, module = LogModuleEnum.USER_MANAGE, title = "删除用户")
    public Response<String> delete(HttpServletRequest request,
                                   @RequestParam("ids[]") List<Integer> ids) {
        LoginUserDTO loginUser = loginService.getLoginUser(request);
        return userService.deleteByIds(ids, loginUser);
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    @Permission
    public Response<String> updatePwd(HttpServletRequest request, String password){
        LoginUserDTO loginUser = loginService.getLoginUser(request);
        return userService.updatePwd(loginUser, password);
    }

}
