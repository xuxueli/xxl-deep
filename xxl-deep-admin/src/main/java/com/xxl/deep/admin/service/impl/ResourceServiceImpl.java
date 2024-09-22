package com.xxl.deep.admin.service.impl;

import com.xxl.deep.admin.mapper.XxlDeepResourceMapper;
import com.xxl.deep.admin.model.dto.XxlDeepResourceDTO;
import com.xxl.deep.admin.model.entity.XxlDeepResource;
import com.xxl.deep.admin.service.ResourceService;
import com.xxl.tool.core.CollectionTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

		// limit： 按钮只能是叶子节点 + 资源类型不能变化；

		xxlDeepResourceMapper.insert(xxlDeepResource);
		return new ResponseBuilder<String>().success().build();
	}

	/**
	* 删除
	*/
	@Override
	public Response<String> delete(List<Integer> ids) {
		int ret = xxlDeepResourceMapper.delete(ids);
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

	@Override
	public List<XxlDeepResourceDTO> treeList(String name, int status) {
		List<XxlDeepResource> resourceList = xxlDeepResourceMapper.queryResource(name, status);
		return generateTreeList(resourceList);
	}

	@Override
	public List<XxlDeepResourceDTO> simpleTreeList(String name, int status) {
		List<XxlDeepResource> resourceList = xxlDeepResourceMapper.queryResource(name, status);
		List<XxlDeepResourceDTO> result = new ArrayList<>();

		for (XxlDeepResource resource : resourceList) {
			XxlDeepResourceDTO resourceDTO = new XxlDeepResourceDTO(resource, null);
			resourceDTO.setUrl(null);
			resourceDTO.setIcon(null);
			result.add(resourceDTO);
		}
		return result;
	}

	private List<XxlDeepResourceDTO> generateTreeList(List<XxlDeepResource> resourceList) {
		List<XxlDeepResourceDTO> resultList = new ArrayList<>();
		if (CollectionTool.isEmpty(resourceList)) {
			return resultList;
		}

		// collect children data
		Map<Integer, List<XxlDeepResourceDTO>> parentMap = new HashMap<>();;
		for (XxlDeepResource resource : resourceList) {
			int pId = resource.getParentId();

			List<XxlDeepResourceDTO> sameLevelData = parentMap.containsKey(pId)?parentMap.get(pId) :new ArrayList<>();
			sameLevelData.add(new XxlDeepResourceDTO(resource, null));

			parentMap.put(pId, sameLevelData);
		}

		// fill chindren
		List<XxlDeepResourceDTO> toFillParent = parentMap.get(0);
		while (CollectionTool.isNotEmpty(toFillParent)) {
			List<XxlDeepResourceDTO> toFillParentTmp = new ArrayList<>();
			for (XxlDeepResourceDTO resource : toFillParent) {
				List<XxlDeepResourceDTO> children = parentMap.get(resource.getId());
				if (CollectionTool.isNotEmpty(children)) {
					resource.setChildren(children);
					toFillParentTmp.addAll(children);
				}
			}
			toFillParent = toFillParentTmp;
		}

		return parentMap.get(0);
	}

}
