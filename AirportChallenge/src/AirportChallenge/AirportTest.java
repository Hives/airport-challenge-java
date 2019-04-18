package AirportChallenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class AirportTest {

    Airport airport = new Airport();
    Plane planeDouble1 = new Plane();

    @Test
    public void itCanLandPlanes() {
        airport.clearForLanding(planeDouble1);
        assertTrue(airport.contains(planeDouble1));
    }

    @Test
    public void itCanTellPlanesToTakeOff() {
        airport.clearForLanding(planeDouble1);
        airport.clearForTakeOff(planeDouble1);
        assertFalse(airport.contains(planeDouble1));
    }
}

