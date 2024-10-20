package com.xxl.deep.admin.controller.tool;

import com.xxl.deep.admin.annotation.Permission;
import com.xxl.deep.admin.util.codegen.ClassInfo;
import com.xxl.deep.admin.util.codegen.TableParseUtil;
import com.xxl.tool.core.StringTool;
import com.xxl.tool.freemarker.FreemarkerTool;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tool/codegen")
public class CodeGenController {
    private static final Logger logger = LoggerFactory.getLogger(CodeGenController.class);

    @Autowired
    private Configuration freemarkerConfig;

    @RequestMapping
    @Permission
    public String index(Model model) {
        return "tool/codegen";
    }

    @RequestMapping("/genCode")
    @ResponseBody
    @Permission
    public Response<Map<String, String>> codeGenerate(String tableSql) {

        try {
            if (StringTool.isBlank(tableSql)) {
                return new ResponseBuilder<Map<String, String>>().fail("表结构信息不可为空").build();
            }

            // parse table
            ClassInfo classInfo = TableParseUtil.processTableIntoClassInfo(tableSql);

            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("classInfo", classInfo);

            // result
            Map<String, String> result = new HashMap<String, String>();

            result.put("controller_code", FreemarkerTool.processString(freemarkerConfig,"tool/codegen-module/controller.ftl", params));
            result.put("service_code", FreemarkerTool.processString(freemarkerConfig,"tool/codegen-module/service.ftl", params));
            result.put("service_impl_code", FreemarkerTool.processString(freemarkerConfig,"tool/codegen-module/service_impl.ftl", params));
            result.put("mapper_code", FreemarkerTool.processString(freemarkerConfig,"tool/codegen-module/mapper.ftl", params));
            result.put("mapper_xml_code", FreemarkerTool.processString(freemarkerConfig,"tool/codegen-module/mapper_xml.ftl", params));
            result.put("entity_code", FreemarkerTool.processString(freemarkerConfig,"tool/codegen-module/entity.ftl", params));

            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item: result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += StringTool.countMatches(item.getValue(), "\n");
                }
            }
            logger.info("genCode lineNum：{}", lineNum);

            return new ResponseBuilder<Map<String, String>>().success(result).build();
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return new ResponseBuilder<Map<String, String>>().fail("表结构解析失败").build();
        }

    }


}
