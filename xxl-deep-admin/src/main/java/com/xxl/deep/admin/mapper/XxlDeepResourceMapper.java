package com.xxl.deep.admin.mapper;

import com.xxl.deep.admin.model.entity.XxlDeepResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* XxlDeepResource Mapper
*
* Created by xuxueli on '2024-07-28 12:52:39'.
*/
@Mapper
    public interface XxlDeepResourceMapper {

    /**
    * 新增
    */
    public int insert(XxlDeepResource xxlDeepResource);

    /**
    * 删除
    */
    public int delete(@Param("ids") List<Integer> ids);

    /**
    * 更新
    */
    public int update(XxlDeepResource xxlDeepResource);

    /**
    * Load查询
    */
    public XxlDeepResource load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<XxlDeepResource> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
     * Tree查询
     */
    public List<XxlDeepResource> queryResource(@Param("name") String name, @Param("status") int status);

}
