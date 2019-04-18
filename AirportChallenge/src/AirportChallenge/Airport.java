package AirportChallenge;

import java.util.ArrayList;

public class Airport {

    private ArrayList<Plane> hanger = new ArrayList<Plane>();

    public void clearForLanding(Plane plane) {
        hanger.add(plane);
    }

    public void clearForTakeOff(Plane plane) {
        hanger.remove(plane);
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }

}

