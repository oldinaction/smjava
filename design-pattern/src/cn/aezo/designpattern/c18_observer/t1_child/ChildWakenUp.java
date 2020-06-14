package cn.aezo.designpattern.c18_observer.t1_child;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 小孩醒来事件
 */
class WakenUpEvent {
	private long time; // 时间
	private String loc; // 说明
	private Child source; // 事件源：小孩
	
	public WakenUpEvent(long time, String loc, Child source) {
		super();
		this.time = time;
		this.loc = loc;
		this.source = source;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Child getSource() {
		return source;
	}
	public void setSource(Child source) {
		this.source = source;
	}
}

/**
 * 事件监听类，实现此类后可以对此事件做出响应
 */
interface WakenUpListener {
	// 对时间做出响应的方法
	public void ActionToWakenUp(WakenUpEvent wakenUpEvent);
}

/**
 * 小孩
 * 1. 本来是爸爸需要一直观察小孩是否醒了，那么需要爸爸启动线程监听。但此处让小孩启动线程，醒来后就调用爸爸的方法(或者发出一个事件)
 * 2. 本身是需要喂奶，如果需求复杂就可能是小孩不同时间醒了需要做不同的事情(早上喂奶, 中午看电视, 晚上散步)。此时则新加一个事件对象，小孩醒了就发出一个事件，有爸爸去监听做出响应
 * 3. 可能小孩醒了不仅爸爸需要喂奶，爷爷/奶奶也要做出响应，则此时应该基于接口编程。有一个小孩醒来的事件监听接口，小孩只需要调用此接口的事件响应方法，爷爷/奶奶只需要实现此接口即可
 * 4. 可将接口的实现从配置文件中读取，读取出对应的类名，并通过Class.forName(myClassName).newInstance()进行实例化
 */
class Child implements Runnable {
	private List<WakenUpListener> wakenUpListeners = new ArrayList<WakenUpListener>();

	public void addWakenUpListener(WakenUpListener l) {
		wakenUpListeners.add(l);
	}
	
	void wakeUp() {
		for(int i=0; i<wakenUpListeners.size(); i++) {
			WakenUpListener l = wakenUpListeners.get(i);
			l.ActionToWakenUp(new WakenUpEvent(System.currentTimeMillis(), "bed", this));
		}
	}

	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.wakeUp();
	}
	
}

/**
 * 爸爸
 */
class Dad implements WakenUpListener {

	public void ActionToWakenUp(WakenUpEvent wakenUpEvent) {
		System.out.println("feed child");
	}
	
}

/**
 * 爷爷
 */
class GrandFather  implements WakenUpListener {

	public void ActionToWakenUp(WakenUpEvent wakenUpEvent) {
		System.out.println("hug child");
	}
	
}

/**
 * 小狗
 */
class Dog implements WakenUpListener {

	public void ActionToWakenUp(WakenUpEvent arg0) {
		System.out.println("wang wang ...");
	}
	
}

/**
 * 实现功能：小孩醒来爸爸需要给他喂奶(或者抱抱)
 */
public class ChildWakenUp {
	public static void main(String[] args) {
		Child c = new Child();

		// 从配置文件中读取观察者
		String[] observers = PropertyMgr.getProperty("observers").split(",");

		for(String s : observers) {
			try {
				c.addWakenUpListener((WakenUpListener)(Class.forName(s).newInstance()));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		new Thread(c).start();
	}
}

class PropertyMgr {
	private static Properties props = new Properties();
	
	static {
		try {
			props.load(ChildWakenUp.class.getClassLoader().getResourceAsStream("cn/aezo/designpattern/c18_observer/t1_child/observer.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}

class CryEvent {
}

abstract class Event {

}
