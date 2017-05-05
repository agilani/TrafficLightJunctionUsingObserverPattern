package me.agilani.www.java.patterns.observer;

public class TrafficPole extends TrafficLight {

    private boolean switchSequence = false;
    private boolean switchSignal = false;

    public TrafficPole(String name,
                       LightState initialState,
                       boolean isPrimary,
                       TrafficControllerComputer trafficControllerComputer) {
        super(name, initialState, isPrimary, trafficControllerComputer);
    }

    @Override
    public void switchSignal() {
        switchSignal = true;
    }

    @Override
    public void run() {
        while (super.keepThisStationRunning() == true) {
            if (switchSignal) {
                switchSignal = false;
                this.changeLight();
            }
        }
    }

    private void changeLight() {
        LightState currentState = this.getLightState();
        LightState nextState = this.getNextLightState(currentState);
        System.out.println(this.getStationName() + " was " + currentState.toString() + ". changed to " + nextState.toString());
        this.setLightState(nextState);
    }

    private LightState getNextLightState(LightState current) {
        LightState newLight = null;

        if (current.getSequence() + 1 >= LightState.values().length) {
            return LightState.values()[0];
        }
        return LightState.values()[current.getSequence() + 1];
    }
}
