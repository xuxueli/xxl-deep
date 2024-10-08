package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.mapper.XxlDeepOrgMapper;
import com.xxl.deep.admin.model.dto.XxlDeepOrgDTO;
import com.xxl.deep.admin.model.entity.XxlDeepOrg;
import com.xxl.deep.admin.service.OrgService;
import com.xxl.tool.core.CollectionTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import com.xxl.tool.response.Response;
import com.xxl.tool.response.ResponseBuilder;
import com.xxl.tool.response.PageModel;

/**
* XxlDeepOrg Service Impl
*
* Created by xuxueli on '2024-09-30 15:38:21'.
*/
@Service
public class OrgServiceImpl implements OrgService {

	@Resource
	private XxlDeepOrgMapper xxlDeepOrgMapper;

	/**
    * 新增
    */
	@Override
	public Response<String> insert(XxlDeepOrg xxlDeepOrg) {

		// valid
		if (xxlDeepOrg == null) {
			return new ResponseBuilder<String>().fail("必要参数缺失").build();
        }

		xxlDeepOrgMapper.insert(xxlDeepOrg);
		return new ResponseBuilder<String>().success().build();
	}

	/**
	* 删除
	*/
	@Override
	public Response<String> delete(List<Integer> ids) {
		int ret = xxlDeepOrgMapper.delete(ids);
		return ret>0? new ResponseBuilder<String>().success().build()
					: new ResponseBuilder<String>().fail().build() ;
	}

	/**
	* 更新
	*/
	@Override
	public Response<String> update(XxlDeepOrg xxlDeepOrg) {
		int ret = xxlDeepOrgMapper.update(xxlDeepOrg);
		return ret>0? new ResponseBuilder<String>().success().build()
					: new ResponseBuilder<String>().fail().build() ;
	}

	/**
	* Load查询
	*/
	@Override
	public Response<XxlDeepOrg> load(int id) {
		XxlDeepOrg record = xxlDeepOrgMapper.load(id);
		return new ResponseBuilder<XxlDeepOrg>().success(record).build();
	}

	/**
	* 分页查询
	*/
	@Override
	public PageModel<XxlDeepOrg> pageList(int offset, int pagesize) {

		List<XxlDeepOrg> pageList = xxlDeepOrgMapper.pageList(offset, pagesize);
		int totalCount = xxlDeepOrgMapper.pageListCount(offset, pagesize);

		// result
		PageModel<XxlDeepOrg> pageModel = new PageModel<XxlDeepOrg>();
		pageModel.setPageData(pageList);
		pageModel.setTotalCount(totalCount);

		return pageModel;
	}

	@Override
	public List<XxlDeepOrgDTO> treeList(String name, int status) {
		List<XxlDeepOrg> orgDTOList = xxlDeepOrgMapper.queryOrg(name, status);
		return generateTreeList(orgDTOList);
	}

	private List<XxlDeepOrgDTO> generateTreeList(List<XxlDeepOrg> orgList) {
		List<XxlDeepOrgDTO> resultList = new ArrayList<>();
		if (CollectionTool.isEmpty(orgList)) {
			return resultList;
		}

		// collect children data
		Map<Integer, List<XxlDeepOrgDTO>> parentMap = new HashMap<>();;
		for (XxlDeepOrg org : orgList) {
			int pId = org.getParentId();

			List<XxlDeepOrgDTO> sameLevelData = parentMap.containsKey(pId)?parentMap.get(pId) :new ArrayList<>();
			sameLevelData.add(new XxlDeepOrgDTO(org, null));

			parentMap.put(pId, sameLevelData);
		}

		// fill chindren
		List<XxlDeepOrgDTO> toFillParent = parentMap.get(0);
		while (CollectionTool.isNotEmpty(toFillParent)) {
			List<XxlDeepOrgDTO> toFillParentTmp = new ArrayList<>();
			for (XxlDeepOrgDTO org : toFillParent) {
				List<XxlDeepOrgDTO> children = parentMap.get(org.getId());
				if (CollectionTool.isNotEmpty(children)) {
					org.setChildren(children);
					toFillParentTmp.addAll(children);
				}
			}
			toFillParent = toFillParentTmp;
		}

		return parentMap.get(0);
	}

	@Override
	public List<XxlDeepOrgDTO> simpleTreeList(String name, int status) {
		List<XxlDeepOrg> orgList = xxlDeepOrgMapper.queryOrg(name, status);
		List<XxlDeepOrgDTO> result = new ArrayList<>();

		for (XxlDeepOrg org : orgList) {
			XxlDeepOrgDTO resourceDTO = new XxlDeepOrgDTO(org, null);
			result.add(resourceDTO);
		}
		return result;
	}

}
