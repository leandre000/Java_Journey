import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class NumberWithRemainder implements Comparable<NumberWithRemainder> {
    int number;
    int remainder;

    public NumberWithRemainder(int number, int remainder) {
        this.number = number;
        this.remainder = remainder;
    }

    @Override
    public int compareTo(NumberWithRemainder other) {
        return Integer.compare(this.remainder, other.remainder);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}

