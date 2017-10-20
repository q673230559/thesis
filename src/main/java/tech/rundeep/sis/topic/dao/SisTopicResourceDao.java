/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.topic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tech.rundeep.sis.topic.entity.SisTopicResource;

/**
 * 选题DAO接口
 * @author rundeep
 * @version 2017-10-08
 */
@MyBatisDao
public interface SisTopicResourceDao extends CrudDao<SisTopicResource> {
	
}