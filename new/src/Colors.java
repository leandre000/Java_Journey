public class Colors {
    enum Color {
        RED, GREEN, BLUE
    }
    public static void main(String[] args) {
        for (Color color : Color.values()) {
            System.out.println(color);
        }
    }
}
