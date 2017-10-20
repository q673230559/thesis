/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.user.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 我的书童Entity
 * @author rundeep
 * @version 2017-09-28
 */
public class SisMyfriend extends DataEntity<SisMyfriend> {
	
	private static final long serialVersionUID = 1L;
	private String friendship;		// 亲密度
	private String status;		// 拉黑否
	private String myId;		// 我的ID
	private String friendId;		// 好友ID
	
	public SisMyfriend() {
		super();
	}

	public SisMyfriend(String id){
		super(id);
	}

	@Length(min=0, max=10, message="亲密度长度必须介于 0 和 10 之间")
	public String getFriendship() {
		return friendship;
	}

	public void setFriendship(String friendship) {
		this.friendship = friendship;
	}
	
	@Length(min=0, max=1, message="拉黑否长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="我的ID长度必须介于 0 和 64 之间")
	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}
	
	@Length(min=0, max=64, message="好友ID长度必须介于 0 和 64 之间")
	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	
}