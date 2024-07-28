package com.xxl.deep.admin.service;

import com.xxl.deep.admin.model.entity.XxlDeepUser;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;

import java.util.List;

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
     * 删除
     */
    Response<String> deleteByIds(List<Integer> ids, XxlDeepUser loginUser);

    /**
     * 更新
     */
    public Response<String> update(XxlDeepUser user, XxlDeepUser loginUser);

    /**
     * 修改密码
     */
    public Response<String> updatePwd(XxlDeepUser loginUser, String password);

    /**
     * Load查询
     */
    public Response<XxlDeepUser> loadByUserName(String username);

    /**
     * 分页查询
     */
    public PageModel<XxlDeepUser> pageList(int offset, int pagesize, String username, int status);

}
