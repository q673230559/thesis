package tech.rundeep.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tech.rundeep.api.entity.Result;

public interface ToplicApiInterface {
	
	/**
	 * 推荐列表
	 * @param currentPage
	 * @param count
	 * @param request
	 * @param response
	 * @return
	 */
	public Result recommend(int currentPage,int count,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 我参与的列表
	 * @param currentPage
	 * @param count
	 * @param request
	 * @param response
	 * @return
	 */
	public Result MyParticipated(int currentPage,int count,HttpServletRequest request,HttpServletResponse response);

	/**
	 * 我发布的
	 * @param currnetPage
	 * @param count
	 * @param request
	 * @param response
	 * @return
	 */
	public Result MyPublished(int currentPage,int count,HttpServletRequest request,HttpServletResponse response);

	/**
	 * 已完成
	 * @param currentPage
	 * @param count
	 * @param request
	 * @param response
	 * @return
	 */
	public Result Accomplish(int currentPage,int count,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 点击我想参与
	 * @param TopicID
	 * @param request
	 * @param response
	 * @return
	 */
	public Result IWantparticipate(String topicID,HttpServletRequest request,HttpServletResponse response);

	/**
	 * 选题详情
	 * @param topicID
	 * @param request
	 * @param response
	 * @return
	 */
	public Result TopicInfo(String topicID,HttpServletRequest request,HttpServletResponse response);
	
	
}
