/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.user.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tech.rundeep.sis.user.entity.SisMyfriend;

/**
 * 我的书童DAO接口
 * @author rundeep
 * @version 2017-09-28
 */
@MyBatisDao
public interface SisMyfriendDao extends CrudDao<SisMyfriend> {
	
}