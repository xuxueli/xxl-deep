package com.xxl.deep.admin.mapper;

import com.xxl.deep.admin.model.entity.XxlDeepOrg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* XxlDeepOrg Mapper
*
* Created by xuxueli on '2024-09-30 15:38:21'.
*/
@Mapper
public interface XxlDeepOrgMapper {

    /**
    * 新增
    */
    public int insert(@Param("xxlDeepOrg") XxlDeepOrg xxlDeepOrg);

    /**
    * 删除
    */
    public int delete(@Param("ids") List<Integer> ids);

    /**
    * 更新
    */
    public int update(@Param("xxlDeepOrg")  XxlDeepOrg xxlDeepOrg);

    /**
    * Load查询
    */
    public XxlDeepOrg load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<XxlDeepOrg> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
     * queryOrg
     */
    List<XxlDeepOrg> queryOrg(@Param("name") String name, @Param("status") int status);

}