import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

import jdk.jfr.Timestamp;

import java.util.ArrayList;

public class AirportTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

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
    public void itCanInstructAPlaneToLand() {
        ArrayList<Plane> hanger = airport.clearForLanding(plane1);
        assertTrue(airport.contains(plane1));
    }

    @Test
    public void itCanInstructThreePlanesToLand() {
        landThreePlanes();
        assertTrue(airport.contains(plane1));
        assertTrue(airport.contains(plane2));
        assertTrue(airport.contains(plane3));
    }

    // The same plane can't be landed twice
    // @Test
    // public void itCanOnlyLandAPlaneOnce() {
    //     airport.clearForLanding(plane1);
    //     exception.expect(AirportException.class);
    //     airport.clearForLanding(plane1);
    //     assertTrue(true);
    // }

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
