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
public class SisTopicResource extends DataEntity<SisTopicResource> {
	
	private static final long serialVersionUID = 1L;
	private SisTopic sis_topic;		// 选题ID 父类
	private String resourceUrl;		// 资源地址
	private String flowStatus;		// 流程状态
	private String status;		// 状态
	
	public SisTopicResource() {
		super();
	}

	public SisTopicResource(String id){
		super(id);
	}

	public SisTopicResource(SisTopic sis_topic){
		this.sis_topic = sis_topic;
	}

	@Length(min=1, max=64, message="选题ID长度必须介于 1 和 64 之间")
	public SisTopic getSis_topic() {
		return sis_topic;
	}

	public void setSis_topic(SisTopic sis_topic) {
		this.sis_topic = sis_topic;
	}
	
	@Length(min=0, max=255, message="资源地址长度必须介于 0 和 255 之间")
	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	
	@Length(min=0, max=1, message="流程状态长度必须介于 0 和 1 之间")
	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}