package AirportChallenge;

import java.util.ArrayList;

public class Airport {

    private ArrayList<Plane> hanger = new ArrayList<Plane>();

    public ArrayList<Plane> clearForLanding(Plane plane) throws AirportException {

        if (hanger.contains(plane)) {
            throw new AirportException("Could not land plane. Plane was already at airport.");
        }
        hanger.add(plane);
        return hanger;
//        try {
//            addPlaneToHanger(plane);
//        }
//        catch (AirportException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return hanger;
    }

//    public void addPlaneToHanger(Plane plane) throws AirportException {
//        if (hanger.contains(plane)) {
//            throw new AirportException("no mate");
//        } else {
//            hanger.add(plane);
//        }
//    }

    public ArrayList<Plane> clearForTakeOff(Plane plane) {
        hanger.remove(plane);
        return hanger;
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }

}

