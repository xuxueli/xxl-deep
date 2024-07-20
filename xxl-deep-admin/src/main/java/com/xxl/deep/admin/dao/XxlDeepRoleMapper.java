package com.xxl.deep.admin.dao;

import com.xxl.deep.admin.model.XxlDeepRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* role dao
*
* Created by xuxueli on '2024-07-21 02:06:59'.
*/
@Mapper
public interface XxlDeepRoleMapper {

    /**
    * 新增
    */
    public int insert(XxlDeepRole xxlDeepRole);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(XxlDeepRole xxlDeepRole);

    /**
    * Load查询
    */
    public XxlDeepRole load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<XxlDeepRole> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
