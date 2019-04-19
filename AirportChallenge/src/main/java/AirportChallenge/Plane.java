package AirportChallenge;

public class Plane {
    private boolean flying = true;

    public boolean isFlying() {
        return flying;
    }

    public void takeOff() throws PlaneException {
        if (flying) throw new PlaneException("Plane could not take off. Plane was already flying.");
        flying = true;
    }

    public void land() throws PlaneException {
        if (!flying) throw new PlaneException("Plane could not land. Plane was not flying.");
        flying = false;
    }
}
