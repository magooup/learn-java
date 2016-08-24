//package com.magooup.learn.backpack;
//
//import common.Pair;
//
//import java.util.*;
//
///**
// * Created by zhiyong.ma on 2016/5/25.
// */
//public class LearnBackpack {
//
//    public static void main(String[] args) {
//        int capacity = 10;
//        int worth = 128;
//        Pair<Integer, Integer>[] inputs = new Pair[]{new Pair(4, 64), new Pair(3, 34), new Pair(3,58), new Pair(5, 56), new Pair(4, 60)};
//        //backpack1_2(inputs, capacity);
//        backpack2_2(inputs, capacity, worth);
//    }
//
//
//    static void backpack2_1(Pair<Integer, Integer>[] inputs, int capacity, int worth) {
//        int num = inputs.length;
//
//        Set<Pair<List<Integer>, Integer>>[] records = new Set[capacity + 1];
//
//        for (int i = 1; i <= num; i++) {
//
//            Pair<Integer, Integer> input = inputs[i - 1];
//            int volume = input.getFirst();
//            int value = input.getSecond();
//            for (int j = capacity; j >= volume; j--) {
//                Set<Pair<List<Integer>, Integer>> lastRecord = records[j - volume];
//                Set<Pair<List<Integer>, Integer>> newRecord = new HashSet<>();
//                if (null != lastRecord) {
//                    for (Pair<List<Integer>, Integer> pair : lastRecord) {
//                        List<Integer> newNums = new ArrayList<>();
//                        newNums.addAll(pair.getFirst());
//                        newNums.add(i - 1);
//                        int newSum = pair.getSecond() + value;
//                        newRecord.add(new Pair(newNums, newSum));
//                    }
//                }
//                if (volume == j) {
//                    List<Integer> nums = new ArrayList<>();
//                    nums.add(i - 1);
//                    newRecord.add(new Pair(nums, value));
//                }
//                if (newRecord.size() > 0) {
//                    if (null == records[j]) {
//                        records[j] = newRecord;
//                    } else {
//                        records[j].addAll(newRecord);
//                    }
//                }
//            }
//
//        }
//
//        Set<Pair<List<Integer>, Integer>> result = records[capacity];
//        if (null == result) {
//            System.out.println("No result");
//        } else {
//            for (Pair<List<Integer>, Integer> pair : result) {
//                System.out.println(pair.getFirst());
//                System.out.println(pair.getSecond());
//                System.out.println();
//            }
//        }
//
//
//    }
//
//    static void backpack2_2(Pair<Integer, Integer>[] inputs, int capacity, int worth) {
//        int num = inputs.length;
//
//        Set<Pair<List<Integer>, Integer>>[] records = new Set[capacity + 1];
//
//        for (int i = 1; i <= num; i++) {
//
//            Pair<Integer, Integer> input = inputs[i - 1];
//            int volume = input.getFirst();
//            int value = input.getSecond();
//            for (int j = volume; j <= capacity; j++) {
//                Set<Pair<List<Integer>, Integer>> lastRecord = records[j - volume];
//                Set<Pair<List<Integer>, Integer>> newRecord = new HashSet<>();
//                if (null != lastRecord) {
//                    for (Pair<List<Integer>, Integer> pair : lastRecord) {
//                        List<Integer> newNums = new ArrayList<>();
//                        newNums.addAll(pair.getFirst());
//                        newNums.add(i - 1);
//                        int newSum = pair.getSecond() + value;
//                        newRecord.add(new Pair(newNums, newSum));
//                    }
//                }
//                if (volume == j) {
//                    List<Integer> nums = new ArrayList<>();
//                    nums.add(i - 1);
//                    newRecord.add(new Pair(nums, value));
//                }
//                if (newRecord.size() > 0) {
//                    if (null == records[j]) {
//                        records[j] = newRecord;
//                    } else {
//                        records[j].addAll(newRecord);
//                    }
//                }
//            }
//
//        }
//
//        Set<Pair<List<Integer>, Integer>> result = records[capacity];
//        if (null == result) {
//            System.out.println("No result");
//        } else {
//            for (Pair<List<Integer>, Integer> pair : result) {
//                System.out.println(pair.getFirst());
//                System.out.println(pair.getSecond());
//                System.out.println();
//            }
//        }
//
//
//    }
//
//    // Time:O(Cn) Space:O(Cn)
//    static void backpack1_1(Pair<Integer, Integer>[] inputs, int capacity) {
//        int num = inputs.length;
//        int[][] dynamics = new int[num + 1][capacity + 1];
//        Arrays.fill(dynamics[0], 0);
//
//        List<Pair> list = new ArrayList<>();
//        int vCount = 0;
//
//        for (int i = 1; i <= num; i++) {
//            for (int j = 0; j <= capacity; j++) {
//                dynamics[i][j] = dynamics[i - 1][j];
//                if (j >= inputs[i - 1].getFirst()) {
//                    dynamics[i][j] = Math.max(dynamics[i - 1][j - inputs[i - 1].getFirst()] + inputs[i - 1].getSecond(), dynamics[i][j]);
//                }
//            }
//
//            if (dynamics[i][capacity] > dynamics[i - 1][capacity]) {
//                vCount += inputs[i - 1].getFirst();
//                if (vCount > capacity) {
//                    Pair<Integer, Integer> last = list.remove(list.size() - 1);
//                    vCount -= last.getFirst();
//                }
//                list.add(inputs[i - 1]);
//            }
//            System.out.println(vCount);
//        }
//
//        System.out.println("Max worth is :" + dynamics[num][capacity]);
//
//        for (Pair pair : list) {
//            System.out.println(pair.getFirst() + "," + pair.getSecond());
//        }
//
//    }
//
//    // Time:O(Cn) Space:O(C)
//    static void backpack1_2(Pair<Integer, Integer>[] inputs, int capacity) {
//        int num = inputs.length;
//        int[] dynamics = new int[capacity + 1];
//        Arrays.fill(dynamics, 0);
//
//        List<Pair> list = new ArrayList<>();
//        int vCount = 0;
//
//        for (int i = 1; i <= num; i++) {
//            int last = dynamics[capacity];
//            for (int j = capacity; j >= inputs[i - 1].getFirst(); j--) {
//                dynamics[j] = Math.max(dynamics[j - inputs[i - 1].getFirst()] + inputs[i - 1].getSecond(), dynamics[j]);
//            }
//            if (dynamics[capacity] > last) {
//                vCount += inputs[i - 1].getFirst();
//                if (vCount > capacity) {
//                    Pair<Integer, Integer> lastPush = list.remove(list.size() - 1);
//                    vCount -= lastPush.getFirst();
//                }
//                list.add(inputs[i - 1]);
//            }
//        }
//
//        System.out.println("Max worth is :" + dynamics[capacity]);
//
//        for (Pair pair : list) {
//            System.out.println(pair.getFirst() + "," + pair.getSecond());
//        }
//        System.out.println(vCount);
//    }
//}
