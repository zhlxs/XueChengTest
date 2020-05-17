package com.xuecheng.manageCms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll(){
        List<CmsPage> list = cmsPageRepository.findAll();
        System.out.println(list.size());
    }

    @Test
    public void testFindAllByExample(){
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);

        //条件对象
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("1");
        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        // 模糊查询
        //必须返回这个对象
        exampleMatcher = exampleMatcher.withMatcher("", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        List<CmsPage> content = all.getContent();
        System.out.println(content.size());
    }
}