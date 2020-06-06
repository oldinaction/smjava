package cn.aezo.javase.msb.c08_2_JMH;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author smalle
 * @date 2020-05-30 09:39
 */
public class T01_PS {
    private static List<Integer> nums = new ArrayList<>();

    static {
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }
    }

    static void foreach() {
        nums.forEach(T01_PS::isPrime);
    }

    // 内部使用了ForkJoinPool
    static void parallelStreamForeach() {
        nums.parallelStream().forEach(T01_PS::isPrime);
    }

    // 判断是否为质数
    static boolean isPrime(Integer num) {
        for (int i=2; i<=num/2; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        parallelStreamForeach();
    }
}
