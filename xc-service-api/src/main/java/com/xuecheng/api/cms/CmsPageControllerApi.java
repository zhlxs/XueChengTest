package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("cms页面管理接口")
public interface CmsPageControllerApi
{
	//页面查询
	public QueryResponseResult findList(int pageIndex, int pageSize, QueryPageRequest queryPageRequest);

	// 新增页面
	public CmsPageResult add(CmsPage cmsPage);

	/**
	 * 根据ID查询页面信息
	 *
	 * @param id
	 * @return
	 */
	public CmsPage findById(String id);

	/**
	 * 修改页面
	 *
	 * @param id
	 * @param cmsPage
	 * @return
	 */
	public CmsPageResult edit(String id, CmsPage cmsPage);

	/**
	 * 删除页面
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("删除页面")
	public ResponseResult delete(String id);
}
