package AirportChallenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class AirportTest {

    Airport airport = new Airport();
    Plane planeDouble1 = new Plane();
    Plane planeDouble2 = new Plane();
    Plane planeDouble3 = new Plane();

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
    public void itThrowsErrorIfPlaneToldToTakeOffWhenNotAtAirport() throws AirportException {
        assertThrows(AirportException.class, () -> {
            airport.clearForTakeOff(planeDouble1);
        });
    }
}

