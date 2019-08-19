package cn.aezo.others;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.aezo.po.User;

@Controller
public class AjaxController {
	@RequestMapping(value="/json/string", produces="application/json;charset=utf-8")
	public @ResponseBody String ruturnJson(){
		return "{\"id\":1,\"name\":'小易'}";//如果返回字符串的话，这里可以使用 json工具转换为字符串	
	}
	
	@RequestMapping(value="/json/user")
	@ResponseBody
	public User checkName(String name){
		User user = new User();
		user.setId(1);
		user.setName(name);
		return  user;
	}
	
	/*测试失败
	@RequestMapping(value="/json/byid", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User findById(Integer id){
		User user = new User();
		user.setId(id);
		user.setName("小易");
		return user;
	}
	*/
	
	/*测试失败
	//跳转到ajax页面
	@RequestMapping("/ajax")
	public String ajax() {
		return "ajax";
	}
	
	//接受json格式的数据
	@RequestMapping(value="/ajax/add", method = {RequestMethod.POST})
	public String add(@RequestBody User user){
		System.out.println(user.getName());
		return null;
	}
	*/
	

}
