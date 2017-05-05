package me.agilani.www.java.patterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrafficControllerComputer implements Runnable{

    private volatile boolean keepAllStationRunning = true;
    private List<TrafficLight> trafficLights = new ArrayList<TrafficLight>();
    ExecutorService  executorService = Executors.newFixedThreadPool(4);
    private volatile int delayBetweenSignalChange = 5000;

    public void addTrafficLightToSystem(TrafficLight trafficLight)
    {
        executorService.execute(trafficLight);
        this.trafficLights.add(trafficLight);
        System.out.println(trafficLight.getStationName() + " is operational, currently showing " + trafficLight.getLightState().toString());
    }

    public TrafficLight switchSignal() {
        TrafficLight primaryTrafficLight = null;
        for (TrafficLight trafficLight : trafficLights) {
            trafficLight.switchSignal();
            if(trafficLight.isPrimary()) {
                primaryTrafficLight = trafficLight;
            }
        }
        return primaryTrafficLight;
    }

    @Override
    public void run() {
        while(keepAllStationRunning) {
            try {
                Thread.currentThread().sleep(delayBetweenSignalChange);
            } catch (InterruptedException e) {
                //switch off lights
                System.out.println("Signals are broken...");
                for (TrafficLight trafficLight : trafficLights) {
                    trafficLight.setKeepThisStationRunning(false);
                }
                this.executorService.shutdownNow();
            }
            TrafficLight primaryTrafficLight = this.switchSignal();
            if(primaryTrafficLight!=null && primaryTrafficLight.getLightState()==LightState.YELLOW) {
                delayBetweenSignalChange = 2000; // on yellow 2 second delay
            } else {
                delayBetweenSignalChange = 5000; // on any other color 5 second delay
            }
        }

        //switch off lights
        for (TrafficLight trafficLight : trafficLights) {
            trafficLight.setKeepThisStationRunning(false);
        }
        this.executorService.shutdownNow();
    }
}
