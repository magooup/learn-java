package com.magooup.learn.backpack;

import common.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhiyong.ma on 2016/5/25.
 */
public class LearnBackpack {

    public static void main(String[] args) {
        int capacity = 12;
        int worth = 15;
        Pair<Integer, Integer>[] inputs = new Pair[]{new Pair(4, 9), new Pair(3, 6), new Pair(5, 4), new Pair(2, 5), new Pair(5, 2)};
        backpack2(inputs, capacity);
        //backpack2(inputs, capacity, worth);
    }

    static void backpack3(Pair<Integer, Integer>[] inputs, int capacity, int worth) {
        int num = inputs.length;
        int[][] dynamics = new int[num + 1][capacity + 1];
        Arrays.fill(dynamics[0], 0);
        for (int k = 1; k <= num; k++) {
            for (int m = 1; m < k; m++) {
                Arrays.fill(dynamics[m], 0);
            }
            int totol = 0;
            for (int i = k; i <= num; i++) {
                for (int j = 0; j <= capacity; j++) {
                    dynamics[i][j] = dynamics[i - 1][j];
                    if (j >= inputs[i - 1].getFirst()) {
                        int max = Math.max(dynamics[i - 1][j - inputs[i - 1].getFirst()] + inputs[i - 1].getSecond(), dynamics[i][j]);
                        if (max <= worth) {
                            dynamics[i][j] = max;
                        }
                    }
                }
                if (dynamics[i][capacity] > dynamics[i - 1][capacity]) {
                    System.out.println("Push: " + inputs[i - 1].toString());
                    totol += inputs[i - 1].getFirst();
                }
            }
            if (dynamics[num][capacity] == worth && totol == capacity) {
                break;
            }
        }
        System.out.println("Max worth is :" + dynamics[num][capacity]);
    }

    // Time:O(Cn) Space:O(Cn)
    static void backpack1(Pair<Integer, Integer>[] inputs, int capacity) {
        int num = inputs.length;
        int[][] dynamics = new int[num + 1][capacity + 1];
        Arrays.fill(dynamics[0], 0);

        List<Pair> list = new ArrayList<>();
        int vCount = 0;

        for (int i = 1; i <= num; i++) {
            for (int j = 0; j <= capacity; j++) {
                dynamics[i][j] = dynamics[i - 1][j];
                if (j >= inputs[i - 1].getFirst()) {
                    dynamics[i][j] = Math.max(dynamics[i - 1][j - inputs[i - 1].getFirst()] + inputs[i - 1].getSecond(), dynamics[i][j]);
                }
            }

            if (dynamics[i][capacity] > dynamics[i - 1][capacity]) {
                vCount += inputs[i - 1].getFirst();
                if (vCount > capacity) {
                    Pair<Integer, Integer> last = list.remove(list.size() - 1);
                    vCount -= last.getFirst();
                }
                list.add(inputs[i - 1]);
            }
        }

        System.out.println("Max worth is :" + dynamics[num][capacity]);

        for (Pair pair : list) {
            System.out.println(pair.getFirst() + "," + pair.getSecond());
        }

    }

    // Time:O(Cn) Space:O(C)
    static void backpack2(Pair<Integer, Integer>[] inputs, int capacity) {
        int num = inputs.length;
        int[] dynamics = new int[capacity + 1];
        Arrays.fill(dynamics, 0);

        List<Pair> list = new ArrayList<>();
        int vCount = 0;

        for (int i = 1; i <= num; i++) {
            int last = dynamics[capacity];
            for (int j = capacity; j >= inputs[i - 1].getFirst(); j--) {
                dynamics[j] = Math.max(dynamics[j - inputs[i - 1].getFirst()] + inputs[i - 1].getSecond(), dynamics[j]);
            }
            if (dynamics[capacity] > last) {
                vCount += inputs[i - 1].getFirst();
                if (vCount > capacity) {
                    Pair<Integer, Integer> lastPush = list.remove(list.size() - 1);
                    vCount -= lastPush.getFirst();
                }
                list.add(inputs[i - 1]);
            }
        }

        System.out.println("Max worth is :" + dynamics[capacity]);

        for (Pair pair : list) {
            System.out.println(pair.getFirst() + "," + pair.getSecond());
        }
    }
}
