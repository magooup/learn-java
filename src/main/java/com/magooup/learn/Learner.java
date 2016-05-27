package com.magooup.learn;


import java.io.IOException;
import java.util.*;

public class Learner {

    // The number strings
    static String[] NUMBERS = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
    // Generate the VOLUMES and VALUES
    static int[] VOLUMES = new int[NUMBERS.length];
    static int[] VALUES = new int[NUMBERS.length];

    static {
        for (int i = 0; i < NUMBERS.length; i++) {
            String num = NUMBERS[i];
            VOLUMES[i] = num.length();
            VALUES[i] = 0;
            for (char c : num.toCharArray()) {
                VALUES[i] += (int) c - 64;
            }
        }
        //System.out.println("Volumes: " + Arrays.toString(VOLUMES));
        //System.out.println("Values: " + Arrays.toString(VALUES));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        decrypt("ETHER");
        decrypt("OZONETOWER");
        decrypt("OURNEONFOE");
    }

    static void decrypt(String input) {
        int capacity = input.length();
        int worth = 0;
        for (char c : input.toCharArray()) {
            worth += (int) c - 64;
        }
        //System.out.println("Capacity: " + capacity);
        //System.out.println("Worth: " + worth);
        System.out.print("Decrypt the \"" + input + "\": ");
        List<Integer> list = backpack_variant(VOLUMES, VALUES, capacity, worth);
        if (null != list) {
            Collections.sort(list);
            for (Integer index : list) {
                System.out.print(String.format("[%d, %s] ", index, NUMBERS[index]));
            }
        }
        System.out.println();
    }


    static List<Integer> backpack_variant(int[] volumes, int[] values, int capacity, int worth) {
        assert volumes.length == values.length;
        int length = values.length;
        Set<Record>[] records = new Set[capacity + 1];
        //
        for (int i = 1; i <= length; i++) {
            int volume = volumes[i - 1];
            int value = values[i - 1];
            for (int j = volume; j <= capacity; j++) {
                Set<Record> last = records[j - volume];
                Set<Record> current = new HashSet<>();
                if (null != last) {
                    for (Record record : last) {
                        List<Integer> indexes = new ArrayList<>(record.indexes);
                        indexes.add(i - 1);
                        int sum = record.worth + value;
                        current.add(new Record(indexes, sum));
                    }
                }
                if (volume == j) {
                    current.add(new Record(new ArrayList<>(Arrays.asList(i - 1)), value));
                }
                if (current.size() > 0) {
                    if (null == records[j]) {
                        records[j] = current;
                    } else {
                        records[j].addAll(current);
                    }
                }
            }
        }
        // Pick the target worth
        Set<Record> result = records[capacity];
        if (null == result) {
            System.out.println("No result");
        } else {
            for (Record record : result) {
                if (record.worth == worth) {
                    return record.indexes;
                }
            }
        }
        return null;
    }

    static class Record {
        List<Integer> indexes;
        int worth;

        Record(List<Integer> indexes, int worth) {
            this.indexes = indexes;
            this.worth = worth;
        }
    }

}


