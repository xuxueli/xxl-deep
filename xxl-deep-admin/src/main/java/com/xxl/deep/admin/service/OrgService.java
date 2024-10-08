package com.xxl.deep.admin.service;

import java.util.List;

import com.xxl.deep.admin.model.dto.XxlDeepOrgDTO;
import com.xxl.deep.admin.model.entity.XxlDeepOrg;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.PageModel;

/**
* XxlDeepOrg Service
*
* Created by xuxueli on '2024-09-30 15:38:21'.
*/
public interface OrgService {

    /**
    * 新增
    */
    public Response<String> insert(XxlDeepOrg xxlDeepOrg);

    /**
    * 删除
    */
    public Response<String> delete(List<Integer> ids);

    /**
    * 更新
    */
    public Response<String> update(XxlDeepOrg xxlDeepOrg);

    /**
    * Load查询
    */
    public Response<XxlDeepOrg> load(int id);

    /**
    * 分页查询
    */
    public PageModel<XxlDeepOrg> pageList(int offset, int pagesize);

    /**
     * treeList
     */
    List<XxlDeepOrgDTO> treeList(String name, int status);

    List<XxlDeepOrgDTO> simpleTreeList(String name, int status);

}
