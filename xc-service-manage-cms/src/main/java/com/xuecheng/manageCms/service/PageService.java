package com.xuecheng.manageCms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manageCms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageService
{
    @Autowired
    CmsPageRepository cmsPageRepository;

    /**
     * 页面查询
     * @param pageIndex
     * @param pageSize
     * @param queryPageRequest
     * @return
     */
    public QueryResponseResult findList(int pageIndex, int pageSize, QueryPageRequest queryPageRequest)
    {
        if (pageIndex <= 0){
            pageIndex = 1;
        }
        if (pageSize <= 0){
            pageSize = 10;
        }
        pageIndex = pageIndex - 1;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }
}
