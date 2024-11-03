package com.xxl.boot.admin.model.adaptor;

import com.xxl.boot.admin.constant.enums.ResourceStatuEnum;
import com.xxl.boot.admin.model.dto.LoginUserDTO;
import com.xxl.boot.admin.model.dto.XxlBootResourceDTO;
import com.xxl.boot.admin.model.dto.XxlBootRoleDTO;
import com.xxl.boot.admin.model.dto.XxlBootUserDTO;
import com.xxl.boot.admin.model.entity.XxlBootRole;
import com.xxl.boot.admin.model.entity.XxlBootUser;
import com.xxl.tool.core.CollectionTool;
import com.xxl.tool.core.MapTool;
import com.xxl.tool.core.StringTool;

import java.util.*;
import java.util.stream.Collectors;

public class XxlBootUserAdaptor {

    public static XxlBootUser adapt(XxlBootUserDTO xxlJobUser) {
        if (xxlJobUser == null) {
            return null;
        }

        XxlBootUser xxlUser = new XxlBootUser();
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

    public static XxlBootUserDTO adapt2dto(XxlBootUser xxlJobUser, boolean withPwd, Map<Integer, List<Integer>> userIdToRoleIdsMap) {
        if (xxlJobUser == null) {
            return null;
        }

        XxlBootUserDTO xxlUser = new XxlBootUserDTO();
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

    public static List<XxlBootRole> adaptRoleList(List<XxlBootRoleDTO> roleDTOList) {
        if (CollectionTool.isEmpty(roleDTOList)) {
            return null;
        }
        return roleDTOList
                .stream()
                .map(XxlBootUserAdaptor::adaptRole)
                .collect(Collectors.toList());
    }

    public static XxlBootRole adaptRole(XxlBootRoleDTO roleDTO) {
        XxlBootRole xxlRole = new XxlBootRole();
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
     * @param xxlBootUser
     * @param resourceList
     * @return
     */
    public static LoginUserDTO adapt2LoginUser(XxlBootUser xxlBootUser, List<XxlBootResourceDTO> resourceList) {
        if (xxlBootUser == null) {
            return null;
        }

        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setId(xxlBootUser.getId());
        loginUserDTO.setUsername(xxlBootUser.getUsername());
        loginUserDTO.setPassword(xxlBootUser.getPassword());
        loginUserDTO.setUserToken(xxlBootUser.getUserToken());
        loginUserDTO.setRealName(xxlBootUser.getRealName());
        if (CollectionTool.isNotEmpty(resourceList)) {
            /*List<String> permissionList = resourceList.stream()
                    .filter(x -> x.getStatus()== ResourceStatuEnum.NORMAL.getValue())
                    .map(XxlBootResourceDTO::getPermission)
                    .collect(Collectors.toList());*/
            Set<String> permissionList = extractPermissions(resourceList);
            loginUserDTO.setPermissionList(new ArrayList<>(permissionList));
        }

        return loginUserDTO;
    }

    private static Set<String> extractPermissions(List<XxlBootResourceDTO> resources) {
        Set<String> permissions = new HashSet<>();
        for (XxlBootResourceDTO resource : resources) {
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
