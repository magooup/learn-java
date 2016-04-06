package com.magooup.learn.sub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhiyong.ma on 2016/3/31.
 */
public class LearnSublist {

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(new String[]{"F", "A", "B", "C", "D", "Z", "A", "F", "C"}));
        ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(new String[]{"A", "B", "C", "F", "D", "Z"}));


        greatestSub(list1, list2);
    }

    static void greatestSub(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> result = null;
        if (Arrays.equals(list1.toArray(), list2.toArray())) {
            result = list1;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                String word = list1.get(i);
                //int index = list2.indexOf(word); // if list2 has repeat words, this can not give a exact result.
                for (int index : indicesOf(list2, word)) { // support repeat words in list2, but need a small loop.
                    if (index >= 0) {
                        int ori = i;
                        ArrayList<String> temp = new ArrayList<String>();
                        temp.add(word);
                        //while (true) {
                        //    int pos1 = (i + 1) % list1.size();
                        //    int pos2 = (index + 1) % list2.size();
                        //    if (list1.get(pos1).equals(list2.get(pos2))) {
                        while (index < list2.size() - 1) {
                            if (i + 1 < list1.size() && list1.get(i + 1).equals(list2.get(index + 1))) {
                                temp.add(list1.get(i + 1));
                                i++;
                                index++;
                            } else {
                                break;
                            }
                        }
                        System.out.println(String.format("Found a subset: %s", temp));
                        if (null == result || temp.size() > result.size()) {
                            result = temp;
                        }
                    }
                }
            }
        }
        if (null != result) {
            System.out.println("The greatest subset is: " + result);
        } else {
            System.out.println("No subset found.");
        }
    }

    static Integer[] indicesOf(ArrayList<String> list, String obj) {
        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (obj.equals(list.get(i))) {
                indices.add(i);
            }
        }
        return indices.toArray(new Integer[]{});
    }
}
