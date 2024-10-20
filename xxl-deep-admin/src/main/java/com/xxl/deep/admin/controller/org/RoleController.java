package com.xxl.deep.admin.controller.org;

import com.xxl.deep.admin.annotation.Permission;
import com.xxl.deep.admin.constant.enums.UserStatuEnum;
import com.xxl.deep.admin.model.entity.XxlDeepRole;
import com.xxl.deep.admin.service.RoleService;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import com.xxl.tool.response.Response;

/**
* role
*
* Created by xuxueli on '2024-07-21 13:58:17'.
*/
@Controller
@RequestMapping("/org/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping
    @Permission("org:role")
    public String index(Model model) {

        /*PageModel<XxlDeepRole> pageModel = roleService.pageList(0, 100);*/
        model.addAttribute("userStatuEnum", UserStatuEnum.values());

        return "org/role";
    }

    /**
     * 分页查询
     */
    @RequestMapping("/pageList")
    @ResponseBody
    @Permission("org:role")
    public Response<PageModel<XxlDeepRole>> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                                     @RequestParam(required = false, defaultValue = "10") int pagesize,
                                                     String name) {
        PageModel<XxlDeepRole> pageModel = roleService.pageList(offset, pagesize, name);
        return new ResponseBuilder<PageModel<XxlDeepRole>>().success(pageModel).build();
    }

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    @Permission("org:role")
    public Response<String> insert(XxlDeepRole xxlDeepRole){
        return roleService.insert(xxlDeepRole);
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    @Permission("org:role")
    public Response<String> delete(@RequestParam("ids[]") List<Integer> ids) {
        return roleService.deleteByIds(ids);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    @Permission("org:role")
    public Response<String> update(XxlDeepRole xxlDeepRole){
        return roleService.update(xxlDeepRole);
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    @Permission("org:role")
    public Response<XxlDeepRole> load(int id){
        return roleService.load(id);
    }

    /**
     * 角色资源查询
     */
    @RequestMapping("/loadRoleRes")
    @ResponseBody
    @Permission("org:role")
    public Response<List<Integer>> loadRoleRes(int roleId){
        return roleService.loadRoleRes(roleId);
    }

    /**
     * 角色资源授权
     */
    @RequestMapping("/updateRoleRes")
    @ResponseBody
    @Permission("org:role")
    public Response<String> updateRoleRes(@RequestParam int roleId,
                                          @RequestParam(value = "resourceIds[]", required = false) List<Integer> resourceIds){
        return roleService.updateRoleRes(roleId, resourceIds);
    }


}