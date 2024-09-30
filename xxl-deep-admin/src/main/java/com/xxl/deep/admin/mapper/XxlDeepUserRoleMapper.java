package com.xxl.deep.admin.mapper;

import com.xxl.deep.admin.model.entity.XxlDeepUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* XxlDeepUserRole Mapper
*
* Created by xuxueli on '2024-09-30 12:53:32'.
*/
@Mapper
public interface XxlDeepUserRoleMapper {

    /**
    * 新增
    */
    public int batchInsert(@Param("userRoleList") List<XxlDeepUserRole> userRoleList);

    /**
    * 删除
    */
    public int deleteByUserId(@Param("userId") int userId);

    /**
    * Load查询
    */
    public List<XxlDeepUserRole> queryByUserId(@Param("userId") int userId);

    public List<XxlDeepUserRole> queryByUserIds(@Param("userIds") List<Integer> userIds);

}