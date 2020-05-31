package com.xuecheng.manageCms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manageCms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi
{
	@Autowired
	PageService pageService;

	@Override
	@GetMapping("/list/{page}/{size}")
	public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest)
	{
		//暂时用静态数据测试
		return pageService.findList(page, size, queryPageRequest);
	}

	@Override
	@PostMapping("/add")
	public CmsPageResult add(@RequestBody CmsPage cmsPage)
	{
		return pageService.add(cmsPage);
	}

	@Override
	@GetMapping("/get/{id}")
	public CmsPage findById(@PathVariable("id") String id)
	{
		return pageService.getPageById(id);
	}

	@Override
	@PutMapping("/edit/{id}")
	public CmsPageResult edit(@PathVariable("id") String id, @RequestBody CmsPage cmsPage)
	{
		return pageService.update(id, cmsPage);
	}

	@Override
	@DeleteMapping("/del/{id}")
	public ResponseResult delete(@PathVariable("id") String id)
	{
		return pageService.delete(id);
	}
}
