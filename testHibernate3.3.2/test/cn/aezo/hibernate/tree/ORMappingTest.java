package cn.aezo.hibernate.tree;

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
	public void testSaveTree() {
		Org o = new Org();
		o.setName("0");
		Org o1 = new Org();
		o1.setName("1");
		Org o2 = new Org();
		o2.setName("2");
		Org o21 = new Org();
		o21.setName("21");
		Org o22 = new Org();
		o22.setName("22");
		
		o.getChildren().add(o1);
		o.getChildren().add(o2);
		o2.getChildren().add(o21);
		o2.getChildren().add(o22);
		o1.setParent(o);
		o2.setParent(o);
		o21.setParent(o2);
		o22.setParent(o2);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(o);//设置了all，所有只存储根节点，关联的子节点也一并存储了
		session.getTransaction().commit();
	}
	
	@Test
	public void testLoadTree() {	
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Org o = (Org)session.load(Org.class, 1);
		print(o, 0);
		session.getTransaction().commit();
	}
	
	private void print(Org o, int level) {
		String preStr = "";
		for(int i=0; i<level; i++) {
			preStr += "----";
		}
		
		System.out.println(preStr + o.getName());
		
		for (Org child : o.getChildren()) {
			print(child, level+1);
		}
	}

	//@Test
	public void orm() {
		//生成表的语句
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	}
	


}
