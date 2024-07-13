package com.xxl.deep.admin.service;

import com.xxl.deep.admin.model.XxlDeepUser;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;

/**
 * user service
 *
 * @author xuxueli
 */
public interface UserService {

    /**
     * 新增
     */
    public Response<String> insert(XxlDeepUser user);

    /**
     * 删除
     */
    public Response<String> delete(int id);

    /**
     * 更新
     */
    public Response<String> update(XxlDeepUser user);

    /**
     * Load查询
     */
    public Response<XxlDeepUser> loadByUserName(String username);

    /**
     * 分页查询
     */
    public PageModel<XxlDeepUser> pageList(int offset, int pagesize, String username, int role);

}
