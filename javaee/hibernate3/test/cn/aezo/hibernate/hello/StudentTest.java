package cn.aezo.hibernate.hello;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentTest {
	private static SessionFactory sf;

	@BeforeClass
	public static void beforeClass() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();//读取hibernate.cfg.xml文件后再创建一个session工厂
	}
	
	@AfterClass
	public static void afterClass() {
		sf.close();
	}
	
	@Test
	public void test() {
		Student student = new Student();
		
		//student.setId(1);//在Student.hbm.xml中定义了id的generator为identity，最终会在字段后面加上auto_increment自动递增
		student.setName("smalle");
		
		/*联合主键的例子时用到，测试之前删除原来的表，否则表信息注入错误
		StudentPK pk = new StudentPK();
		pk.setId(1);
		pk.setName("smalle");
		student.setPk(pk);
		*/
		
		student.setAge(18);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();//开始一个事物
		session.save(student);
		session.getTransaction().commit();//提交事物
	}
	
	
	/*Junit的bug：有时不报错，则将他作为application来运行	
 	public static void main(String[] args) {
		beforeClass();
	}
	*/
	
}
