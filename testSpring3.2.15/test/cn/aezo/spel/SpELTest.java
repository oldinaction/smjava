package cn.aezo.spel;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import cn.aezo.model.User;
import cn.aezo.spel.MyBean;

//Spring的SpEL动态语言测试
@ContextConfiguration("classpath:beans.xml")
public class SpELTest extends AbstractJUnit4SpringContextTests {
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
