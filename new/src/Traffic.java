
public class Traffic {
    enum TrafficLight {
        RED {
            public String getAction() {
                return "Stop";
            }
        },
        YELLOW {
            public String getAction() {
                return "Get Ready";
            }
        },
        GREEN {
            public String getAction() {
                return "Go";
            }
        };

        public abstract String getAction();
    }
    public static void main(String[] args) {
        for (TrafficLight light : TrafficLight.values()) {
            System.out.println(light + ": " + light.getAction());
        }
    }
}
