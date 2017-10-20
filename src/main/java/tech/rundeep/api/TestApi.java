package tech.rundeep.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "TestApi")
@Controller
public class TestApi {

	@ApiOperation(value="登录")
	@RequestMapping(value="/log",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(@RequestParam String uname,@RequestParam String pwd){
		Map<String, Object> m= new HashMap<String, Object>();
		System.out.println(uname+"--"+pwd);
		m.put("code", 0);
		m.put("msg", "登录成功");
		m.put("data", 100);
		return m;
	}
	
	
	
	 	@ResponseBody
	    @RequestMapping(value ="/getUserName", method= RequestMethod.GET)
	    @ApiOperation(value="根据用户编号获取用户姓名", notes="test: 仅1和2有正确返回")
	    @ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "Integer")
	    public String getUserName(@RequestParam Integer userNumber){
	        if(userNumber == 1){
	            return "张三丰";
	        }
	        else if(userNumber == 2){
	            return "慕容复";
	        }
	        else{
	            return "未知";
	        }
	    }
}
