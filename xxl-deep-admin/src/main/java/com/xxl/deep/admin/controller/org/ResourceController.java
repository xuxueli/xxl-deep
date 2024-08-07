package com.xxl.deep.admin.controller.org;
import com.xxl.deep.admin.annotation.Permission;
import com.xxl.deep.admin.constant.enums.ResourceStatuEnum;
import com.xxl.deep.admin.constant.enums.ResourceTypeEnum;
import com.xxl.deep.admin.model.entity.XxlDeepResource;
import com.xxl.deep.admin.service.ResourceService;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import com.xxl.tool.response.Response;

import java.util.List;

/**
 * XxlDeepResource Controller
 *
 * Created by xuxueli on '2024-07-28 12:52:39'.
 */
@Controller
@RequestMapping("/org/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    /**
     * 页面
     */
    @RequestMapping
    @Permission(adminuser = true)
    public String index(Model model) {

        model.addAttribute("resourceStatuEnum", ResourceStatuEnum.values());
        model.addAttribute("resourceTypeEnum", ResourceTypeEnum.values());

        return "org/resource";
    }

    /**
     * 分页查询
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public Response<PageModel<XxlDeepResource>> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                                         @RequestParam(required = false, defaultValue = "10") int pagesize) {
        PageModel<XxlDeepResource> pageModel = resourceService.pageList(offset, pagesize);
        return new ResponseBuilder<PageModel<XxlDeepResource>>().success(pageModel).build();
    }

    /**
     * Load查询
     */
    @RequestMapping("/load")
    @ResponseBody
    public Response<XxlDeepResource> load(int id){
        return resourceService.load(id);
    }

    /**
     * 新增
     */
    @RequestMapping("/insert")
    @ResponseBody
    public Response<String> insert(XxlDeepResource xxlDeepResource){
        return resourceService.insert(xxlDeepResource);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Response<String> delete(@RequestParam("ids[]") List<Integer> ids){
        return resourceService.delete(ids);
    }

    /**
     * 更新
     */
    @RequestMapping("/update")
    @ResponseBody
    public Response<String> update(XxlDeepResource xxlDeepResource){
        return resourceService.update(xxlDeepResource);
    }

}
