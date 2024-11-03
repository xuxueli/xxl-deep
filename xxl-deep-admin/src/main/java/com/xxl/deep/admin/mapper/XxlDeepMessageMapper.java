package com.xxl.deep.admin.mapper;

import com.xxl.deep.admin.model.entity.XxlDeepMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* XxlDeepMessage Mapper
*
* Created by xuxueli on '2024-11-03 11:03:29'.
*/
@Mapper
public interface XxlDeepMessageMapper {

    /**
    * 新增
    */
    public int insert(@Param("xxlDeepMessage") XxlDeepMessage xxlDeepMessage);

    /**
    * 删除
    */
    public int delete(@Param("ids") List<Integer> ids);

    /**
    * 更新
    */
    public int update(@Param("xxlDeepMessage") XxlDeepMessage xxlDeepMessage);

    /**
    * Load查询
    */
    public XxlDeepMessage load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<XxlDeepMessage> pageList(@Param("status") int status,
                                         @Param("title") String title,
                                         @Param("offset") int offset,
                                         @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("status") int status,
                             @Param("title") String title,
                             @Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
