package cn.aezo.testbase.dynamicProxy;

/**
 * 动态代理
 * <BR>
 * DynamicProxyTest <BR>
 * @author smalle
 * @date 2015年11月10日-上午11:21:48
 * @version 1.0.0
 *
 */
public class DynamicProxyTest {
	public static void main(String[] args) {
		// 实例化目标对象(被代理对象)
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		// 实例化InvocationHandler
		MyInvocationHandler handler = new MyInvocationHandler(userServiceImpl);
		
		// 根据目标对象生成代理对象 
        UserService proxy = (UserService) handler.getProxy();
System.out.println(proxy.getClass().getName());
          
        // 调用代理对象的方法  
        proxy.add(); 
        
	}
	
	/**
	 * 产生的代理类$Proxy0大致代码：
	 * class $Proxy0 implements UserService {
	 * 
	 * 	//@Override
	 * 	save() {
	 * 		Method m = UserService.getClass.getMethod...
	 * 		handler.invoke(this, m, args);
	 * 	}
	 * 
	 * }
	 */
}
