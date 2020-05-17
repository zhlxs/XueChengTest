package com.xuecheng.manageCms.service;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.response.Combox;
import com.xuecheng.manageCms.dao.CmsTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("templateService")
public class TemplateService {

    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    public List<Combox> findAllSite(){
        List<Combox> comboxList = null;
        List<CmsTemplate> list = cmsTemplateRepository.findAll();
        if (!CollectionUtils.isEmpty(list)){
            comboxList = new CopyOnWriteArrayList<>();
            for (CmsTemplate template : list){
                Combox combox = new Combox();
                combox.setValue(template.getTemplateId());
                combox.setText(template.getTemplateName());
                comboxList.add(combox);
            }
        }
        return comboxList;
    }
}
