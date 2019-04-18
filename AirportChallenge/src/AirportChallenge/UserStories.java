package AirportChallenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserStories {
    Airport airport = new Airport();
    Plane plane1 = new Plane();

    // As an air traffic controller
    // So I can get passengers to a destination
    // I want to instruct a plane to land at an airport
    @Test
    public void anAirportCanInstructAPlaneToLand() {
        airport.clearForLanding(plane1);
        assertTrue(airport.contains(plane1));
    }

    // As an air traffic controller
    // So I can get passengers on the way to their destination
    // I want to instruct a plane to take off from an airport and confirm that it is no longer in the airport
    @Test
    public void anAirportCanInstructAPlaneToTakeOff() {
        airport.clearForLanding(plane1);
        airport.clearForTakeOff(plane1);
        assertFalse(airport.contains(plane1));
    }
}
