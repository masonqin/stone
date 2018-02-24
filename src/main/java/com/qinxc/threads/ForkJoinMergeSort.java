package com.qinxc.threads;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinMergeSort {

    public static int[] generateRandom(int size) {

        Random random = new Random();
        int[] ret = new int[size];

        for (int i = 0; i < ret.length; i++) {
            ret[i] = random.nextInt(10000000);
        }
        return ret;
    }

    public static void sort(int[] array, int left, int right) {
        int mid;
        if (right <= left)
            return;
        else {
            mid = left + (right - left) / 2;
            sort(array, left, mid);
            sort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {

        int lo = left, hi = mid + 1;
        int[] temp = new int[array.length];
        int index;
        for (int i = left; i <= right; i++) {
            temp[i] = array[i];
        }

        for (index = left; index <= right; index++) {
            if (lo <= mid && hi <= right) {
                if (temp[lo] < temp[hi]) {
                    array[index] = temp[lo];
                    lo++;
                } else {
                    array[index] = temp[hi];
                    hi++;
                }
            } else if (lo <= mid && hi > right) {
                array[index] = temp[lo];
                lo++;

            } else if (hi <= right && lo > mid) {
                array[index] = temp[hi];
                hi++;
            }
        }
    }

    public static boolean checkResult(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] < array[i])
                return false;
        }
        return true;
    }

    static class MyTask extends RecursiveTask<int[]> {

        private int[] source;

        public MyTask(int[] source) {
            this.source = source;
        }

        @Override
        protected int[] compute() {
            int sourceLen = source.length;
            if (sourceLen > 2) {
                int midIndex = sourceLen / 2;
                MyTask task1 = new MyTask(Arrays.copyOf(source, midIndex));
                task1.fork();
                MyTask task2 = new MyTask(Arrays.copyOfRange(source, midIndex, sourceLen));
                task2.fork();
                int result1[] = task1.join();
                int result2[] = task2.join();
                int merge[] = mergeInt(result1, result2);
                return merge;
            } else {

                if (sourceLen == 1 || source[0] < source[1])
                    return source;
                else {
                    int target[] = new int[sourceLen];
                    target[0] = source[1];
                    target[1] = source[0];
                    return target;
                }
            }
        }

        private int[] mergeInt(int[] arr1, int[] arr2) {
            int left = 0;
            int right = 0;
            int[] mergeArr = new int[arr1.length + arr2.length];
            if (mergeArr.length == 0) return null;
            for (int i = 0; i < arr1.length + arr2.length; i++) {
                if (arr1.length == left) {
                    mergeArr[i] = arr2[right];
                    right++;
                    continue;
                } else if (arr2.length == right) {
                    mergeArr[i] = arr1[left];
                    left++;
                    continue;
                }
                if (arr1[left] <= arr2[right]) {
                    mergeArr[i] = arr1[left];
                    left++;
                } else {
                    mergeArr[i] = arr2[right];
                    right++;
                }
            }
            return mergeArr;
        }
    }

    public static void main(String args[]) {

        int array_size = 1000000;
        int[] array = generateRandom(array_size);
        int[] array2 = new int[array_size];
        System.arraycopy(array, 0, array2, 0, array_size);
        //single thread mode
        long start = System.currentTimeMillis();
        //sort(array, 0, array_size - 1);
        long end = System.currentTimeMillis();
        //System.out.println("Single thread time: " + (end - start));
        //System.out.println("Check result:" + checkResult(array));
        //fork join mode
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyTask task = new MyTask(array2);
        start = System.currentTimeMillis();
        ForkJoinTask<int[]> taskResult = forkJoinPool.submit(task);
        int[] array3 = null;
        try {
            array3 = taskResult.get();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        end = System.currentTimeMillis();
        System.out.println("Multi thread time: " + (end - start));
        System.out.println("Check result:" + checkResult(array3));

    }

}
