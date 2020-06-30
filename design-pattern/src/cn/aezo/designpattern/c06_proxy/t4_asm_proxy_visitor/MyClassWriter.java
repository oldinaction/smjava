package cn.aezo.designpattern.c06_proxy.t4_asm_proxy_visitor;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author smalle
 * @date 2020-06-14 18:00
 */
public class MyClassWriter {
    public static void main(String[] args) {
        byte[] b = write();

        // 加载类
        MyClassLoader myClassLoader = new MyClassWriter.MyClassLoader();
        Class c = myClassLoader.defineClass("cn.aezo.MyComparable", b);
        System.out.println(c.getMethods()[0].getName());

        // 也可写出class到磁盘(可进行反编译查看)
        try {
            FileChannel fc = new FileOutputStream(System.getProperty("user.dir") + "/com/MyComparable.class").getChannel();
            fc.write(ByteBuffer.wrap(b));
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 生成class
    private static byte[] write() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "cn/aezo/MyComparable", null, "java/lang/Object", null);
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        return b;
    }

    static class MyClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }
}
