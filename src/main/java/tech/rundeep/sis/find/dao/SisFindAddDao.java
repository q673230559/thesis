/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.find.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tech.rundeep.sis.find.entity.SisFindAdd;

/**
 * 主子表生成DAO接口
 * @author rundeep
 * @version 2017-09-20
 */
@MyBatisDao
public interface SisFindAddDao extends CrudDao<SisFindAdd> {
	
}