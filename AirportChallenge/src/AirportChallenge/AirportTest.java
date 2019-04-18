package AirportChallenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class AirportTest {

    Plane planeDouble1 = new Plane();
    Plane planeDouble2 = new Plane();
    Plane planeDouble3 = new Plane();
    WeatherDouble weather = new WeatherDouble();
    Airport airport = new Airport(weather);

    @Test
    public void itCanLandPlanes() throws AirportException {
        airport.clearForLanding(planeDouble1);
        assertTrue(airport.contains(planeDouble1));
    }

    @Test
    public void itThrowsErrorIfSamePlaneLandedTwice() throws AirportException {
        airport.clearForLanding(planeDouble1);
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(planeDouble1);
        });
    }

    @Test
    public void itCanTellPlanesToTakeOff() throws AirportException {
        airport.clearForLanding(planeDouble1);
        airport.clearForTakeOff(planeDouble1);
        assertFalse(airport.contains(planeDouble1));
    }

    @Test
    public void itTellsTheRightPlaneToTakeOff() throws AirportException {
        airport.clearForLanding(planeDouble1);
        airport.clearForLanding(planeDouble2);
        airport.clearForLanding(planeDouble3);
        airport.clearForTakeOff(planeDouble2);
        assertTrue(airport.contains(planeDouble1));
        assertFalse(airport.contains(planeDouble2));
        assertTrue(airport.contains(planeDouble3));
    }

    @Test
    public void itThrowsErrorIfPlaneToldToTakeOffWhenNotAtAirport() {
        assertThrows(AirportException.class, () -> {
            airport.clearForTakeOff(planeDouble1);
        });
    }

    @Test
    public void itThrowsErrorIfTakeOffAttemptedInBadWeather() throws AirportException {
        airport.clearForLanding(planeDouble1);
        weather.stormy = true;
        assertThrows(AirportException.class, () -> {
            airport.clearForTakeOff(planeDouble1);
        });
    }

    @Test
    public void itDoesNotTakeOffAPlaneInBadWeather() throws AirportException {
        airport.clearForLanding(planeDouble1);
        weather.stormy = true;
        try {
            airport.clearForTakeOff(planeDouble1);
        }
        catch (AirportException exception) {}
        assertTrue(airport.contains(planeDouble1));
    }

    @Test
    public void itThrowsErrorIfLandingAttemptedInBadWeather() {
        weather.stormy = true;
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(planeDouble1);
        });
    }

    @Test
    public void itDoesNotLandAPlaneInBadWeather() {
        weather.stormy = true;
        try {
            airport.clearForLanding(planeDouble1);
        }
        catch (AirportException exception) {}
        assertFalse(airport.contains(planeDouble1));
    }


    @Test
    public void itThrowsErrorIfLandingAttemptedWhenFull() throws AirportException {
        for (int i = 0; i < 20; i++) {
            airport.clearForLanding(new PlaneDouble());
        }
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(new PlaneDouble());
        });
    }

    @Test
    public void itDoesNotLandAPlaneIfFull() throws AirportException {
        for (int i = 0; i < 20; i++) {
            airport.clearForLanding(new PlaneDouble());
        }
        try {
            airport.clearForLanding(planeDouble1);
        }
        catch (AirportException exception) {}
        assertFalse(airport.contains(planeDouble1));
    }
}

