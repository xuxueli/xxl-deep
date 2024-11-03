package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.mapper.XxlDeepMessageMapper;
import com.xxl.deep.admin.model.adaptor.XxlDeepMesssageAdaptor;
import com.xxl.deep.admin.model.dto.LoginUserDTO;
import com.xxl.deep.admin.model.dto.XxlDeepMessageDTO;
import com.xxl.deep.admin.model.entity.XxlDeepMessage;
import com.xxl.deep.admin.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import com.xxl.tool.response.PageModel;

/**
* XxlDeepMessage Service Impl
*
* Created by xuxueli on '2024-11-03 11:03:29'.
*/
@Service
public class MessageServiceImpl implements MessageService {

	@Resource
	private XxlDeepMessageMapper xxlDeepMessageMapper;

	/**
    * 新增
    */
	@Override
	public Response<String> insert(XxlDeepMessage xxlDeepMessage, LoginUserDTO loginUser) {

		// valid
		if (xxlDeepMessage == null) {
			return new ResponseBuilder<String>().fail("必要参数缺失").build();
        }
		xxlDeepMessage.setSender(loginUser.getUsername());

		xxlDeepMessageMapper.insert(xxlDeepMessage);
		return new ResponseBuilder<String>().success().build();
	}

	/**
	* 删除
	*/
	@Override
	public Response<String> delete(List<Integer> ids) {
		int ret = xxlDeepMessageMapper.delete(ids);
		return ret>0? new ResponseBuilder<String>().success().build()
					: new ResponseBuilder<String>().fail().build() ;
	}

	/**
	* 更新
	*/
	@Override
	public Response<String> update(XxlDeepMessage xxlDeepMessage) {
		int ret = xxlDeepMessageMapper.update(xxlDeepMessage);
		return ret>0? new ResponseBuilder<String>().success().build()
					: new ResponseBuilder<String>().fail().build() ;
	}

	/**
	* Load查询
	*/
	@Override
	public Response<XxlDeepMessage> load(int id) {
		XxlDeepMessage record = xxlDeepMessageMapper.load(id);
		return new ResponseBuilder<XxlDeepMessage>().success(record).build();
	}

	/**
	* 分页查询
	*/
	@Override
	public PageModel<XxlDeepMessageDTO> pageList(int status, String title, int offset, int pagesize) {

		List<XxlDeepMessage> pageList = xxlDeepMessageMapper.pageList(status, title, offset, pagesize);
		int totalCount = xxlDeepMessageMapper.pageListCount(status, title, offset, pagesize);

		// adaptor
		List<XxlDeepMessageDTO> dtoList = XxlDeepMesssageAdaptor.adaptor(pageList);

		// result
		PageModel<XxlDeepMessageDTO> pageModel = new PageModel<XxlDeepMessageDTO>();
		pageModel.setPageData(dtoList);
		pageModel.setTotalCount(totalCount);

		return pageModel;
	}

}
