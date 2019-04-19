package AirportChallengeTests;

import AirportChallenge.Plane;
import AirportChallenge.PlaneException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaneTest {
    Plane plane = new Plane();

    @Test void aNewPlaneIsFlying() {
        assertTrue(plane.isFlying());
    }

    @Test
    public void itCanLandIfFlying() throws PlaneException {
        plane.land();
        assertFalse(plane.isFlying());
    }

    @Test
    public void itCanTakeOffIfNotFlying() throws PlaneException {
        plane.land();
        plane.takeOff();
        assertTrue(plane.isFlying());
    }

    @Test
    public void itRaisesErrorIfFlyingAndToldToTakeOff() {
        Throwable exception = assertThrows(PlaneException.class, () -> {
            plane.takeOff();
        });
        assertEquals("Plane could not take off. Plane was already flying.", exception.getMessage());
    }

    @Test
    public void itRaisesErrorIfNotFlyingAndToldToLand() throws PlaneException {
        plane.land();
        Throwable exception = assertThrows(PlaneException.class, () -> {
            plane.land();
        });
        assertEquals("Plane could not land. Plane was not flying.", exception.getMessage());
    }
}
