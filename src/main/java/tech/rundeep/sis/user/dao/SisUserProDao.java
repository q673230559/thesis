/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.user.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tech.rundeep.sis.user.entity.SisUserPro;

/**
 * 用户相关功能DAO接口
 * @author rundeep
 * @version 2017-09-26
 */
@MyBatisDao
public interface SisUserProDao extends CrudDao<SisUserPro> {
	
}