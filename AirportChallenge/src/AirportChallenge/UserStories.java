package AirportChallenge;

import org.junit.jupiter.api.Test;

public class UserStories {
    Airport airport = new Airport();
    Plane plane1 = new Plane();

    // As an air traffic controller
    // So I can get passengers to a destination
    // I want to instruct a plane to land at an airport
    @Test
    public void anAirportCanInstructAPlaneToLand() {
        airport.clearForLanding(plane1);
        assert(airport.contains(plane1));
    }
}
