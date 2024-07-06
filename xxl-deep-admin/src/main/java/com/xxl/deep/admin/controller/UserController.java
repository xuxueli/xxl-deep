package com.xxl.deep.admin.controller;

import com.xxl.deep.admin.core.annotation.Permission;
import com.xxl.deep.admin.model.XxlDeepUser;
import com.xxl.deep.admin.util.I18nUtil;
import com.xxl.deep.admin.dao.XxlDeepUserMapper;
import com.xxl.deep.admin.service.impl.LoginService;
import com.xxl.tool.core.StringTool;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuxueli 2019-05-04 16:39:50
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private XxlDeepUserMapper xxlJobUserDao;

    @RequestMapping
    @Permission(adminuser = true)
    public String index(Model model) {

        // 执行器列表
        //List<XxlJobGroup> groupList = new ArrayList<>();
        model.addAttribute("groupList", null);

        return "user/user.index";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    @Permission(adminuser = true)
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length,
                                        String username, int role) {

        // page list
        List<XxlDeepUser> list = xxlJobUserDao.pageList(start, length, username, role);
        int list_count = xxlJobUserDao.pageListCount(start, length, username, role);

        // filter
        if (list!=null && list.size()>0) {
            for (XxlDeepUser item: list) {
                item.setPassword(null);
            }
        }

        // package result
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("recordsTotal", list_count);		// 总记录数
        maps.put("recordsFiltered", list_count);	// 过滤后的总记录数
        maps.put("data", list);  					// 分页列表
        return maps;
    }

    @RequestMapping("/add")
    @ResponseBody
    @Permission(adminuser = true)
    public Response<String> add(XxlDeepUser xxlJobUser) {

        // valid username
        if (StringTool.isBlank(xxlJobUser.getUsername())) {
            return new ResponseBuilder<String>().fail(I18nUtil.getString("system_please_input") + I18nUtil.getString("user_username")).build();
        }
        xxlJobUser.setUsername(xxlJobUser.getUsername().trim());
        if (!(xxlJobUser.getUsername().length()>=4 && xxlJobUser.getUsername().length()<=20)) {
            return new ResponseBuilder<String>().fail(I18nUtil.getString("system_lengh_limit")+"[4-20]").build();
        }
        // valid password
        if (StringTool.isBlank(xxlJobUser.getPassword())) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("system_please_input")+I18nUtil.getString("user_password") ).build();
        }
        xxlJobUser.setPassword(xxlJobUser.getPassword().trim());
        if (!(xxlJobUser.getPassword().length()>=4 && xxlJobUser.getPassword().length()<=20)) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("system_lengh_limit")+"[4-20]" ).build();
        }
        // md5 password
        xxlJobUser.setPassword(DigestUtils.md5DigestAsHex(xxlJobUser.getPassword().getBytes()));

        // check repeat
        XxlDeepUser existUser = xxlJobUserDao.loadByUserName(xxlJobUser.getUsername());
        if (existUser != null) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("user_username_repeat") ).build();
        }

        // write
        xxlJobUserDao.save(xxlJobUser);
        return new ResponseBuilder<String>().success().build();
    }

    @RequestMapping("/update")
    @ResponseBody
    @Permission(adminuser = true)
    public Response<Object> update(HttpServletRequest request, XxlDeepUser xxlJobUser) {

        // avoid opt login seft
        XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        if (loginUser.getUsername().equals(xxlJobUser.getUsername())) {
            return new ResponseBuilder<Object>().fail( I18nUtil.getString("user_update_loginuser_limit") ).build();
        }

        // valid password
        if (StringTool.isNotBlank(xxlJobUser.getPassword())) {
            xxlJobUser.setPassword(xxlJobUser.getPassword().trim());
            if (!(xxlJobUser.getPassword().length()>=4 && xxlJobUser.getPassword().length()<=20)) {
                return new ResponseBuilder<Object>().fail(  I18nUtil.getString("system_lengh_limit")+"[4-20]" ).build();
            }
            // md5 password
            xxlJobUser.setPassword(DigestUtils.md5DigestAsHex(xxlJobUser.getPassword().getBytes()));
        } else {
            xxlJobUser.setPassword(null);
        }

        // write
        xxlJobUserDao.update(xxlJobUser);
        return new ResponseBuilder<Object>().success().build();
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

        xxlJobUserDao.delete(id);
        return new ResponseBuilder<String>().success().build();
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public Response<String> updatePwd(HttpServletRequest request, String password){

        // valid password
        if (password==null || password.trim().length()==0){
            new ResponseBuilder<String>().fail( "密码不可为空" ).build();
        }
        password = password.trim();
        if (!(password.length()>=4 && password.length()<=20)) {
            new ResponseBuilder<String>().fail( I18nUtil.getString("system_lengh_limit")+"[4-20]" ).build();
        }

        // md5 password
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        // update pwd
        XxlDeepUser loginUser = (XxlDeepUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);

        // do write
        XxlDeepUser existUser = xxlJobUserDao.loadByUserName(loginUser.getUsername());
        existUser.setPassword(md5Password);
        xxlJobUserDao.update(existUser);

        return new ResponseBuilder<String>().success().build();
    }

}
