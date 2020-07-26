package cn.aezo.jvm.c02_classloader;

/**
 * 每个ClassLoader负责的目录(findClass查找的目录)。结果：
 *
 * C:\soft\java\jdk1.8.0_111\jre\lib\resources.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\rt.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\sunrsasign.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\jsse.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\jce.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\charsets.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\jfr.jar
 * C:\soft\java\jdk1.8.0_111\jre\classes
 * --------------------
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext
 * C:\WINDOWS\Sun\Java\lib\ext
 * --------------------
 * C:\soft\java\jdk1.8.0_111\jre\lib\charsets.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\deploy.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\access-bridge-64.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\cldrdata.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\dnsns.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\jaccess.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\jfxrt.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\localedata.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\nashorn.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\sunec.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\sunjce_provider.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\sunmscapi.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\sunpkcs11.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\ext\zipfs.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\javaws.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\jce.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\jfr.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\jfxswt.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\jsse.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\management-agent.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\plugin.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\resources.jar
 * C:\soft\java\jdk1.8.0_111\jre\lib\rt.jar
 * D:\gitwork\smjava\jvm\classes
 * C:\soft\idea\lib\idea_rt.jar
 *
 * @author smalle
 * @date 2020-07-02 08:01
 */
public class T02_ClassLoaderSocpe {
    public static void main(String[] args) {
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";", System.lineSeparator()));

        System.out.println("--------------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";", System.lineSeparator()));

        System.out.println("--------------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";", System.lineSeparator()));
    }
}
