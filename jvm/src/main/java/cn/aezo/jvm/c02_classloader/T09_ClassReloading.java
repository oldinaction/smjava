package cn.aezo.jvm.c02_classloader;

/**
 * 结果：
 *
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 * true
 * findClass...
 * cn.aezo.jvm.c02_classloader.T04_MyClassLoader@677327b6
 * findClass...
 * false
 *
 * @author smalle
 * @date 2020-07-02 23:12
 */
public class T09_ClassReloading {
    public static void main(String[] args) throws Exception {
        // 实际有AppClassLoader加载
        T04_MyClassLoader classLoader = new T04_MyClassLoader();
        Class clazz = classLoader.loadClass("cn.aezo.jvm.c01_class.T1_HelloWorld3");
        System.out.println(clazz.getClassLoader());

        classLoader = new T04_MyClassLoader();
        Class clazz1 = classLoader.loadClass("cn.aezo.jvm.c01_class.T1_HelloWorld3");
        System.out.println(clazz == clazz1); // true

        // 实际有T04_MyClassLoader加载
        classLoader = new T04_MyClassLoader();
        clazz = classLoader.loadClass("cn.aezo.jvm.c01_class.T1_HelloWorld4");
        System.out.println(clazz.getClassLoader());

        classLoader = new T04_MyClassLoader();
        clazz1 = classLoader.loadClass("cn.aezo.jvm.c01_class.T1_HelloWorld4");
        System.out.println(clazz == clazz1); // false
    }
}
