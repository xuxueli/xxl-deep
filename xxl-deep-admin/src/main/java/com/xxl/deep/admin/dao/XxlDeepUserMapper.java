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

	public List<XxlDeepUser> pageList(@Param("offset") int offset,
									  @Param("pagesize") int pagesize,
									  @Param("username") String username,
									  @Param("role") int role);
	public int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("username") String username,
							 @Param("role") int role);

	public XxlDeepUser loadByUserName(@Param("username") String username);

	public int save(XxlDeepUser xxlJobUser);

	public int update(XxlDeepUser xxlJobUser);
	
	public int delete(@Param("id") int id);

}
