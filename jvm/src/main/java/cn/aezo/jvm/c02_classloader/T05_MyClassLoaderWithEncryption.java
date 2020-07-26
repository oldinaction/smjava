package cn.aezo.jvm.c02_classloader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 加密class文件。结果：
 *
 * findClass...
 * name: smalle
 */
public class T05_MyClassLoaderWithEncryption extends ClassLoader {
    private static int seed = 0xFF; // 也可写成0B开头，如OB10011100
    private static String path = "D:/gitwork/smjava/jvm/src/main/java";

    public static void main(String[] args) throws Exception {
        encFile("cn.aezo.jvm.c01_class.T1_HelloWorld4");

        ClassLoader l = new T05_MyClassLoaderWithEncryption();
        Class clazz = l.loadClass("cn.aezo.jvm.c01_class.T1_HelloWorld4");

        Object o = clazz.newInstance();
        Method getName = clazz.getMethod("getName");
        String name = (String) getName.invoke(o);
        System.out.println("name: " + name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass...");
        File f = new File(path, name.replace('.', '/').concat(".myclass"));

        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int data;
            while ((data = bis.read()) != -1) {
                bos.write(data ^ seed);
            }
            bos.flush();
            byte[] bytes = bos.toByteArray();

            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name); // throws ClassNotFoundException
    }

    private static void encFile(String name) {
        File f = new File(path, name.replace('.', '/').concat(".class"));
        File outFile = new File(path, name.replaceAll("\\.", "/").concat(".myclass"));
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile))
        ) {
            int data;
            while ((data = bis.read()) != -1) {
                bos.write(data ^ seed);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
