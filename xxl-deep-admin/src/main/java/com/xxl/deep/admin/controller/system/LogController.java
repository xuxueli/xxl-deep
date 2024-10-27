package com.xxl.deep.admin.controller.system;

import com.xxl.deep.admin.constant.enums.LogModuleEnum;
import com.xxl.deep.admin.constant.enums.LogTypeEnum;
import com.xxl.deep.admin.model.dto.XxlDeepLogDTO;
import com.xxl.deep.admin.model.entity.XxlDeepLog;
import com.xxl.deep.admin.service.XxlDeepLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import javax.annotation.Resource;

import com.xxl.tool.response.Response;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.ResponseBuilder;
import com.xxl.deep.admin.annotation.Permission;

/**
 * XxlDeepLog Controller
 *
 * Created by xuxueli on '2024-10-27 12:19:06'.
 */
@Controller
@RequestMapping("/system/log")
public class LogController {

    @Resource
    private XxlDeepLogService xxlDeepLogService;

    /**
     * 页面
     */
    @RequestMapping
    @Permission
    public String index(Model model) {

        model.addAttribute("LogTypeEnum", LogTypeEnum.values());
        model.addAttribute("LogModuleEnum", LogModuleEnum.values());

        return "system/log";
    }

    /**
     * 分页查询
     */
    @RequestMapping("/pageList")
    @ResponseBody
    @Permission
    public Response<PageModel<XxlDeepLogDTO>> pageList(@RequestParam(required = false, defaultValue = "-1") int type,
                                                    String module,
                                                    String title,
                                                    @RequestParam(required = false, defaultValue = "0") int offset,
                                                    @RequestParam(required = false, defaultValue = "10") int pagesize) {
        PageModel<XxlDeepLogDTO> pageModel = xxlDeepLogService.pageList(type, module, title, offset, pagesize);
        return new ResponseBuilder<PageModel<XxlDeepLogDTO>>().success(pageModel).build();
    }

    /**
     * Load查询
     */
    @RequestMapping("/load")
    @ResponseBody
    @Permission
    public Response<XxlDeepLog> load(int id){
        return xxlDeepLogService.load(id);
    }

    /**
     * 新增
     */
    @RequestMapping("/insert")
    @ResponseBody
    @Permission
    public Response<String> insert(XxlDeepLog xxlDeepLog){
        return xxlDeepLogService.insert(xxlDeepLog);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    @Permission
    public Response<String> delete(@RequestParam("ids[]") List<Integer> ids){
        return xxlDeepLogService.delete(ids);
    }

    /**
     * 更新
     */
    @RequestMapping("/update")
    @ResponseBody
    @Permission
    public Response<String> update(XxlDeepLog xxlDeepLog){
        return xxlDeepLogService.update(xxlDeepLog);
    }

}