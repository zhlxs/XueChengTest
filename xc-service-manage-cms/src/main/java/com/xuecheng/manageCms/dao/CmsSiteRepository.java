package com.xuecheng.manageCms.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CmsSiteRepository extends MongoRepository<CmsSite, String> {

}
