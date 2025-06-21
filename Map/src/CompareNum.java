import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompareNum {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(19, 25, 80, 47, 41, 12);


        List<NumberWithRemainder> pairedList = new ArrayList<>();
        for (Integer num : list) {
            int rem = num % 10;
            pairedList.add(new NumberWithRemainder(num, rem));
        }


        Collections.sort(pairedList);


        System.out.println("Numbers sorted by their remainders:");
        System.out.println(pairedList);
    }
}