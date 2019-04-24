package AirportChallenge;

import java.util.ArrayList;

public class Airport {

    private ArrayList<Plane> hanger = new ArrayList<Plane>();
    private Weather weather;
    private int capacity;
    public static final int DEFAULT_MAX_CAPACITY = 25;

    public Airport() {
        this.weather = new Weather();
        this.capacity = this.DEFAULT_MAX_CAPACITY;
    }

    public Airport(Weather weather) {
        this.weather = weather;
        this.capacity = this.DEFAULT_MAX_CAPACITY;
    }

    public Airport(int capacity) {
        this.weather = new Weather();
        this.capacity = capacity;
    }

    public Airport(Weather weather, int capacity) {
        this.weather = weather;
        this.capacity = capacity;
    }

    public void clearForLanding(Plane plane) throws AirportException, PlaneException {
        if (hanger.size() >= capacity) throw new AirportException("Could not clear plane for landing. Airport is full.");
        if (weather.isStormy()) throw new AirportException("Could not clear plane for landing. Weather was stormy.");
        plane.land(this);
        hanger.add(plane);
    }

    public void clearForTakeOff(Plane plane) throws AirportException, PlaneException {
        if (!contains(plane)) throw new AirportException("Could not clear plane for take off. Plane is not at this airport.");
        if (weather.isStormy()) throw new AirportException("Could not clear plane for take off. Weather was stormy.");
        plane.takeOff();
        hanger.remove(plane);
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }

}
