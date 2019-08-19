package cn.aezo.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

//***********测试失败*************
@Controller
public class ModelAndViewController {
	
	//注意方法的返回值是String
	@RequestMapping(value="/view/mav")//访问http://127.0.0.1:8080/testSpringMVC/view/mav.do?n=smalle&p=123
	public ModelAndView login(@RequestParam("n") String name, @RequestParam("p") String pwd) {
		System.out.println("login==" + name + "---" + pwd);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/welcome.jsp");
		mav.getModelMap().put("mname", name);
		
		return mav;
	}
}
