package trafficlight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] initialStates = scanner.nextLine().split("\\s+");
        int updatesCount = Integer.parseInt(scanner.nextLine());

        ArrayList<TrafficLight> trafficLights = new ArrayList<>();

        for (String initialState : initialStates) {
            TrafficLight trafficLight = new TrafficLight(Light.valueOf(initialState));
            trafficLights.add(trafficLight);
        }


        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < updatesCount; i++) {
            for (TrafficLight trafficLight : trafficLights) {
                trafficLight.update();
                builder.append(trafficLight.toString()).append(" ");
            }
            builder.append(System.lineSeparator());
        }
        System.out.println(builder);
    }
}
