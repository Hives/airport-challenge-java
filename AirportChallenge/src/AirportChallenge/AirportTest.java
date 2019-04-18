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
        assert(airport.contains(planeDouble1));
    }
}

