import java.util.HashMap;
import java.util.Map;

public class StudentMarks {
    public static void main(String[] args) {

        Map<String, Integer> marks = new HashMap<>();


        marks.put("Bonheur", 75);
        marks.put("Forgiveness", 80);
        marks.put("Ruth", 70);
        marks.put("Chael", 85);


        marks.put("Ruth", 75);


        marks.putIfAbsent("Chael", 95);

        if (marks.containsValue(70)) {
            System.out.println("There is a mark of 70 in the list.");
        } else {
            System.out.println("There is no mark of 70 in the list.");
        }


        System.out.println("Student Marks:");
        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
