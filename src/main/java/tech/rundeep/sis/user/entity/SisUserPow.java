/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.user.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户相关功能Entity
 * @author rundeep
 * @version 2017-09-26
 */
public class SisUserPow extends DataEntity<SisUserPow> {
	
	private static final long serialVersionUID = 1L;
	private SisUser sis_user;		// 用户ID 父类
	private String power;		// 科研能力
	
	public SisUserPow() {
		super();
	}

	public SisUserPow(String id){
		super(id);
	}

	public SisUserPow(SisUser sis_user){
		this.sis_user = sis_user;
	}

	@Length(min=1, max=64, message="用户ID长度必须介于 1 和 64 之间")
	public SisUser getSis_user() {
		return sis_user;
	}

	public void setSis_user(SisUser sis_user) {
		this.sis_user = sis_user;
	}
	
	@Length(min=0, max=1, message="科研能力长度必须介于 0 和 1 之间")
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	
}