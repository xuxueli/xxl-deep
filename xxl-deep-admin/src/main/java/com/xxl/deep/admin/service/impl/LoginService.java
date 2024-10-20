package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.constant.enums.UserStatuEnum;
import com.xxl.deep.admin.mapper.XxlDeepUserMapper;
import com.xxl.deep.admin.model.adaptor.XxlDeepUserAdaptor;
import com.xxl.deep.admin.model.dto.LoginUserDTO;
import com.xxl.deep.admin.model.dto.XxlDeepResourceDTO;
import com.xxl.deep.admin.model.entity.XxlDeepUser;
import com.xxl.deep.admin.service.ResourceService;
import com.xxl.deep.admin.util.I18nUtil;
import com.xxl.tool.core.StringTool;
import com.xxl.tool.gson.GsonTool;
import com.xxl.tool.net.CookieTool;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;

/**
 * @author xuxueli 2019-05-04 22:13:264
 */
@Configuration
public class LoginService {

    public static final String LOGIN_IDENTITY_KEY = "XXL_DEEP_LOGIN_IDENTITY";

    @Resource
    private XxlDeepUserMapper xxlJobUserMapper;
    @Resource
    private ResourceService resourceService;

    /**
     * make token from user
     */
    private String makeToken(LoginUserDTO loginUserDTO){
        String tokenJson = GsonTool.toJson(loginUserDTO);
        String tokenHex = new BigInteger(tokenJson.getBytes()).toString(16);
        return tokenHex;
    }

    /**
     * parse token to user
     */
    private LoginUserDTO parseToken(String tokenHex){
        LoginUserDTO loginUser = null;
        if (tokenHex != null) {
            String tokenJson = new String(new BigInteger(tokenHex, 16).toByteArray());      // username_password(md5)
            loginUser = GsonTool.fromJson(tokenJson, LoginUserDTO.class);
        }
        return loginUser;
    }

    /**
     * get login user from request
     * @param request
     * @return
     */
    public static LoginUserDTO getLoginUser(HttpServletRequest request){
        LoginUserDTO loginUser = (LoginUserDTO) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        return loginUser;
    }

    /**
     * login
     *
     * @param response
     * @param username
     * @param password
     * @param ifRemember
     * @return
     */
    public Response<String> login(HttpServletResponse response, String username, String password, boolean ifRemember){

        // param
        if (StringTool.isBlank(username) || StringTool.isBlank(password)){
            return new ResponseBuilder<String>().fail( I18nUtil.getString("login_param_empty") ).build();
        }

        // valid user, empty、status、passowrd
        XxlDeepUser xxlDeepUser = xxlJobUserMapper.loadByUserName(username);
        if (xxlDeepUser == null) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("login_param_unvalid") ).build();
        }
        if (xxlDeepUser.getStatus() != UserStatuEnum.NORMAL.getStatus()) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("login_status_invalid") ).build();
        }
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!passwordMd5.equals(xxlDeepUser.getPassword())) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("login_param_unvalid") ).build();
        }

        // find resource
        List<XxlDeepResourceDTO> resourceList = this.queryAuthentication(xxlDeepUser.getId());

        // make token
        LoginUserDTO loginUserDTO = XxlDeepUserAdaptor.adapt2LoginUser(xxlDeepUser, resourceList);
        String loginToken = makeToken(loginUserDTO);

        // do login
        CookieTool.set(response, LOGIN_IDENTITY_KEY, loginToken, ifRemember);
        return new ResponseBuilder<String>().success().build();
    }

    /**
     * logout
     *
     * @param request
     * @param response
     */
    public Response<String> logout(HttpServletRequest request, HttpServletResponse response){
        CookieTool.remove(request, response, LOGIN_IDENTITY_KEY);
        return new ResponseBuilder<String>().success().build();
    }

    /**
     * check iflogin
     *
     * @param request
     * @return
     */
    public LoginUserDTO ifLogin(HttpServletRequest request, HttpServletResponse response){
        String cookieToken = CookieTool.getValue(request, LOGIN_IDENTITY_KEY);
        if (cookieToken != null) {
            LoginUserDTO loginUser = null;
            try {
                loginUser = parseToken(cookieToken);
            } catch (Exception e) {
                logout(request, response);
            }
            if (loginUser != null) {
                XxlDeepUser dbUser = xxlJobUserMapper.loadByUserName(loginUser.getUsername());
                if (dbUser != null) {
                    if (loginUser.getPassword().equals(dbUser.getPassword())) {
                        return loginUser;
                    }
                }
            }
        }
        return null;
    }

    /**
     * query Authentication resource
     * @param userId
     * @return
     */
    public List<XxlDeepResourceDTO> queryAuthentication(int userId){
        // all resource
        //List<XxlDeepResourceDTO> resourceList = resourceService.treeList(null, ResourceStatuEnum.NORMAL.getValue());
        // auth resource
        List<XxlDeepResourceDTO> resourceList = resourceService.treeListByUserId(userId);
        return resourceList;
    }


}
