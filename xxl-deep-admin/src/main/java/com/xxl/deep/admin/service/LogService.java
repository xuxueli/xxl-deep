package com.xxl.deep.admin.service;

import java.util.List;

import com.xxl.deep.admin.model.dto.XxlDeepLogDTO;
import com.xxl.deep.admin.model.entity.XxlDeepLog;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.PageModel;

/**
* XxlDeepLog Service
*
* Created by xuxueli on '2024-10-27 12:19:06'.
*/
public interface LogService {

    /**
    * 新增
    */
    public Response<String> insert(XxlDeepLog xxlDeepLog);

    /**
    * 删除
    */
    public Response<String> delete(List<Integer> ids);

    /**
    * 更新
    */
    public Response<String> update(XxlDeepLog xxlDeepLog);

    /**
    * Load查询
    */
    public Response<XxlDeepLog> load(int id);

    /**
    * 分页查询
    */
    public PageModel<XxlDeepLogDTO> pageList(int type, String module, String title, int offset, int pagesize);

}
