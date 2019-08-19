package cn.aezo.testbase.jvm.permgen.classloader;

import java.io.File;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法区说明
 * 1. 方法区(Method Area)和Java堆一样是线程共享的。主要用来存放类信息(类型修饰符、)、常量、静态变量、及时编译器编译后的代码。习惯上叫PermGen(永久代)，但本质上两者并不等价。
 * 2. 由不同的类加载器实例根据同一类文件加载的类(型)也会视为不同的类(型)，哪怕是同一类型类加载器的不同实例加载的，都会在PermGen区域分配相应的空间来存储每个类(型)的信息
 * 3. 新类型加载时，会在PermGen区域申请相应的空间来存储类型信息，类型被卸载后，PermGen区域上的垃圾收集会释放对应的内存空间
 * 4. 一种类型被卸载的前提条件是：类对应的普通实例、类对应的java.lang.Class实例、加载此类的ClassLoader实例，三者中有任何一种或者多种是reachable(可达)状态的，那么此类型就不可能被卸载。
 *  （unreachable不可达状态. 大致可以理解为不能通过特定活动线程对应的栈出发通过引用计算来到达对应的实例）
 * 5. jdk1.8 去除了方法区，方法区中存储的信息保存到了本地内存中。(同时-XX:PermSize、-XX:MaxPermSize两个JVM参数作废)
 * 6. 方法区OOM出现的常见情况有：大量JSP或动态生成JSP的应用(JSP第一次运行时需要先编译成Java类)、基于OSGi(Java动态模型系统)的应用
 */
public class PermGenOOM {
    public static void main(String[] args) {
        // 1.测试时VM参数加 -XX:PermSize=4M -XX:MaxPermSize=4M -verbose -verbose:gc
        // test2相比test1就是没有保存加载Hello类型的ClassLoader引用，从而Hello使用完成，便会进行卸载
        // test1类型Hello无法卸载的原因，前面说明过，是由于对应的类加载器实例一直是reachaable状态，缓存对象实例或者java.lang.Class实例同样可以达到无法卸载类型的效果
        test1();
        // test2();

        "".intern();
    }

    /**
     * 容易内存溢出
     */
    public static void test1() {
        try {
            //准备url
            URL url = new File("D:/gitwork/smjava/javase/test/src/cn/aezo/jvm/permgen/classloader/classes").toURL();
            URL[] urls = {url};

            //获取有关类型加载的JMX接口
            ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();

            //用于缓存类加载器
            List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();

            while (true) {
                //加载类型并缓存类加载器实例
                ClassLoader classLoader = new URLClassLoader(urls);
                classLoaders.add(classLoader);
                classLoader.loadClass("Hello");

                //显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
                System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
                System.out.println("active: " + loadingBean.getLoadedClassCount());
                System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不容易内存溢出
     */
    public static void test2() {
        try {
            //准备url
            URL url = new File("D:/gitwork/smjava/javase/test/src/cn/aezo/jvm/permgen/classloader/classes").toURL();
            URL[] urls = {url};

            //获取有关类型加载的JMX接口
            ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
            while (true) {
                //加载类型并缓存类加载器实例
                ClassLoader classLoader = new URLClassLoader(urls);
                classLoader.loadClass("Hello");

                //显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
                System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
                System.out.println("active: " + loadingBean.getLoadedClassCount());
                System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
