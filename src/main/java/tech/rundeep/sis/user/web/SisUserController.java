/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import tech.rundeep.sis.user.entity.SisUser;
import tech.rundeep.sis.user.service.SisUserService;

/**
 * 用户相关功能Controller
 * @author rundeep
 * @version 2017-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/user/sisUser")
public class SisUserController extends BaseController {

	@Autowired
	private SisUserService sisUserService;
	
	@ModelAttribute
	public SisUser get(@RequestParam(required=false) String id) {
		SisUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sisUserService.get(id);
		}
		if (entity == null){
			entity = new SisUser();
		}
		return entity;
	}
	
	@RequiresPermissions("user:sisUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SisUser sisUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SisUser> page = sisUserService.findPage(new Page<SisUser>(request, response), sisUser); 
		model.addAttribute("page", page);
		return "sis/user/sisUserList";
	}

	@RequiresPermissions("user:sisUser:view")
	@RequestMapping(value = "form")
	public String form(SisUser sisUser, Model model) {
		model.addAttribute("sisUser", sisUser);
		return "sis/user/sisUserForm";
	}

	@RequiresPermissions("user:sisUser:edit")
	@RequestMapping(value = "save")
	public String save(SisUser sisUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sisUser)){
			return form(sisUser, model);
		}
		sisUserService.save(sisUser);
		addMessage(redirectAttributes, "保存用户功能成功");
		return "redirect:"+Global.getAdminPath()+"/user/sisUser/?repage";
	}
	
	@RequiresPermissions("user:sisUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SisUser sisUser, RedirectAttributes redirectAttributes) {
		sisUserService.delete(sisUser);
		addMessage(redirectAttributes, "删除用户功能成功");
		return "redirect:"+Global.getAdminPath()+"/user/sisUser/?repage";
	}

}