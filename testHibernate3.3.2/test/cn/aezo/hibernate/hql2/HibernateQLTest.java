package cn.aezo.hibernate.hql2;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
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
			Category1 c = new Category1();
			c.setName("c" + i);
			session.save(c);
		}
		
		for(int i=0; i<10; i++) {
			Category1 c = new Category1();
			c.setId(1);
			Topic1 t = new Topic1();
			t.setCategory(c);
			t.setTitle("t" + i);
			t.setCreateDate(new Date());
			session.save(t);
			
		}
		
		for(int i=0; i<10; i++) {
			
			Topic1 t = new Topic1();
			t.setId(1);
			Msg1 m = new Msg1();
			m.setCont("m" + i);
			m.setTopic(t);
			session.save(m);
			
		}
		
		
		
		
		session.getTransaction().commit();
		session.close();
	}
	
	//is empty and is not empty
	@Test
	public void testHQL_20() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.msgs is empty");
		
		for(Object o : q.list()) {
			Topic1 t = (Topic1)o;
			System.out.println(t.getId() + "-" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_21() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.title like '%5'");
		
		for(Object o : q.list()) {
			Topic1 t = (Topic1)o;
			System.out.println(t.getId() + "-" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_22() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.title like '_5'");
		
		for(Object o : q.list()) {
			Topic1 t = (Topic1)o;
			System.out.println(t.getId() + "-" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	//����Ҫ
	@Test
	public void testHQL_23() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select lower(t.title)," +
											 "upper(t.title)," +
											 "trim(t.title)," +
											 "concat(t.title, '***')," +
											 "length(t.title)" +
											 " from Topic t ");
		
		for(Object o : q.list()) {
			Object[] arr = (Object[])o;
			System.out.println(arr[0] + "-" + arr[1] + "-" + arr[2] + "-" + arr[3] + "-" + arr[4] + "-");
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_24() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select abs(t.id)," +
											 "sqrt(t.id)," +
											 "mod(t.id, 2)" +
											 " from Topic t ");
		
		for(Object o : q.list()) {
			Object[] arr = (Object[])o;
			System.out.println(arr[0] + "-" + arr[1] + "-" + arr[2] );
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_25() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select current_date, current_time, current_timestamp, t.id from Topic t");
		
		for(Object o : q.list()) {
			Object[] arr = (Object[])o;
			System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2] + " | " + arr[3]);
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_26() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.createDate < :date");
		q.setParameter("date", new Date());
		for(Object o : q.list()) {
			Topic1 t = (Topic1)o;
			System.out.println(t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_27() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select t.title, count(*) from Topic t group by t.title") ;
		for(Object o : q.list()) {
			Object[] arr = (Object[])o;
			System.out.println(arr[0] + "|" + arr[1]);
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_28() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select t.title, count(*) from Topic t group by t.title having count(*) >= 1") ;
		for(Object o : q.list()) {
			Object[] arr = (Object[])o;
			System.out.println(arr[0] + "|" + arr[1]);
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_29() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.id < (select avg(t.id) from Topic t)") ;
		for(Object o : q.list()) {
			Topic1 t = (Topic1)o;
			System.out.println(t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_30() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.id < ALL (select t.id from Topic t where mod(t.id, 2)= 0) ") ;
		for(Object o : q.list()) {
			Topic1 t = (Topic1)o;
			System.out.println(t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//��in ����ʵ��exists�Ĺ���
	//����existsִ��Ч�ʸ�
	@Test
	public void testHQL_31() {
		Session session = sf.openSession();
		session.beginTransaction();// t.id not in (1)
		Query q = session.createQuery("from Topic t where not exists (select m.id from Msg m where m.topic.id=t.id)") ;
//		Query q = session.createQuery("from Topic t where exists (select m.id from Msg m where m.topic.id=t.id)") ;
		for(Object o : q.list()) {
			Topic1 t = (Topic1)o;
			System.out.println(t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//update and delete
	//�淶��û��˵���ǲ���Ҫ����persistent object���������Ҫʹ�ã������ڵ�����trasaction��ִ��
	
	@Test
	public void testHQL_32() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("update Topic t set t.title = upper(t.title)") ;
		
		q.executeUpdate();
		q = session.createQuery("from Topic");
		for(Object o : q.list()) {
			Topic1 t = (Topic1)o;
			System.out.println(t.getTitle());
		}
		session.createQuery("update Topic t set t.title = lower(t.title)")
			.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}
	
	//����Ҫ
	@Test
	public void testHQL_33() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.getNamedQuery("topic.selectCertainTopic");
		q.setParameter("id", 5);
		Topic1 t = (Topic1)q.uniqueResult();
		System.out.println(t.getTitle());
		session.getTransaction().commit();
		session.close();
		
	}
	
	//Native���˽⣩
	@Test
	public void testHQL_34() {
		Session session = sf.openSession();
		session.beginTransaction();
		SQLQuery q = session.createSQLQuery("select * from category limit 2,4").addEntity(Category1.class);
		List<Category1> categories = (List<Category1>)q.list();
		for(Category1 c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_35() {
		//��δʵ��JPA�����NativeSQL
		
	}
	
	public static void main(String[] args) {
		beforeClass();
	}
}
