package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.response.ComboxResult;
import io.swagger.annotations.Api;

@Api("cms模板管理接口")
public interface CmsTemplateControllerApi {
    /**
     * @description:模板下拉列表
     * @author: HSL
     * @date: 2020/5/17
     */
    public ComboxResult findAllSite();
}
