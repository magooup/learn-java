package com.magooup.learn;

public class Learner {

    {
    }

    public static void main(String[] args) throws Throwable {

        int a = 1;
        printClassNames(a);
        Integer b = 1;
        printClassNames(b);
        int[] c = new int[1];
        printClassNames(c);
        Integer[] d = new Integer[1];
        printClassNames(d);
        Inner e = new Inner();
        printClassNames(e);
        InnerInterface f = new InnerInterface() {
        };
        printClassNames(f);
        InnerAbstract g = new InnerAbstract() {
        };
        printClassNames(g);
        Learner h = new Learner();
        printClassNames(h);


    }

    static void printClassNames(Object object) {
        System.out.println(String.format("Class[%s] with name[%s] with canonicalName[%s]", object.getClass(), object.getClass().getName(),
                object.getClass().getCanonicalName()));
    }

    static interface InnerInterface {
    }

    static abstract class InnerAbstract {
    }

    static class Inner {

    }


}
