package airport;

import java.util.ArrayList;

public class Airport {
    private ArrayList<Plane> hanger = new ArrayList<Plane>();

    public ArrayList<Plane> clearForLanding(Plane plane) {
        // if (hanger.contains(plane)) {
        //     System.out.println("** plane is already at airport **");
        //     try {
        //         throw new AirportException("Plane is already at airport.");
        //     }
        //     catch (AirportException ex) {
        //         System.out.println("Caught");
        //         System.out.println(ex.getMessage());
        //     }
        // }
        hanger.add(plane);
        return hanger;
    }

    public ArrayList<Plane> clearForTakeOff(Plane plane) {
        hanger.remove(plane);
        return hanger;
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }
}

class AirportException extends Exception {
    public AirportException(String message) {
        super(message);
    }
}