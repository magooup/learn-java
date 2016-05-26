package com.magooup.learn.encrypt;

/**
 * Created by zhiyong.ma on 2016/4/28.
 */
public class LearnEncrypt {

    public static void main(String[] args) {
        simple();
    }

    static void simple() {
        String str = "PLAINTEXTMESSAGEPLAINTEXTMESSAGE";

        // To get the row and column
        double sqrt = Math.sqrt(str.length());
        int row = (int)sqrt; // be careful
        int column = row + (sqrt % 1d > 0 ? 1 : 0);

        // To encrypt
        char[][] array = new char[row][column];

        for (int i = 0, cursor = 0; i < row * column; i++) {
            array[i % row][i / row] = cursor < str.length() ? str.charAt(cursor++) : 0;
        }

        //// Just a equivalent to above
        //for (int i = 0, cursor = 0; i < column; i++) {
        //    for (int j = 0; j < row; j++) {
        //        array[j][i] = cursor < str.length() ? str.charAt(cursor++) : 0;
        //    }
        //}

        // Print the encrypted array
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < row * column; i++) {
            char c = array[i / column][i % column];
            System.out.print(c + ((i + 1) % column == 0 ? "\n" : " "));
            encrypted.append(c);
        }
        System.out.println("The encrypted string: " + encrypted);


        // To decrypt
        System.out.println();

        int decryptRow = array.length;
        if (decryptRow < 1) {
            throw new RuntimeException("Empty array");
        }

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < row * column; i++) {
            char c = array[i % row][i / row];
            System.out.print(c + ((i + 1) % row == 0 ? "\n" : " "));
            decrypted.append(c);
        }
        System.out.println("The decrypted string: " + decrypted);
    }
}
