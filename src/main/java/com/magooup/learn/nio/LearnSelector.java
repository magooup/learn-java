package com.magooup.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;

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

    public static void main(String[] args) {

    }

    static void selector() throws IOException {


    }

    static class Server {
        void start() {
            Thread damon = new Thread() {
                @Override
                public void run() {
                    ServerSocket server = null;
                    try {
                        server = new ServerSocket();
                        server.bind(new InetSocketAddress("127.0.0.1", 8888));
                        setDaemon(true);
                        while (true) {
                            try {
                                Socket socket = null;
                                while (null != (socket = server.accept())) {

                                }
                            } catch (IOException e) {

                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    }


}
