package com.magooup.learn.sort;

import java.util.Arrays;

/**
 * Created by zhiyong.ma on 2016/3/10.
 */
public class LearnQuickSort {


    public static void main(String[] args) {
        int[] array = {2, 4, 6, 2, 9, 8};
        System.out.println(Arrays.toString(array));
        System.out.println();
        qsort(array, 0, array.length - 1);
        System.out.println();
        System.out.println(Arrays.toString(array));
    }


    static void qsort(int[] array, int head, int tail) {
        int i = head, j = tail;
        int key = array[i];
        while (i < j) {
            while (j > i) {
                if (array[j] >= key) {
                    j--;
                    continue;
                }
                swap(array, i, j);
                System.out.println("i=" + i + ";j=" + j);
                System.out.println(Arrays.toString(array));
                break;
            }
            while (i < j) {
                if (array[i] <= key) {
                    i++;
                    continue;
                }
                swap(array, i, j);
                System.out.println("i=" + i + ";j=" + j);
                System.out.println(Arrays.toString(array));
                break;
            }
            System.out.println();
            System.out.println("i=" + i + ";j=" + j);
            System.out.println(Arrays.toString(array));
            System.out.println();
        }
        if (i != j) {
            throw new RuntimeException("Something is wrong. i=" + i + ";j=" + j);
        }
        if (i - 1 > head) {
            qsort(array, head, i - 1);
        }
        if (j + 1 < tail) {
            qsort(array, j + 1, tail);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
