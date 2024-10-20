package com.xxl.deep.admin.service;

import com.xxl.deep.admin.model.dto.LoginUserDTO;
import com.xxl.deep.admin.model.dto.XxlDeepUserDTO;
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
    public Response<String> insert(XxlDeepUserDTO xxlJobUser);

    /**
     * 删除
     */
    public Response<String> delete(int id);

    /**
     * 删除
     */
    Response<String> deleteByIds(List<Integer> userIds, LoginUserDTO loginUser);

    /**
     * 更新
     */
    public Response<String> update(XxlDeepUserDTO xxlJobUser, LoginUserDTO loginUser);

    /**
     * 修改密码
     */
    public Response<String> updatePwd(LoginUserDTO loginUser, String password);

    /**
     * Load查询
     */
    public Response<XxlDeepUser> loadByUserName(String username);

    /**
     * 分页查询
     */
    public PageModel<XxlDeepUserDTO> pageList(int offset, int pagesize, String username, int status);

}
