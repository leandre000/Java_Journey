 public class Weekday0rWeekend {
    enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
    public static void main(String[] args) {

        Day day = Day.SATURDAY;

        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                System.out.println(day + " is a weekday.");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println(day + " is a weekend.");
                break;
        }
    }



}
