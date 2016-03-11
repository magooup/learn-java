package learn.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LearnCollection {

    public static void main(String[] args) {
        learnHash();
    }

    public static void learnHash() {
        byte[] b1 = "a".getBytes();
        byte[] b2 = "a".getBytes();

        System.out.println(b1.hashCode());
        System.out.println(b2.hashCode());
    }

    public static void learnSet() {
        Set<byte[]> set_1 = new HashSet<byte[]>();
        for (int i = 0; i < 100000; i++) {
            String s = "I am a string, my number is " + i;
            set_1.add(s.getBytes());
        }
        Set<byte[]> set_2 = new HashSet<byte[]>();
        for (int i = 50000; i < 100000; i++) {
            String s = "I am a string, my number is " + i;
            set_2.add(s.getBytes());
        }

        Set<byte[]> set_3 = null;
        Map<byte[], Object> map = new HashMap<byte[], Object>();
        for (int i = 50000; i < 100000; i++) {
            String s = "I am a string, my number is " + i;
            map.put(s.getBytes(), null);
        }
        set_3 = map.keySet();

        long start = System.currentTimeMillis();
        set_1.retainAll(set_3);
        long end1 = System.currentTimeMillis();
        System.out.println("Retain_all spends " + (end1 - start) + " ms.");
        System.out.println(set_1.size());

    }

    public static void learnAddAllSpend() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            String s = "I am a string, my number is " + i;
            list.add(s);
        }

        Set<String> set = new HashSet<String>();
        long start = System.currentTimeMillis();
        set.addAll(list);
        long end1 = System.currentTimeMillis();
        System.out.println("Spends " + (end1 - start));

        Set<String> setNew = new HashSet<String>();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            setNew.add(it.next());
            // it.remove();
        }
        list.clear();
        long end2 = System.currentTimeMillis();
        System.out.println("Spends " + (end2 - end1));

    }

    public static void learnContainSpend() {

        List<String> list_1 = new ArrayList<String>();
        Set<String> set_1 = new HashSet<String>();
        Set<String> set2_1 = new LinkedHashSet<String>();

        for (int i = 0; i < 1000000; i++) {
            String s = "I am a string, my number is " + i;
            list_1.add(s);
            set_1.add(s);
            set2_1.add(s);
        }

        //

        List<String> list_2 = new ArrayList<String>();
        Set<String> set_2 = new HashSet<String>();
        Set<String> set2_2 = new LinkedHashSet<String>();

        for (int i = 500000; i < 1000000; i++) {
            String s = "I am a string, my number is " + i;
            list_2.add(s);
            set_2.add(s);
            set2_2.add(s);
        }
        //
        String target = "I am a string, my number is 888888";
        long start = System.currentTimeMillis();
        System.out.println(list_1.contains(target));
        long end1 = System.currentTimeMillis();
        System.out.println("List contains spends " + (end1 - start) + " ms.");

        System.out.println(set_2.contains(target));
        long end2 = System.currentTimeMillis();
        System.out.println("Hashset contains spends " + (end2 - end1) + " ms.");

        System.out.println(set2_2.contains(target));
        long end3 = System.currentTimeMillis();
        System.out.println("LinkedHashSet contains spends " + (end3 - end2) + " ms.");

        //
        long start_1 = System.currentTimeMillis();
        set2_1.retainAll(set2_2);
        long end_1 = System.currentTimeMillis();
        System.out.println("LinkedHashset retainAll spends " + (end_1 - start_1) + " ms.");
        set_1.retainAll(set_2);
        long end_2 = System.currentTimeMillis();
        System.out.println("Hashset retainAll spends " + (end_2 - end_1) + " ms.");
        list_1.retainAll(list_2);
        long end_3 = System.currentTimeMillis();
        System.out.println("ArrayList retainAll spends " + (end_3 - end_2) + " ms.");
    }
}
