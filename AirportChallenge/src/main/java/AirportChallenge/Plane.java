package AirportChallenge;

public class Plane {
    private boolean flying = true;
    private Airport myAirport = null;

    public boolean isFlying() {
        return flying;
    }

    public void takeOff() throws PlaneException {
        if (flying) throw new PlaneException("Plane could not take off. Plane is already flying.");
        flying = true;
    }

    public void land(Airport airport) throws PlaneException {
        if (!flying) throw new PlaneException("Plane could not land. Plane is not flying.");
        flying = false;
        myAirport = airport;
    }

    public Airport airport() {
        return myAirport;
    }
}
