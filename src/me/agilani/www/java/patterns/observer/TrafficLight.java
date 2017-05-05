package me.agilani.www.java.patterns.observer;

public abstract class TrafficLight extends Thread {

    private volatile boolean keepThisStationRunning = true;
    private String stationName;
    private LightState currentSate;
    private boolean isPrimary;
    private TrafficControllerComputer trafficControllerComputer;

    public abstract void switchSignal();

    public TrafficLight(String stationName,
                        LightState initialState,
                        boolean isPrimary,
                        TrafficControllerComputer trafficControllerComputer) {
        this.stationName = stationName;
        this.currentSate = initialState;
        this.isPrimary = isPrimary;
        this.trafficControllerComputer = trafficControllerComputer;
        this.trafficControllerComputer.addTrafficLightToSystem(this);
    }

    public LightState getLightState() {
        return currentSate;
    }

    public void setLightState(LightState currentSate) {
        this.currentSate = currentSate;
    }

    public String getStationName() { return stationName; }

    public boolean isPrimary() { return isPrimary; }

    public boolean keepThisStationRunning() { return keepThisStationRunning; }

    public void setKeepThisStationRunning(boolean keepThisStationRunning) { this.keepThisStationRunning = keepThisStationRunning; }

}
