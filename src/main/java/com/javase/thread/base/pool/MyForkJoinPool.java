package com.javase.thread.base.pool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author miclefengzss
 * 2023/2/6 下午2:46
 */
public class MyForkJoinPool {

    static int[] nums = new int[1000000];

    public static final int MAX_NUM = 50000;

    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println(Arrays.stream(nums).sum());
    }

    static class AddTask extends RecursiveAction {

        int start, end;

        AddTask(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from: " + start + " to: " + end + " = " + sum);
            } else {
                int middle = start + (end - start) / 2;

                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);

                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    static class AddTaskRet extends RecursiveTask<Long> {

        int start, end;

        AddTaskRet(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }

            int middle = start + (end - start) / 2;

            AddTaskRet subTask1 = new AddTaskRet(start, middle);
            AddTaskRet subTask2 = new AddTaskRet(middle, end);
            subTask1.fork();
            subTask2.fork();
            return subTask1.join() + subTask2.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0, nums.length);
        fjp.execute(task);
        task.join();

        ForkJoinPool fjpRet = new ForkJoinPool();
        AddTaskRet taskRet = new AddTaskRet(0, nums.length);
        fjpRet.execute(taskRet);
        long result = taskRet.join();
        System.out.println(" ----- " + result);

    }
}
