package cn.aezo.testbase.classes;

// 运行顺序 static{} -> {} -> 构造函数 -> 其他

/*new Test()时，输出1 3 5 7 2 4 父类的构造 6 8 子类的构造
调用Test类的内部get方法输出 1 3 5 7 2 4 父类的构造 6 8 子类的构造 调用Test类的方法
Test.got()时输出 1 3 5 7 调用T类的方法，静态方法
Test.poot()时输出 1 3 5 7 调用Test类的静态方法*/

class T {
    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    T() {
        System.out.println("父类构造");
    }

    public static void got() {
        System.out.println("调用T类的方法，静态方法");
    }

    static {
        System.out.println("3");
    }

    {
        System.out.println("4");
    }
}

public class StaticTest extends T {
    static {
        System.out.println("5");
    }

    {
        System.out.println("6");
    }

    public StaticTest() {
        System.out.println("子类构造函数");
    }

    public void get() {
        System.out.println("调用Test类的方法");
    }

    public static void post() {
        System.out.println("调用Test类的静态方法");
    }

    public static void main(String[] args) {
        StaticTest t = new StaticTest();
        t.get();
        System.out.println("=====================");
        StaticTest.got();
        System.out.println("=====================");

        StaticTest.post();
    }

    static {
        System.out.println("7");
    }

    {
        System.out.println("8");
    }
}