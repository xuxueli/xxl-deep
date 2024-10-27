package com.xxl.deep.admin.mapper;

import com.xxl.deep.admin.model.entity.XxlDeepLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* XxlDeepLog Mapper
*
* Created by xuxueli on '2024-10-27 12:19:06'.
*/
@Mapper
public interface XxlDeepLogMapper {

    /**
    * 新增
    */
    public int insert(@Param("xxlDeepLog") XxlDeepLog xxlDeepLog);

    /**
    * 删除
    */
    public int delete(@Param("ids") List<Integer> ids);

    /**
    * 更新
    */
    public int update(@Param("xxlDeepLog") XxlDeepLog xxlDeepLog);

    /**
    * Load查询
    */
    public XxlDeepLog load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<XxlDeepLog> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize);

}
