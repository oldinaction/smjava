package cn.aezo.jvm.c04_instruction_set;

/**
 * 通过jclasslib观察指令的不同
 *
 * 1.main方法指令
 *
 * 0 iconst_3                                           // 将int型常量放到栈顶(执行后栈底->栈顶：3)
 * 1 istore_1                                           // 将栈顶元素放到第1个本地变量表(方法的args参数放在第0个本地变量表中)
 * 2 invokestatic #2 <cn/aezo/jvm/c04_instruction_set/T01_IntAddAdd.add1>
 * 5 invokestatic #3 <cn/aezo/jvm/c04_instruction_set/T01_IntAddAdd.add2>
 * 8 return
 *
 * 2.add1的指令
 *
 *  0 iconst_3                                          // 将int型常量放到栈顶(执行后栈底->栈顶：3)
 *  1 istore_0                                          // 将int型值保存到第0个本地变量表(执行后栈底->栈顶：空)
 *  2 iload_0                                           // 将第0个本地变量表的int型值放到栈顶(执行后栈底->栈顶：3)
 *  3 iinc 0 by 1                                       // 将本地变量表的第0个位置(int型)值增加1(仅修改了本地变量表的值，此时为1，并没有修改栈中的值；执行后栈底->栈顶：0)
 *  6 istore_0                                          // 将栈顶的值(0)保存在本地变量表的第0个位置(此时本地变量第0个位置值为0；执行后栈底->栈顶：空)
 *  7 getstatic #4 <java/lang/System.out>
 * 10 iload_0                                           // 加载本地变量表的第0个位置值到栈顶(执行后栈底->栈顶：3)
 * 11 invokevirtual #5 <java/io/PrintStream.println>    // 打印栈顶的值(执行后栈底->栈顶：空)
 * 14 return
 *
 * 3.add2的指令
 *
 *  0 iconst_3
 *  1 istore_0
 *  2 iinc 0 by 1
 *  5 iload_0
 *  6 istore_0
 *  7 getstatic #4 <java/lang/System.out>
 * 10 iload_0
 * 11 invokevirtual #5 <java/io/PrintStream.println>
 * 14 return
 *
 * @author smalle
 * @date 2020-07-04 20:19
 */
public class T01_IntAddAdd {
    public static void main(String[] args) {
        int i = 3;

        add1(); // 3

        add2(); // 4
    }

    public static void add1() {
        int i = 3;
        i = i++;
        System.out.println(i);
    }

    public static void add2() {
        int i = 3;
        i = ++i;
        System.out.println(i);
    }
}
