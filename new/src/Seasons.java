public class Seasons {
    enum Season {
        SPRING("Pleasant season"),
        SUMMER("Hot season"),
        AUTUMN("Cool season"),
        WINTER("Cold season");

        private String description;

        Season(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
    public static void main(String[] args) {
        for (Season season : Season.values()) {
            System.out.println(season + ": " + season.getDescription());
        }
    }
}
