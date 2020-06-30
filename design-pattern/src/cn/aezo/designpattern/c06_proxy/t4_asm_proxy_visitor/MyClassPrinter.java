package cn.aezo.designpattern.c06_proxy.t4_asm_proxy_visitor;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.IOException;

/**
 * 结果：
 *
 * java/lang/Runnable extends java/lang/Object {
 *     run()V
 * }
 *
 * 1.Java类型对应的类型描述符(Java type: Type descriptor)
 * boolean Z
 * char C
 * byte B
 * short S
 * int I
 * float F
 * long J
 * double D
 * Object Ljava/lang/Object;
 * int[] [I
 * Object[][] [[Ljava/lang/Object;
 *
 * 2.方法声明对应描述符(Method declaration in source file: Method descriptor)
 * void m(int i, float f) (IF)V
 * int m(Object o) (Ljava/lang/Object;)I
 * int[] m(int i, String s) (ILjava/lang/String;)[I
 * Object m(int[] i) ([I)Ljava/lang/Object;
 *
 * @author smalle
 * @date 2020-06-14 17:39
 */
public class MyClassPrinter extends ClassVisitor {
    public MyClassPrinter() {
        super(Opcodes.ASM4);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("    " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("    " + name + desc);
        return null;
    }

    public void visitEnd() {
        System.out.println("}");
    }

    // 读取类结构
    public static void main(String[] args) throws IOException {
        MyClassPrinter cp = new MyClassPrinter();
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cp, 0);
    }
}