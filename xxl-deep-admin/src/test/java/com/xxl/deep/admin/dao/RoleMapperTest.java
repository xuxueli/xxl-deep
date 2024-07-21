package com.xxl.deep.admin.dao;

import com.xxl.deep.admin.controller.AbstractSpringMvcTest;
import com.xxl.deep.admin.controller.IndexControllerTest;
import com.xxl.deep.admin.model.XxlDeepRole;
import com.xxl.deep.admin.service.RoleService;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleMapperTest {
    private static Logger logger = LoggerFactory.getLogger(RoleMapperTest.class);

    @Resource
    private RoleService roleService;

    @Test
    public void loadTest() throws Exception {
        Response<XxlDeepRole> role = roleService.load(1);
        Assertions.assertNotNull(role);
    }

    @Test
    public void pageTest() throws Exception {
        PageModel<XxlDeepRole> result = roleService.pageList(0, 100, null);
        Assertions.assertNotNull(result);
    }

    @Test
    public void insertTest() throws Exception {
        XxlDeepRole role = new XxlDeepRole();
        role.setName("admin22");
        role.setOrder(22);

        Response<String> result = roleService.insert(role);
        Assertions.assertNotNull(result);
    }

    @Test
    public void updateTest() throws Exception {
        XxlDeepRole role = roleService.load(2).getData();
        if (role == null) {
            return;
        }

        role.setName("admin33");
        role.setOrder(33);

        Response<String> result = roleService.update(role);
        Assertions.assertNotNull(result);
    }

    @Test
    public void deleteTest() throws Exception {
        Response<String> result = roleService.deleteByIds(Arrays.asList(2));
        Assertions.assertNotNull(result);
    }

}
