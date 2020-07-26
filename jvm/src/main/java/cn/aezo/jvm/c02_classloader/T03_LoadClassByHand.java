package cn.aezo.jvm.c02_classloader;

/**
 * 手动加载(如Spring管理bean时用到，或使用热部署时用到)
 *
 * @author smalle
 * @date 2020-07-02 08:03
 */
public class T03_LoadClassByHand {
    public static void main(String[] args) throws ClassNotFoundException {
        // 通过loadClass方法加载类
        Class clazz = T03_LoadClassByHand.class.getClassLoader().loadClass("cn.aezo.jvm.c02_classloader.T03_LoadClassByHand");
        System.out.println(clazz.getName()); // cn.aezo.jvm.c02_classloader.T03_LoadClassByHand

        // 利用类加载器加载资源
        // InputStream is = T03_LoadClassByHand.class.getClassLoader().getResourceAsStream("");
    }
}
