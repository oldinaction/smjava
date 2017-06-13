package com.exjava.tankWar;

import java.io.IOException;
import java.util.Properties;

public class ProperMgr {
	//Singleton模式，只实例化一个对象
	//读配置文件用的对象，这个对象只需要把配置文件加载到内存就行，之后就和他没关系，所以只需实例化一次
	static 	Properties props = new Properties();
	
	static {
		try {
			/* 1、得到ProperMgr这个类编译后的class文件(ProperMgr.class)
			 * 2、得到类装载器(getClassLoader())
			 * 3、通过装载器得到源文件("config/tank.properties")的Stream(getResourceAsStream())
			 * 4、加载(load)源文件
			 */	
			props.load(ProperMgr.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private ProperMgr() {} //构造方法定义成静态的，就是不准别的类new这种对象
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

}
