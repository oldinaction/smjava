package cn.aezo.designpattern.c06_proxy.t4_asm_proxy_visitor;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 模拟JDK动态代理生成代理类
 *
 * @author smalle
 * @date 2020-06-14 18:34
 */
public class MyTransforming {

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(
                MyTransforming.class.getClassLoader().getResourceAsStream("cn/aezo/designpattern/c06_proxy/t1_static_proxy_v2/Dog.class")
        );

        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM4, cw) {
            @Override
            public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
                MethodVisitor mv = super.visitMethod(i, s, s1, s2, strings);
                return new MethodVisitor(Opcodes.ASM4, mv) {
                    @Override
                    public void visitCode() {
                        // 在方法前面插入一行代码
                        visitMethodInsn(Opcodes.INVOKESTATIC, "System", "currentTimeMillis", "()J", false);
                        super.visitCode();
                    }
                };
            }
        };

        cr.accept(cv, 0);
        byte[] b = cw.toByteArray();

        try {
            FileChannel fc = new FileOutputStream(System.getProperty("user.dir") + "/com/MovableTimeProxy.class").getChannel();
            fc.write(ByteBuffer.wrap(b));
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
