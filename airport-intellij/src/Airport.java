import java.util.ArrayList;

public class Airport {

    private ArrayList<Plane> hanger = new ArrayList<Plane>();

    public ArrayList<Plane> clearForLanding(Plane plane) throws Exception {
        if (hanger.contains(plane)) {
            try {
                throw new Exception("no mate");
            }
            catch (Exception ex) {
               System.out.println(ex.getMessage());
            }
        }
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
