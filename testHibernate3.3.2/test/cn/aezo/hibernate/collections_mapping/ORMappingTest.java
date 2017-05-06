package cn.aezo.hibernate.collections_mapping;

import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ORMappingTest {
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
	public void testLoadGroup() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Group4 group4 = (Group4)session.get(Group4.class, 1);//请先确保表中有值，没有先手动插入
		for(Entry<Integer, User4> entry : group4.getUsers().entrySet()) {
			System.out.println(entry.getValue().getName());
		}
		session.getTransaction().commit();
	}
	
	//@Test
	public void orm() {
		//生成表的语句
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	}
	


}
