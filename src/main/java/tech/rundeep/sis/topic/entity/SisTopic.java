/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.topic.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 选题Entity
 * @author rundeep
 * @version 2017-10-08
 */
public class SisTopic extends DataEntity<SisTopic> {
	
	private static final long serialVersionUID = 1L;
	private String keyword;		// 关键字
	private String publisher;		// 发布者
	private Date startTime;		// 起始时间
	private Date endTime;		// 截止时间
	private String bidPrice;		// 标价
	private String realPrice;		// 现价
	private String sisUserId;		// 参与者
	private String status;		// 状态
	private String sort;		// 排序
	private String hotSort;		// 推荐热度
	private String flowStatus;		// 流程状态
	private List<SisTopicPro> sisTopicProList = Lists.newArrayList();		// 子表列表
	private List<SisTopicResource> sisTopicResourceList = Lists.newArrayList();		// 子表列表
	
	public SisTopic() {
		super();
	}

	public SisTopic(String id){
		super(id);
	}

	@Length(min=0, max=255, message="关键字长度必须介于 0 和 255 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=1, max=64, message="发布者长度必须介于 1 和 64 之间")
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=10, message="标价长度必须介于 0 和 10 之间")
	public String getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}
	
	@Length(min=0, max=10, message="现价长度必须介于 0 和 10 之间")
	public String getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}
	
	@Length(min=0, max=64, message="参与者长度必须介于 0 和 64 之间")
	public String getSisUserId() {
		return sisUserId;
	}

	public void setSisUserId(String sisUserId) {
		this.sisUserId = sisUserId;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=10, message="排序长度必须介于 0 和 10 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=10, message="推荐热度长度必须介于 0 和 10 之间")
	public String getHotSort() {
		return hotSort;
	}

	public void setHotSort(String hotSort) {
		this.hotSort = hotSort;
	}
	
	@Length(min=0, max=1, message="流程状态长度必须介于 0 和 1 之间")
	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}
	
	public List<SisTopicPro> getSisTopicProList() {
		return sisTopicProList;
	}

	public void setSisTopicProList(List<SisTopicPro> sisTopicProList) {
		this.sisTopicProList = sisTopicProList;
	}
	public List<SisTopicResource> getSisTopicResourceList() {
		return sisTopicResourceList;
	}

	public void setSisTopicResourceList(List<SisTopicResource> sisTopicResourceList) {
		this.sisTopicResourceList = sisTopicResourceList;
	}
}