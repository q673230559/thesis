/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.user.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户相关功能Entity
 * @author rundeep
 * @version 2017-09-26
 */
public class SisUser extends DataEntity<SisUser> {
	
	private static final long serialVersionUID = 1L;
	private String nickname;		// 用户昵称
	private String phonenumber;		// 手机号码
	private String email;		// 邮箱
	private String password;		// 密码
	private String degree;		// 最高学位
	private Integer balance;		// 余额
	private Integer withdrawBalance;		// 可提现余额
	private String status;		// 状态
	private String headPic;		// 头像照片
	private Integer exp;		// 经验值
	private List<SisUserPow> sisUserPowList = Lists.newArrayList();		// 子表列表
	private List<SisUserPro> sisUserProList = Lists.newArrayList();		// 子表列表
	
	public SisUser() {
		super();
	}

	public SisUser(String id){
		super(id);
	}

	@Length(min=0, max=30, message="用户昵称长度必须介于 0 和 30 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=11, message="手机号码长度必须介于 0 和 11 之间")
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	@Length(min=0, max=30, message="邮箱长度必须介于 0 和 30 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=100, message="密码长度必须介于 0 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=1, message="最高学位长度必须介于 0 和 1 之间")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	public Integer getWithdrawBalance() {
		return withdrawBalance;
	}

	public void setWithdrawBalance(Integer withdrawBalance) {
		this.withdrawBalance = withdrawBalance;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="头像照片长度必须介于 0 和 255 之间")
	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	
	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}
	
	public List<SisUserPow> getSisUserPowList() {
		return sisUserPowList;
	}

	public void setSisUserPowList(List<SisUserPow> sisUserPowList) {
		this.sisUserPowList = sisUserPowList;
	}
	public List<SisUserPro> getSisUserProList() {
		return sisUserProList;
	}

	public void setSisUserProList(List<SisUserPro> sisUserProList) {
		this.sisUserProList = sisUserProList;
	}
}