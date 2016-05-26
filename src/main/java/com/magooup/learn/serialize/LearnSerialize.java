package com.magooup.learn.serialize;

import java.io.*;

/**
 * Created by zhiyong.ma on 2016/4/27.
 */
public class LearnSerialize {
    public static void main(String[] args) throws Exception {
        Sub sub = new Sub();
        sub.setName("val-name");
        sub.amstring = "val-string";
        sub.amboolean = true;
        sub.ambyte = 5;
        sub.amlong = 1000l;
        sub.amLong = 10000L;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f:/data-2"));
        oos.writeObject(sub);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f:/data-2"));
        Sub other = (Sub) ois.readObject();
        System.out.println(other.G);

    }


    static class Super implements Serializable {
        public static String G = "G";

        static {
            System.out.println("HeHe");
        }

        private String name;

        public Super() {
            //System.out.println(" i am super");
        }

        public Super(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Sub extends Super implements Serializable {

        private static final long serialVersionUID = 3846752112809249244L;

        String amstring;
        boolean amboolean;
        byte ambyte;
        long amlong;
        Long amLong;

        public Sub() {
        }

        public Sub(String name) {
            super(name);
        }


    }
}
