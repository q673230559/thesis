package tech.rundeep.sis.user.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface MySisMyFriendDao {

	/**
	 * 删除一条朋友记录
	 * @param my_id
	 * @param it_id
	 * @return
	 */
	public int delete(String my_id, String it_id);
	
}
