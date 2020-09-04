package cn.aezo.javase.jdk8.c01_lambda;

/**
 * Created by smalle on 2020-09-04 11:22.
 */
public class T01_Lambda {
    public static void main(String[] args) {
        printString("test", (s) -> System.out.println(s));
    }

    public static void printString(String s, Print<String> print) {
        print.print(s);
    }

    @FunctionalInterface
    interface Print<T> {
        void print(T s);
    }
}
