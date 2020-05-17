package com.xuecheng.manageCms.service;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.response.Combox;
import com.xuecheng.manageCms.dao.CmsSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("siteService")
public class SiteService {

    @Autowired
    CmsSiteRepository cmsSiteRepository;

    public List<Combox> findAllSite(){
        List<Combox> comboxList = null;
        List<CmsSite> list = cmsSiteRepository.findAll();
        if (!CollectionUtils.isEmpty(list)){
            comboxList = new CopyOnWriteArrayList<>();
            for (CmsSite site : list){
                Combox combox = new Combox();
                combox.setValue(site.getSiteId());
                combox.setText(site.getSiteName());
                comboxList.add(combox);
            }
        }
        return comboxList;
    }
}
