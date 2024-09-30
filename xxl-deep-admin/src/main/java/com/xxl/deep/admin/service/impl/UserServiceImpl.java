package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.mapper.XxlDeepRoleMapper;
import com.xxl.deep.admin.mapper.XxlDeepUserMapper;
import com.xxl.deep.admin.mapper.XxlDeepUserRoleMapper;
import com.xxl.deep.admin.model.adaptor.XxlDeepUserAdaptor;
import com.xxl.deep.admin.model.dto.XxlDeepUserDTO;
import com.xxl.deep.admin.model.entity.XxlDeepRole;
import com.xxl.deep.admin.model.entity.XxlDeepUser;
import com.xxl.deep.admin.model.entity.XxlDeepUserRole;
import com.xxl.deep.admin.service.UserService;
import com.xxl.deep.admin.util.I18nUtil;
import com.xxl.tool.core.CollectionTool;
import com.xxl.tool.core.StringTool;
import com.xxl.tool.response.PageModel;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * user service
 *
 * @author xuxueli
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private XxlDeepUserMapper userMapper;
    @Resource
    private XxlDeepRoleMapper xxlDeepRoleMapper;
    @Autowired
    private XxlDeepUserRoleMapper xxlDeepUserRoleMapper;

    /**
     * 新增
     */
    @Override
    public Response<String> insert(XxlDeepUserDTO xxlJobUser) {

        // adapt
        XxlDeepUser user = XxlDeepUserAdaptor.adapt(xxlJobUser);
        List<Integer> roleIds = xxlJobUser.getRoleIds();


        // valid empty
        if (user == null) {
            return new ResponseBuilder<String>().fail(I18nUtil.getString("system_param_empty")).build();
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

        // valid user role
        if (CollectionTool.isNotEmpty(roleIds)) {
            List<XxlDeepRole> roles = xxlDeepRoleMapper.queryByRoleIds(roleIds);
            if (!(roles!=null && roles.size()==roleIds.size())) {
                return new ResponseBuilder<String>().fail("操作失败，角色ID非法").build();
            }
        }

        // check repeat
        XxlDeepUser existUser = userMapper.loadByUserName(user.getUsername());
        if (existUser != null) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("user_username_repeat") ).build();
        }

        // save user
        userMapper.insert(user);

        // save user-role
        if (CollectionTool.isNotEmpty(roleIds)) {
            List<XxlDeepUserRole> userRoleList = roleIds
                    .stream()
                    .map(roleId-> new XxlDeepUserRole(user.getId(), roleId))
                    .collect(Collectors.toList());
            xxlDeepUserRoleMapper.batchInsert(userRoleList);
        }

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
    public Response<String> deleteByIds(List<Integer> userIds, XxlDeepUser loginUser) {

        // valid
        if (CollectionTool.isEmpty(userIds)) {
            return new ResponseBuilder<String>().fail(I18nUtil.getString("system_please_choose") + I18nUtil.getString("user_tips")).build();
        }

        // avoid opt login seft
        if (userIds.contains(loginUser.getId())) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("user_update_loginuser_limit") ).build();
        }

        // valid user role
        List<XxlDeepUserRole> userRoleList = xxlDeepUserRoleMapper.queryByUserIds(userIds);
        if (CollectionTool.isNotEmpty(userRoleList)) {
            return new ResponseBuilder<String>().fail("无法删除，请先取消关联角色").build();
        }


        int ret = userMapper.deleteByIds(userIds);
        return ret>0? new ResponseBuilder<String>().success().build()
                : new ResponseBuilder<String>().fail().build() ;
    }

    /**
     * 更新
     */
    @Override
    public Response<String> update(XxlDeepUserDTO xxlJobUser, XxlDeepUser loginUser) {

        // adapt
        XxlDeepUser user = XxlDeepUserAdaptor.adapt(xxlJobUser);
        List<Integer> roleIds = xxlJobUser.getRoleIds();

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

        // valid user role
        if (CollectionTool.isNotEmpty(roleIds)) {
            List<XxlDeepRole> roles = xxlDeepRoleMapper.queryByRoleIds(roleIds);
            if (!(roles!=null && roles.size()==roleIds.size())) {
                return new ResponseBuilder<String>().fail("操作失败，角色ID非法").build();
            }
        }

        // update user
        int ret = userMapper.update(user);

        // update user-role
        xxlDeepUserRoleMapper.deleteByUserId(user.getId());
        if (CollectionTool.isNotEmpty(roleIds)) {
            List<XxlDeepUserRole> userRoleList = roleIds
                    .stream()
                    .map(roleId-> new XxlDeepUserRole(user.getId(), roleId))
                    .collect(Collectors.toList());
            xxlDeepUserRoleMapper.batchInsert(userRoleList);
        }

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
    public PageModel<XxlDeepUserDTO> pageList(int offset, int pagesize, String username, int status) {

        // data
        List<XxlDeepUser> pageList = userMapper.pageList(offset, pagesize, username, status);
        int totalCount = userMapper.pageListCount(offset, pagesize, username, status);

        // adaptor
        List<XxlDeepUserDTO> pageListDto = new ArrayList<>();
        if (CollectionTool.isNotEmpty(pageList)) {
            // find role
            List<Integer> userIds = pageList.stream().map(XxlDeepUser::getId).collect(Collectors.toList());
            List<XxlDeepUserRole> userRoleList = xxlDeepUserRoleMapper.queryByUserIds(userIds);

            // user-roleids map
            Map<Integer, List<Integer>> userIdToRoleIdsMap = Optional
                    .ofNullable(userRoleList)
                    .orElse(new ArrayList<>()).stream()
                    .collect(Collectors.groupingBy(
                            XxlDeepUserRole::getUserId,
                            Collectors.mapping(XxlDeepUserRole::getRoleId, Collectors.toList())
                    ));

            // dto list
            pageListDto = pageList
                    .stream()
                    .map(item->XxlDeepUserAdaptor.adapt2dto(item, false, userIdToRoleIdsMap))
                    .collect(Collectors.toList());
        }

        // result
        PageModel<XxlDeepUserDTO> pageModel = new PageModel<XxlDeepUserDTO>();
        pageModel.setPageData(pageListDto);
        pageModel.setTotalCount(totalCount);

        return pageModel;
    }

}
