package tech.rundeep.api;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tech.rundeep.api.common.P;
import tech.rundeep.api.entity.Result;
import tech.rundeep.sis.user.dao.MySisMyFriendDao;
import tech.rundeep.sis.user.dao.MySisUserDao;
import tech.rundeep.sis.user.dao.SisMyfriendDao;
import tech.rundeep.sis.user.dao.SisUserDao;
import tech.rundeep.sis.user.entity.SisMyfriend;
import tech.rundeep.sis.user.entity.SisUser;

@Api(value="用户相关接口")
@Controller
public class UserApi extends BaseController implements UserApiInterface{
	
	@Autowired
	private SisUserDao sisUserDao;
	@Autowired
	private MySisUserDao mySisUserDao;
	@Autowired
	private SisMyfriendDao sisMyfriendDao;
	@Autowired
	private MySisMyFriendDao mySisMyFriendDao;
	
	@ApiOperation(value = "登录",notes="登录")
	@RequestMapping(value="/sis_login",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisLogin(@RequestParam String userName, @RequestParam String password, HttpServletRequest request,
			HttpServletResponse response) {
		SisUser user = mySisUserDao.selectUserByUNamePwd(userName, password);
		if(user == null){
			return new Result(P.FAILURE, "用户或密码错误", null);
		}
		return new Result(P.SUCCESS, "登录成功", user);
	}

	@ApiOperation(value = "注册",notes="注册")
	@RequestMapping(value="/sis_sign",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisSign(@RequestParam String nickName,@RequestParam String phoneNumber,@RequestParam String validateCode,@RequestParam String Password,
			@RequestParam String passwordAgain, @RequestParam String email, HttpServletRequest request, HttpServletResponse response) {
		if(!"123321".equals(validateCode)){
			return new Result(P.FAILURE, "验证码错误",null);
		}else if (!"".equals(Password) && !Password.equals(passwordAgain)){
			return new Result(P.FAILURE, "两次密码不一致",null);
		}else if(!"".equals(Password)){
			SisUser entity = new SisUser();
			entity.setId(IdGen.uuid());
			entity.setNickname(nickName);
			entity.setPhonenumber(phoneNumber);
			entity.setPassword(Password);
			entity.setEmail(email);
			entity.setCreateDate(new Date());
			entity.setCreateBy(new User("1"));
			entity.setUpdateBy(new User("1"));
			entity.setUpdateDate(new Date());
			entity.setDelFlag("0");
			entity.setBalance(0);
			entity.setWithdrawBalance(0);
			entity.setStatus("1");
			entity.setExp(10);
			sisUserDao.insert(entity);
			return new Result(P.SUCCESS, "注册成功",entity);
		}
		return new Result(P.FAILURE, "系统异常",null);
	}

	@Deprecated
	@ApiOperation(value = "注销",notes="注销")
	@RequestMapping(value="/sis_logout",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisLogout(HttpServletRequest request, HttpServletResponse response) {
		if(!isLogin(request, response)){
			return new Result(P.FAILURE, "未登录",null);
		}
		request.getSession().removeAttribute("sisuser");
			return new Result(P.SUCCESS, "注销成功",null);
	}

	@ApiOperation(value = "按照专业ID获取用户列表",notes="按照专业ID获取用户列表")
	@RequestMapping(value="/sis_classifying_user",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisListByPro(@RequestParam char pro_field, HttpServletRequest request, HttpServletResponse response) {
		List<SisUser> users = mySisUserDao.selectUsersByPro(pro_field);
		if (users == null){
			return new Result(P.FAILURE, "查询失败",null);
		}
		return new Result(P.SUCCESS, "查询成功", users);
	}
	
	@ApiOperation(value = "按照ID获取用户",notes="按照ID获取用户")
	@RequestMapping(value="/sis_get_user",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisSelectOneById(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
		SisUser user = sisUserDao.get(id);
		if (user == null){
			return new Result(P.FAILURE, "查询失败",null);
		}
		return new Result(P.SUCCESS, "查询成功", user);
	}

	@ApiOperation(value = "按照我的ID获取用户列表(查询我的书童)",notes="按照我的ID获取用户列表(查询我的书童)")
	@RequestMapping(value="/sis_myfrineds",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisSelectMyFriendList(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
		if(!isLogin(request, response)){
			return new Result(P.FAILURE, "未登录",null);
		}
		List<SisUser> users = mySisUserDao.selectUsersByMyId(id);
		return new Result(P.SUCCESS, "查询成功", users);
	}

	@ApiOperation(value = "查询是否是我的书童（我们是否已经取得过联系）",notes="查询是否是我的书童（我们是否已经取得过联系）")
	@RequestMapping(value="/sis_is_myfriend",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisSelectIsMyFriend(@RequestParam String my_id, @RequestParam String it_id, HttpServletRequest request,
			HttpServletResponse response) {
		if(!isLogin(request, response)){
			return new Result(P.FAILURE, "未登录",null);
		}
		SisUser user = mySisUserDao.selectUserByMy_idIt_id(my_id,it_id);
		if (user == null){
			return new Result(P.FAILURE, "不是我的好友",null);
		}
		return new Result(P.SUCCESS, "是我的好友", user);
	}
	
	@ApiOperation(value = "添加书童",notes="添加书童")
	@RequestMapping(value="/sis_add_myfriend",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisAddMyFriend(@RequestParam String my_id, @RequestParam String it_id, HttpServletRequest request,
			HttpServletResponse response) {
		if(!isLogin(request, response)){
			return new Result(P.FAILURE, "未登录",null);
		}
		SisMyfriend entity = new SisMyfriend();
		entity.setCreateDate(new Date());
		entity.setCreateBy(new User("1"));
		entity.setUpdateBy(new User("1"));
		entity.setUpdateDate(new Date());
		entity.setDelFlag("0");
		entity.setId(IdGen.uuid());
		entity.setMyId(my_id);
		entity.setFriendId(it_id);
		entity.setFriendship("10");
		entity.setStatus("0");
		int i = sisMyfriendDao.insert(entity);
		if (i > 0){
			return new Result(P.SUCCESS, "添加好友成功", entity);
		}
		return new Result(P.FAILURE, "添加好友失败",null);
	}
	
	@ApiOperation(value = "删除我的书童",notes="删除我的书童")
	@RequestMapping(value="/sis_del_myfriend",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisDelMyFriend(@RequestParam String my_id, @RequestParam String it_id, HttpServletRequest request, HttpServletResponse response) {
		if(!isLogin(request, response)){
			return new Result(P.FAILURE, "未登录",null);
		}
		int i = mySisMyFriendDao.delete(my_id,it_id);
		
		if (i > 0){
			return new Result(P.SUCCESS, "删除好友成功",null);
		}
		return new Result(P.FAILURE, "删除好友失败",null);
	}
	
	@ApiOperation(value = "编辑头像",notes="编辑头像")
	@RequestMapping(value="/sis_edi_headpic",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisEdiHeadPic(@RequestParam MultipartFile head_pic, HttpServletRequest request, HttpServletResponse response) {
		if(!isLogin(request, response)){
			return new Result(P.FAILURE, "未登录",null);
		}
		String realFileName = head_pic.getOriginalFilename(); 
		String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1).toLowerCase();
		String saveUrl ="";
		realFileName = IdGen.uuid() +"."+suffix;
		String destDir = request.getSession().getServletContext().getRealPath("/")+"/";
		/**
	     * 文件大小拦截，不能超过5M
	     */
	    if(head_pic.getSize() > 1024*1024 * 5){
	    	return new Result(P.FAILURE, "超过大小限制",null);
	    }else if(",gif,jpg,jpeg,png,bmp,ico".indexOf(suffix)<0){
	    	 //检查扩展名
	    	return new Result(P.FAILURE, "格式不正确",null);
	    }else{
	    	saveUrl +="userfiles/1/files/user/sisUser/";
		    //保存  
	        try {
	        	 if(!new File(destDir+saveUrl).exists()){  
	        		 new File(destDir+saveUrl).mkdirs();  
		 	     }  
	        	File targetFile = new File(destDir+saveUrl,realFileName);  
	 	        head_pic.transferTo(targetFile);
	        } catch (Exception e) {  
	        	return new Result(P.FAILURE, "上传失败",null);
	        } 
	        
	    }
	    SisUser entity = sisUserDao.get(((SisUser)request.getSession().getAttribute("sisuser")).getId());
	    entity.setHeadPic("|/thesis/"+saveUrl+realFileName);
	    sisUserDao.update(entity);
		return new Result(P.SUCCESS, "上传成功", entity);
	}
	
	
	@ApiOperation(value = "重置密码",notes="重置密码")
	@RequestMapping(value="/sis_reset_pwd",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result SisReSetPwd(@RequestParam String Password, @RequestParam String passwordAgain, @RequestParam String email, @RequestParam String email_code) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
