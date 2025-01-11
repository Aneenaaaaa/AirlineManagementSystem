package com.proj.log;
public class FlightSelectionHolder {
    private static FlightSelectionHolder instance;
    private Hflight selectedFlight;

    private FlightSelectionHolder() {}

    public static FlightSelectionHolder getInstance() {
        if (instance == null) {
            instance = new FlightSelectionHolder();
        }
        return instance;
    }

    public void setSelectedFlight(Hflight flight) {
        this.selectedFlight = flight;
    }

    public Hflight getSelectedFlight() {
        return selectedFlight;
    }
}
