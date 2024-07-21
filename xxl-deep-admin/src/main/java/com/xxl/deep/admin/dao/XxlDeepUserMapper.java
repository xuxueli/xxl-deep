package com.xxl.deep.admin.dao;

import com.xxl.deep.admin.model.XxlDeepUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:44:59
 */
@Mapper
public interface XxlDeepUserMapper {

	public int insert(XxlDeepUser xxlJobUser);

	public int delete(@Param("id") int id);

	public int deleteByIds(@Param("ids") List<Integer> ids);

	public int update(XxlDeepUser xxlJobUser);

	public XxlDeepUser loadByUserName(@Param("username") String username);

	public List<XxlDeepUser> pageList(@Param("offset") int offset,
									  @Param("pagesize") int pagesize,
									  @Param("username") String username,
									  @Param("status") int status);
	public int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("username") String username,
							 @Param("status") int status);

}
