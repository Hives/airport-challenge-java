import java.util.ArrayList;

public class Airport {
    ArrayList<Plane> hanger = new ArrayList<Plane>();

    public ArrayList<Plane> clearForLanding(Plane plane) {
        hanger.add(plane);
        return hanger;
    }
}