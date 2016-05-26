package com.magooup.learn;


import java.io.IOException;
import java.util.Date;

public class Learner {

    public static void main(String[] args) throws IOException, InterruptedException {
        //
        //int capacity = 10;
        //
        //int[] inputVolumes = {4, 3, 5, 2, 5};
        //int[] inputWorths = {9, 6, 1, 4, 1};
        //
        //int num = inputVolumes.length;
        //int[] chosed = new int[num];
        //
        //int[][] dynamics = new int[num + 1][capacity + 1];
        //for (int i = 0; i <= num; i++) {
        //    for (int j = 0; j <= capacity; j++) {
        //        dynamics[i][j] = 0 == i ? 0 : dynamics[i - 1][j];
        //        if (i > 0 && j > inputVolumes[i - 1]) {
        //            dynamics[i][j] = Math.max(dynamics[i - 1][j], dynamics[i - 1][j - inputVolumes[i - 1]] + inputWorths[i - 1]);
        //        }
        //    }
        //}
        //
        //System.out.println("Max worth is " + dynamics[num][capacity]);
        //int j = capacity;
        //for (int i = num; i > 0; i--) {
        //    if (dynamics[i][j] > dynamics[i - 1][j]) {
        //        chosed[i - 1] = 1;
        //        j = j - inputVolumes[i - 1];
        //        System.out.println(String.format("{%d,[%d,%d]}", i - 1, inputVolumes[i - 1], inputWorths[i - 1]));
        //    }
        //}


        System.out.println(getHexDigits(""));
    }
    /**
     * Get a hex value
     *
     * @param value
     * @return
     */
    private static String getHexDigits(String value) {
        boolean negative = false;
        String str = value;
        String hexString = null;
        if (value.startsWith("-")) {
            negative = true;
            str = value.substring(1);
        }
        if (str.startsWith("0x") || str.startsWith("0X")) {
            hexString = str.substring(2);
            if (negative) {
                hexString = "-" + hexString;
            }
            return hexString;
        }
        return null;
    }

}


