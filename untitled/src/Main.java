public class Main{
    public static void main(String[] args) {
        String input = "Aagent 890";

        String pattern = "^[a-zA-Z][a-zA-Z]*\\s+\\d{2,3}$";

        if (input.matches(pattern)) {
            System.out.println("True.");
        } else {
            System.out.println("False.");
        }
    }
}