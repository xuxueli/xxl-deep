package com.xxl.deep.admin.controller.system;

import com.xxl.deep.admin.annotation.Permission;
import com.xxl.deep.admin.constant.enums.LogModuleEnum;
import com.xxl.deep.admin.constant.enums.LogTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * system message (sys 2 user)
 *
 * Created by xuxueli on 2024-10-20
 */
@Controller
@RequestMapping("/system/message")
public class MessageController {

    /**
     * 页面
     */
    @RequestMapping
    @Permission
    public String index(Model model) {

        model.addAttribute("LogTypeEnum", LogTypeEnum.values());
        model.addAttribute("LogModuleEnum", LogModuleEnum.values());

        return "system/message";
    }

}
