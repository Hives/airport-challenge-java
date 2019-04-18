package AirportChallenge;

import java.util.ArrayList;

public class Airport {

    public final int MAX_CAPACITY = 20;

    private ArrayList<Plane> hanger = new ArrayList<Plane>();
    private Weather weather;

    public Airport() {
        Weather weather = new Weather();
        this.weather = weather;
    }
    public Airport(Weather weather) {
        this.weather =  weather;
    }

    public void clearForLanding(Plane plane) throws AirportException {
        if (hanger.size() >= MAX_CAPACITY) throw new AirportException("Plane could not land. Airport is full.");
        if (weather.isStormy()) throw new AirportException("Plane could not land. Weather was stormy.");
        if (contains(plane)) throw new AirportException("Plane could not land. Plane already at airport.");
        hanger.add(plane);
    }

    public void clearForTakeOff(Plane plane) throws AirportException {
        if (weather.isStormy()) throw new AirportException("Plane could not take off. Weather was stormy.");
        if (!contains(plane)) throw new AirportException("Plane could not take off. Plane not at airport.");
        hanger.remove(plane);
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }

}

