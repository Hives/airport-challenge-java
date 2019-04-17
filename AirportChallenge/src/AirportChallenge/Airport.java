package AirportChallenge;

import java.util.ArrayList;

public class Airport {

    private ArrayList<Plane> hanger = new ArrayList<Plane>();
    private Weather weather;

    public Airport(Weather weather) {
        this.weather = weather;
    }

    // constructor overloading
    public Airport() {
        Weather weather = new Weather();
        this.weather = weather;
    }

    public ArrayList<Plane> clearForLanding(Plane plane) throws AirportException {
        if (weather.isStormy()) throw new AirportException("Plane could not land. Weather was stormy.");
        if (contains(plane)) throw new AirportException("Plane could not land. Plane already at airport.");
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

