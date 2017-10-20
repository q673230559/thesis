/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.find.web;

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
import tech.rundeep.sis.find.entity.SisFind;
import tech.rundeep.sis.find.service.SisFindService;

/**
 * 主子表生成Controller
 * @author rundeep
 * @version 2017-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/find/sisFind")
public class SisFindController extends BaseController {

	@Autowired
	private SisFindService sisFindService;
	
	@ModelAttribute
	public SisFind get(@RequestParam(required=false) String id) {
		SisFind entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sisFindService.get(id);
		}
		if (entity == null){
			entity = new SisFind();
		}
		return entity;
	}
	
	@RequiresPermissions("find:sisFind:view")
	@RequestMapping(value = {"list", ""})
	public String list(SisFind sisFind, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SisFind> page = sisFindService.findPage(new Page<SisFind>(request, response), sisFind); 
		model.addAttribute("page", page);
		return "sis/find/sisFindList";
	}

	@RequiresPermissions("find:sisFind:view")
	@RequestMapping(value = "form")
	public String form(SisFind sisFind, Model model) {
		model.addAttribute("sisFind", sisFind);
		return "sis/find/sisFindForm";
	}

	@RequiresPermissions("find:sisFind:edit")
	@RequestMapping(value = "save")
	public String save(SisFind sisFind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sisFind)){
			return form(sisFind, model);
		}
		sisFindService.save(sisFind);
		addMessage(redirectAttributes, "保存主子表成功");
		return "redirect:"+Global.getAdminPath()+"/find/sisFind/?repage";
	}
	
	@RequiresPermissions("find:sisFind:edit")
	@RequestMapping(value = "delete")
	public String delete(SisFind sisFind, RedirectAttributes redirectAttributes) {
		sisFindService.delete(sisFind);
		addMessage(redirectAttributes, "删除主子表成功");
		return "redirect:"+Global.getAdminPath()+"/find/sisFind/?repage";
	}

}