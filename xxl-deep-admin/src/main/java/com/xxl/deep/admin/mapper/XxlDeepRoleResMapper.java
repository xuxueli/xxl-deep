package com.xxl.deep.admin.mapper;

import com.xxl.deep.admin.model.entity.XxlDeepRoleRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* role res dao
*
* Created by xuxueli on 2024-09-28
*/
@Mapper
public interface XxlDeepRoleResMapper {

    /**
     * 新增
     */
    public int batchInsert(@Param("roleResList") List<XxlDeepRoleRes> roleResList);

    /**
     * 删除
     */
    public int deleteByRoleId(@Param("roleId") int roleId);

    /**
     * 查询
     */
    public List<XxlDeepRoleRes> loadRoleRes(@Param("roleId") int roleId);

    /**
     * 查询
     */
    public List<XxlDeepRoleRes> queryRoleRes(@Param("roleIds") List<Integer> roleIds);

}