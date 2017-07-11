package cn.aezo.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	
	//注意方法的返回值是String
	@RequestMapping("/view/login")//访问http://127.0.0.1:8080/testSpringMVC/view/login.do?n=smalle&p=123
	public String login(@RequestParam("n") String name, @RequestParam("p") String pwd,
			HttpServletRequest request, HttpSession session, ModelMap map) {
		System.out.println("login==" + name + "---" + pwd);
		
		request.setAttribute("rname", name);
		session.setAttribute("sname", name);
		map.put("mname", name);//ModelMap是Map的实现，可以在其中存放属性，作用域同request。好处是与servlet环境解耦
		
		return "forward:/welcome.jsp";//服务器内部跳转，注意welcome.jsp前面的/
	}
	
	@RequestMapping("/view/baidu")//访问http://127.0.0.1:8080/testSpringMVC/view/baidu.do
	public String baidu() {
		return "redirect:http://www.baidu.com";//客户端重定向
	}
	
	//访问WEB-INF下的页面（客户端是访问不到的）
	@RequestMapping("/view/webinfo")//访问http://127.0.0.1:8080/testSpringMVC/view/webinfo.do
	public String webinfo() {
		//return "forward:/WEB-INF/jsp/admin/admin.jsp";//直接写目录比较麻烦
		return "admin/admin";//凡是没有写forward和redirect的就交由spring-servlet.xml文件中配置的视图解析器处理
	}

}
