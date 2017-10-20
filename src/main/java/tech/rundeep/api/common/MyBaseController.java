package tech.rundeep.api.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 我的基本控制类
 * @author Administrator
 *
 */
public class MyBaseController {
	
	/**
	 * 验证是否登录
	 * @param request
	 * @param response
	 */
	public Boolean isLogin(HttpServletRequest request, HttpServletResponse response){
		return request.getSession().getAttribute("sisuser") != null? true:false;
	}
}
