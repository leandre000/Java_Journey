public class Planets {
    enum Planet {
        MERCURY("Mercury is the closest planet to the Sun."),
        VENUS("Venus is the hottest planet in our solar system."),
        EARTH("Earth is a habitable planet."),
        MARS("Mars is known as the Red Planet.");

        private String message;

        Planet(String message) {
            this.message = message;
        }

        public void printMessage() {
            System.out.println(message);
        }
    }
    public static void main(String[] args) {
        for (Planet planet : Planet.values()) {
            planet.printMessage();
        }
    }
}