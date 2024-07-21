package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.dao.XxlDeepUserMapper;
import com.xxl.deep.admin.model.XxlDeepUser;
import com.xxl.deep.admin.service.UserService;
import com.xxl.deep.admin.util.I18nUtil;
import com.xxl.tool.core.CollectionTool;
import com.xxl.tool.core.StringTool;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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

        // valid empty
        if (user == null) {
            return new ResponseBuilder<String>().fail("必要参数缺失").build();
        }
        // valid username
        if (StringTool.isBlank(user.getUsername())) {
            return new ResponseBuilder<String>().fail(I18nUtil.getString("system_please_input") + I18nUtil.getString("user_username")).build();
        }
        user.setUsername(user.getUsername().trim());
        if (!(user.getUsername().length()>=4 && user.getUsername().length()<=20)) {
            return new ResponseBuilder<String>().fail(I18nUtil.getString("system_lengh_limit")+"[4-20]").build();
        }
        // valid password
        if (StringTool.isBlank(user.getPassword())) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("system_please_input")+I18nUtil.getString("user_password") ).build();
        }
        user.setPassword(user.getPassword().trim());
        if (!(user.getPassword().length()>=4 && user.getPassword().length()<=20)) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("system_lengh_limit")+"[4-20]" ).build();
        }
        // md5 password
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        // check repeat
        XxlDeepUser existUser = userMapper.loadByUserName(user.getUsername());
        if (existUser != null) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("user_username_repeat") ).build();
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
     * 删除
     */
    @Override
    public Response<String> deleteByIds(List<Integer> ids, XxlDeepUser loginUser) {

        // valid
        if (CollectionTool.isEmpty(ids)) {
            return new ResponseBuilder<String>().fail(I18nUtil.getString("system_please_choose") + I18nUtil.getString("user_tips")).build();
        }

        // avoid opt login seft
        if (ids.contains(loginUser.getId())) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("user_update_loginuser_limit") ).build();
        }

        int ret = userMapper.deleteByIds(ids);
        return ret>0? new ResponseBuilder<String>().success().build()
                : new ResponseBuilder<String>().fail().build() ;
    }

    /**
     * 更新
     */
    @Override
    public Response<String> update(XxlDeepUser user, XxlDeepUser loginUser) {

        // avoid opt login seft
        if (loginUser.getUsername().equals(user.getUsername())) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("user_update_loginuser_limit") ).build();
        }

        // valid password
        if (StringTool.isNotBlank(user.getPassword())) {
            user.setPassword(user.getPassword().trim());
            if (!(user.getPassword().length()>=4 && user.getPassword().length()<=20)) {
                return new ResponseBuilder<String>().fail(  I18nUtil.getString("system_lengh_limit")+"[4-20]" ).build();
            }
            // md5 password
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        } else {
            user.setPassword(null);
        }

        int ret = userMapper.update(user);
        return ret>0? new ResponseBuilder<String>().success().build()
                : new ResponseBuilder<String>().fail().build() ;
    }

    /**
     * 修改密码
     */
    public Response<String> updatePwd(XxlDeepUser loginUser, String password){
        // valid password
        if (StringTool.isBlank(password)){
            new ResponseBuilder<String>().fail( "密码不可为空" ).build();
        }
        password = password.trim();
        if (!(password.length()>=4 && password.length()<=20)) {
            new ResponseBuilder<String>().fail( I18nUtil.getString("system_lengh_limit")+"[4-20]" ).build();
        }

        // md5 password
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        // update pwd
        XxlDeepUser existUser = userMapper.loadByUserName(loginUser.getUsername());
        existUser.setPassword(md5Password);
        userMapper.update(existUser);

        return new ResponseBuilder<String>().success().build();
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
    public PageModel<XxlDeepUser> pageList(int offset, int pagesize, String username, int status) {

        // data
        List<XxlDeepUser> pageList = userMapper.pageList(offset, pagesize, username, status);
        int totalCount = userMapper.pageListCount(offset, pagesize, username, status);

        // filter
        if (CollectionTool.isNotEmpty(pageList)) {
            for (XxlDeepUser item: pageList) {
                item.setPassword(null);
            }
        }

        // result
        PageModel<XxlDeepUser> pageModel = new PageModel<XxlDeepUser>();
        pageModel.setPageData(pageList);
        pageModel.setTotalCount(totalCount);

        return pageModel;
    }

}
