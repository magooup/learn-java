package com.magooup.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by zhiyong.ma on 2016/3/11.
 */
public class LearnSelector {

    static Selector selector;

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        Server.start();
    }

    public static void main(String[] args) throws IOException {
        selector();
    }

    static void selector() throws IOException {

        SocketChannel channel1 = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        channel1.configureBlocking(false);
        channel1.register(selector, SelectionKey.OP_WRITE);
        SocketChannel channel2 = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        channel2.configureBlocking(false);
        channel2.register(selector, SelectionKey.OP_WRITE);
        while (true) {
            int num = selector.select(1000);
            System.out.println("Ready " + num);
            if (num > 0) {
                Set<SelectionKey> set = selector.selectedKeys();
                for (final SelectionKey key : set) {

                    if (key.isWritable()) {
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                    SocketChannel channel = (SocketChannel) key.channel();
                                    buffer.put((channel + "").getBytes());
                                    buffer.flip();
                                    while (buffer.hasRemaining()) {
                                        System.out.println("Do a write: " + channel);
                                        channel.write(buffer);
                                    }
                                    buffer.clear();
                                    while (true) {
                                        while (-1 != (channel.read(buffer))) {
                                            buffer.flip();
                                            if (buffer.limit() <= 0) {
                                                continue;
                                            }
                                            byte[] bytes = new byte[buffer.limit()];
                                            buffer.get(bytes);
                                            System.out.println("Accept a response: " + new String(bytes));
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }

                    if (key.isAcceptable()) {
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                                    SocketChannel channel = null;
                                    if (null != (channel = server.accept())) {
                                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                                        buffer.clear();
                                        while (-1 != channel.read(buffer)) {
                                            buffer.flip();
                                            while (buffer.hasRemaining()) {
                                                byte[] bytes = new byte[buffer.limit()];
                                                buffer.get(bytes);
                                                System.out.println("Do a read: " + new String(bytes));
                                            }
                                            buffer.clear();
                                        }
                                        buffer.clear();
                                        buffer.put((server + "").getBytes());
                                        System.out.println("Write a response: " + server);
                                        buffer.flip();
                                        channel.write(buffer);
                                        buffer.clear();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Server {
        static void start() {
            Thread damon = new Thread() {
                @Override
                public void run() {
                    try {
                        ServerSocketChannel server1 = ServerSocketChannel.open();
                        server1.configureBlocking(false);
                        server1.bind(new InetSocketAddress("127.0.0.1", 8888));
                        System.out.println("Server1 started, listening at 127.0.0.1:8888");
                        ServerSocketChannel server2 = ServerSocketChannel.open();
                        server2.configureBlocking(false);
                        server2.bind(new InetSocketAddress("127.0.0.1", 9999));
                        System.out.println("Server2 started, listening at 127.0.0.1:9999");

                        server1.register(selector, SelectionKey.OP_ACCEPT);
                        server2.register(selector, SelectionKey.OP_ACCEPT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            damon.start();
        }
    }


}
