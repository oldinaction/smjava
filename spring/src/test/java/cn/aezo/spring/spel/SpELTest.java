package cn.aezo.spring.spel;

import cn.aezo.spring.base.hello.model.User;
import cn.aezo.spring.spel.spel.MyBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//Spring的SpEL动态语言测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:cn/aezo/spring/spel/beans.xml")
public class SpELTest {
	@Resource(name="myBean")
	private MyBean myBean;
	
	@Test
	public void testSpel1() {
		User user = new User();
		user.setUsername("smalle");
		
		EvaluationContext context = new StandardEvaluationContext(user);

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("username");//user.getUsername()
		
		System.out.println((String) exp.getValue(context));
		System.out.println((String) exp.getValue(user));
	}
	
	@Test
	public void testSpel2() {
		System.out.println(this.myBean.getRandomNumber());
		System.out.println(this.myBean.getUserRegion());
	}
	
	
	
	
	
}
