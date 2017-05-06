package cn.aezo.hibernate.hello;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.aezo.hibernate.hello.Teacher;
import cn.aezo.hibernate.hello.ZhiChen;

public class TeacherTest {
	private static SessionFactory sf;

	//在类加载之前执行下面的方法，因为SessionFactory全程只需要一个对象即可
	@BeforeClass
	public static void beforeClass() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();//读取hibernate.cfg.xml文件后再创建一个session工厂
	}
	
	@Test
	public void test() {
		Teacher teacher = new Teacher(); 
		//teacher.setId(1);//设置了id批注@GeneratedValue，则会自动生成id
		
		teacher.setName("smalle");
		teacher.setTitle("高级");
		teacher.setBirthDate(new Date());
		teacher.setZhiChen(ZhiChen.A);
		
		Session session =  sf.getCurrentSession();//此处的session相当于和数据库的一个连接
		session.beginTransaction();//开始一个事物
		session.save(teacher);
		session.getTransaction().commit();//提交事物
	}
	
	@AfterClass
	public static void afterClass() {
		sf.close();
	}

}
