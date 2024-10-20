package com.xxl.deep.admin.model.adaptor;

import com.xxl.deep.admin.constant.enums.ResourceStatuEnum;
import com.xxl.deep.admin.model.dto.LoginUserDTO;
import com.xxl.deep.admin.model.dto.XxlDeepResourceDTO;
import com.xxl.deep.admin.model.dto.XxlDeepRoleDTO;
import com.xxl.deep.admin.model.dto.XxlDeepUserDTO;
import com.xxl.deep.admin.model.entity.XxlDeepRole;
import com.xxl.deep.admin.model.entity.XxlDeepUser;
import com.xxl.tool.core.CollectionTool;
import com.xxl.tool.core.MapTool;
import com.xxl.tool.core.StringTool;

import java.util.*;
import java.util.stream.Collectors;

public class XxlDeepUserAdaptor {

    public static XxlDeepUser adapt(XxlDeepUserDTO xxlJobUser) {
        if (xxlJobUser == null) {
            return null;
        }

        XxlDeepUser xxlUser = new XxlDeepUser();
        xxlUser.setId(xxlJobUser.getId());
        xxlUser.setUsername(xxlJobUser.getUsername());
        xxlUser.setPassword(xxlJobUser.getPassword());
        xxlUser.setUserToken(xxlJobUser.getUserToken());
        xxlUser.setStatus(xxlJobUser.getStatus());
        xxlUser.setRealName(xxlJobUser.getRealName());
        xxlUser.setAddTime(xxlJobUser.getAddTime());
        xxlUser.setUpdateTime(xxlJobUser.getUpdateTime());
        return xxlUser;
    }

    public static XxlDeepUserDTO adapt2dto(XxlDeepUser xxlJobUser, boolean withPwd, Map<Integer, List<Integer>> userIdToRoleIdsMap) {
        if (xxlJobUser == null) {
            return null;
        }

        XxlDeepUserDTO xxlUser = new XxlDeepUserDTO();
        xxlUser.setId(xxlJobUser.getId());
        xxlUser.setUsername(xxlJobUser.getUsername());
        if (withPwd) {
            xxlUser.setPassword(xxlJobUser.getPassword());
        }
        xxlUser.setUserToken(xxlJobUser.getUserToken());
        xxlUser.setStatus(xxlJobUser.getStatus());
        xxlUser.setRealName(xxlJobUser.getRealName());
        xxlUser.setAddTime(xxlJobUser.getAddTime());
        xxlUser.setUpdateTime(xxlJobUser.getUpdateTime());
        if (MapTool.isNotEmpty(userIdToRoleIdsMap)) {
            xxlUser.setRoleIds(userIdToRoleIdsMap.get(xxlJobUser.getId()));
        }

        return xxlUser;
    }

    public static List<XxlDeepRole> adaptRoleList(List<XxlDeepRoleDTO> roleDTOList) {
        if (CollectionTool.isEmpty(roleDTOList)) {
            return null;
        }
        return roleDTOList
                .stream()
                .map(XxlDeepUserAdaptor::adaptRole)
                .collect(Collectors.toList());
    }

    public static XxlDeepRole adaptRole(XxlDeepRoleDTO roleDTO) {
        XxlDeepRole xxlRole = new XxlDeepRole();
        xxlRole.setId(roleDTO.getId());
        xxlRole.setName(roleDTO.getName());
        xxlRole.setOrder(roleDTO.getOrder());
        xxlRole.setAddTime(roleDTO.getAddTime());
        xxlRole.setUpdateTime(roleDTO.getUpdateTime());

        return xxlRole;
    }

    /**
     * adapt to login user
     *
     * @param xxlDeepUser
     * @param resourceList
     * @return
     */
    public static LoginUserDTO adapt2LoginUser(XxlDeepUser xxlDeepUser, List<XxlDeepResourceDTO> resourceList) {
        if (xxlDeepUser == null) {
            return null;
        }

        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setId(xxlDeepUser.getId());
        loginUserDTO.setUsername(xxlDeepUser.getUsername());
        loginUserDTO.setPassword(xxlDeepUser.getPassword());
        loginUserDTO.setUserToken(xxlDeepUser.getUserToken());
        if (CollectionTool.isNotEmpty(resourceList)) {
            /*List<String> permissionList = resourceList.stream()
                    .filter(x -> x.getStatus()== ResourceStatuEnum.NORMAL.getValue())
                    .map(XxlDeepResourceDTO::getPermission)
                    .collect(Collectors.toList());*/
            Set<String> permissionList = extractPermissions(resourceList);
            loginUserDTO.setPermissionList(new ArrayList<>(permissionList));
        }

        return loginUserDTO;
    }

    private static Set<String> extractPermissions(List<XxlDeepResourceDTO> resources) {
        Set<String> permissions = new HashSet<>();
        for (XxlDeepResourceDTO resource : resources) {
            if (StringTool.isNotBlank(resource.getPermission())) {
                permissions.add(resource.getPermission().trim());
            }
            if (CollectionTool.isNotEmpty(resource.getChildren())) {
                permissions.addAll(extractPermissions(resource.getChildren()));
            }
        }
        return permissions;
    }

}
