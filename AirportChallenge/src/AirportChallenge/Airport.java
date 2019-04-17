package AirportChallenge;

import java.util.ArrayList;

public class Airport {

    public static final int DEFAULT_MAX_CAPACITY = 3;

    private ArrayList<Plane> hanger = new ArrayList<Plane>();
    private Weather weather;
    private int capacity;

    // constructor overloading
    // three constructors here. some repetition. is this the best way to do it?
    public Airport() {
        Weather weather = new Weather();
        this.weather = weather;
        this.capacity = DEFAULT_MAX_CAPACITY;
    }
    public Airport(int capacity) {
        Weather weather = new Weather();
        this.weather = weather;
        this.capacity = capacity;
    }
    public Airport(Weather weather, int capacity) {
        this.weather = weather;
        this.capacity = capacity;
    }

    public ArrayList<Plane> clearForLanding(Plane plane) throws AirportException {
        if (weather.isStormy()) throw new AirportException("Plane could not land. Weather was stormy.");
        if (contains(plane)) throw new AirportException("Plane could not land. Plane already at airport.");
        if (hanger.size() >= capacity) throw new AirportException("Plane could not land. Airport at capacity.");
        hanger.add(plane);
        return hanger;
    }

    public ArrayList<Plane> clearForTakeOff(Plane plane) throws AirportException {
        if (weather.isStormy()) throw new AirportException("Plane could not take off. Weather was stormy.");
        if (!contains(plane)) throw new AirportException("Plane could not take off. Plane was not at airport.");
        hanger.remove(plane);
        return hanger;
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }

}

