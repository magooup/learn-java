package com.magooup.learn.nio;

import java.nio.ByteBuffer;

/**
 * Created by zhiyong.ma on 2016/3/11.
 */
public class LearnBuffer {

    public static void main(String[] args) {
        byteBuffer();
    }

    static void byteBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(40);
        for (int i = 0; i < 10; i++) {
            buffer.putInt(i);
        }
        // no flip read
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getInt() + "[" + buffer.position() + "] ");
        }
        System.out.println(" -- no flip read");
        // with flip read
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getInt() + "[" + buffer.position() + "] ");
        }
        System.out.println(" -- with flip read");
        // with clear read
        buffer.clear();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getInt() + "[" + buffer.position() + "] ");
        }
        System.out.println(" -- with clear read");
        // with rewind read
        buffer.rewind();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getInt() + "[" + buffer.position() + "] ");
        }
        System.out.println(" -- with rewind read");
        // with reset read
        buffer.position(0);
        buffer.mark(); // if no mark before reset, throw exception
        buffer.reset();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getInt() + "[" + buffer.position() + "] ");
        }
        System.out.println(" -- with reset read");
        // with double flip read
        buffer.flip();
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getInt() + "[" + buffer.position() + "] ");
        }
        System.out.println(" -- with double flip read");
        // clear after double flip read
        buffer.clear();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getInt() + "[" + buffer.position() + "] ");
        }
        System.out.println(" -- with clear after double flip read");
        // over write
        buffer.flip();
        for (int i = 10; i < 15; i++) {
            buffer.putInt(i);
        }
        buffer.clear();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getInt() + "[" + buffer.position() + "] ");
        }
        System.out.println(" -- with clear after overwrite read");


    }

}
