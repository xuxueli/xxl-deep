package com.xxl.deep.admin.controller.org;
import com.xxl.deep.admin.annotation.Permission;
import com.xxl.deep.admin.constant.enums.ResourceStatuEnum;
import com.xxl.deep.admin.constant.enums.ResourceTypeEnum;
import com.xxl.deep.admin.model.dto.XxlDeepResourceDTO;
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
    @Permission("org:resource")
    public String index(Model model) {

        model.addAttribute("resourceStatuEnum", ResourceStatuEnum.values());
        model.addAttribute("resourceTypeEnum", ResourceTypeEnum.values());

        return "org/resource";
    }

    /**
     * tree数据查询
     *
     *  <pre>
     *  {
     *      "data":
     *          [
     *              {
     *                  "name": "lhmyy521125",
     *                  ...
     *                  "children": [
     *                      {
     *                          "name": "hello",
     *                          ...
     *                      }
     *                  ]
     *              }
     *          ]
     *  }
     *  </pre>
     */
    @RequestMapping("/treeList")
    @ResponseBody
    @Permission("org:resource")
    public Response<List<XxlDeepResourceDTO>> treeList(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false, defaultValue = "-1") int status) {

        List<XxlDeepResourceDTO> treeListData = resourceService.treeList(name, status);
        return new ResponseBuilder<List<XxlDeepResourceDTO>>().success(treeListData).build();
    }

    /**
     * 简单tree数据查询
     *
     * <pre>
     *     [
     * 			  {id: 1, pId: 0, name: "资源A", open: true},
     *            {id: 5, pId: 1, name: "资源A1"},
     *            {id: 2, pId: 0, name: "资源B", open: false},
     *            {id: 11, pId: 2, name: "资源B2"}
     * 		]
     * </pre>
     */
    @RequestMapping("/simpleTreeList")
    @ResponseBody
    @Permission("org:resource")
    public Response<List<XxlDeepResourceDTO>> simpleTreeList(@RequestParam(required = false) String name,
                                                             @RequestParam(required = false, defaultValue = "-1") int status) {
        List<XxlDeepResourceDTO> treeListData = resourceService.simpleTreeList(name, status);
        return new ResponseBuilder<List<XxlDeepResourceDTO>>().success(treeListData).build();
    }

    /**
     * Load查询
     */
    @RequestMapping("/load")
    @ResponseBody
    @Permission("org:resource")
    public Response<XxlDeepResource> load(int id){
        return resourceService.load(id);
    }

    /**
     * 新增
     */
    @RequestMapping("/insert")
    @ResponseBody
    @Permission("org:resource")
    public Response<String> insert(XxlDeepResource xxlDeepResource){
        return resourceService.insert(xxlDeepResource);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    @Permission("org:resource")
    public Response<String> delete(@RequestParam("ids[]") List<Integer> ids){
        return resourceService.delete(ids);
    }

    /**
     * 更新
     */
    @RequestMapping("/update")
    @ResponseBody
    @Permission("org:resource")
    public Response<String> update(XxlDeepResource xxlDeepResource){
        return resourceService.update(xxlDeepResource);
    }

}
