
public class Enum {
   public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        for (Day day : Day.values()) {
            System.out.println(day);
        }
    }
}