package com.xxl.deep.admin.service;

import java.util.List;

import com.xxl.deep.admin.model.dto.LoginUserDTO;
import com.xxl.deep.admin.model.dto.XxlDeepMessageDTO;
import com.xxl.deep.admin.model.entity.XxlDeepMessage;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.PageModel;

/**
* XxlDeepMessage Service
*
* Created by xuxueli on '2024-11-03 11:03:29'.
*/
public interface MessageService {

    /**
    * 新增
    */
    public Response<String> insert(XxlDeepMessage xxlDeepMessage, LoginUserDTO loginUser);

    /**
    * 删除
    */
    public Response<String> delete(List<Integer> ids);

    /**
    * 更新
    */
    public Response<String> update(XxlDeepMessage xxlDeepMessage);

    /**
    * Load查询
    */
    public Response<XxlDeepMessage> load(int id);

    /**
    * 分页查询
    */
    public PageModel<XxlDeepMessageDTO> pageList(int status, String title, int offset, int pagesize);

}
