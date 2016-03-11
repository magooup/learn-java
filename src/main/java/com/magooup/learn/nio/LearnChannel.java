package com.magooup.learn.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhiyong.ma on 2016/3/11.
 */
public class LearnChannel {

    public static void main(String[] args) throws IOException {
        fileChannel();
    }

    static void fileChannel() throws IOException {
        RandomAccessFile file = new RandomAccessFile("f:/file", "rw");

        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(52);

        for (int i = 'a'; i <= 'z'; i++) {
            char c = (char) i;
            buffer.putChar(c);
        }
        buffer.flip(); // must flip(limit=position,position=0)
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        channel.force(true);

        buffer.clear();
        channel.position(0); // Before read, set position to 0
        while (-1 != channel.read(buffer)) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print(buffer.getChar());
            }
            buffer.clear();
        }


    }


}
