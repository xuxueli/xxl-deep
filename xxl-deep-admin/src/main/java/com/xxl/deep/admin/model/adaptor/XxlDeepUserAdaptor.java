package com.xxl.deep.admin.model.adaptor;

import com.xxl.deep.admin.model.dto.XxlDeepRoleDTO;
import com.xxl.deep.admin.model.dto.XxlDeepUserDTO;
import com.xxl.deep.admin.model.entity.XxlDeepRole;
import com.xxl.deep.admin.model.entity.XxlDeepUser;
import com.xxl.tool.core.CollectionTool;
import com.xxl.tool.core.MapTool;

import java.util.List;
import java.util.Map;
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

}
