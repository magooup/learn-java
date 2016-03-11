package com.magooup.learn.syncronize;

public class WaitAndNotify {
    public static Object object = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread3 thread3 = new Thread3();

        thread1.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + " call wait()");
                    object.wait();
                    System.out.println("线程" + Thread.currentThread().getName() + " done wait()");
                } catch (InterruptedException e) {
                }
                System.out.println("线程" + Thread.currentThread().getName() + " get lock");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() + " call notify()");
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + " done notify()");
            }
            System.out.println("线程" + Thread.currentThread().getName() + " release lock");
        }
    }

    static class Thread3 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() + " normally");
            }
        }
    }
}