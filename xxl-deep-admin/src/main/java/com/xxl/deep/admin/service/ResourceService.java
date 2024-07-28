package com.xxl.deep.admin.service;

import com.xxl.deep.admin.model.entity.XxlDeepResource;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.PageModel;

/**
* XxlDeepResource Service
*
* Created by xuxueli on '2024-07-28 12:52:39'.
*/
public interface ResourceService {

    /**
    * 新增
    */
    public Response<String> insert(XxlDeepResource xxlDeepResource);

    /**
    * 删除
    */
    public Response<String> delete(int id);

    /**
    * 更新
    */
    public Response<String> update(XxlDeepResource xxlDeepResource);

    /**
    * Load查询
    */
    public Response<XxlDeepResource> load(int id);

    /**
    * 分页查询
    */
    public PageModel<XxlDeepResource> pageList(int offset, int pagesize);

}
