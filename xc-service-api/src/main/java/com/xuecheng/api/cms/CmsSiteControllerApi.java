package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.response.ComboxResult;
import io.swagger.annotations.Api;

@Api("cms站点管理接口")
public interface CmsSiteControllerApi {
    /**
     * @description:站点列表
     * @author: HSL
     * @date: 2020/5/17
     */
    public ComboxResult findAllSite();
}
