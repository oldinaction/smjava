package cn.aezo.jvm.c02_classloader;

/**
 * 结果：
 *
 * null
 * null
 * sun.misc.Launcher$ExtClassLoader@45ee12a7
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 * null
 * null
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 *
 * @author smalle
 * @date 2020-07-02 07:37
 */
public class T01_ClassLoaderLevel {

    public static void main(String[] args) {
        // null. String属于核心类，通过Bootstrap加载(C++实现)，因此返回null
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        // sun.misc.Launcher$ExtClassLoader@45ee12a7. Extension加载扩展包
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        // sun.misc.Launcher$AppClassLoader@18b4aac2. 加载classpath指定内容
        System.out.println(T01_ClassLoaderLevel.class.getClassLoader());

        // null. DNSNameService类加载器(ExtClassLoader)则是由Bootstrap加载
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(T01_ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());

        // 系统默认的ClassLoader为AppClassLoader
        System.out.println(new T04_MyClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader());
    }

}
