import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByLastDigit {


    static class LastDigitComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer num1, Integer num2) {

            return (num1 % 10) - (num2 % 10);
        }
    }

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(23);
        numbers.add(45);
        numbers.add(12);
        numbers.add(89);
        numbers.add(34);


        Collections.sort(numbers, new LastDigitComparator());


        System.out.println("Numbers sorted by last digit: " + numbers);
    }
}