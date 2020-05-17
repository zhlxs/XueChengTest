package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;

@Api("cms页面管理接口")
public interface CmsPageControllerApi {
    //页面查询
    public QueryResponseResult findList(int pageIndex, int pageSize, QueryPageRequest queryPageRequest);
    // 新增页面
    public CmsPageResult add(CmsPage cmsPage);
}
