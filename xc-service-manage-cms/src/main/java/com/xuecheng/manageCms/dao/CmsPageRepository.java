package com.xuecheng.manageCms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage, String>
{
    /*
    同Spring Data JPA一样Spring Data mongodb也提供自定义方法的规则，如下：
    按照findByXXX，findByXXXAndYYY、countByXXXAndYYY等规则定义方法，实现查询操作。
    */
    //根据页面名称查询
    CmsPage findByPageName(String pageName);
    //根据页面名称和类型查询
    CmsPage findByPageNameAndPageType(String pageName,String pageType);
    //根据站点和页面类型查询记录数
    int countBySiteIdAndPageType(String siteId,String pageType);
    //根据站点和页面类型分页查询
    Page<CmsPage> findBySiteIdAndPageType(String siteId, String pageType, Pageable pageable);

    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);

}
/**
 * 关于Optional：
 * Optional是jdk1.8引入的类型，Optional是一个容器对象，它包括了我们需要的对象，使用isPresent方法判断所包
 * 含对象是否为空，isPresent方法返回false则表示Optional包含对象为空，否则可以使用get()取出对象进行操作。
 * Optional的优点是：
 * 1、提醒你非空判断。
 * 2、将对象非空检测标准化。
 */