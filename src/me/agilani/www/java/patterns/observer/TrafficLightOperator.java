package me.agilani.www.java.patterns.observer;

/**
 * Created by agilani on 04/05/2017.
 */
public class TrafficLightOperator {

    public static void main(String[] args) {
        TrafficControllerComputer trafficControllerComputer = new TrafficControllerComputer();

        TrafficLight pole_West = new TrafficPole("West", LightState.RED, false, trafficControllerComputer);
        TrafficLight pole_East = new TrafficPole("East", LightState.RED, true, trafficControllerComputer);

        TrafficLight pole_South = new TrafficPole("South", LightState.GREEN, false, trafficControllerComputer);
        TrafficLight pole_North = new TrafficPole("North", LightState.GREEN, false, trafficControllerComputer);

        trafficControllerComputer.run();
    }
}
