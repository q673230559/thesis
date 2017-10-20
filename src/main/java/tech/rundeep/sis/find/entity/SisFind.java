/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.find.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 主子表生成Entity
 * @author rundeep
 * @version 2017-09-20
 */
public class SisFind extends DataEntity<SisFind> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String modType;		// 模块类型
	private String resType;		// 资源类型
	private String bidPrice;		// 标价
	private String realPrice;		// 现价
	private String content;		// 内容
	private String picUrl;		// 封面地址
	private String vidUrl;		// 视频
	private String status;		// 状态
	private String isFree;		// 是否免费
	private String isHot;		// 是否热门
	private String sort;		// 排序
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private List<SisFindAdd> sisFindAddList = Lists.newArrayList();		// 子表列表
	
	public SisFind() {
		super();
	}

	public SisFind(String id){
		super(id);
	}

	@Length(min=0, max=50, message="标题长度必须介于 0 和 50 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1, message="模块类型长度必须介于 0 和 1 之间")
	public String getModType() {
		return modType;
	}

	public void setModType(String modType) {
		this.modType = modType;
	}
	
	@Length(min=0, max=1, message="资源类型长度必须介于 0 和 1 之间")
	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="封面地址长度必须介于 0 和 255 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=255, message="视频长度必须介于 0 和 255 之间")
	public String getVidUrl() {
		return vidUrl;
	}

	public void setVidUrl(String vidUrl) {
		this.vidUrl = vidUrl;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="是否免费长度必须介于 0 和 1 之间")
	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	
	@Length(min=0, max=1, message="是否热门长度必须介于 0 和 1 之间")
	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}
	
	@Length(min=0, max=10, message="排序长度必须介于 0 和 10 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
	public List<SisFindAdd> getSisFindAddList() {
		return sisFindAddList;
	}

	public void setSisFindAddList(List<SisFindAdd> sisFindAddList) {
		this.sisFindAddList = sisFindAddList;
	}
}