package cn.aezo.hibernate.one2many_many2one_bi_curd;

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
	public void testSaveUser() {
		User3 user = new User3();
		user.setName("u1");
		Group3 group = new Group3();
		group.setName("g1");
		user.setGroup(group);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		//session.save(group);//关联的批注上设置了cascade，此处就不用手动保存关联的表
		session.save(user);
		session.getTransaction().commit();
	}
	
	@Test
	public void testSaveGroup() {
		User3 user21 = new User3();
		user21.setName("u21");
		User3 user22 = new User3();
		user22.setName("u22");
		Group3 group = new Group3();
		group.setName("g2");
		group.getUsers().add(user21);//设定了group到user的关联，即可以通过group找到user
		group.getUsers().add(user22);
		user21.setGroup(group);//双向关系在程序中要设定双向关联，否则user3表中的group字段为空
		user22.setGroup(group);//设定了user到group的关联
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		//session.save(user);//关联的批注上设置了cascade，此处就不用手动保存关联的表
		session.save(group);
		session.getTransaction().commit();
	}
	
	@Test
	public void testGetUser() {
		testSaveUser();
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		User3 user3 = (User3)session.get(User3.class, 1);//当多对一时，取多时，默认会把一也取出来。此时取用户的信息时也会把组的信息取出来
		session.getTransaction().commit();
		System.out.println("user3.getGroup().getName() = " + user3.getGroup().getName());
	}
	
	@Test
	public void testGetGroup() {
		testSaveGroup();
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Group3 group3 = (Group3)session.get(Group3.class, 1);//取一对多时，默认只会取出一不会取出多。但如果在关联的批注处设定了fetch=FetchType.EAGER，则会同时取出用户信息
		// Set<User3> user3s = group3.getUsers(); // 如果fetch没有设定了eager，则可以在此处手动把User都拿出来放到内存中
		session.getTransaction().commit();
		
		/*如果fetch设定了eager则已经将用户信息取到内存中了. 否则此处会报错
		for(User3 u : group3.getUsers()) {
			System.out.println(u.getName());
		}
		*/
	}
	
	@Test
	public void testUpdateUser() {
		testSaveGroup();
		
		Session session1 = sf.getCurrentSession();
		session1.beginTransaction();
		User3 user4 = (User3)session1.get(User3.class, 1);
		session1.getTransaction().commit();
		
		user4.setName("user4");
		user4.getGroup().setName("group4");//此时也会更新group3表，因为cascade设置的为ALL
		
		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		session2.update(user4);
		session2.getTransaction().commit();
	}
	
	@Test
	public void testDeleteUser() {
		testSaveGroup();
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		/*法一
		User3 user5 = (User3)session.load(User3.class, 1);
		user5.setGroup(null);//当User和Group双向关联且cascade设定为all后，删除user5时就会同时删除group,想要删除group就会把所有的user删掉，最终就都删除了。所有此处解除user5和group的关联
		session.delete(user5);
		*/
		//法二：当知道想要删除的那条id时，使用HQL语句
		session.createQuery("delete from User3 u where u.id = 1").executeUpdate();
		session.getTransaction().commit();		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@Test
	public void orm() {
		//生成表的语句
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	}
	


}
