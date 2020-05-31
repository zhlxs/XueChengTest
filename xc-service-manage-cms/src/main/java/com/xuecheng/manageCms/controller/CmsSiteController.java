package com.xuecheng.manageCms.controller;

import com.xuecheng.api.cms.CmsSiteControllerApi;
import com.xuecheng.framework.domain.cms.response.Combox;
import com.xuecheng.framework.domain.cms.response.ComboxResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.manageCms.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/site")
public class CmsSiteController implements CmsSiteControllerApi {

    @Autowired
    SiteService siteService;

    @Override
    @GetMapping("/comboxList")
    public ComboxResult findAllSite() {
        ComboxResult result = new ComboxResult();
        try {
            List<Combox> list = siteService.findAllSite();
            result.setComboxList(list);
            result.setCode(CommonCode.SUCCESS.code());
            return result;
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ComboxResult(CommonCode.FAIL.code(),"接口异常！");
        }
    }
}
