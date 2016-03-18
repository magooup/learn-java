package com.magooup.learn.clazz;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by zhiyong.ma on 2016/3/17.
 */
public class LearnClass {
    static Class<?>[] EMPTY_ARRAY = new Class[]{};

    public static void main(String[] args) throws Throwable {

        demoForConstructors();
    }

    public static void demoForAllMethods() {
        printClassAllMethod(GrandSon1.class);
        printClassAllMethod(GrandSon2.class);
    }


    public static void demoForMethods() {
        printClassMethods(GrandSon1.class, "call1", EMPTY_ARRAY);
        printClassMethods(GrandSon1.class, "call2", EMPTY_ARRAY);
        printClassMethods(GrandSon1.class, "call3", EMPTY_ARRAY);
        printClassMethods(GrandSon1.class, "call4", EMPTY_ARRAY);

        printClassMethods(GrandSon1.class, "call5", EMPTY_ARRAY);
        printClassMethods(GrandSon2.class, "call5", EMPTY_ARRAY);
    }

    public static void demoForConstructors() {
        printClassConstructors(GrandSon1.class, EMPTY_ARRAY);
        printClassConstructors(GrandSon2.class, EMPTY_ARRAY);
    }

    public static void demoForNames() {
        printClassNames(int.class);
        printClassNames(Integer.class);
        int[] c = new int[1];
        printClassNames(c.getClass());
        Integer[] d = new Integer[1];
        printClassNames(d.getClass());
        Inner e = new Inner();
        printClassNames(e.getClass());
        InnerInterface f = new InnerInterface() {
        };
        printClassNames(f.getClass());
        InnerAbstract g = new InnerAbstract() {
        };
        printClassNames(g.getClass());
    }

    static void printClassAllMethod(Class clazz) {
        System.out.println(String.format("All methods for %s", clazz));
        for (Method method : clazz.getMethods()) {
            System.out.println("\t" + method);
        }
        System.out.println(String.format("All declaredMethods for %s", clazz));
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("\t" + method);
        }
        System.out.println();
    }

    static void printClassMethods(Class clazz, String methodName, Class[] paramTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Class[").append(clazz).append("] with method[");
        try {
            sb.append(clazz.getMethod(methodName, paramTypes));
        } catch (NoSuchMethodException e) {
            sb.append("null");
        }
        sb.append("] with declaredMethod[");
        try {
            sb.append(clazz.getDeclaredMethod(methodName, paramTypes));
        } catch (NoSuchMethodException e) {
            sb.append("null");
        }
        sb.append("] use method[").append(methodName).append("] and paramTypes[").append(Arrays.toString(paramTypes)).append("]");
        System.out.println(sb);
    }

    static void printClassConstructors(Class clazz, Class[] paramTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Class[").append(clazz).append("] with constructor[");
        try {
            sb.append(clazz.getConstructor(paramTypes));
        } catch (NoSuchMethodException e) {
            sb.append("null");
        }
        sb.append("] with declaredConstructor[");
        try {
            sb.append(clazz.getDeclaredConstructor(paramTypes));
        } catch (NoSuchMethodException e) {
            sb.append("null");
        }
        sb.append("] use paramTypes[").append(Arrays.toString(paramTypes)).append("]");
        System.out.println(sb);
    }

    static void printClassNames(Class clazz) {
        System.out.println(String.format("Class[%s] with name[%s] with canonicalName[%s]", clazz, clazz.getName(), clazz.getCanonicalName()));
    }

    interface InnerInterface {
    }

    static class Parent {
        Parent() {
        }

        public void call3() {
        }

        void call1() {
        }

    }

    static class Son extends Parent {
        Son() {
        }

        public void call4() {
        }

        void call2() {
        }
    }

    static class GrandSon1 extends Son {
        GrandSon1() {
        }

        void call5() {
        }
    }

    static class GrandSon2 extends Son {

        public void call5() {
        }
    }

    static abstract class InnerAbstract {
    }

    static class Inner {

    }
}
