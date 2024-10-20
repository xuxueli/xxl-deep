package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.mapper.XxlDeepUserMapper;
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
     *
     * @param xxlJobUser
     * @return
     */
    private String makeToken(XxlDeepUser xxlJobUser){
        String tokenJson = GsonTool.toJson(xxlJobUser);
        String tokenHex = new BigInteger(tokenJson.getBytes()).toString(16);
        return tokenHex;
    }

    /**
     * parse token to user
     * @param tokenHex
     * @return
     */
    private XxlDeepUser parseToken(String tokenHex){
        XxlDeepUser xxlJobUser = null;
        if (tokenHex != null) {
            String tokenJson = new String(new BigInteger(tokenHex, 16).toByteArray());      // username_password(md5)
            xxlJobUser = GsonTool.fromJson(tokenJson, XxlDeepUser.class);
        }
        return xxlJobUser;
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

        // valid passowrd
        XxlDeepUser xxlDeepUser = xxlJobUserMapper.loadByUserName(username);
        if (xxlDeepUser == null) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("login_param_unvalid") ).build();
        }
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!passwordMd5.equals(xxlDeepUser.getPassword())) {
            return new ResponseBuilder<String>().fail( I18nUtil.getString("login_param_unvalid") ).build();
        }

        // make token
        String loginToken = makeToken(xxlDeepUser);

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
    public XxlDeepUser ifLogin(HttpServletRequest request, HttpServletResponse response){
        String cookieToken = CookieTool.getValue(request, LOGIN_IDENTITY_KEY);
        if (cookieToken != null) {
            XxlDeepUser cookieUser = null;
            try {
                cookieUser = parseToken(cookieToken);
            } catch (Exception e) {
                logout(request, response);
            }
            if (cookieUser != null) {
                XxlDeepUser dbUser = xxlJobUserMapper.loadByUserName(cookieUser.getUsername());
                if (dbUser != null) {
                    if (cookieUser.getPassword().equals(dbUser.getPassword())) {
                        return dbUser;
                    }
                }
            }
        }
        return null;
    }

    /**
     * query Authentication resource
     * @param loginUser
     * @return
     */
    public List<XxlDeepResourceDTO> queryAuthentication(XxlDeepUser loginUser){
        // all resource
        //List<XxlDeepResourceDTO> resourceList = resourceService.treeList(null, ResourceStatuEnum.NORMAL.getValue());
        // auth resource
        List<XxlDeepResourceDTO> resourceList = resourceService.treeListByUserId(loginUser.getId());
        return resourceList;
    }


}
