package com.xxl.deep.admin.controller.org;

import com.xxl.deep.admin.annotation.Permission;
import com.xxl.deep.admin.constant.enums.UserStatuEnum;
import com.xxl.deep.admin.model.entity.XxlDeepRole;
import com.xxl.deep.admin.service.RoleService;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
* resource
*
* Created by xuxueli on '2024-07-21 13:58:17'.
*/
@Controller
@RequestMapping("/org/resource")
public class ResourceController {

    @Resource
    private RoleService roleService;

    @RequestMapping
    @Permission(adminuser = true)
    public String index(Model model) {

        /*PageModel<XxlDeepRole> pageModel = roleService.pageList(0, 100);*/
        model.addAttribute("userStatuEnum", UserStatuEnum.values());

        return "org/resource";
    }

    /**
     * 分页查询
     */
    @RequestMapping("/pageList")
    @ResponseBody
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
    public Response<String> insert(XxlDeepRole xxlDeepRole){
        return roleService.insert(xxlDeepRole);
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public Response<String> delete(@RequestParam("ids[]") List<Integer> ids) {
        return roleService.deleteByIds(ids);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public Response<String> update(XxlDeepRole xxlDeepRole){
        return roleService.update(xxlDeepRole);
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public Response<XxlDeepRole> load(int id){
        return roleService.load(id);
    }

}