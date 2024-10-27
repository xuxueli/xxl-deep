package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.mapper.XxlDeepLogMapper;
import com.xxl.deep.admin.model.adaptor.XxlDeepLogAdaptor;
import com.xxl.deep.admin.model.dto.XxlDeepLogDTO;
import com.xxl.deep.admin.model.entity.XxlDeepLog;
import com.xxl.deep.admin.service.XxlDeepLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import com.xxl.tool.response.PageModel;

/**
* XxlDeepLog Service Impl
*
* Created by xuxueli on '2024-10-27 12:19:06'.
*/
@Service
public class XxlDeepLogServiceImpl implements XxlDeepLogService {

	@Resource
	private XxlDeepLogMapper xxlDeepLogMapper;

	/**
    * 新增
    */
	@Override
	public Response<String> insert(XxlDeepLog xxlDeepLog) {

		// valid
		if (xxlDeepLog == null) {
			return new ResponseBuilder<String>().fail("必要参数缺失").build();
        }

		xxlDeepLogMapper.insert(xxlDeepLog);
		return new ResponseBuilder<String>().success().build();
	}

	/**
	* 删除
	*/
	@Override
	public Response<String> delete(List<Integer> ids) {
		int ret = xxlDeepLogMapper.delete(ids);
		return ret>0? new ResponseBuilder<String>().success().build()
					: new ResponseBuilder<String>().fail().build() ;
	}

	/**
	* 更新
	*/
	@Override
	public Response<String> update(XxlDeepLog xxlDeepLog) {
		int ret = xxlDeepLogMapper.update(xxlDeepLog);
		return ret>0? new ResponseBuilder<String>().success().build()
					: new ResponseBuilder<String>().fail().build() ;
	}

	/**
	* Load查询
	*/
	@Override
	public Response<XxlDeepLog> load(int id) {
		XxlDeepLog record = xxlDeepLogMapper.load(id);
		return new ResponseBuilder<XxlDeepLog>().success(record).build();
	}

	/**
	* 分页查询
	*/
	@Override
	public PageModel<XxlDeepLogDTO> pageList(int type, String module, String title, int offset, int pagesize) {

		List<XxlDeepLog> pageList = xxlDeepLogMapper.pageList(offset, pagesize);
		int totalCount = xxlDeepLogMapper.pageListCount(offset, pagesize);

		List<XxlDeepLogDTO> pageListDTO = XxlDeepLogAdaptor.adaptor(pageList);

		// result
		PageModel<XxlDeepLogDTO> pageModel = new PageModel<XxlDeepLogDTO>();
		pageModel.setPageData(pageListDTO);
		pageModel.setTotalCount(totalCount);

		return pageModel;
	}

}
