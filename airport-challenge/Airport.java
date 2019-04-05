import java.util.ArrayList;

public class Airport {
    private ArrayList<Plane> hanger = new ArrayList<Plane>();

    public ArrayList<Plane> clearForLanding(Plane plane) {
        hanger.add(plane);
        return hanger;
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }
}