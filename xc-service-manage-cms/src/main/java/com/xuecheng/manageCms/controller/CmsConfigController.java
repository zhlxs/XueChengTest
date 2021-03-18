package com.xuecheng.manageCms.controller;

import com.xuecheng.api.cms.CmsConfigControllerApi;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manageCms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi
{
	@Autowired
	PageService pageService;

	@Override
	@GetMapping("/getmodel/{id}")
	public CmsConfig getmodel(@PathVariable("id") String id)
	{
		return pageService.getConfigById(id);
	}
}