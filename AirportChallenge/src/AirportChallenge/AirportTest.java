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

    Airport airport = new Airport(weather, Airport.DEFAULT_MAX_CAPACITY);
    Plane plane1 = new Plane();
    Plane plane2 = new Plane();
    Plane plane3 = new Plane();

    private void landThreePlanes() throws AirportException {
        airport.clearForLanding(plane1);
        airport.clearForLanding(plane2);
        airport.clearForLanding(plane3);
    }

    // As an air traffic controller
    // So I can get passengers to a destination
    // I want to instruct a plane to land at an airport
    @Test
    public void itCanLandAPlane() throws AirportException {
        airport.clearForLanding(plane1);
        assertTrue(airport.contains(plane1));
    }
    @Test
    public void itCanLandThreePlanes() throws AirportException {
        landThreePlanes();
        assertTrue(airport.contains(plane1));
        assertTrue(airport.contains(plane2));
        assertTrue(airport.contains(plane3));
    }
    @Test
    public void clearForLandingThrowsErrorIfPlaneAtAirport() throws AirportException {
        airport.clearForLanding(plane1);
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
    }

    // As an air traffic controller
    // So I can get passengers on the way to their destination
    // I want to instruct a plane to take off from an airport and confirm that it is no longer in the airport
    @Test
    public void itCanInstructAPlaneToTakeOff() throws AirportException {
        landThreePlanes();

        airport.clearForTakeOff(plane2);

        assertTrue(airport.contains(plane1));
        assertFalse(airport.contains(plane2));
        assertTrue(airport.contains(plane3));
    }
    @Test
    public void clearForTakeOffThrowsErrorIfPlaneNotAtAirport() throws AirportException {
        assertThrows(AirportException.class, () -> {
            airport.clearForTakeOff(plane1);
        });
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent takeoff when weather is stormy
    @Test
    public void takingOffInBadWeatherThrowsError() throws AirportException {
        airport.clearForLanding(plane1);
        weather.stormy = true;
        assertThrows(AirportException.class, () -> {
            airport.clearForTakeOff(plane1);
        });
    }
    @Test
    public void planeCannotLeaveAirportInBadWeather() throws AirportException {
        airport.clearForLanding(plane1);
        weather.stormy = true;
        try {
            airport.clearForTakeOff(plane1);
        }
        catch (AirportException ex) {
            System.out.println(ex.getMessage());
        }
        assertTrue(airport.contains(plane1));
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent landing when weather is stormy
    @Test
    public void landingInBadWeatherThrowsError() throws AirportException {
        weather.stormy = true;
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
    }
    @Test
    public void planeCannotLandAtAirportInBadWeather() throws AirportException {
        weather.stormy = true;
        try {
            airport.clearForLanding(plane1);
        }
        catch (AirportException ex) {
            System.out.println(ex.getMessage());
        }
        assertFalse(airport.contains(plane1));
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent landing when the airport is full
    @Test
    public void planeCannotLandIfAirportFull() throws AirportException {
        for (int i = 0; i < airport.DEFAULT_MAX_CAPACITY; i++) {
            airport.clearForLanding(new Plane ());
        }
        try {
            airport.clearForLanding(plane1);
        }
        catch (AirportException ex) {
            System.out.println(ex.getMessage());
        }
        assertFalse(airport.contains(plane1));
    }
    @Test void landingAtFullAirportThrowsError() throws AirportException {
        for (int i = 0; i < airport.DEFAULT_MAX_CAPACITY; i++) {
            airport.clearForLanding(new Plane ());
        }
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
    }

    // As the system designer
    // So that the software can be used for many different airports
    // I would like a default airport capacity that can be overridden as appropriate
    @Test
    public void airportDefaultCapacityCanBeOverridden() throws AirportException {
        int capacity = Airport.DEFAULT_MAX_CAPACITY + 1;
        Airport airport = new Airport(weather, capacity);
        for (int i = 0; i < capacity; i++) {
            airport.clearForLanding(new Plane ());
        }
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });

    }
}


