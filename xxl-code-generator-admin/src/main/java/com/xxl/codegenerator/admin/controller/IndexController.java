package com.xxl.codegenerator.admin.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxl.codegenerator.admin.core.CodeGeneratorTool;
import com.xxl.codegenerator.admin.core.model.ClassInfo;
import com.xxl.codegenerator.admin.model.ReturnT;
import com.xxl.codegenerator.admin.util.FreemarkerTool;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.IOUtils;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sso server (for web)
 *
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value(value = "classpath:verification.json")
    private org.springframework.core.io.Resource verificationResource;

    @Resource
    private FreemarkerTool freemarkerTool;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/getVerification")
    @ResponseBody
    public ReturnT<Map<String, String>> getVerification(String columnName, String fieldName, String fieldClass, String fieldComment) {
        try {
            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("columnName", columnName);
            params.put("fieldName", fieldName);
            params.put("fieldClass", fieldClass);
            params.put("fieldComment", fieldComment);

            //json rule
            String jsonString = new String(IOUtils.readFully(verificationResource.getInputStream(), -1, true));
            Gson gson = new Gson();
            List jsonResult = gson.fromJson(jsonString, new TypeToken<List<HashMap<String, String>>>() {
            }.getType());
            Map<String, String> result = new HashMap<String, String>();
            params.put("rule", jsonResult);

            // result
            result.put("verification", freemarkerTool.processString("xxl-code-generator/verification.ftl", params));

            return new ReturnT<Map<String, String>>(result);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构解析失败");
        }

    }

    @RequestMapping("/getParseTableSql")
    @ResponseBody
    public ReturnT<Map<String, String>> getParseTableSql(String tableSql, String packageName) {

        try {
            if (StringUtils.isBlank(packageName)) {
                return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "包名不能为空");
            }
            if (StringUtils.isBlank(tableSql)) {
                return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构信息不可为空");
            }
            // parse table
            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);
            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("classInfo", classInfo);
            // result
            Map<String, String> result = new HashMap<String, String>();

            result.put("optionSelect", freemarkerTool.processString("xxl-code-generator/form.ftl", params));

            return new ReturnT<Map<String, String>>(result);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构解析失败");
        }

    }


    @RequestMapping("/codeGenerate")
    @ResponseBody
    public ReturnT<Map<String, String>> codeGenerate(String tableSql, String packageName) {

        try {

            if (StringUtils.isBlank(packageName)) {
                return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "包名不能为空");
            }

            if (StringUtils.isBlank(tableSql)) {
                return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构信息不可为空");
            }


            // parse table
            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);

            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("classInfo", classInfo);

            params.put("packageName", packageName);


            // result
            Map<String, String> result = new HashMap<String, String>();

            result.put("controller_code", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
            result.put("service_code", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
            result.put("service_impl_code", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));

            result.put("dao_code", freemarkerTool.processString("xxl-code-generator/dao.ftl", params));
            result.put("mybatis_code", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
            result.put("model_code", freemarkerTool.processString("xxl-code-generator/model.ftl", params));

            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item : result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += StringUtils.countMatches(item.getValue(), "\n");
                }
            }
            logger.info("生成代码行数：{}", lineNum);

            return new ReturnT<Map<String, String>>(result);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构解析失败");
        }

    }

}