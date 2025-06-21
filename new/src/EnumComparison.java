public class EnumComparison {
    enum Color {
        RED, GREEN, BLUE
    }

    public static void main(String[] args) {
        Color color1 = Color.RED;
        Color color2 = Color.GREEN;

        System.out.println(color1 + " ordinal: " + color1.ordinal());
        System.out.println(color2 + " ordinal: " + color2.ordinal());

        if (color1.ordinal() < color2.ordinal()) {
            System.out.println(color1 + " comes before " + color2);
        } else {
            System.out.println(color2 + " comes before " + color1);
        }
    }
}
