/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.topic.web;

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
import tech.rundeep.sis.topic.entity.SisTopic;
import tech.rundeep.sis.topic.service.SisTopicService;

/**
 * 选题Controller
 * @author rundeep
 * @version 2017-10-08
 */
@Controller
@RequestMapping(value = "${adminPath}/topic/sisTopic")
public class SisTopicController extends BaseController {

	@Autowired
	private SisTopicService sisTopicService;
	
	@ModelAttribute
	public SisTopic get(@RequestParam(required=false) String id) {
		SisTopic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sisTopicService.get(id);
		}
		if (entity == null){
			entity = new SisTopic();
		}
		return entity;
	}
	
	@RequiresPermissions("topic:sisTopic:view")
	@RequestMapping(value = {"list", ""})
	public String list(SisTopic sisTopic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SisTopic> page = sisTopicService.findPage(new Page<SisTopic>(request, response), sisTopic); 
		model.addAttribute("page", page);
		return "sis/topic/sisTopicList";
	}

	@RequiresPermissions("topic:sisTopic:view")
	@RequestMapping(value = "form")
	public String form(SisTopic sisTopic, Model model) {
		model.addAttribute("sisTopic", sisTopic);
		return "sis/topic/sisTopicForm";
	}

	@RequiresPermissions("topic:sisTopic:edit")
	@RequestMapping(value = "save")
	public String save(SisTopic sisTopic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sisTopic)){
			return form(sisTopic, model);
		}
		sisTopicService.save(sisTopic);
		addMessage(redirectAttributes, "保存选题成功");
		return "redirect:"+Global.getAdminPath()+"/topic/sisTopic/?repage";
	}
	
	@RequiresPermissions("topic:sisTopic:edit")
	@RequestMapping(value = "delete")
	public String delete(SisTopic sisTopic, RedirectAttributes redirectAttributes) {
		sisTopicService.delete(sisTopic);
		addMessage(redirectAttributes, "删除选题成功");
		return "redirect:"+Global.getAdminPath()+"/topic/sisTopic/?repage";
	}

}