package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.dao.XxlDeepUserMapper;
import com.xxl.deep.admin.model.XxlDeepUser;
import com.xxl.deep.admin.service.UserService;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user service
 *
 * @author xuxueli
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private XxlDeepUserMapper userMapper;

    /**
     * 新增
     */
    @Override
    public Response<String> insert(XxlDeepUser user) {

        // valid
        if (user == null) {
            return new ResponseBuilder<String>().fail("必要参数缺失").build();
        }

        userMapper.insert(user);
        return new ResponseBuilder<String>().success().build();
    }

    /**
     * 删除
     */
    @Override
    public Response<String> delete(int id) {
        int ret = userMapper.delete(id);
        return ret>0? new ResponseBuilder<String>().success().build()
                : new ResponseBuilder<String>().fail().build() ;
    }

    /**
     * 更新
     */
    @Override
    public Response<String> update(XxlDeepUser user) {
        int ret = userMapper.update(user);
        return ret>0? new ResponseBuilder<String>().success().build()
                : new ResponseBuilder<String>().fail().build() ;
    }

    /**
     * Load查询
     */
    @Override
    public Response<XxlDeepUser> loadByUserName(String username){
        XxlDeepUser record = userMapper.loadByUserName(username);
        return new ResponseBuilder<XxlDeepUser>().success(record).build();
    }

    /**
     * 分页查询
     */
    @Override
    public PageModel<XxlDeepUser> pageList(int offset, int pagesize, String username, int role) {

        List<XxlDeepUser> pageList = userMapper.pageList(offset, pagesize, username, role);
        int totalCount = userMapper.pageListCount(offset, pagesize, username, role);

        // result
        PageModel<XxlDeepUser> pageModel = new PageModel<XxlDeepUser>();
        pageModel.setPageData(pageList);
        pageModel.setTotalCount(totalCount);

        return pageModel;
    }

}
