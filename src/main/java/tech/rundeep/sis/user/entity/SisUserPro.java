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
public class SisUserPro extends DataEntity<SisUserPro> {
	
	private static final long serialVersionUID = 1L;
	private SisUser sis_user;		// 用户ID 父类
	private String proField;		// 专业领域
	
	public SisUserPro() {
		super();
	}

	public SisUserPro(String id){
		super(id);
	}

	public SisUserPro(SisUser sis_user){
		this.sis_user = sis_user;
	}

	@Length(min=1, max=64, message="用户ID长度必须介于 1 和 64 之间")
	public SisUser getSis_user() {
		return sis_user;
	}

	public void setSis_user(SisUser sis_user) {
		this.sis_user = sis_user;
	}
	
	@Length(min=0, max=1, message="专业领域长度必须介于 0 和 1 之间")
	public String getProField() {
		return proField;
	}

	public void setProField(String proField) {
		this.proField = proField;
	}
	
}