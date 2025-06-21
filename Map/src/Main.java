import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Boy());
        Thread t2 = new Thread(new Girl());

        t1.start();
        t2.start();





//        Integer[] arr = {19, 25, 80, 47, 41, 12};
//
//
//        Comparator<Integer> lastDigitComparator = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer a, Integer b) {
//                return Integer.compare(a % 10, b % 10);
//            }
//        };
//
//
//        Arrays.sort(arr, lastDigitComparator);
//
//
//        System.out.println(Arrays.toString(arr));
    }
}


