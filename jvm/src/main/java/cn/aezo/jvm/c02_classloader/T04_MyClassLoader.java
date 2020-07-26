package cn.aezo.jvm.c02_classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 结果：
 *
 * findClass...
 * true
 * name: smalle
 * cn.aezo.jvm.c02_classloader.T04_MyClassLoader@1540e19d
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 *
 * @author smalle
 * @date 2020-07-02 09:02
 */
public class T04_MyClassLoader extends ClassLoader {
    private static String path = "D:/gitwork/smjava/jvm/src/main/java";

    public static void main(String[] args) throws Exception {
        ClassLoader l = new T04_MyClassLoader();
        Class clazz = l.loadClass("cn.aezo.jvm.c01_class.T1_HelloWorld4");
        Class clazz1 = l.loadClass("cn.aezo.jvm.c01_class.T1_HelloWorld4");

        System.out.println(clazz == clazz1);

        Object o = clazz.newInstance();
        Method getName = clazz.getMethod("getName");
        String name = (String) getName.invoke(o);
        System.out.println("name: " + name);

        System.out.println(clazz.getClassLoader());
        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());
        System.out.println(getSystemClassLoader());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass...");
        File f = new File(path, name.replace(".", "/").concat(".class"));
        try {
            InputStream is = new FileInputStream(f);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name); // throws ClassNotFoundException
    }
}
