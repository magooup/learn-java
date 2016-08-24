package com.magooup.learn;


import javax.script.ScriptException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class Learner {

    public static void main(String[] args) throws InterruptedException, NoSuchMethodException, MalformedURLException, InvocationTargetException, IllegalAccessException, ScriptException, ClassNotFoundException {
//        Throwable ex = new Throwable();
//        StackTraceElement[] stackElements = ex.getStackTrace();
//
//        StackTraceElement stackElements[] = Thread.currentThread().getStackTrace();
//
//        if (stackElements != null) {
//            for (int i = 0; i < stackElements.length; i++) {
//                System.out.println(stackElements[i].getClassName());
//                System.out.println(stackElements[i].getFileName());
//                System.out.println(stackElements[i].getLineNumber());
//                System.out.println(stackElements[i].getMethodName());
//                System.out.println("-----------------------------------");
//            }
//        }

        String str = "123";
        print(str);
        str = "123456";
    }

    static void print(String s) {
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}



