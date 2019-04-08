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

    public ArrayList<Plane> clearForLanding(Plane plane) {
        if(weather.isStormy()) {
            System.out.println("weather was stormy");
        } else {
            hanger.add(plane);
        }
        return hanger;
    }

    public ArrayList<Plane> clearForTakeOff(Plane plane) {
        if(weather.isStormy()) {
            System.out.println("weather was stormy");
        } else {
            hanger.remove(plane);
        }
        return hanger;
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }

}

