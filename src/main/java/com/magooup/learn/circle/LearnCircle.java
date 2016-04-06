package com.magooup.learn.circle;

import java.util.Arrays;

/**
 * Created by zhiyong.ma on 2016/3/31.
 */
public class LearnCircle {
    public static void main(String[] args) {
        int cyclesUse = 4;
        int playersUse = 8;

        circle(cyclesUse, playersUse);
    }

    private static void circle(int cyclesUse, int playersUse) {
        int count = 0;
        int[] array = new int[playersUse];
        while (count < playersUse) {
            array[count] = count;
            count++;
        }


        int remainNum = playersUse;
        int loopCount = 0;
        int playerCount = 0;

        while (remainNum > 1) {
            int pos = ++loopCount % playersUse;
            int playerPos = pos == 0 ? playersUse - 1 : pos - 1;
            playerCount++;
            if (array[playerPos] == 999) {
                playerCount--;
                continue;
            }
            if (playerCount % cyclesUse == 0) {
                array[playerPos] = 999;
                remainNum--;
                System.out.println(String.format("Out %dth player(array[%d])\t%s", playerPos + 1, playerPos, Arrays.toString(array)));
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 999) {
                System.out.println(String.format("The winer is %dth player(array[%d])", i + 1, i));
            }
        }
    }
}
