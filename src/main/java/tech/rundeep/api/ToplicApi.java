package tech.rundeep.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tech.rundeep.api.common.P;
import tech.rundeep.api.entity.MySisTopic;
import tech.rundeep.api.entity.Result;
import tech.rundeep.sis.topic.dao.MySisTopicDao;
import tech.rundeep.sis.topic.dao.SisTopicDao;
import tech.rundeep.sis.topic.entity.SisTopic;

@Api(value="选题相关接口")
@Controller
public class ToplicApi extends BaseController implements ToplicApiInterface{

	@Autowired
	private SisTopicDao sisTopicDao;
	
	@Autowired
	private MySisTopicDao mySisTopicDao;
	
	@ApiOperation(value = "推荐列表",notes="推荐列表")
	@RequestMapping(value="/sis_recommend_list",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Result recommend(@RequestParam int currentPage, @RequestParam int count, HttpServletRequest request, HttpServletResponse response) {
		if(!isLogin(request, response)){
			//mySisTopicDao
			return new Result(P.FAILURE, "未登录",null);
		}
		
		
		
		return null;
	}

	@Override
	public Result MyParticipated(int currentPage, int count, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result MyPublished(int currentPage, int count, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result Accomplish(int currentPage, int count, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result IWantparticipate(String topicID, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result TopicInfo(String topicID, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
