package com.xxl.deep.admin.service;

import com.xxl.deep.admin.controller.AbstractSpringMvcTest;
import com.xxl.deep.admin.mapper.XxlDeepResourceMapper;
import com.xxl.deep.admin.model.dto.XxlDeepResourceDTO;
import com.xxl.deep.admin.model.entity.XxlDeepResource;
import com.xxl.tool.gson.GsonTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ResourceServiceImplTest extends AbstractSpringMvcTest {
    private static Logger logger = LoggerFactory.getLogger(ResourceServiceImplTest.class);

    @Resource
    private ResourceService resourceService;

    //@SpyBean
    @MockBean
    private XxlDeepResourceMapper xxlDeepResourceMapper;

    @Test
    public void treeListTest(){
        // mock data
        List<XxlDeepResource> mockData = new ArrayList<>();
        mockData.add(new XxlDeepResource(0,1,"1"));
        mockData.add(new XxlDeepResource(0,2,"2"));
        mockData.add(new XxlDeepResource(2,21,"21"));
        mockData.add(new XxlDeepResource(21,211,"211"));
        mockData.add(new XxlDeepResource(21,212,"212"));
        mockData.add(new XxlDeepResource(2,22,"22"));
        mockData.add(new XxlDeepResource(0,3,"3"));

        // mock
        Mockito.when(xxlDeepResourceMapper.queryResource(null, -1)).thenReturn(mockData);

        List<XxlDeepResourceDTO> result = resourceService.treeList(null, -1);
        logger.info(GsonTool.toJson(result));

        Assertions.assertNotNull(result);
    }


}
