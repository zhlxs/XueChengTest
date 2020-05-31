package com.xuecheng.manageCms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manageCms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService
{
	@Autowired
	CmsPageRepository cmsPageRepository;

	/**
	 * 页面查询
	 *
	 * @param pageIndex
	 * @param pageSize
	 * @param queryPageRequest
	 * @return
	 */
	public QueryResponseResult findList(int pageIndex, int pageSize, QueryPageRequest queryPageRequest)
	{
		//自定义条件查询
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().
				withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
		CmsPage cmsPage = new CmsPage();
		//站点id
		if (StringUtils.isNotEmpty(queryPageRequest.getSiteId()))
		{
			cmsPage.setSiteId(queryPageRequest.getSiteId());
		}
		//模板id
		if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId()))
		{
			cmsPage.setTemplateId(queryPageRequest.getTemplateId());
		}
		//页面别名
		if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase()))
		{
			cmsPage.setPageAliase(queryPageRequest.getPageAliase());
		}
		//定义条件对象
		Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

		if (pageIndex <= 0)
		{
			pageIndex = 1;
		}
		if (pageSize <= 0)
		{
			pageSize = 10;
		}
		pageIndex = pageIndex - 1;
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		//自定义条件分页查询
		Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
		QueryResult queryResult = new QueryResult();
		queryResult.setList(all.getContent());
		queryResult.setTotal(all.getTotalElements());
		QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
		return queryResponseResult;
	}

	/**
	 * 新增页面
	 *
	 * @param cmsPage
	 * @return
	 */
	public CmsPageResult add(CmsPage cmsPage)
	{
		CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
		if (null != cmsPage1)
		{
			ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
		}
		if (cmsPage1 == null)
		{
			cmsPage.setPageId(null);
			cmsPageRepository.save(cmsPage);
			return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
		}
		return new CmsPageResult(CommonCode.FAIL, null);
	}

	/**
	 * 获取页面
	 *
	 * @param id
	 * @return
	 */
	public CmsPage getPageById(String id)
	{
		Optional<CmsPage> optional = cmsPageRepository.findById(id);
		if (optional.isPresent())
		{
			CmsPage cmsPage = optional.get();
			return cmsPage;
		}
		return null;
	}

	/**
	 * 更新页面
	 *
	 * @param id
	 * @param cmsPage
	 * @return
	 */
	public CmsPageResult update(String id, CmsPage cmsPage)
	{
		CmsPage oldPage = this.getPageById(id);
		if (null != oldPage)
		{
			oldPage.setTemplateId(cmsPage.getTemplateId());
			oldPage.setSiteId(cmsPage.getSiteId());
			oldPage.setPageAliase(cmsPage.getPageAliase());
			oldPage.setPageName(cmsPage.getPageName());
			oldPage.setPageWebPath(cmsPage.getPageWebPath());
			oldPage.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
			//提交修改
			cmsPageRepository.save(oldPage);
			return new CmsPageResult(CommonCode.SUCCESS, oldPage);
		}
		return new CmsPageResult(CommonCode.FAIL, null);
	}

	/**
	 * 删除页面
	 *
	 * @param id
	 * @return
	 */
	public ResponseResult delete(String id)
	{
		Optional<CmsPage> optional = cmsPageRepository.findById(id);
		if (optional.isPresent())
		{
			cmsPageRepository.deleteById(id);
			return new ResponseResult(CommonCode.SUCCESS);
		}
		return new ResponseResult(CommonCode.FAIL);
	}
}
