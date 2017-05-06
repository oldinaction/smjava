package cn.aezo.hibernate.one2many_uni;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ORMappingTest {
	private static SessionFactory sf;

	//@BeforeClass
	public static void beforeClass() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();//读取hibernate.cfg.xml文件后再创建一个session工厂
	}
	
	//@AfterClass
	public static void afterClass() {
		sf.close();
	}
	
	@Test
	public void orm() {
		//生成表的语句
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	}
	


}
