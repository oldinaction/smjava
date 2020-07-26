package cn.aezo.jvm.c03_object_size;

/**
 * 运行是加入VM参数：-javaagent:D:/gitwork/smjava/jvm/src/main/resources/ObjectSizeAgent.jar
 *
 * 1.java -XX:+PrintCommandLineFlags -version 结果：
 *
 * -XX:InitialHeapSize=266743424 -XX:MaxHeapSize=4267894784 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
 * java version "1.8.0_111"
 * Java(TM) SE Runtime Environment (build 1.8.0_111-b14)
 * Java HotSpot(TM) 64-Bit Server VM (build 25.111-b14, mixed mode)
 *
 * 2.64位16G内存测试结果：
 *
 * 16 // 8(markword) + 4(ClassPointer指针) + 0(无实例数据/属性) + 4(Padding对齐) = 16
 * 16 // 8 + 4 + 4(数组长度) + 0(数组数据) + 0(Padding对齐) = 16
 * 32
 *
 * 3.增加JVM参数-XX:-UseCompressedClassPointers(关闭ClassPointers压缩)，结果：
 *
 * 16 // 8(markword) + 8(ClassPointer指针) + 0(无实例数据/属性) + 0(Padding对齐) = 16
 * 24
 * 40
 *
 * 4.增加JVM参数-XX:-UseCompressedClassPointers -XX:-UseCompressedOops，结果：
 *
 * 16
 * 24
 * 48
 *
 * @author smalle
 * @date 2020-07-03 23:55
 */
public class T1_SizeOfAnObject {
    public static void main(String[] args) {
        // 使用resources/ObjectSizeAgent.jar，源码参考ObjectSizeAgent.java.bak
        System.out.println(ObjectSizeAgent.sizeOf(new Object()));
        System.out.println(ObjectSizeAgent.sizeOf(new int[] {}));
        System.out.println(ObjectSizeAgent.sizeOf(new P()));
    }

    private static class P {
                        //8 markword
                        //4 ClassPointer指针
        int id;         //4
        String name;    //4 // 存放的是对象指针(oops)，压缩情况下占用4字节，未压缩占用8个字节
        int age;        //4

        byte b1;        //1
        byte b2;        //1

        Object o;       //4 // 存放的是对象指针(oops)，压缩情况下占用4字节，未压缩占用8个字节
        byte b3;        //1
                        //1 // Padding对齐
    }
}
