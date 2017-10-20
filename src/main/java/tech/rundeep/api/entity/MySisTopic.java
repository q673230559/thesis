package tech.rundeep.api.entity;

import tech.rundeep.sis.topic.entity.SisTopic;

/**
 * 生成的SisTopic扩展类
 * @author Administrator
 *
 */
public class MySisTopic extends SisTopic{
	
	/**
	 * 用来标志当前用户参与状态0我想参与1已申请2已参与
	 */
	private int mystatus = 0;

	public int getMystatus() {
		return mystatus;
	}

	public void setMystatus(int mystatus) {
		this.mystatus = mystatus;
	}
	
	

}
