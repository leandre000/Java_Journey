import java.util.EnumSet;

public class Weekday {
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("Weekdays: " + weekdays);
    }
}
