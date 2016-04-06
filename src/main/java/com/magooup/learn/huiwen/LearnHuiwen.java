package com.magooup.learn.huiwen;

/**
 * Created by zhiyong.ma on 2016/3/30.
 */
public class LearnHuiwen {


    static boolean method3(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left--;
            right--;
        }
        return true;
    }


    static boolean method2(String str) {
        for (int i = 0, j = str.length() - 1; i <= str.length() / 2; i++) {
            char left = str.charAt(i);
            char right = str.charAt(j - i);
            if (left != right) {
                return false;
            }
        }
        return true;

    }

    static boolean method1(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();

        return str.equals(sb.toString());
    }

}
