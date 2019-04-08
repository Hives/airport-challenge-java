package AirportChallenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class WeatherStub extends Weather {
    public boolean stormy = false;

    public boolean isStormy() {
        return stormy;
    }
}

class AirportTest {
    WeatherStub weather = new WeatherStub();
    Airport airport = new Airport(weather);
    Plane plane1 = new Plane();
    Plane plane2 = new Plane();
    Plane plane3 = new Plane();

    private void landThreePlanes() {
        airport.clearForLanding(plane1);
        airport.clearForLanding(plane2);
        airport.clearForLanding(plane3);
    }

    // As an air traffic controller
    // So I can get passengers to a destination
    // I want to instruct a plane to land at an airport
    @Test
    public void landAPlane() {
        ArrayList<Plane> hanger = airport.clearForLanding(plane1);
        assertTrue(airport.contains(plane1));
    }
    @Test
    public void itCanLandThreePlanes() {
        landThreePlanes();
        assertTrue(airport.contains(plane1));
        assertTrue(airport.contains(plane2));
        assertTrue(airport.contains(plane3));
    }
    // airport.clearForLanding should raise an error if plane is already at airport

    // As an air traffic controller
    // So I can get passengers on the way to their destination
    // I want to instruct a plane to take off from an airport and confirm that it is no longer in the airport
    @Test
    public void itCanInstructAPlaneToTakeOff() {
        landThreePlanes();

        airport.clearForTakeOff(plane2);

        assertTrue(airport.contains(plane1));
        assertFalse(airport.contains(plane2));
        assertTrue(airport.contains(plane3));
    }
    // airport.clearForTakeoff should raise an error if plane is not at airport

    // As an air traffic controller
    // To ensure safety
    // I want to prevent takeoff when weather is stormy
    @Test
    public void planesCantTakeOffInBadWeather() {
        airport.clearForLanding(plane1);
        weather.stormy = true;
        airport.clearForTakeOff(plane1);
        assertTrue(airport.contains(plane1));
        // this should raise an error
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent landing when weather is stormy
    @Test
    public void planesCantLandInBadWeather() {
        weather.stormy = true;
        airport.clearForLanding(plane1);
        assertFalse(airport.contains(plane1));
        // this should raise an error
    }
}