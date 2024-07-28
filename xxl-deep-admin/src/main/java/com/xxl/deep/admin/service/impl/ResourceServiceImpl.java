package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.mapper.XxlDeepResourceMapper;
import com.xxl.deep.admin.model.entity.XxlDeepResource;
import com.xxl.deep.admin.service.ResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import com.xxl.tool.response.PageModel;

/**
* XxlDeepResource Service Impl
*
* Created by xuxueli on '2024-07-28 12:52:39'.
*/
@Service
public class ResourceServiceImpl implements ResourceService {

	@Resource
	private XxlDeepResourceMapper xxlDeepResourceMapper;

	/**
    * 新增
    */
	@Override
	public Response<String> insert(XxlDeepResource xxlDeepResource) {

		// valid
		if (xxlDeepResource == null) {
			return new ResponseBuilder<String>().fail("必要参数缺失").build();
        }

		xxlDeepResourceMapper.insert(xxlDeepResource);
		return new ResponseBuilder<String>().success().build();
	}

	/**
	* 删除
	*/
	@Override
	public Response<String> delete(int id) {
		int ret = xxlDeepResourceMapper.delete(id);
		return ret>0? new ResponseBuilder<String>().success().build()
					: new ResponseBuilder<String>().fail().build() ;
	}

	/**
	* 更新
	*/
	@Override
	public Response<String> update(XxlDeepResource xxlDeepResource) {
		int ret = xxlDeepResourceMapper.update(xxlDeepResource);
		return ret>0? new ResponseBuilder<String>().success().build()
					: new ResponseBuilder<String>().fail().build() ;
	}

	/**
	* Load查询
	*/
	@Override
	public Response<XxlDeepResource> load(int id) {
		XxlDeepResource record = xxlDeepResourceMapper.load(id);
		return new ResponseBuilder<XxlDeepResource>().success(record).build();
	}

	/**
	* 分页查询
	*/
	@Override
	public PageModel<XxlDeepResource> pageList(int offset, int pagesize) {

		List<XxlDeepResource> pageList = xxlDeepResourceMapper.pageList(offset, pagesize);
		int totalCount = xxlDeepResourceMapper.pageListCount(offset, pagesize);

		// result
		PageModel<XxlDeepResource> pageModel = new PageModel<XxlDeepResource>();
		pageModel.setPageData(pageList);
		pageModel.setTotalCount(totalCount);

		return pageModel;
	}

}
