package cn.aezo.javase.msb.c07_4_ForkJoinPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author smalle
 * @date 2020-06-06 22:30
 */
public class T1_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for(int i=0; i<nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println("---" + Arrays.stream(nums).sum()); // stream api
    }

    // 无返回值
    static class AddTask extends RecursiveAction {
        int start, end;

        AddTask(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected void compute() {
            if(end-start <= MAX_NUM) {
                long sum = 0L;
                for(int i=start; i<end; i++) sum += nums[i];
                System.out.println("from1:" + start + " to1:" + end + " = " + sum);
            } else {
                int middle = start + (end-start)/2;

                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    // 有返回值
    static class AddTaskRet extends RecursiveTask<Long> {

        private static final long serialVersionUID = 1L;
        int start, end;

        AddTaskRet(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected Long compute() {
            if(end-start <= MAX_NUM) {
                long sum = 0L;
                for(int i=start; i<end; i++) sum += nums[i];
                System.out.println("from2:" + start + " to2:" + end + " = " + sum);
                return sum;
            }

            int middle = start + (end-start)/2;

            AddTaskRet subTask1 = new AddTaskRet(start, middle);
            AddTaskRet subTask2 = new AddTaskRet(middle, end);
            subTask1.fork();
            subTask2.fork();

            return subTask1.join() + subTask2.join();
        }

    }

    public static void main(String[] args) throws IOException {
		ForkJoinPool fjp1 = new ForkJoinPool();
		AddTask task1 = new AddTask(0, nums.length);
		fjp1.execute(task1);

        ForkJoinPool fjp2 = new ForkJoinPool();
        AddTaskRet task2 = new AddTaskRet(0, nums.length);
        fjp2.execute(task2);
        long result = task2.join();
        System.out.println("result==>" + result);
    }

}
