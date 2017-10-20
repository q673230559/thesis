/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.find.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 主子表生成Entity
 * @author rundeep
 * @version 2017-09-20
 */
public class SisFindAdd extends DataEntity<SisFindAdd> {
	
	private static final long serialVersionUID = 1L;
	private SisFind sis_find;		// 发现编号 父类
	private String resTitle;		// 资源标题
	private String resAddType;		// 资源类型
	private String resUrl;		// 资源地址
	private String picUrl;		// 资源封面地址
	private String resContent;		// 内容
	private String resStatus;		// 状态
	private Integer resSort;		// 排序
	
	public SisFindAdd() {
		super();
	}

	public SisFindAdd(String id){
		super(id);
	}

	public SisFindAdd(SisFind sis_find){
		this.sis_find = sis_find;
	}

	@Length(min=1, max=64, message="发现编号长度必须介于 1 和 64 之间")
	public SisFind getSis_find() {
		return sis_find;
	}

	public void setSis_find(SisFind sis_find) {
		this.sis_find = sis_find;
	}
	
	@Length(min=0, max=50, message="资源标题长度必须介于 0 和 50 之间")
	public String getResTitle() {
		return resTitle;
	}

	public void setResTitle(String resTitle) {
		this.resTitle = resTitle;
	}
	
	@Length(min=0, max=1, message="资源类型长度必须介于 0 和 1 之间")
	public String getResAddType() {
		return resAddType;
	}

	public void setResAddType(String resAddType) {
		this.resAddType = resAddType;
	}
	
	@Length(min=0, max=255, message="资源地址长度必须介于 0 和 255 之间")
	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	
	@Length(min=0, max=255, message="资源封面地址长度必须介于 0 和 255 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=64, message="内容长度必须介于 0 和 64 之间")
	public String getResContent() {
		return resContent;
	}

	public void setResContent(String resContent) {
		this.resContent = resContent;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getResStatus() {
		return resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}
	
	public Integer getResSort() {
		return resSort;
	}

	public void setResSort(Integer resSort) {
		this.resSort = resSort;
	}
	
}