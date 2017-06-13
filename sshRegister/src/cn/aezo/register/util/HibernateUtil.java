package cn.aezo.register.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static SessionFactory sf = null;
	
	static {
		AnnotationConfiguration acfg = new AnnotationConfiguration().configure();
		sf = acfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return sf;
	}
	
}
