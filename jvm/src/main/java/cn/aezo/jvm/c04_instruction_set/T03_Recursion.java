package cn.aezo.jvm.c04_instruction_set;

/**
 * jclasslib观察指令如下：
 *
 * 1.main方法
 *
 *  0 new #2 <cn/aezo/jvm/c04_instruction_set/T03_Recursion>
 *  3 dup
 *  4 invokespecial #3 <cn/aezo/jvm/c04_instruction_set/T03_Recursion.<init>>
 *  7 astore_1
 *  8 aload_1
 *  9 iconst_3
 * 10 invokevirtual #4 <cn/aezo/jvm/c04_instruction_set/T03_Recursion.m>
 * 13 istore_2
 * 14 return
 *
 * 2.m方法
 *
 *  0 iload_1
 *  1 iconst_1
 *  2 if_icmpne 7 (+5)      // 比较栈顶两int型数值大小，当结果不等于0时跳转到第7号指令(当前指令号+5)
 *  5 iconst_1
 *  6 ireturn
 *  7 iload_1
 *  8 aload_0
 *  9 iload_1
 * 10 iconst_1
 * 11 isub
 * 12 invokevirtual #4 <cn/aezo/jvm/c04_instruction_set/T03_Recursion.m>
 * 15 imul
 * 16 ireturn
 *
 * @author smalle
 * @date 2020-07-04 21:32
 */
public class T03_Recursion {

    public static void main(String[] args) {
        T03_Recursion h = new T03_Recursion();
        int i = h.m(3);
    }

    public int m(int n) {
        if(n == 1) return 1;
        return n * m(n-1);
    }

}
