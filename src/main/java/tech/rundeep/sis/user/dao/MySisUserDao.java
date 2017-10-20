package tech.rundeep.sis.user.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import tech.rundeep.sis.user.entity.SisUser;

@MyBatisDao
public interface MySisUserDao{
	
	/**
	 * 按照用户名和密码查询用户
	 * @param usr
	 * @param pwd
	 * @return
	 */
	public SisUser selectUserByUNamePwd(String usr,String pwd);

	/**
	 * 按照专业ID查询用户列表
	 * @param pro_field
	 * @return
	 */
	public List<SisUser> selectUsersByPro(char pro_field);

	/**
	 * 查找用户列表按照我的ID(我的好友)
	 * @param id
	 * @return
	 */
	public List<SisUser> selectUsersByMyId(String id);

	/**
	 * 查询是否我们是好友
	 * @param my_id
	 * @param it_id
	 * @return
	 */
	public SisUser selectUserByMy_idIt_id(String my_id, String it_id);
}
