package cn.aezo.hibernate.qbe;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateQLTest {
	private static SessionFactory sf;
	
	@BeforeClass
	public static void beforeClass() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	@AfterClass
	public static void afterClass() {
		sf.close();
	}
	
	@Test
	public void testSchemaExport() {
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	}
	
	@Test
	public void testSave() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		for(int i=0; i<10; i++) {
			Category3 c = new Category3();
			c.setName("c" + i);
			session.save(c);
		}
		
		for(int i=0; i<10; i++) {
			Category3 c = new Category3();
			c.setId(1);
			Topic3 t = new Topic3();
			t.setCategory(c);
			t.setTitle("t" + i);
			t.setCreateDate(new Date());
			session.save(t);
			
		}
		
		for(int i=0; i<10; i++) {
			
			Topic3 t = new Topic3();
			t.setId(1);
			Msg3 m = new Msg3();
			m.setCont("m" + i);
			m.setTopic(t);
			session.save(m);
			
		}
		
		
		
		
		session.getTransaction().commit();
		session.close();
	}
	
	//is empty and is not empty
	//query by criteria query by example
	@Test
	public void testQBE() {
		Session session = sf.openSession();
		session.beginTransaction();
		Topic3 tExample = new Topic3();
		tExample.setTitle("T_");
		
		Example e = Example.create(tExample)
					.ignoreCase().enableLike();
		Criteria c = session.createCriteria(Topic3.class)
					 .add(Restrictions.gt("id", 2))
					 .add(Restrictions.lt("id", 8))
					 .add(e)
					 ;
					 
		
		for(Object o : c.list()) {
			Topic3 t = (Topic3)o;
			System.out.println(t.getId() + "-" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	public static void main(String[] args) {
		beforeClass();
	}
}
