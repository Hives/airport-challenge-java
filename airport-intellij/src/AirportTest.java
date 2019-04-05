import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AirportTest {

    Airport airport = new Airport();
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
    // The same plane can't be landed twice
//    @Test
//    void itCanOnlylandAPlaneOnce() {
//        airport.clearForLanding(plane1);
//        assertThrows(Exception.class, () -> {
//            airport.clearForLanding(plane1);
//        });
//    }

    // As an air traffic controller
    // So I can get passengers on the way to their destination
    // I want to instruct a plane to take off from an airport and confirm that it is no longer in the airport
    @Test
    public void itCanInstructAPlaneToTakeOff() {
        landThreePlanes();

        airport.clearForTakeOff(plane2);

        assertTrue(airport.contains(plane1));
        assertTrue(airport.contains(plane3));
        assertFalse(airport.contains(plane2));
    }

}