/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.topic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 选题Entity
 * @author rundeep
 * @version 2017-10-08
 */
public class SisTopicPro extends DataEntity<SisTopicPro> {
	
	private static final long serialVersionUID = 1L;
	private SisTopic sis_topic;		// 选题ID 父类
	private String proField;		// 专业领域
	
	public SisTopicPro() {
		super();
	}

	public SisTopicPro(String id){
		super(id);
	}

	public SisTopicPro(SisTopic sis_topic){
		this.sis_topic = sis_topic;
	}

	@Length(min=1, max=64, message="选题ID长度必须介于 1 和 64 之间")
	public SisTopic getSis_topic() {
		return sis_topic;
	}

	public void setSis_topic(SisTopic sis_topic) {
		this.sis_topic = sis_topic;
	}
	
	@Length(min=1, max=200, message="专业领域长度必须介于 1 和 200 之间")
	public String getProField() {
		return proField;
	}

	public void setProField(String proField) {
		this.proField = proField;
	}
	
}