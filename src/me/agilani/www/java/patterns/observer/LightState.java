package me.agilani.www.java.patterns.observer;

public enum LightState {
    RED(0, "Red"),
    YELLOW(1, "Yellow"),
    GREEN(2, "Green"),
    YellowAfterGreen(3, "Yellow");

    private final String state;
    private final int sequence;

    private LightState(int sequence, String state) {
        this.sequence = sequence;
        this.state = state;
    }

    public int getSequence () {
        return this.sequence;
    }

    public String toString() {
        return state;
    }
}
