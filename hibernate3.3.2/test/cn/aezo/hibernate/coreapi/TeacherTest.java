package cn.aezo.hibernate.coreapi;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;


public class TeacherTest {
	private static SessionFactory sf;

	//在类加载之前执行下面的方法，因为SessionFactory全程只需要一个对象即可
	@BeforeClass
	public static void beforeClass() {
		//如果hibernate配置文件起名不是hibernate.cfg.xml，就需要在方法configure()中指明
		AnnotationConfiguration acfg = new AnnotationConfiguration().configure();
		sf = acfg.buildSessionFactory();//读取hibernate.cfg.xml文件后再创建一个session工厂
	}
	
	@AfterClass
	public static void afterClass() {
		sf.close();
	}
	
	@Test
	public void testSessoinFactor() {
		Teacher1 teacher1 = new Teacher1(); 
		teacher1.setName("smalle");
		teacher1.setTitle("高级");
		teacher1.setBirthDate(new Date());
		teacher1.setZhiChen1(ZhiChen1.A);
		
		Session session = sf.getCurrentSession();//从上下文找(要在hibernate配置文件中配置session运行的上下文)，如果有直接用，如果没有重新创建。事务提交自动close，下次获取的就是新的session
		//Session session =  sf.openSession();//每次都是新的，需要close
		session.beginTransaction();//开始一个事物
		session.save(teacher1);
		session.getTransaction().commit();//提交事物
		//session.close();
		
	}
	
	@Test
	public void testSaveAndDelete() {
		Teacher1 teacher1 = new Teacher1(); 
		teacher1.setName("smalle");
		teacher1.setTitle("中级");
		teacher1.setBirthDate(new Date());
		teacher1.setZhiChen1(ZhiChen1.B);
		
		Session session = sf.getCurrentSession();//从上下文找(要在hibernate配置文件中配置session运行的上下文)，如果有直接用，如果没有重新创建。事务提交自动close，下次获取的就是新的session
		session.beginTransaction();//开始一个事物
		session.save(teacher1);
		//session.delete(teacher1);//删除数据库的任务单元
		session.getTransaction().commit();//提交事物
	}
	
	@Test
	public void testLoad() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		//Teacher1.class表示拿出来的对象是一个Teacher1类，拿出id=1的那条数据
		Teacher1 teacher1 = (Teacher1)session.load(Teacher1.class, 1);//这里没有发出sql语句。此时load返回的是代理对象,等到真正用到对象的内容时才发出sql语句,没有获取真正的对象
		System.out.println(teacher1.getClass()); // load返回的是代理对象，打印可知是Teacher1_$$_javassist_1
		System.out.println(teacher1.getName()); // 这里才开始发出sql语句
		session.getTransaction().commit();
		System.out.println(teacher1.getName());

		/*
		// 此时会报错：org.hibernate.LazyInitializationException: could not initialize proxy - no Session
		Teacher1 teacher1 = (Teacher1)session.load(Teacher1.class, 1);
		System.out.println(teacher1.getClass()); // load返回的是代理对象，打印可知是Teacher1_$$_javassist_1
		// session关闭前没有发出sql语句
		session.getTransaction().commit();
		System.out.println(teacher1.getName());//此时session已经关闭，就获取不到了真正的对象了
		*/
	}
	
	@Test
	public void testGet() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Teacher1 teacher1 = (Teacher1)session.get(Teacher1.class, 1);//此时get已经向数据库发出sql语句了，并将对象拿到了
		System.out.println(teacher1.getClass());//打印的是Teacher1
		System.out.println(teacher1.getName());//这里可以获取到
		session.getTransaction().commit();
		System.out.println(teacher1.getName());//get是直接从数据库加载，所有在此处可以获取到数据
	}
	
	@Test
	public void testUpdate() {
		//注意看控制台输出的sql语句
		//法一：默认会更新全部字段，如果数据不大就关系不大
		Session session1 = sf.getCurrentSession();
		session1.beginTransaction();
		Teacher1 teacher1 = (Teacher1)session1.get(Teacher1.class, 1);
		teacher1.setName("name1");//persistent状态，在提交之前如果发生变化则自动update一次，不需要手动调用update方法
		session1.getTransaction().commit();
		
		teacher1.setName("name2");
		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		session2.update(teacher1);//用来更新detached对象，更新完成后转为persistent
		session2.getTransaction().commit();

		//法二：使用HQL(EjBQL)，只更新某条数据
		Session session3 = sf.getCurrentSession();
		session3.beginTransaction();
		Query query = session3.createQuery("update Teacher1 t1 set t1.name='name3' where t1.id=1");//注意是更新的Teacher1类型的对象，这是面向对象的数据库编程
		query.executeUpdate();
		session3.getTransaction().commit();
	}
	
	@Test
	public void testFlush() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Teacher1 teacher1 = (Teacher1)session.get(Teacher1.class, 1);//此时get已经向数据库发出sql语句了，并将对象拿到了
		teacher1.setName("name4");//session的事务已经存在
		session.flush();//如果不刷新，则控制台只输出一条update语句。当session的事务提交后,会强制将内存(session缓存)与数据库同步.默认情况下是session的事务提交(commit)时才同步!
		teacher1.setName("name5");
		session.getTransaction().commit();
	}

}
