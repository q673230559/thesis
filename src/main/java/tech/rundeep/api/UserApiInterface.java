package tech.rundeep.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import tech.rundeep.api.entity.Result;

public interface UserApiInterface {

	/**
	 * 登录
	 * @param usr
	 * @param pwd
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisLogin(String userName,String password,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 注册
	 * @param nickName
	 * @param phoneNumber
	 * @param validateCode
	 * @param Password
	 * @param passwordAgain
	 * @param email
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisSign(String nickName, String phoneNumber, String validateCode, String Password,
			String passwordAgain ,String email,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisLogout(HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 按照专业获取用户列表
	 * @param pro_field
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisListByPro(char pro_field, HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 按照id查寻某个人的信息
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisSelectOneById(String id,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 查找我的书童
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisSelectMyFriendList(String id,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 查询是否是我的书童（我们是否已经取得过联系）
	 * @param id
	 * @param it_id
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisSelectIsMyFriend(String my_id,String it_id,HttpServletRequest request,HttpServletResponse response);

	/**
	 * 添加为好友（书童）
	 * @param my_id
	 * @param it_id
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisAddMyFriend(String my_id,String it_id,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 删除我的好友（好友）
	 * @param it_id
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisDelMyFriend(String my_id, String it_id,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 编辑头像
	 * @param head_pic
	 * @param request
	 * @param response
	 * @return
	 */
	public Result SisEdiHeadPic(MultipartFile head_pic,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 找回密码
	 * @param Password
	 * @param passwordAgain
	 * @param email
	 * @param email_code
	 * @return
	 */
	public Result SisReSetPwd(String Password,String passwordAgain ,String email,String email_code);
}
