package cn.aezo.spring.base.annotation;

import cn.aezo.spring.base.annotation.combineannotation.DemoConfig;
import cn.aezo.spring.base.annotation.combineannotation.DemoService;
import cn.aezo.spring.base.annotation.condition.ConditionConfig;
import cn.aezo.spring.base.annotation.condition.ListService;
import cn.aezo.spring.base.annotation.el.ELConfig;
import cn.aezo.spring.base.annotation.event.DemoPublisher;
import cn.aezo.spring.base.annotation.event.EventConfig;
import cn.aezo.spring.base.annotation.model.User;
import cn.aezo.spring.base.annotation.scheduled.TaskScheduledConfig;
import cn.aezo.spring.base.annotation.service.UserService;
import cn.aezo.spring.base.annotation.springaware.AwareConfig;
import cn.aezo.spring.base.annotation.springaware.AwareService;
import cn.aezo.spring.base.annotation.thread.AsyncTaskService;
import cn.aezo.spring.base.annotation.thread.TaskExecutorConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//DI(inversion of control):动态注入：容器根据xml文件中配置的bean的不同，实例化出不同的对象
//也叫IoC(dependency injection):控制反转：本来需要我们自己控制实例化的UserService和UserDAO(不同的对象写不同的实例化语句)，最后交给了容器帮我们实例化不同的对象

//annotation方式的aop举例
public class AnnotationTest {
	// spEL
	@Test
	public void testEl() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ELConfig.class);
		ELConfig elConfig = ac.getBean(ELConfig.class);
		elConfig.outputResource();
		ac.close();
	}

	// 事件
	@Test
	public void testEvent() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(EventConfig.class);
		DemoPublisher demoPublisher = ac.getBean(DemoPublisher.class);
		demoPublisher.publish("hello application event");
		ac.close();
	}

	// spring aware
	@Test
	public void testAware() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AwareConfig.class);
		AwareService awareService = ac.getBean(AwareService.class);
		awareService.outputResult();
		ac.close();
	}

	// 多线程
	@Test
	public void testThread() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
		AsyncTaskService asyncTaskService = ac.getBean(AsyncTaskService.class);
		for (int i = 0; i < 10; i++) {
			asyncTaskService.executeAsyncTask(i);
			asyncTaskService.executeAsyncTaskPlus(i);
		}
		ac.close();
	}

	// 任务
	@Test
	public void testScheduled() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TaskScheduledConfig.class);

		try {
			Thread.sleep(1000 * 60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 条件注解
	@Test
	public void testCondition() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ConditionConfig.class);
		ListService listService = ac.getBean(ListService.class);
		System.out.println(listService.showListCmd());
	}

	// 组合注解
	@Test
	public void testCombineAnnotation() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DemoConfig.class);
		DemoService demoService = ac.getBean(DemoService.class);
		demoService.out();
	}
	
	// 使用xml配置扫描路径，spring 3.0推荐时间java配置
	@Deprecated
	@Test
	public void testAdd() throws Exception {
		ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("cn/aezo/spring/base/annotationAopBeans.xml");
		
		UserService service = (UserService) cac.getBean("userServiceAnno");
		User u = new User();
		u.setUsername("smalle");
		u.setPassword("123456");
		service.add(u);
		
		cac.destroy();
	}
	
}
