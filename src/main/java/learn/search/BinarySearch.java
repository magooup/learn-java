package learn.search;

/**
 * BinarySearch
 *
 * @author zhiyong.ma(zhiyong.ma@roboo.com)
 * @create 2015年6月29日
 */
public class BinarySearch {
    static int count = 0;

    public static void main(String[] args) {
        Integer[] array = {1, 4, 9, 10, 44, 99, 109, 880, 990, 1902, 2098, 5555, 8888, 9999, 10000};
        int target = 2;
        System.out.println("Target " + target);
        int pos = search(array, target, 0, array.length - 1);
        System.out.println("The pos " + pos);
        if (pos >= 0) {
            System.out.println("Vertify the target " + array[pos]);
        }
        System.out.println("Totally search " + count);
    }

    private static <T> int search(Comparable<T>[] array, T target, int head, int tail) {
        count++; //
        if (head > tail) {
            return -1;
        } else if (head == tail) {
            return array[head].equals(array[tail]) ? head : -1;
        } else {
            int headOffset = array[head].compareTo(target);
            int tailOffset = array[tail].compareTo(target);
            if (headOffset > 0 || tailOffset < 0) {
                return -1;
            } else if (headOffset == 0) {
                return head;
            } else if (tailOffset == 0) {
                return tail;
            } else {
                int middle = (head + tail) / 2;
                int offset = array[middle].compareTo(target);
                if (tail - head == 2 || tail - head == 1) {
                    return offset == 0 ? middle : -1;
                } else if (offset > 0) {
                    return search(array, target, head, middle);
                } else if (offset < 0) {
                    return search(array, target, middle, tail);
                } else {
                    return middle;
                }
            }
        }
    }
}
